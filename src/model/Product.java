package model;
import java.math.BigDecimal;
public class Product {

       // Bir ürünün temel bilgilerini tutar.
        private String name;
        private BigDecimal price;
        private int stock;
        private double rating;

        // Yeni bir ürün oluşturmak için kullanılır.
        public Product(String name, BigDecimal price, int stock, double rating) {
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.rating = rating;
        }

        // Ürün adını döndürür.
        public String getName() {
            return name;
        }

        // Ürün fiyatını döndürür.
        public BigDecimal getPrice() {
            return price;
        }

        // Ürünün mevcut stok miktarını döndürür.
        public int getStock() {
            return stock;
        }

        // Ürünün değerlendirme puanını döndürür.
        public double getRating() {
            return rating;
        }

        // Sepete ürün eklendiğinde stok miktarını azaltır.
        public void decreaseStock(int quantity)
        {
            stock = stock - quantity;
        }

        // Ürün bilgilerini ekranda göstermek için kullanılır.
        @Override
        public String toString() {

            return name
                    + " - Fiyat: " + price
                    + ", Stok: " + stock
                    + ", Değerlendirme: " + rating;
        }
    }
