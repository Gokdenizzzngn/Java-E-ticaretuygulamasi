package service;

import model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.text.Collator;
import java.util.Locale;

public class ProductService {

    // Kullanıcının sisteme eklediği bütün ürünleri tutar.
    private List<Product> products = new ArrayList<>();

    // Yeni bir ürünü ürün listesine ekler.
    public void addProduct(Product product) {
        products.add(product);
    }

    /*
     * Kullanıcının girdiği ürün adına göre ürün listesinde arama yapar.

     * Ürün bulunursa ürünü döndürür.
     * Ürün bulunamazsa null döndürür.
     */
    public Product findProductByName(String productName) {

        for (Product product : products) {

            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }

        return null;
    }

    /*
     * Ürünleri kullanıcının seçtiği kritere göre artan veya azalan olarak sıralar.

     * kriter:
     * name
     * stock
     * rating

     * sıralama:
     * artan
     * azalan
     */
    public List<Product> sortProducts(String criterion, String direction) {

        // Orijinal ürün listesini bozmamak için yeni bir liste oluşturulur.
        List<Product> sortedProducts = new ArrayList<>(products);

        // Kullanıcının seçtiği kritere göre sıralama işlemi yapılır.
        Comparator<Product> comparator;

        switch (criterion) {

            case "name":

                // Türkçe alfabetik sıralama yapmak için Collator kullanılır.
                Collator turkishCollator =
                        Collator.getInstance(new Locale("tr", "TR"));

                comparator = Comparator.comparing(
                        Product::getName,
                        turkishCollator
                );
                break;

            case "stock":
                comparator = Comparator.comparingInt(Product::getStock);
                break;

            case "rating":
                comparator = Comparator.comparingDouble(Product::getRating);
                break;

            default:
                throw new IllegalArgumentException("Geçersiz sıralama kriteri.");
        }

        // Kullanıcı azalan sıralama seçmişse sıralama yönü ters çevrilir.
        if (direction.equals("azalan")) {
            comparator = comparator.reversed();
        }

        // Belirlenen kritere göre ürün listesi sıralanır.
        sortedProducts.sort(comparator);

        return sortedProducts;
    }
}