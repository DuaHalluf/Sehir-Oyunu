/*
Ad Soyad: Dua Halluf Edib
Numara: G221210564
Ders: Programlama Dillerinin Prensipleri
Proje: SehirOyunu
Açıklama: Bu sınıf mahalle ve içindeki kişileri yönetir.
*/
import java.util.ArrayList;

public class Mahalle {
    private String ad;
    private ArrayList<Kisi> kisiler;

    public Mahalle(String ad) {
        this.ad = ad;
        this.kisiler = new ArrayList<>();
    }

    public void kisiOlustur(int sayi) {
        for (int i = 0; i < sayi; i++) {
        	Kisi yeniKisi = new Kisi();
            kisiler.add(yeniKisi);
        }
    }
    
    public String getAd() {
        return ad;
    }

    public ArrayList<Kisi> getKisiler() {
        return kisiler;
    }

    public void kisiEkle(Kisi kisi) {
        kisiler.add(kisi);
    }

    public int getNufus() {
        return kisiler.size();
    }

    public void yaslariArtir() {
        for (Kisi kisi : kisiler) {
            kisi.yasArtir();
        }
    }
}