import java.util.*;

class CustomerUI {

    private Scanner sc;
    
    private static final int CANCEL_CODE = 6767;

    public CustomerUI(Scanner sc) {
        this.sc = sc;
    }


    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val == CANCEL_CODE) throw new OrderCancelledException();
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input — please enter a number.");
            }
        }
    }

   
    private String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = sc.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public void start() {
        try {
            System.out.println("=== ChaNut ===");

            int nextOrderNum = Order.peekNextOrderNumber();
            System.out.println("Your order number will be: #" + nextOrderNum);
            System.out.println("[Type 6767 at any time to cancel this order!]");

            int count;
            while (true) {
                count = readInt("How many drinks would you like to order: ");
                if (count > 0) break;
                System.out.println("Please enter at least 1 drink.");
            }

            List<Beverage> drinks = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                System.out.println("Drink " + (i + 1) + ":");

                
                int choice;
                while (true) {
                    System.out.println("  1.Matcha  2.Thai tea  3.Milk tea  4.Osmanthus tea  5.BigNut tea");
                    choice = readInt("  Choice: ");
                    if (choice >= 1 && choice <= 5) break;
                    System.out.println("Invalid drink choice. Please choose 1-5.");
                }

                
                String size;
                if (choice == 5) {
                    size = "BIG";
                    System.out.println("Big Nut Tea is only available in BIG size.");
                } else {
                    while (true) {
                        size = readString("  Size (SMALL/BIG): ").toUpperCase();
                        if (size.equals("SMALL") || size.equals("BIG")) break;
                        System.out.println("Invalid size. Please enter SMALL or BIG.");
                    }
                }

               
                String sweet;
                Set<String> validSweet = new HashSet<>(Arrays.asList("0%", "25%", "50%", "75%", "100%"));
                while (true) {
                    sweet = readString("  Sweetness (0%, 25%, 50%, 75%, 100%): ");
                    if (validSweet.contains(sweet)) break;
                    System.out.println(" Invalid sweetness. Please choose 0%, 25%, 50%, 75%, or 100%.");
                }

                Beverage drink = BeverageFactory.createDrink(choice, size, sweet);
                if (drink == null) {
                    System.out.println("Could not create drink. Skipping.");
                    continue;
                }

                
                int t;
                while (true) {
                    t = readInt("  Topping (0 none, 1 boba, 2 jelly, 3 warabi): ");
                    if (t >= 0 && t <= 3) break;
                    System.out.println("Invalid topping. Please choose 0, 1, 2, or 3.");
                }

                if (t == 1)      drink = new Boba(drink);
                else if (t == 2) drink = new Jelly(drink);
                else if (t == 3) drink = new WarabiMochi(drink);

                drinks.add(drink);
            }

            if (drinks.isEmpty()) {
                System.out.println("No valid drinks in order. Order cancelled.");
                showPostOrderMenu();
                return;
            }

            Order order = new Order(drinks);
            OrderManager.getInstance().placeOrder(order);
            System.out.println("Order #" + order.getOrderNumber() + " has been placed successfully!");
            showPostOrderMenu();

        } catch (OrderCancelledException e) {
            
            System.out.println("Order cancelled.");
            showPostOrderMenu();
        }
    }

    
    public void showPostOrderMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   What would you like to do next?    ║");
        System.out.println("║   Type: place order                  ║");
        System.out.println("║   Type: pick up order XXXX           ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    
    private static class OrderCancelledException extends RuntimeException {}
}
