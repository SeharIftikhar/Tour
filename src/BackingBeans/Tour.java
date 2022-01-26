package BackingBeans;

import java.util.Date;

public class Tour {
    private String destination;
    private Date date;

    private String pickup;

    public Tour() {
    }

    public Tour(String destination, Date date, String pickup) {
        this.destination = destination;
        this.date = date;
        this.pickup = pickup;

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
