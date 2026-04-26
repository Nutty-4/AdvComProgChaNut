public class DrinkTask implements Runnable {

    private Order order;
    private Beverage drink;
    private OrderManager manager;

    public DrinkTask(Order order, Beverage drink, OrderManager manager) {
        this.order = order;
        this.drink = drink;
        this.manager = manager;
    }

    @Override
    public void run() {
        manager.prepareDrink(order, drink);
    }
    
}
