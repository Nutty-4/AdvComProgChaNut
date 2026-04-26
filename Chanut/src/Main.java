import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        new ShowMenuJFrame("Menu.jpg");

        OrderManager manager = OrderManager.getInstance();
        manager.addObserver(new TeaMakerDisplay());
        manager.addObserver(new ReadyOrderDisplay());

        Scanner sc = new Scanner(System.in);
        CustomerUI ui = new CustomerUI(sc);

        ui.start();

        while (true) {
            String input = sc.nextLine().trim().toLowerCase();

            
            if (input.equals("place order")) {
                ui.start();

            } else if (input.startsWith("pick up order")) {
                String digits = input.replaceAll("[^0-9]", "");
                if (digits.isEmpty()) {
                    System.out.println("Please include an order number. e.g. pick up order 1001");
                    ui.showPostOrderMenu();
                } else {
                    int num = Integer.parseInt(digits);
                    manager.pickupOrder(num);
                    ui.showPostOrderMenu();
                }

            } else {
                System.out.println("Unrecognised input. Please try again.");
                ui.showPostOrderMenu();
            }
        }
    }
}
