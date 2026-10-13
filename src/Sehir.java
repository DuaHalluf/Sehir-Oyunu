/*
Ad Soyad: Dua Halluf Edib
Numara: G221210564
Ders: Programlama Dillerinin Prensipleri
Proje: SehirOyunu
Açıklama: Bu sınıf şehir bilgilerini ve ilçeleri tutar.
*/
import java.util.ArrayList;

public class Sehir {
    private String ad;
    private ArrayList<Ilce> ilceler;
    private int ilceSayisi;
    private int mahalleSayisi;
    private int nufus;

    public Sehir(String ad, int ilceSayisi, int mahalleSayisi, int nufus) {
        this.ad = ad;
        this.ilceler = new ArrayList<>();
        this.ilceSayisi = ilceSayisi;
        this.mahalleSayisi = mahalleSayisi;
        this.nufus = nufus;
    }
    public Sehir(String ad) {
        this.ad = ad;
        this.ilceler = new ArrayList<>();
        this.ilceSayisi = 0;
        this.mahalleSayisi = 0;
        this.nufus = 0;
    }
    
    public String getAd() {
        return ad;
    }

    public ArrayList<Ilce> getIlceler() {
        return ilceler;
    }

    public void ilceEkle(Ilce ilce) {
        ilceler.add(ilce);
    }

    public int getIlceSayisi() {
        return ilceSayisi;
    }

    public void setIlceSayisi(int ilceSayisi) {
        this.ilceSayisi = ilceSayisi;
    }

    public int getMahalleSayisi() {
        return mahalleSayisi;
    }

    public void setMahalleSayisi(int mahalleSayisi) {
        this.mahalleSayisi = mahalleSayisi;
    }

    public int getNufus() {
        int toplam = 0;
        for (Ilce ilce : ilceler) {
            toplam += ilce.getNufus();
        }
        return toplam;
    }
    public void setNufus(int nufus) {
        this.nufus = nufus;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }
}