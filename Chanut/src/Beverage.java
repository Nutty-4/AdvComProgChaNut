public abstract class Beverage {
    protected String name;
    protected String size;
    protected String sweetness;

    public String getDescription() {
        return name + " (" + size + ", " + sweetness + ")";
    }

    public abstract double getPrice();

    public abstract int getPreparationTime(); // in seconds
}

class Matcha extends Beverage {
    public Matcha(String size, String sweetness) {
        this.name = "Matcha";
        this.size = size;
        this.sweetness = sweetness;
    }

    public double getPrice() {
        return size.equals("SMALL") ? 110 : 130;
    }

    public int getPreparationTime() {
        return 3; 
    }
}

class ThaiTea extends Beverage {
    public ThaiTea(String size, String sweetness) {
        this.name = "Thai Tea";
        this.size = size;
        this.sweetness = sweetness;
    }

    public double getPrice() {
        return size.equals("SMALL") ? 90 : 110;
    }

    public int getPreparationTime() {
        return 2; 
    }
}

class MilkTea extends Beverage {
    public MilkTea(String size, String sweetness) {
        this.name = "Milk Tea";
        this.size = size;
        this.sweetness = sweetness;
    }

    public double getPrice() {
        return size.equals("SMALL") ? 95 : 115;
    }

    public int getPreparationTime() {
        return 2; 
    }
}

class OsmanthusTea extends Beverage {
    public OsmanthusTea(String size, String sweetness) {
        this.name = "Osmanthus Tea";
        this.size = size;
        this.sweetness = sweetness;
    }

    public double getPrice() {
        return size.equals("SMALL") ? 110 : 130;
    }

    public int getPreparationTime() {
        return 4; 
    }
}

class BigNutTea extends Beverage {
    public BigNutTea(String sweetness) {
        this.name = "Big Nut Tea";
        this.size = "BIG";
        this.sweetness = sweetness;
    }

    public double getPrice() {
        return 150;
    }

    public int getPreparationTime() {
        return 4; 
    }
}
