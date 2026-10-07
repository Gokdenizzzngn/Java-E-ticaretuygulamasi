package model;

public class CartItem {

        // Sepette hangi ürünün bulunduğunu ve bu üründen kaç adet eklendiğini tutar.
        private Product product;
        private int quantity;

        // Sepete yeni bir ürün eklendiğinde ürün ve adet bilgilerini kaydeder.
        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        // Sepetteki ürün bilgisini döndürür.
        public Product getProduct() {
            return product;
        }

        // Sepetteki ürünün adet bilgisini döndürür.
        public int getQuantity() {
            return quantity;
        }

        // Aynı ürün sepete tekrar eklenirse mevcut ürünün adedini artırır.
        public void increaseQuantity(int quantity) {
            this.quantity = this.quantity + quantity;
        }
    }
