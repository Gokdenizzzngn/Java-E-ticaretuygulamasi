# 🛒 Java E-Ticaret Ürün Yönetim Uygulaması

Bu proje, kullanıcıdan alınan ürün bilgilerini kaydeden, ürünleri farklı kriterlere göre sıralayan, seçilen ürünleri sepete ekleyen ve belirlenen indirim kuralına göre sepet toplamını hesaplayan modüler bir Java konsol uygulamasıdır.

---

## 🚀 Özellikler

- **Ürün Ekleme:** Kullanıcıdan ürün adı, fiyat, stok miktarı ve değerlendirme puanı bilgileri alınır.
- **Ürün Bilgisi Kontrolleri:** Ürün adı, fiyat, stok miktarı ve değerlendirme puanı için gerekli doğrulamalar yapılır.
- **Ürün Sıralama:** Ürünler;
  - ada göre,
  - stok miktarına göre,
  - değerlendirme puanına göre  
  artan veya azalan şekilde sıralanabilir.
- **Türkçe Alfabetik Sıralama:** Ürün isimlerinin sıralanmasında Türkçe karakterlerin doğru sıralanması desteklenir.
- **Sepete Ürün Ekleme:** Kullanıcı ürün adı ve adet bilgisi girerek ürünleri sepete ekleyebilir.
- **Stok Kontrolü:** Sepete eklenmek istenen ürün miktarı mevcut stok ile karşılaştırılır.
- **Stok Güncelleme:** Sepete eklenen ürünlerin stok miktarı otomatik olarak azaltılır.
- **İndirim Hesaplama:** Sepetteki ürünler eklenme sırasına göre bir sonraki ürün ile karşılaştırılır. Mevcut ürünün fiyatı sonraki ürünün fiyatından yüksekse, sonraki ürünün birim fiyatı kadar indirim uygulanır.
- **Sepet Toplamı:** İndirimler uygulandıktan sonra ürünlerin toplam fiyatı ve genel sepet tutarı hesaplanır.
- **Güvenli Girdi Yönetimi:** Hatalı kullanıcı girişleri kontrol edilerek kullanıcıdan tekrar veri girmesi istenir.
- **Modüler Kod Yapısı:** Ürün, sepet, kullanıcı girdileri ve iş mantığı farklı class'lara ayrılmıştır.

---

## 📂 Proje Yapısı

```text
src
│
├── Question2.java
│
├── model
│   ├── Product.java
│   └── CartItem.java
│
├── service
│   ├── ProductService.java
│   └── CartService.java
│
└── util
    └── InputReader.java
```

### Class'ların Görevleri

- **Question2.java:** Programın ana akışını yönetir.
- **Product.java:** Ürün adı, fiyat, stok miktarı ve değerlendirme puanı bilgilerini tutar.
- **CartItem.java:** Sepette bulunan ürünü ve ürünün adet bilgisini tutar.
- **ProductService.java:** Ürün ekleme, ürün arama ve ürünleri sıralama işlemlerini gerçekleştirir.
- **CartService.java:** Sepete ürün ekleme, stok kontrolü, indirim ve toplam fiyat hesaplama işlemlerini gerçekleştirir.
- **InputReader.java:** Kullanıcıdan alınan verileri ve giriş kontrollerini yönetir.

---

## ✅ Girdi Kontrolleri

Program içerisinde aşağıdaki kontroller uygulanmaktadır:

- En az **2 farklı ürün** girilmelidir.
- Ürün adı en fazla **20 karakter** olabilir.
- Ürün fiyatı **1 ile 100 arasında** olmalıdır.
- Stok miktarı en az **1** olmalıdır.
- Değerlendirme puanı **0 ile 5 arasında** olmalıdır.
- Sıralama kriteri yalnızca `name`, `stock` veya `rating` olabilir.
- Sıralama yönü yalnızca `artan` veya `azalan` olabilir.
- Sepete eklenen ürün sistemde kayıtlı olmalıdır.
- Sepete eklenmek istenen adet mevcut stok miktarını aşamaz.
- Sepette en az **2 farklı ürün** bulunmalıdır.

