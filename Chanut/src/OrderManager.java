import java.util.*;
import java.util.concurrent.*;

class OrderManager {

    private static OrderManager instance;

    static final Semaphore brewSemaphore = new Semaphore(2);

    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private List<Observer> observers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    private OrderManager() {}

    public void addObserver(Observer o) { observers.add(o); }

    public void notifyObservers(Order order) {
        for (Observer o : observers) o.update(order);
    }

    public static synchronized OrderManager getInstance() {
        if (instance == null) instance = new OrderManager();
        return instance;
    }

    public void placeOrder(Order order) {
        orders.add(order);
        System.out.println("NEW ORDER: #" + order.getOrderNumber());
        order.setStatus("PREPARING");
        notifyObservers(order);
        for (Beverage drink : order.getDrinks()) {
            executor.submit(new DrinkTask(order, drink, this));
        }
    }

    void prepareDrink(Order order, Beverage drink) {
        try {
            brewSemaphore.acquire();

            int time = drink.getPreparationTime();
            Thread.sleep(time * 1000);

            synchronized (order) {
                order.drinkCompleted();
                if (order.isComplete()) {
                    order.setStatus("READY");
                    System.out.println("READY: #" + order.getOrderNumber());
                    notifyObservers(order);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            brewSemaphore.release();
        }
    }

    public void pickupOrder(int orderNumber) {
        for (Order o : orders) {
            if (o.getOrderNumber() == orderNumber) {
                String status = o.getStatus();

                if (status.equals("READY")) {
                    o.setStatus("PICKED UP");
                    System.out.println("Order #" + orderNumber + " picked up");
                    notifyObservers(o);

                    final Order pickedOrder = o;
                    executor.submit(() -> {
                        try {
                            Thread.sleep(10000);
                            pickedOrder.setStatus("REMOVE");
                            notifyObservers(pickedOrder);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });

                } else if (status.equals("PICKED UP") || status.equals("REMOVE")) {
                    System.out.println("Order #" + orderNumber + " has already been picked up.");

                } else {
                    System.out.println("Order #" + orderNumber + " is not ready yet.");
                }
                return;
            }
        }
        System.out.println("Invalid order number: " + orderNumber);
    }
}
