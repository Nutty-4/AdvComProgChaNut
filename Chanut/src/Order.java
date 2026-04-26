import java.util.*;

class Order {
    private static int counter = 1000;

    private int orderNumber;
    private List<Beverage> drinks;
    private int completedDrinks = 0;
    private String status;

    public Order(List<Beverage> drinks) {
        this.orderNumber = ++counter;
        this.drinks = drinks;
        this.status = "PENDING";
    }

    public static int peekNextOrderNumber() {
        return counter + 1;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<Beverage> getDrinks() {
        return drinks;
    }

    public synchronized void drinkCompleted() {
        completedDrinks++;
    }

    public boolean isComplete() {
        return completedDrinks == drinks.size();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        return "#" + orderNumber + " | " + status;
    }
}
