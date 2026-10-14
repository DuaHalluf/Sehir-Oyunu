#  Şehir Oyunu (Konsol Tabanlı Şehir Simülasyonu)

Bu proje, Sakarya Üniversitesi Bilgisayar Mühendisliği Bölümü **Programlama Dillerinin Prensipleri** dersi kapsamında Java dili kullanılarak geliştirilmiş konsol tabanlı bir şehir simülasyon oyunudur[cite: 8, 9]. Program, kullanıcıdan alınan girdiler doğrultusunda hiyerarşik kent yapılarını modeller ve turlar üzerinden nüfus artışı, yaşlanma ve şehir bölünme mekanizmalarını dinamik olarak simüle eder[cite: 9, 10].

---

##  Temel Özellikler ve Simülasyon Mekanizmaları

* **Hiyerarşik Nesne Yönelimli Mimarisi (OOP):** Gerçek dünyadaki coğrafi ve demografik yapıyı modelleyen modüler sınıf yapısı (`Sehir` ➔ `Ilce` ➔ `Mahalle` ➔ `Kisi`) içermektedir[cite: 9, 10].
* **Dinamik Tur Tabanlı Simülasyon Motoru:**
  * **Yaşlanma Sistemi:** Her turda sistemdeki bireylerin yaşlarını otomatik olarak artırır.
  * **Nüfus Artış Mekanizması:** Nüfus değerinin basamaklarına göre artış oranlarını hesaplar ve mahalle nüfuslarını dinamik olarak genişletir[cite: 10].
  * **Şehir Bölünme Kuralları:** Belirli bir nüfus eşiğine ($\ge 1000$) ulaşan şehirleri, ilçeleri veya mahalleleri otomatik olarak bölerek yeni yerleşim birimleri oluşturur.
* **Gerçekçi Veri Üretimi:** **JavaFaker** kütüphanesi (`tr` yerelleştirmesi) kullanılarak rastgele ancak gerçekçi insan isimleri, şehir, ilçe ve sokak isimleri oluşturulur[cite: 3, 10].
* **İnteraktif Konsol Arayüzü ve Detay Görünümü:** Aktif şehirleri her turda 5'li matris formatında ekranı temizleyerek gösterir[cite: 10]. Simülasyon sonunda matris koordinatları (satır-sütun) girilerek ilgili şehrin, ilçelerinin, mahallelerinin ve kişilerinin detaylı dökümü incelenebilir[cite: 10].

---

##  Teknolojiler ve Gereksinimler

* **Programlama Dili:** Java[cite: 9, 10]
* **Kütüphaneler:** `com.github.javafaker:javafaker` (Türkçe veri üretimi için)[cite: 1, 3, 10]
* **Yaklaşım:** Nesne Yönelimli Programlama (OOP)[cite: 9]

---

## 📁 Proje Yapısı (`src/`)

```tree
src/
│
├── Main.java          # Programın başlangıç noktasıdır, oyunu başlatır[cite: 9].
├── Oyun.java          # Simülasyon akışını, turları ve konsol etkileşimlerini yönetir[cite: 9].
├── Sehir.java         # Şehir bilgilerini ve ilçeleri tutan model sınıfıdır[cite: 9].
├── Ilce.java          # İlçeleri ve bağlı mahalleleri temsil eden sınıftır[cite: 9].
├── Mahalle.java       # Mahalleleri ve içindeki kişileri yöneten sınıftır[cite: 9].
├── Kisi.java          # Otomatik ID, ad-soyad ve yaş bilgisi içeren birey sınıfıdır[cite: 9].
└── FakerUretici.java  # JavaFaker kütüphanesi entegrasyonunu sağlayan yardımcı sınıftır[cite: 1].
