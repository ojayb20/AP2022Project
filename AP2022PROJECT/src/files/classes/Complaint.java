package files.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complaint_table")
public class Complaint {
    @Id
    String complaintID;
    String username;
    String firstName;
    String lastName;
    String email;
    String contactNumber;
    String type;
    String complaint;
    String assigner;
    String assignee;
    String response;
    String responseDate;
    String resolved;

    public Complaint(String complaintID, String username, String firstName, String lastName, String email, String contactNumber, String type, String complaint, String assigner, String assignee, String response, String responseDate, String resolved) {
        this.complaintID = complaintID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.type = type;
        this.complaint = complaint;
        this.assigner = assigner;
        this.assignee = assignee;
        this.response = response;
        this.responseDate = responseDate;
        this.resolved = resolved;
    }

    public Complaint(String complaintID, String type, String complaint, String response, String responseDate, String assignee) {
        this.complaintID = complaintID;
        this.type = type;
        this.complaint = complaint;
        this.response = response;
        this.responseDate = responseDate;
        this.assignee = assignee;
    }

    public String getComplaintID() {
        return complaintID;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getComplaint() {
        return complaint;
    }


    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getAssigner() {
        return assigner;
    }

    public String getAssignee() {
        return assignee;
    }


    public String getResponse() {
        return response;
    }


    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public String getResolved() {
        return resolved;
    }
}
