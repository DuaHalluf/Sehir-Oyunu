/*
Ad Soyad: Dua Halluf Edib
Numara: G221210564
Ders: Programlama Dillerinin Prensipleri
Proje: SehirOyunu
Açıklama: Bu sınıf ilçe bilgilerini ve mahalleleri tutar.
*/

import java.util.ArrayList;

public class Ilce {
    private String ad;
    private ArrayList<Mahalle> mahalleler;

    public Ilce(String ad) {
        this.ad = ad;
        this.mahalleler = new ArrayList<>();
    }

    public String getAd() {
        return ad;
    }

    public ArrayList<Mahalle> getMahalleler() {
        return mahalleler;
    }

    public void mahalleEkle(Mahalle mahalle) {
        mahalleler.add(mahalle);
    }

    public int getNufus() {
        int toplam = 0;
        for (Mahalle mahalle : mahalleler) {
            toplam += mahalle.getNufus();
        }
        return toplam;
    }

    public int getMahalleSayisi() {
        return mahalleler.size();
    }
}