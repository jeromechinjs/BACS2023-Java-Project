public class Children extends Customer{
    private double childPrice;
    private final double DISCOUNT = 0.5;

    public Children(int adultQauntity, int childQuantity, int studentQuantity) {
        super(adultQauntity, childQuantity, studentQuantity);
    }

    @Override
    public double calPrice() {
        this.childPrice = super.calPrice() * DISCOUNT;
        return childPrice;
    }
    
    public double calTotalPrice() {
        return calPrice() * (double)super.getChildQuantity();
    }

    @Override
    public String toString() {
        return "ChildPrice=" + calPrice() + "\ntotal Child Price = " + calTotalPrice();
    }
}