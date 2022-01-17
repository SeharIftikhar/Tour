package ManagedBeans;

import BackingBeans.Client;
import DB.DbConnection;

import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ManagedBean(name ="clientBean")
public class ClientBean {
    DbConnection dbConnection=new DbConnection();

    Client c=new Client();

    public void setC(Client c){
        this.c=c;
    }
    public Client getC(){
        return c;

    }

    public ClientBean() {
    }

    public void init(){
        c= new Client();
    }
    public void saveToDB() throws SQLException { //save to db function
        Connection con= null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour","root","");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // System.err.println(e.getClass(), c.getName());
            System.exit(0);

        }
        System.out.println("Opened database successfully");
        PreparedStatement ps=con.prepareStatement("insert into client (name, cnic, contactNo, city) values (?, ?, ?, ?,)");
        ps.setString(1, this.c.getName());

        ps.setString(2,this.c.getCnic());


        ps.setInt(3, this.c.getContactNo());
        ps.setString(3, this.c.getCity());

        ps.executeUpdate();
        System.out.println("Success!!");
    }
    public void bookTour(){

    }
}
