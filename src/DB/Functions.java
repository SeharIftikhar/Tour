package DB;

import BackingBeans.Admin;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Functions {
    private Connection conn;

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try {
            String username = "BaHaiPhan";
            String password = "sof305@PT11303";
            String url = "jdbc:sqlserver://sakadream-sof305.database.windows.net:1433;databaseName=SOF305";

            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            String username = "sa";
            String password = "31011997";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=SOF305_Offline";

            conn = DriverManager.getConnection(url, username, password);
        }
    }

    private void cleanConnection() throws SQLException {
        conn.close();
    }

    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        boolean b = true;

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM USERS WHERE USERNAME LIKE ? AND PASSWORD LIKE ?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) setSessionUsername(username);
        else b = false;

        cleanConnection();

        return b;
    }

    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    private void setSessionUsername(String username) {
        HttpSession session = getSession();
        session.setAttribute("username", username);
    }

   // public List<Admin> showAllEmployees() throws SQLException, ClassNotFoundException {
      //  List<Admin> listEmp = new ArrayList<Admin>();

      //  connect();

     //   PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES");
     //   ResultSet resultSet = preparedStatement.executeQuery();

      //  while (resultSet.next()) {
      //      Admin employee = new Admin(resultSet.getInt(1), resultSet.getString(2),
        //            resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
        //            resultSet.getInt(6));
         //   listEmp.add(employee);
      //  }

       // cleanConnection();

      //  return listEmp;
   // }

   // public Admin getAdminById(int id) throws SQLException, ClassNotFoundException {
     //   Admin employee = new Admin();

       // connect();

        //PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE ID = ?");
      //  preparedStatement.setInt(1, id);
        //ResultSet resultSet = preparedStatement.executeQuery();

     //   if(resultSet.next()) employee = new Admin(resultSet.getInt(1), resultSet.getString(2),
       //         resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
         //       resultSet.getInt(6));

        //cleanConnection();

        //return admin;
    //}

  //  public Admin getAdminByName(String name) throws SQLException, ClassNotFoundException {
    //    Admin admin = new Admin();
//
  //      connect();
//
  //      PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM admin WHERE name LIKE ?");
    //    preparedStatement.setString(1, name);
      //  ResultSet resultSet = preparedStatement.executeQuery();

        //if(resultSet.next()) {
          //  admin = new Admin(resultSet.getString(1), resultSet.getString(2),
            //        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
              //      resultSet.getInt(6));

            //cleanConnection();
        //}

      //  return admin;
    //}

    public void addAdmin( String name, String email, String password)
            throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO admin" +
                "(name, email, password) " +
                "VALUES " +
                "(?, ?, ?)");

        preparedStatement.setNString(1, name);
        preparedStatement.setNString(2, email);
        preparedStatement.setString(3, password);

        preparedStatement.execute();

        cleanConnection();
    }

    public void editAdmin(int id, String name, String email, String password)
            throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE admin " +
                "SET name = ?, email = ?, password = ?, id=?");


        preparedStatement.setNString(1, name);
        preparedStatement.setNString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.setInt(4, id);


        preparedStatement.execute();

        cleanConnection();
    }

    public void deleteAdmin(int id) throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM admin WHERE ID = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        cleanConnection();
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String getParameterByName(String name) {
        HttpServletRequest req = getRequest();
        return req.getParameter(name);
    }
}