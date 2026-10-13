/*
Ad Soyad: Dua Halluf Edib
Numara: G221210564
Ders: Programlama Dillerinin Prensipleri
Proje: SehirOyunu
Açıklama: Bu sınıf bir kişiyi temsil eder.
*/
public class Kisi {
    private static int sonrakiId = 100;

    private int id;
    private String adSoyad;
    private int yas;

    public Kisi() {
        this.id = sonrakiId++;
        this.adSoyad = FakerUretici.faker.name().fullName();
        this.yas = (int)(Math.random() * 51);
    }

    public int getId() {
        return id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public int getYas() {
        return yas;
    }

    public void yasArtir() {
        yas++;
    }
}