package ManagedBeans;

import BackingBeans.User;
import DB.DbConnection;

import java.sql.*;

import javax.faces.bean.ManagedBean;

@ManagedBean(name ="tourBean")
public class UserBean {
    DbConnection dbConnection=new DbConnection();
    User u=new User();
    public void setU(User u){
        this.u=u;
    }
    public User getU(){
        return u;
    }

    public UserBean(){

    }


    public void init(){
        u= new User();
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
        PreparedStatement ps=c.prepareStatement("insert into tours (name, email, gender, address, phoneNo) values ( ?, ?, ?)");
        ps.setString(1, this.u.getName());
        ps.setString(1, this.u.getEmail());

        ps.setString(3, this.u.getGender());
        ps.setString(3, this.u.getAddress());
        ps.setInt(3, this.u.getPhoneNo());


        ps.executeUpdate();
        System.out.println("Success!!");
    }
}

