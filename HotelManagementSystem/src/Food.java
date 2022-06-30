public class Food
{
    private String foodItem;
    private double foodPrice;
    private int fID;

    public Food(int fID, String foodItem, double foodPrice)
    {
        this.foodItem = foodItem;
        this.foodPrice = foodPrice;
        this.fID = fID;
    }

    public void getFoodID(int foodID){
        fID = foodID;
    }

    public int setFoodID() {
        return fID;
    }
    public void placeOrder(String order){
        foodItem = order;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFormattedFood(){
        return fID + "," + foodItem + "," + foodPrice;
    }

    @Override
    public String toString()
    {
        return "ID: " + fID + "\n" + "Food item: " + foodItem + "\n" +
                "Price: DKK " + foodPrice + "\n" + "-----------------" + "\n";
    }
}
