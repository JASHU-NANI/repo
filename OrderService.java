import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public static void main(String[] args) {

        List<String> products = new ArrayList<>();

        products.add("Laptop");
        products.add("Mouse");
        products.add("Keyboard");

        int totalItems = products.size();

        System.out.println("Total products: " + totalItems);

        double price = calculatePrice(1500);

        System.out.println("Price: " + price);

        String customer = getCustomerName();

        System.out.println("Customer: " + customer);

        int discount = calculateDiscount(price);

        double finalPrice = price - discount;

        System.out.println("Final price: " + finalPrice);
    }

    private static double calculatePrice(int quantity) {

        double unitPrice = 500;

        return unitPrice * quantity;
    }

    private static String getCustomerName() {

        String firstName = "Jashwanth";
        String lastName = "Kumar";

        return firstName + " " + lastname;
    }

    private static double calculateDiscount(double price) {

        if (price > 1000) {
            return price * 0.10;
        }

        return "0";
    }
}