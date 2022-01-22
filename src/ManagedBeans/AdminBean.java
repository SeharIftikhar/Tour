package ManagedBean;

import BackingBeans.Admin;
import DB.DbConnection;
import java.sql.DriverManager;

import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ManagedBean(name ="adminBean")
public class AdminBean {
    DbConnection dbConnection=new DbConnection();

    Admin a=new Admin();

    public void setA(Admin a){
        this.a=a;
    }
    public Admin getA(){
        return a;

    }

    public AdminBean() {
    }

    public void init(){
        a= new Admin();
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
        PreparedStatement ps=c.prepareStatement("insert into admin (name, email, password) values (?, ?, ?)");
        ps.setString(1, this.a.getName());
        ps.setString(2,this.a.getEmail());
        ps.setString(3, this.a.getPassword());

        ps.executeUpdate();
        System.out.println("Success!!");


    }
}
