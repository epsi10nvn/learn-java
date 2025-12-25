import model.*;
import service.*;
import exception.*;
import utils.InputUtils;

import java.util.List;

public class Main {
    private static ProductService productService;
    private static CustomerService customerService;
    private static OrderService orderService;

    public static void main(String[] args) {
        initializeServices();
        showMainMenu();
    }

    private static void initializeServices() {
        productService = new ProductService();
        customerService = new CustomerService();
        orderService = new OrderService(productService, customerService);
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n========== ORDER MANAGEMENT SYSTEM ==========");
            System.out.println("1. Product Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Order Management");
            System.out.println("4. Reports");
            System.out.println("0. Exit");
            System.out.println("================================================");

            int choice = InputUtils.nextInt("Select function: ");

            try {
                switch (choice) {
                    case 1:
                        productMenu();
                        break;
                    case 2:
                        customerMenu();
                        break;
                    case 3:
                        orderMenu();
                        break;
                    case 4:
                        reportMenu();
                        break;
                    case 0:
                        System.out.println("Thank you for using the system!");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void productMenu() {
        while (true) {
            System.out.println("\n--- PRODUCT MANAGEMENT ---");
            System.out.println("1. Add new product");
            System.out.println("2. Search products by name");
            System.out.println("3. Display all products");
            System.out.println("0. Back");

            int choice = InputUtils.nextInt("Select function: ");

            try {
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        searchProductsByName();
                        break;
                    case 3:
                        displayAllProducts();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addProduct() {
        try {
            System.out.println("\n--- Add New Product ---");
            String code = InputUtils.nextLine("Enter product code: ");
            String name = InputUtils.nextLine("Enter product name: ");
            double price = InputUtils.nextDouble("Enter price: ");
            int stock = InputUtils.nextInt("Enter stock quantity: ");

            Product product = productService.addProduct(code, name, price, stock);
            System.out.println("Product added successfully!");
            System.out.println(product);
        } catch (AppException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void searchProductsByName() {
        System.out.println("\n--- Search Products ---");
        String name = InputUtils.nextLine("Enter product name (case-insensitive): ");
        List<Product> products = productService.searchProductsByName(name);

        if (products.isEmpty()) {
            System.out.println("No products found!");
        } else {
            System.out.println("Found " + products.size() + " product(s):");
            products.forEach(System.out::println);
        }
    }

    private static void displayAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available!");
        } else {
            System.out.println("\nProduct List:");
            products.forEach(System.out::println);
        }
    }

    private static void customerMenu() {
        while (true) {
            System.out.println("\n--- CUSTOMER MANAGEMENT ---");
            System.out.println("1. Add new customer");
            System.out.println("2. Display all customers");
            System.out.println("0. Back");

            int choice = InputUtils.nextInt("Select function: ");

            try {
                switch (choice) {
                    case 1:
                        addCustomer();
                        break;
                    case 2:
                        displayAllCustomers();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addCustomer() {
        try {
            System.out.println("\n--- Add New Customer ---");
            String code = InputUtils.nextLine("Enter customer code: ");
            String name = InputUtils.nextLine("Enter customer name: ");
            String email = InputUtils.nextLine("Enter email: ");

            Customer customer = customerService.addCustomer(code, name, email);
            System.out.println("Customer added successfully!");
            System.out.println(customer);
        } catch (AppException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void displayAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available!");
        } else {
            System.out.println("\nCustomer List:");
            customers.forEach(System.out::println);
        }
    }

    private static void orderMenu() {
        while (true) {
            System.out.println("\n--- ORDER MANAGEMENT ---");
            System.out.println("1. Create new order");
            System.out.println("2. Add product to order");
            System.out.println("3. Confirm order");
            System.out.println("4. Display all orders");
            System.out.println("0. Back");

            int choice = InputUtils.nextInt("Select function: ");

            try {
                switch (choice) {
                    case 1:
                        createOrder();
                        break;
                    case 2:
                        addProductToOrder();
                        break;
                    case 3:
                        confirmOrder();
                        break;
                    case 4:
                        displayAllOrders();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createOrder() {
        try {
            System.out.println("\n--- Create New Order ---");
            displayAllCustomers();
            String customerCode = InputUtils.nextLine("Enter customer code: ");

            Order order = orderService.createOrder(customerCode);
            System.out.println("Order created successfully!");
            System.out.println(order);
        } catch (AppException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void addProductToOrder() {
        try {
            System.out.println("\n--- Add Product to Order ---");
            displayAllOrders();
            long orderId = InputUtils.nextInt("Enter order ID: ");
            displayAllProducts();
            String productCode = InputUtils.nextLine("Enter product code: ");
            int quantity = InputUtils.nextInt("Enter quantity: ");

            orderService.addProductToOrder(orderId, productCode, quantity);
            System.out.println("Product added to order successfully!");
            
            Order order = orderService.findOrderById(orderId);
            System.out.println("Order total: " + String.format("%.2f", order.getTotal()));
        } catch (AppException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void confirmOrder() {
        try {
            System.out.println("\n--- Confirm Order ---");
            displayAllOrders();
            long orderId = InputUtils.nextInt("Enter order ID: ");

            Order order = orderService.findOrderById(orderId);
            System.out.println("Order before confirmation:");
            System.out.println(order);
            System.out.println("Order details:");
            order.getItems().forEach(item -> {
                System.out.println("  - " + item.getProduct().getName() + 
                                 " x " + item.getQuantity() + 
                                 " = " + String.format("%.2f", item.getSubtotal()));
            });

            orderService.confirmOrder(orderId);
            System.out.println("Order confirmed successfully!");
            System.out.println("Customer total spending: " + 
                              String.format("%.2f", order.getCustomer().getTotalSpending()));
        } catch (AppException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void displayAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders available!");
        } else {
            System.out.println("\nOrder List:");
            orders.forEach(order -> {
                System.out.println(order);
                order.getItems().forEach(item -> 
                    System.out.println("  - " + item));
            });
        }
    }

    private static void reportMenu() {
        while (true) {
            System.out.println("\n--- REPORTS ---");
            System.out.println("1. Find products by price range");
            System.out.println("2. Top N customers by spending");
            System.out.println("3. List orders by customer");
            System.out.println("4. Total revenue from confirmed orders");
            System.out.println("0. Back");

            int choice = InputUtils.nextInt("Select function: ");

            try {
                switch (choice) {
                    case 1:
                        findProductsByPriceRange();
                        break;
                    case 2:
                        getTopCustomers();
                        break;
                    case 3:
                        getCustomerOrders();
                        break;
                    case 4:
                        getTotalRevenue();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void findProductsByPriceRange() {
        try {
            System.out.println("\n--- Find Products by Price Range ---");
            double minPrice = InputUtils.nextDouble("Enter minimum price: ");
            double maxPrice = InputUtils.nextDouble("Enter maximum price: ");

            List<Product> products = productService.findProductsByPriceRange(minPrice, maxPrice);
            if (products.isEmpty()) {
                System.out.println("No products found in this price range!");
            } else {
                System.out.println("Found " + products.size() + " product(s):");
                products.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void getTopCustomers() {
        try {
            System.out.println("\n--- Top N Customers by Spending ---");
            int topN = InputUtils.nextInt("Enter number of customers (N): ");

            List<Customer> topCustomers = customerService.getTopCustomersBySpending(topN);
            if (topCustomers.isEmpty()) {
                System.out.println("No customers available!");
            } else {
                System.out.println("Top " + topN + " customers:");
                for (int i = 0; i < topCustomers.size(); i++) {
                    Customer c = topCustomers.get(i);
                    System.out.println((i + 1) + ". " + c.getName() + 
                                     " - Total spending: " + String.format("%.2f", c.getTotalSpending()));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void getCustomerOrders() {
        try {
            System.out.println("\n--- List Orders by Customer ---");
            displayAllCustomers();
            String customerCode = InputUtils.nextLine("Enter customer code: ");

            List<Order> orders = orderService.getOrdersByCustomer(customerCode);
            if (orders.isEmpty()) {
                System.out.println("This customer has no orders!");
            } else {
                Customer customer = customerService.findCustomerByCode(customerCode);
                System.out.println("Orders for customer: " + customer.getName());
                orders.forEach(order -> {
                    System.out.println(order);
                    order.getItems().forEach(item -> 
                        System.out.println("  - " + item));
                });
            }
        } catch (AppException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void getTotalRevenue() {
        double revenue = orderService.getTotalRevenueFromConfirmedOrders();
        System.out.println("\n--- Total Revenue ---");
        System.out.println("Total revenue from confirmed orders: " + String.format("%.2f", revenue));
    }
}
