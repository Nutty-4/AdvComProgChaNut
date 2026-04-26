public class BeverageFactory {
    public static Beverage createDrink(int choice, String size, String sweetness) {
        switch (choice) {
            case 1: return new Matcha(size, sweetness);
            case 2: return new ThaiTea(size, sweetness);
            case 3: return new MilkTea(size, sweetness);
            case 4: return new OsmanthusTea(size, sweetness);
            case 5: return new BigNutTea(sweetness);
            default: return null;
        }
    }
}
