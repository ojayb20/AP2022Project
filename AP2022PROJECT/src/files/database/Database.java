package files.database;

import java.sql.*;

public class Database {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static int response;
    private static void makeConnection() throws SQLException {
         connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root", "");
    }

    private static void closeAllConnections() throws SQLException {
        connection.close();
        preparedStatement.close();
    }
    public static int registration(String firstName, String lastName, String username, String password, String email, String contact_number, String type) throws SQLException {
        makeConnection();
        String query = "INSERT INTO registration_info (First_Name, Last_Name, Username, Password, Email_Address, Contact_Number, Type) VALUES(?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, username);
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, email);
        preparedStatement.setString(6, contact_number);
        preparedStatement.setString(7, type);
        response = preparedStatement.executeUpdate();
        closeAllConnections();
        return response;
    }

    public static ResultSet getLogin(String username) throws SQLException {
        makeConnection();
        preparedStatement = connection.prepareStatement("SELECT Username, Password, Type FROM registration_info WHERE Username=?");
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static ResultSet getUsername(String username) throws SQLException{
        makeConnection();
        preparedStatement = connection.prepareStatement("SELECT Username FROM registration_info WHERE Username=?");
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static int updatePassword(String username, String password) throws SQLException {
        makeConnection();
        preparedStatement = connection.prepareStatement("UPDATE registration_info SET Password=? WHERE Username=?");
        preparedStatement.setString(1, password);
        preparedStatement.setString(2, username);
        response = preparedStatement.executeUpdate();
        closeAllConnections();
        return response;
    }

    public static int registerComplaint(String username, String firstName, String lastName, String email, String contact_number, String type, String complaint) throws SQLException {
        makeConnection();
        String query = "INSERT INTO complaint_table (Username, First_Name, Last_Name, Email_Address, Contact_Number, Type, Complaint) VALUES(?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, contact_number);
        preparedStatement.setString(6, type);
        preparedStatement.setString(7, complaint);
        response = preparedStatement.executeUpdate();
        closeAllConnections();
        return response;
    }

    public static ResultSet customersComplaints(String username) throws SQLException {
        makeConnection();
        String query = "SELECT ComplaintID, Type, Complaint, Response, Response_Date, Assignee FROM complaint_table WHERE Username=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static int getAmountOfResolvedComplaints() throws SQLException {
        makeConnection();
        String query = "SELECT COUNT(*) FROM complaint_table WHERE Resolved=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "Yes");
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        response = resultSet.getInt(1);
        closeAllConnections();
        return response;
    }

    public static int getAmountOfOutstandingComplaints() throws SQLException {
        makeConnection();
        String query = "SELECT COUNT(*) FROM complaint_table WHERE Resolved IS NULL";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        response = resultSet.getInt(1);
        closeAllConnections();
        return response;
    }

    public static ResultSet getAllComplaints() throws SQLException {
        makeConnection();
        String query = "SELECT * FROM complaint_table";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static ResultSet getTechnicians() throws SQLException {
        makeConnection();
        String query = "SELECT Username FROM registration_info WHERE Type=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "Technician");
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static int assignTechnician(String id, String assigner, String assignee) throws SQLException {
        makeConnection();
        preparedStatement = connection.prepareStatement("UPDATE complaint_table SET Assigner=?, Assignee=? WHERE ComplaintID=?");
        preparedStatement.setString(1, assigner);
        preparedStatement.setString(2, assignee);
        preparedStatement.setString(3, id);
        response = preparedStatement.executeUpdate();
        closeAllConnections();
        return response;

    }

    public static int updateResponse(String id, String technicianResponse, String responseDate) throws SQLException {
        makeConnection();
        preparedStatement = connection.prepareStatement("UPDATE complaint_table SET Response=?, Response_Date=? WHERE ComplaintID=?");
        preparedStatement.setString(1, technicianResponse);
        preparedStatement.setString(2, responseDate);
        preparedStatement.setString(3, id);
        response = preparedStatement.executeUpdate();
        closeAllConnections();
        return response;
    }

    public static ResultSet getComplaintsForTechnician(String username) throws SQLException {
        makeConnection();
        String query = "SELECT * FROM complaint_table WHERE Assignee=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static int updateResolved(String id) throws SQLException{
        makeConnection();
        preparedStatement = connection.prepareStatement("UPDATE complaint_table SET Resolved=? WHERE ComplaintID=?");
        preparedStatement.setString(1, "Yes");
        preparedStatement.setString(2, id);
        response = preparedStatement.executeUpdate();
        closeAllConnections();
        return response;
    }

    public static ResultSet getCustomerInformation(String username) throws SQLException {
        makeConnection();
        String query = "SELECT First_Name, Last_Name, Email_Address, Contact_Number FROM registration_info WHERE Username=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
