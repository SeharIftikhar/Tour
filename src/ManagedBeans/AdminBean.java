package ManagedBeans;

import BackingBeans.Admin;
import DB.Functions;

import java.io.IOException;
import java.sql.DriverManager;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name ="adminBean")
public class AdminBean {
    private Functions func = new Functions();
    Functions dbConnection=new Functions();

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





    public String addAdmin() throws SQLException, ClassNotFoundException {
        String name = func.getParameterByName("name");
        String email = func.getParameterByName("email");
        String password = func.getParameterByName("password");


        func.addAdmin(name, email, password);
        return "home";
    }

    public void editAdmin() throws SQLException, ClassNotFoundException, IOException {
        String idStr = func.getParameterByName("editForm:id");
        int id = Integer.valueOf(idStr);
        String name = func.getParameterByName("name");
        String email = func.getParameterByName("email");
        String password = func.getParameterByName("password");


        func.editAdmin(id, name, email, password);

        FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml"); //Redirect to home.xhtml
    }

    public String deleteAdmin(String idStr) throws SQLException, ClassNotFoundException {
        int id = Integer.valueOf(idStr);

        func.deleteAdmin(id);
        return "home";
    }
}
