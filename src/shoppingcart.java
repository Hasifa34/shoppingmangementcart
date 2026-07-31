import java.util.ArrayList;
import java.util.Scanner;

class Product {

    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    public void display() {
        System.out.println("-------------------------");
        System.out.println("Product ID : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Price      : " + price);
        System.out.println("Quantity   : " + quantity);
        System.out.println("Subtotal   : " + getTotal());
    }
}


public class shoppingcart{

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Product> cart = new ArrayList<>();


    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== SHOPPING CART MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Product to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Search Product");
            System.out.println("4. Update Quantity");
            System.out.println("5. Remove Product from Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    viewCart();
                    break;

                case 3:
                    searchProduct();
                    break;

                case 4:
                    updateQuantity();
                    break;

                case 5:
                    removeProduct();
                    break;

                case 6:
                    checkout();
                    break;

                case 7:
                    System.out.println("Thank you for shopping with us!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

    }
    static void addProduct() {

        System.out.println("\n--- ADD PRODUCT TO CART ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Product existing = findProduct(id);
        if (existing != null) {
            System.out.print("Product already in cart. Enter additional quantity: ");
            int addQty = sc.nextInt();
            existing.setQuantity(existing.getQuantity() + addQty);
            System.out.println("Quantity updated successfully.");
            return;
        }

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        Product product = new Product(id, name, price, quantity);

        cart.add(product);

        System.out.println("Product added to cart successfully.");
    }

    static void viewCart() {

        System.out.println("\n--- CART ITEMS ---");

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double grandTotal = 0;

        for (Product product : cart) {
            product.display();
            grandTotal += product.getTotal();
        }

        System.out.println("-------------------------");
        System.out.println("Grand Total: " + grandTotal);
    }

    static void searchProduct() {

        System.out.println("\n--- SEARCH PRODUCT ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product product = findProduct(id);

        if (product != null) {
            System.out.println("Product found:");
            product.display();
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    static void updateQuantity() {

        System.out.println("\n--- UPDATE QUANTITY ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product product = findProduct(id);

        if (product == null) {
            System.out.println("Product not found in cart.");
            return;
        }

        System.out.print("Enter new quantity: ");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero. Use Remove instead.");
            return;
        }

        product.setQuantity(quantity);

        System.out.println("Quantity updated successfully.");
        System.out.println("Product Name: " + product.getName());
    }

    static void removeProduct() {

        System.out.println("\n--- REMOVE PRODUCT FROM CART ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product product = findProduct(id);

        if (product == null) {
            System.out.println("Product not found in cart.");
            return;
        }

        cart.remove(product);

        System.out.println("Product removed from cart successfully.");
    }

    static void checkout() {

        System.out.println("\n--- CHECKOUT ---");

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }

        double grandTotal = 0;

        for (Product product : cart) {
            product.display();
            grandTotal += product.getTotal();
        }

        System.out.println("-------------------------");
        System.out.println("Total Amount Payable: " + grandTotal);

        cart.clear();

        System.out.println("Checkout complete. Cart is now empty.");
    }

    static Product findProduct(int id) {

        for (Product product : cart) {

            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }
}
