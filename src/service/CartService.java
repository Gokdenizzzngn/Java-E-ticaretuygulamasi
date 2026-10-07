package service;

import model.CartItem;
import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartService {

    // Kullanıcının sepete eklediği ürünleri tutar.
    private List<CartItem> cartItems = new ArrayList<>();

    /*
      Kullanıcının seçtiği ürünü sepete ekler.
      Stok yeterliyse ürün sepete eklenir.
     */
    public boolean addToCart(Product product, int quantity) {

        // Kullanıcının istediği adet stoktan fazlaysa ürün sepete eklenmez.
        if (quantity > product.getStock()) {
            return false;
        }

        CartItem existingItem = null;

        // Ürünün daha önce sepete eklenip eklenmediği kontrol edilir.
        for (CartItem item : cartItems) {

            if (item.getProduct()
                    .getName()
                    .equalsIgnoreCase(product.getName())) {

                existingItem = item;
                break;
            }
        }

        // Ürün daha önce sepette varsa mevcut ürünün adedi artırılır.
        if (existingItem != null) {

            existingItem.increaseQuantity(quantity);

        } else {

         // Ürün daha önce sepette yoksa yeni bir CartItem oluşturulur.
            CartItem newItem =
                    new CartItem(product, quantity);

            cartItems.add(newItem);
        }

        // Sepete eklenen adet kadar ürünün stok miktarı azaltılır.
        product.decreaseStock(quantity);

        return true;
    }

    // Sepette en az iki farklı ürün olup olmadığını kontrol eder.
    public boolean hasMinimumProductCount() {

        return cartItems.size() >= 2;
    }

    /*
     * Bir ürünün indirim uygulanmış birim fiyatını hesaplar.

     * Sepetteki ürünler eklenme sırasına göre karşılaştırılır.
     * 1. ürün -> 2. ürün
     * 2. ürün -> 3. ürün

     * Eğer mevcut ürün bir sonraki üründen daha pahalıysa,sonraki ürünün fiyatı kadar indirim uygulanır.
     */
    public BigDecimal calculateDiscountedUnitPrice(int index) {

        // Şu anda işlem yapılacak ürün ve fiyat bilgisi alınır.
        CartItem currentItem =
                cartItems.get(index);

        BigDecimal currentPrice =
                currentItem.getProduct().getPrice();

        // Son üründen sonra başka ürün olmadığı için son ürüne indirim uygulanmaz.
        if (index == cartItems.size() - 1) {
            return currentPrice;
        }

        // Mevcut üründen sonra gelen ürün ve fiyat bilgisi alınır.
        CartItem nextItem =
                cartItems.get(index + 1);

        BigDecimal nextPrice =
                nextItem.getProduct().getPrice();

        // Mevcut ürün sonraki üründen daha pahalıysa sonraki ürünün birim fiyatı kadar indirim yapılır.
        if (currentPrice.compareTo(nextPrice) > 0) {

            return currentPrice.subtract(nextPrice);
        }

        // Mevcut ürün daha pahalı değilse indirim yapılmadan normal fiyat döndürülür.
        return currentPrice;
    }

    // Sepetteki bütün ürünlerin toplam fiyatını hesaplar.
    public BigDecimal calculateTotal() {

        BigDecimal total = BigDecimal.ZERO;

        // Sepetteki bütün ürünler sırayla gezilir.
        for (int i = 0; i < cartItems.size(); i++) {

            CartItem item =
                    cartItems.get(i);

            // Ürünün varsa indirimli birim fiyatı hesaplanır.
            BigDecimal unitPrice =
                    calculateDiscountedUnitPrice(i);

            // Birim fiyat ürün adedi ile çarpılır.
            BigDecimal itemTotal =
                    unitPrice.multiply(
                            BigDecimal.valueOf(
                                    item.getQuantity()
                            )
                    );

            // Hesaplanan tutar sepet toplamına eklenir.
            total = total.add(itemTotal);
        }

        return total;
    }

    // Sepetteki ürünleri ve toplam fiyatı ekrana yazdırır.
    public void printCart() {

        System.out.println();
        System.out.println("Sepetiniz:");

        // Sepette bulunan bütün ürünler sırayla yazdırılır.
        for (int i = 0; i < cartItems.size(); i++) {

            CartItem item = cartItems.get(i);

            BigDecimal normalPrice = item.getProduct().getPrice();

            BigDecimal finalPrice = calculateDiscountedUnitPrice(i);

            BigDecimal itemTotal = finalPrice.multiply(BigDecimal.valueOf(item.getQuantity())
                    );

            System.out.print(item.getProduct().getName() + " - ");

            System.out.print("Adet: "+ item.getQuantity() + ", ");

            // Normal fiyat ile hesaplanan fiyat farklıysa ürüne indirim uygulanmıştır.
            if (normalPrice.compareTo(finalPrice) != 0) {

                System.out.print("Birim Fiyat: "
                                + normalPrice
                                + " -> "
                                + finalPrice
                                + " (indirimli), "
                );
            }
            else {

                System.out.print("Birim Fiyat: " + normalPrice + ", ");
            }

            System.out.println("Toplam Fiyat: " + itemTotal);

        }

        // Bütün ürünler yazdırıldıktan sonra sepetin genel toplamı gösterilir.
        System.out.println("Sepet Toplamı: " + calculateTotal());
    }
}