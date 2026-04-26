public abstract class ToppingDecorator extends Beverage {
    protected Beverage beverage;
}

class Boba extends ToppingDecorator {
    public Boba(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + " + Boba";
    }

    public double getPrice() {
        return beverage.getPrice() + 20;
    }

    public int getPreparationTime() {
        return beverage.getPreparationTime() + 1;
    }
}

class Jelly extends ToppingDecorator {
    public Jelly(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + " + Jelly";
    }

    public double getPrice() {
        return beverage.getPrice() + 10;
    }

    public int getPreparationTime() {
        return beverage.getPreparationTime() + 1;
    }
}

class WarabiMochi extends ToppingDecorator {
    public WarabiMochi(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + " + Warabi Mochi";
    }

    public double getPrice() {
        return beverage.getPrice() + 20;
    }

    public int getPreparationTime() {
        return beverage.getPreparationTime() + 2;
    }
}