---

## 💸 İndirim Mantığı

Sepetteki ürünler eklenme sıralarına göre karşılaştırılır.

Örneğin:

```text
1. ürün → 2. ürün ile karşılaştırılır
2. ürün → 3. ürün ile karşılaştırılır
3. ürün → 4. ürün ile karşılaştırılır
```

Eğer mevcut ürünün birim fiyatı sonraki ürünün birim fiyatından büyükse, mevcut ürüne sonraki ürünün birim fiyatı kadar indirim uygulanır.

### Örnek

```text
Defter → 3.00 TL
Kalem  → 1.50 TL
```

Defter, Kalem'den daha pahalı olduğu için:

```text
3.00 - 1.50 = 1.50 TL
```

Defterin indirimli birim fiyatı `1.50 TL` olur.

---

## 🛠️ Teknolojiler ve Gereksinimler

- **Dil:** Java
- **Java Sürümü:** JDK 8 veya üzeri
- **IDE:** IntelliJ IDEA
- **Kullanılan Standart Java Yapıları:**
  - `ArrayList`
  - `Comparator`
  - `BigDecimal`
  - `Scanner`
  - `Collator`
  - `Locale`

---

## 💻 Kurulum ve Çalıştırma

### 1. Depoyu Klonlayın

```bash
git clone https://github.com/Gokdenizzzngn/Java-E-ticaretuygulamasi.git
```

### 2. Proje Klasörüne Girin

```bash
cd Java-E-ticaretuygulamasi
```

### 3. Projeyi IntelliJ IDEA ile Açın

IntelliJ IDEA içerisinde:

```text
File → Open → Proje klasörünü seçin
```

Daha sonra `Question2.java` dosyasını açıp çalıştırabilirsiniz.

---

## 🧪 Örnek Kullanım

```text
Kaç farklı ürün gireceksiniz: 2

Ürün 1
Ürün Adı: Kalem
Birim Fiyat: 1.50
Stok Miktarı: 100
Değerlendirme Puanı: 4.5

Ürün 2
Ürün Adı: Defter
Birim Fiyat: 3.00
Stok Miktarı: 50
Değerlendirme Puanı: 4.7

Sıralama kriteri (name/stock/rating): rating
Sıralama türü (artan/azalan): azalan

Sıralanmış Ürünler:
Defter - Fiyat: 3.00, Stok: 50, Değerlendirme: 4.7
Kalem - Fiyat: 1.50, Stok: 100, Değerlendirme: 4.5

Sepete ürün eklemek ister misiniz? (Evet/Hayır): Evet
Eklemek istediğiniz ürünün adı: Defter
Eklemek istediğiniz adet: 2

Sepete ürün eklemek ister misiniz? (Evet/Hayır): Evet
Eklemek istediğiniz ürünün adı: Kalem
Eklemek istediğiniz adet: 2

Sepete ürün eklemek ister misiniz? (Evet/Hayır): Hayır
```

İndirim uygulandıktan sonra:

```text
Defter
Adet: 2
Birim Fiyat: 3.00 -> 1.50 (indirimli)
Toplam: 3.00

Kalem
Adet: 2
Birim Fiyat: 1.50
Toplam: 3.00

Sepet Toplamı: 6.00
```

---

## 🎯 Projenin Amacı

Bu proje ile Java'da;

- Nesne yönelimli programlama,
- Class ve metod kullanımı,
- Liste yönetimi,
- Kullanıcı girdilerinin doğrulanması,
- Comparator ile sıralama,
- BigDecimal ile fiyat hesaplama,
- Sepet ve stok yönetimi,
- Kodun farklı sorumluluklara göre class'lara ayrılması

konularının uygulanması amaçlanmıştır.
