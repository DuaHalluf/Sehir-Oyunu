/*
Ad Soyad: Dua Halluf Edib
Numara: G221210564
Ders: Programlama Dillerinin Prensipleri
Proje: SehirOyunu
Açıklama: Bu sınıf oyunun ana akışını yönetir, kullanıcıdan veri alır, turları çalıştırır ve şehirleri günceller.
*/
import java.util.ArrayList;
import java.util.Scanner;

public class Oyun {

    private ArrayList<Sehir> aktifSehirler;
    private Scanner input;

    public Oyun() {
        aktifSehirler = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public void oyunuBaslat() {
        System.out.print("Tur sayısını giriniz: ");
        int tur = input.nextInt();
        input.nextLine();

        System.out.print("Sayıları giriniz: ");
        String line = input.nextLine();
        String[] sayilar = line.split(" ");

        // Şehirleri baştan gerçek nesne olarak oluştur
        for (String s : sayilar) {
            if (s.length() != 2 || s.charAt(0) == '0') {
                System.out.println("Geçersiz sayı: " + s);
                continue;
            }

            int sayi = Integer.parseInt(s);
            Sehir sehir = sayidanSehirOlustur(sayi);
            aktifSehirler.add(sehir);
        }

        // Başlangıç durumu
        System.out.println("Başlangıç durumu:");
        sehirleriYazdir();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Turlar
        for (int t = 1; t <= tur; t++) {
            ArrayList<Sehir> yeniSehirler = new ArrayList<>();
            ArrayList<Sehir> sonaEklenecekler = new ArrayList<>();

            for (Sehir sehir : aktifSehirler) {
                sehirdekiYaslariArtir(sehir);
                sehirNufusunuArttir(sehir);

                if (sehir.getNufus() >= 1000) {
                    Sehir yeniSehir = sehriBol(sehir);
                    yeniSehirler.add(sehir);

                    if (yeniSehir != null) {
                        sonaEklenecekler.add(yeniSehir);
                    }
                } else {
                    yeniSehirler.add(sehir);
                }
            }

            yeniSehirler.addAll(sonaEklenecekler);
            aktifSehirler = yeniSehirler;

            ekraniTemizle();
            System.out.println("Tur " + t + ":");
            sehirleriYazdir();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Son turdan sonra ekranı temizle ve son durumu göster
        ekraniTemizle();
        System.out.println("Son durum:");
        sehirleriYazdir();

        System.out.print("Satır giriniz: ");
        int satir = input.nextInt();

        System.out.print("Sütun giriniz: ");
        int sutun = input.nextInt();

        int index = satir * 5 + sutun;

        if (index < 0 || index >= aktifSehirler.size()) {
            System.out.println("Gecersiz satir veya sutun!");
            input.close();
            return;
        }

        Sehir secilenSehir = aktifSehirler.get(index);
        sehirDetayYazdir(secilenSehir);

        System.out.println("Cikmak icin bir tusa basiniz...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        input.close();
    }


    private Sehir sayidanSehirOlustur(int sayi) {
        int ilceSayisi = sayi / 10;
        int mahalleSayisi = sayi % 10;

        while (mahalleSayisi == 0 || mahalleSayisi % ilceSayisi != 0) {
            mahalleSayisi++;
            if (mahalleSayisi == 10) {
                mahalleSayisi = 1;
            }
        }

        int nufus = ilceSayisi * 10 + mahalleSayisi;
        while (nufus % mahalleSayisi != 0) {
            nufus++;
        }

        Sehir sehir = new Sehir(FakerUretici.faker.address().city());
        int mahalleHerIlce = mahalleSayisi / ilceSayisi;
        int kisiSayisiHerMahalle = nufus / mahalleSayisi;

        for (int i = 0; i < ilceSayisi; i++) {
            Ilce ilce = new Ilce(FakerUretici.faker.address().cityName());

            for (int j = 0; j < mahalleHerIlce; j++) {
                Mahalle mahalle = new Mahalle(FakerUretici.faker.address().streetName());
                mahalle.kisiOlustur(kisiSayisiHerMahalle);
                ilce.mahalleEkle(mahalle);
            }

            sehir.ilceEkle(ilce);
        }

        return sehir;
    }

    private void sehirdekiYaslariArtir(Sehir sehir) {
        for (Ilce ilce : sehir.getIlceler()) {
            for (Mahalle mahalle : ilce.getMahalleler()) {
                mahalle.yaslariArtir();
            }
        }
    }

    private void sehirNufusunuArttir(Sehir sehir) {
        int nufus = sehir.getNufus();
        int onlar = (nufus / 10) % 10;
        int birler = nufus % 10;
        int artisOrani = onlar + birler;

        for (Ilce ilce : sehir.getIlceler()) {
            for (Mahalle mahalle : ilce.getMahalleler()) {
                ArrayList<Kisi> mevcut = mahalle.getKisiler();
                int eskiSayi = mevcut.size();

                int yeniSayi;
                if (artisOrani == 0) {
                    yeniSayi = eskiSayi + 1;
                } else {
                    yeniSayi = eskiSayi * artisOrani;
                }

                int eklenecek = yeniSayi - eskiSayi;
                for (int i = 0; i < eklenecek; i++) {
                    mahalle.kisiEkle(new Kisi());
                }
            }
        }
    }

    private Sehir sehriBol(Sehir eskiSehir) {
        Sehir yeniSehir = new Sehir(FakerUretici.faker.address().city());

        ArrayList<Ilce> ilceler = eskiSehir.getIlceler();
        int eskiIlceSayisi = ilceler.size();

        // 1) Eğer şehirde birden fazla ilçe varsa -> ilçeleri böl
        if (eskiIlceSayisi > 1) {
            int yeniSehirIlceSayisi = eskiIlceSayisi / 2;

            ArrayList<Ilce> tasinacaklar = new ArrayList<>();

            for (int i = 0; i < yeniSehirIlceSayisi; i++) {
                Ilce tasinan = ilceler.remove(ilceler.size() - 1);
                tasinacaklar.add(0, tasinan);
            }

            for (Ilce ilce : tasinacaklar) {
                yeniSehir.ilceEkle(ilce);
            }
        }
        // 2) Eğer şehirde sadece 1 ilçe varsa -> mahalleleri böl
        else {
            Ilce eskiIlce = ilceler.get(0);
            ArrayList<Mahalle> mahalleler = eskiIlce.getMahalleler();

            // 2.a) Eğer birden fazla mahalle varsa -> mahalleleri böl
            if (mahalleler.size() > 1) {
                Ilce yeniIlce = new Ilce(FakerUretici.faker.address().cityName());

                int tasinacakMahalleSayisi = mahalleler.size() / 2;

                ArrayList<Mahalle> tasinacakMahalleler = new ArrayList<>();

                for (int i = 0; i < tasinacakMahalleSayisi; i++) {
                    Mahalle tasinanMahalle = mahalleler.remove(mahalleler.size() - 1);
                    tasinacakMahalleler.add(0, tasinanMahalle);
                }

                for (Mahalle mahalle : tasinacakMahalleler) {
                    yeniIlce.mahalleEkle(mahalle);
                }

                yeniSehir.ilceEkle(yeniIlce);
            }
            // 2.b) Eğer sadece 1 mahalle varsa -> kişileri böl
            else {
                Mahalle eskiMahalle = mahalleler.get(0);
                ArrayList<Kisi> kisiler = eskiMahalle.getKisiler();

                Ilce yeniIlce = new Ilce(FakerUretici.faker.address().cityName());
                Mahalle yeniMahalle = new Mahalle(FakerUretici.faker.address().streetName());

                int tasinacakKisiSayisi = kisiler.size() / 2;

                ArrayList<Kisi> tasinacakKisiler = new ArrayList<>();

                for (int i = 0; i < tasinacakKisiSayisi; i++) {
                    Kisi tasinanKisi = kisiler.remove(kisiler.size() - 1);
                    tasinacakKisiler.add(0, tasinanKisi);
                }

                for (Kisi kisi : tasinacakKisiler) {
                    yeniMahalle.kisiEkle(kisi);
                }

                yeniIlce.mahalleEkle(yeniMahalle);
                yeniSehir.ilceEkle(yeniIlce);
            }
        }

        return yeniSehir;
    }

    private void sehirleriYazdir() {
        for (int i = 0; i < aktifSehirler.size(); i++) {
            System.out.print("[" + aktifSehirler.get(i).getNufus() + "]");

            if (i != aktifSehirler.size() - 1) {
                if ((i + 1) % 5 == 0) {
                    System.out.println();
                } else {
                    System.out.print("-");
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    private void sehirDetayYazdir(Sehir sehir) {
        System.out.println("Sehir: " + sehir.getAd() + " - Nufus: " + sehir.getNufus());

        for (Ilce ilce : sehir.getIlceler()) {
            System.out.println("Ilce: " + ilce.getAd() + " - Nufus: " + ilce.getNufus());

            for (Mahalle mahalle : ilce.getMahalleler()) {
                System.out.println("Mahalle: " + mahalle.getAd() + " - Nufus: " + mahalle.getNufus());

                System.out.println();
                System.out.println("Kisiler:");

                for (Kisi kisi : mahalle.getKisiler()) {
                    System.out.println(kisi.getId() + " - " + kisi.getAdSoyad() + " - " + kisi.getYas());
                }

                System.out.println();
            }

            System.out.println();
        }
    }
    private void ekraniTemizle() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                .inheritIO()
                .start()
                .waitFor();
        } catch (Exception e) {
        }
    }
}
