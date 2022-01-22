package ManagedBeans;

import BackingBeans.Tour;
import DB.DbConnection;

import java.sql.*;

import javax.faces.bean.ManagedBean;

@ManagedBean(name ="tourBean")
public class TourBean {
    DbConnection dbConnection=new DbConnection();

    Tour t=new Tour();

    public void setT(Tour t){
        this.t=t;
    }
    public Tour getT(){
        return t;

    }

    public TourBean() {
    }

    public void init(){
        t= new Tour();
    }
    public void saveToDB() throws SQLException { //save to db function
        Connection c= null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour","root","");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //  System.err.println(e.getClass(), a.getName() +":"e.getMessage());
            System.exit(0);

        }
        System.out.println("Opened database successfully");
        PreparedStatement ps=c.prepareStatement("insert into tours (name, email, password) values (?, ?, ?)");
        ps.setString(1, this.t.getDestination());
        ps.setDate(2, (Date) this.t.getDate());
        ps.setString(3, this.t.getPickup());

        ps.executeUpdate();
        System.out.println("Success!!");
    }
}

