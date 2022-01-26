package BackingBeans;

import java.util.Date;

public class Tour {
    private String destination;
    private Date date;
    String packages;
    private String pickup;

    public Tour() {
    }

    public Tour(String destination, Date date, String pickup, String packages) {
        this.destination = destination;
        this.date = date;
        this.pickup = pickup;
        this.packages=packages;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }
}
