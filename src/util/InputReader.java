package util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputReader {

    // Kullanıcıdan bilgi almak için kullanılır.
    private Scanner scanner = new Scanner(System.in);

    /*
     * Kullanıcıdan kaç farklı ürün gireceğini alır.
     * En az 2 farklı ürün girilmesi gerekir.
     */
    public int readProductCount() {

        while (true) {

            System.out.print("Kaç farklı ürün gireceksiniz: ");

            try {
                int count = Integer.parseInt(scanner.nextLine());

                if (count < 2) {

                    System.out.println("En az 2 farklı ürün girmelisiniz.");

                } else {
                    return count;
                }
            // Farklı formatta değer girilirse hata fırlatılır.
            } catch (NumberFormatException e) {

                System.out.println("Lütfen geçerli bir sayı giriniz.");
            }
        }
    }

    /*
     * Kullanıcıdan ürün adını alır.
     * Ürün adı boş olamaz ve 20 karakterden uzun olamaz.
     */
    public String readProductName() {

        while (true) {

            System.out.print("Ürün Adı: ");

            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {

                System.out.println("Ürün adı boş bırakılamaz.");

            } else if (name.length() > 20) {

                System.out.println("Ürün adı en fazla 20 karakter olabilir.");

            } else {

                return name;

            }
        }
    }

    /*
     * Kullanıcıdan ürünün fiyat bilgisini alır.
     * Fiyat 1 ile 100 arasında olmalıdır.
     */
    public BigDecimal readPrice() {

        while (true) {

            System.out.print("Birim Fiyat: ");

            try {

                String input = scanner.nextLine().replace(",", ".").trim();

                BigDecimal price = new BigDecimal(input);

                if (price.compareTo(BigDecimal.ONE) < 0
                        || price.compareTo(new BigDecimal("100")) > 0) {

                    System.out.println("Fiyat 1 ile 100 arasında olmalıdır.");

                } else {

                    return price;
                }
            // Hatalı fiyat girilince hata fırlatıyor.
            } catch (NumberFormatException e) {

                System.out.println("Lütfen geçerli bir fiyat giriniz.");
            }
        }
    }

    /*
     * Kullanıcıdan ürünün stok miktarını alır.
     * Stok miktarı en az 1 olmalıdır.
     */
    public int readStock() {

        while (true) {

            System.out.print("Stok Miktarı: ");

            try {

                int stock = Integer.parseInt(scanner.nextLine());

                if (stock < 1) {

                    System.out.println("Stok miktarı en az 1 olmalıdır.");

                } else {

                    return stock;
                }
            // Hatalı stock girilince hata fırlatıyor.
            } catch (NumberFormatException e) {

                System.out.println("Lütfen geçerli bir sayı giriniz.");
            }
        }
    }

    /*
     * Kullanıcıdan ürünün değerlendirme puanını alır.
     * Değerlendirme puanı 0 ile 5 arasında olmalıdır.
     */
    public double readRating() {

        while (true) {

            System.out.print("Değerlendirme Puanı (0-5): ");

            try {

                String input = scanner.nextLine().replace(",", ".").trim();

                double rating = Double.parseDouble(input);

                if (rating < 0 || rating > 5) {

                    System.out.println("Değerlendirme puanı 0 ile 5 arasında olmalıdır.");

                } else {

                    return rating;
                }
            // Hatalı rating girilince hata fırlatıyor.
            } catch (NumberFormatException e) {

                System.out.println("Lütfen geçerli bir puan giriniz.");
            }
        }
    }

    /*
     * Kullanıcıdan sıralama kriterini alır.
     * name
     * stock
     * rating
     */
    public String readSortCriterion() {

        while (true) {

            System.out.print("Sıralama kriteri (name/stock/rating): ");

            String criterion = scanner.nextLine().trim().toLowerCase();

            if (criterion.equals("name")
                    || criterion.equals("stock")
                    || criterion.equals("rating")) {

                return criterion;
            }

            System.out.println("Lütfen name, stock veya rating giriniz.");
        }
    }


    // Kullanıcıdan sıralamanın artan mı azalan mı olacağını alır.
    public String readSortDirection() {

        while (true) {

            System.out.print("Sıralama türü (artan/azalan): ");

            String direction = scanner.nextLine().trim().toLowerCase();

            if (direction.equals("artan")
                    || direction.equals("azalan")) {

                return direction;
            }

            System.out.println("Lütfen artan veya azalan giriniz.");
        }
    }

    /*
     * Sepete ürün ekleme sorusunda kullanılır.
     * Evet girilirse true, Hayır girilirse false döndürür.
     */
    public boolean readYesNo(String message) {

        while (true) {

            System.out.print(message + " (Evet/Hayır): ");

            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("evet")) {
                return true;
            }

            if (answer.equals("hayır")
                    || answer.equals("hayir")) {

                return false;
            }

            System.out.println("Lütfen Evet veya Hayır giriniz.");
        }
    }

    // Kullanıcıdan sepete eklemek istediği ürünün adını alır.
    public String readCartProductName() {

        while (true) {

            System.out.print("Eklemek istediğiniz ürünün adı: ");

            String name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println("Ürün adı boş olamaz.");
        }
    }

    // Kullanıcıdan sepete kaç adet ürün eklemek istediğini alır.
    public int readQuantity() {

        while (true) {

            System.out.print("Eklemek istediğiniz adet: ");

            try {

                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity < 1) {

                    System.out.println("Adet en az 1 olmalıdır.");

                } else {

                    return quantity;
                }
            // Hatalı giriş olursa hata fırlatır.
            } catch (NumberFormatException e) {

                System.out.println("Lütfen geçerli bir sayı giriniz.");
            }
        }
    }
}