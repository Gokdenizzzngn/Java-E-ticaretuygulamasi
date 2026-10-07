import model.Product;
import service.CartService;
import service.ProductService;
import util.InputReader;

import java.math.BigDecimal;
import java.util.List;

public class Question2 {

    /*
     * Programın çalışmaya başladığı ana metottur.
     * Genel program akışını yönetir:
     * 1- Ürün bilgilerini alır.
     * 2- Ürünleri kaydeder.
     * 3- Ürünleri sıralar.
     * 4- Sepete ürün ekler.
     * 5- Sepet toplamını ve varsa sepet indirimlerini hesaplatır.
     */
    public static void main(String[] args) {

        // Kullanıcıdan veri almak için InputHelper nesnesi oluşturulur.
        InputReader input = new InputReader();

        // Ürünlerle ilgili işlemler için ProductService oluşturulur.
        ProductService productService = new ProductService();

        // Sepet işlemleri için CartService oluşturulur.
        CartService cartService = new CartService();

        // Kullanıcıdan kaç ürün gireceği alınır.
        int productCount = input.readProductCount();

        // Kullanıcının belirttiği ürün sayısı kadar ürün bilgisi alınır.
        for (int i = 1; i <= productCount; i++) {

            System.out.println();
            System.out.println("Ürün " + i);

            String name;

            // Aynı isimde ürün girilmesini engeller.
            while (true) {

                name = input.readProductName();

                if (productService.findProductByName(name)!=null) {

                    System.out.println("Bu isimde bir ürün zaten var.");
                } else {
                    break;
                }
            }

            // Ürünün fiyatı, stok miktarı ve değerlendirme puanı alınır.
            BigDecimal price = input.readPrice();

            int stock = input.readStock();

            double rating = input.readRating();

            // Alınan bilgiler ile yeni bir ürün oluşturulur.
            Product product = new Product(
                            name,
                            price,
                            stock,
                            rating
                    );

            // Oluşturulan ürün sisteme eklenir.
            productService.addProduct(product);

            System.out.println("Ürün başarıyla eklendi.");
        }

        // Kullanıcıdan sıralama kriteri ve sıralama yönü (artan/azalan) alınır.
        System.out.println();
        System.out.println("===== SIRALAMA =====");

        String criterion = input.readSortCriterion();

        String direction = input.readSortDirection();

        // Ürünler seçilen kriter ve yöne göre sıralanır.
        List<Product> sortedProducts = productService.sortProducts(
                        criterion,
                        direction
                );

        // Sıralanmış ürünler ekrana yazdırılır.
        System.out.println();
        System.out.println("Sıralanmış Ürünler:");

        for (Product product : sortedProducts) {

            System.out.println(product);
        }

        // Kullanıcının sepete ürün eklemesi sağlanır.
        System.out.println();

        while (true) {

            boolean wantsToAdd = input.readYesNo("Sepete ürün eklemek ister misiniz? (Evet/Hayır): ");

            // Kullanıcı ürün eklemek istemezse,sepette en az iki farklı ürün olup olmadığı kontrol edilir.
            if (!wantsToAdd) {

                if (!cartService.hasMinimumProductCount()) {

                    System.out.println("Sepette en az 2 farklı ürün olmalıdır.");

                    continue;
                }

                break;
            }

            // Kullanıcıdan sepete eklemek istediği ürünün adı alınır.
            String productName = input.readCartProductName();

            // Girilen isimdeki ürün sistemde aranır.
            Product selectedProduct = productService.findProductByName(productName);

            // Ürün bulunamazsa kullanıcıya bilgi verilir.
            if (selectedProduct == null) {

                System.out.println("Bu isimde bir ürün bulunamadı.");

                continue;
            }

            // Kullanıcı geçerli bir adet girene kadar ürün sepete eklenmeye çalışılır.
            while (true) {

                int quantity = input.readQuantity();

                boolean added = cartService.addToCart(
                                selectedProduct,
                                quantity
                        );

                // Stok yetersizse kullanıcıdan yeniden adet girmesi istenir.
                if (!added) {

                    System.out.println("Stokta yeterli ürün yok.");

                    System.out.println("Mevcut stok: " + selectedProduct.getStock());

                } else {

                    System.out.println(selectedProduct.getName() + " sepete eklendi."
                    );

                    break;
                }
            }
        }

        // Sepet işlemleri tamamlandıktan sonra ürünler ve toplam fiyat ekrana yazdırılır.
        cartService.printCart();
    }
}