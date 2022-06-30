
public class Room
{

    private int roomNumber = 0;
    private String size;        //double or single
    private String category;    //standard or luxury
    private boolean status;     //true for booked
    private double price;     //true for booked
    private String facilities;

    public Room(int roomNumber, String size, String category, double price, boolean status, String facilities)
    {
        this.roomNumber = roomNumber;
        this.size = size;
        this.category = category;
        this.status = status;
        this.price = price;
        this.facilities = facilities;
    }

    public String getFormattedRoom(){
        return roomNumber+","+size+","+category+","+price+","+status+","+facilities;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getFacilities()
    {
        return facilities;
    }

    public void setFacilities(String facilities)
    {
        this.facilities = facilities;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "Room number: " + roomNumber + ", Size: " + size + ", Category: " + category
                + ", Price: DKK " + price + ", Status: " + status + ", Facilities: " + facilities;
    }

}
