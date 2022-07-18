package files.regularclass;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registration_info")
public class User {
    @Id
    String ID;
    String First_Name;
    String Last_Name;
    String Username;
    String Password;
    String Email_Address;
    String Contact_Number;
    String Type;

    public User(String ID, String first_Name, String last_Name, String username, String password, String email_Address, String contact_Number, String type) {
        this.ID = ID;
        First_Name = first_Name;
        Last_Name = last_Name;
        Username = username;
        Password = password;
        Email_Address = email_Address;
        Contact_Number = contact_Number;
        Type = type;
    }

    public User(String Username){
        this.Username = Username;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail_Address() {
        return Email_Address;
    }

    public void setEmail_Address(String email_Address) {
        Email_Address = email_Address;
    }

    public String getContact_Number() {
        return Contact_Number;
    }

    public void setContact_Number(String contact_Number) {
        Contact_Number = contact_Number;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
