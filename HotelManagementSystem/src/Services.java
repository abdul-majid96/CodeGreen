public class Services
{
    private String service;
    private double servicePrice;
    private int sID;

    public Services(int sID, String service, double servicePrice)
    {
        this.service = service;
        this.servicePrice = servicePrice;
        this.sID = sID;
    }

    public void serviceOrder(String orderS){
        service += orderS;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getFormattedService(){
        return sID + "," + service + "," + servicePrice;
    }

    @Override
    public String toString()
    {
        return "ID: " + sID + "\n" + "Service: " + service + "\n" +
                "Price: DKK " + servicePrice + "\n" + "-----------------" + "\n";
    }
}
