package DataAccessLayer;

/**
 * Created by Yasi on 11/8/2016.
 */

public class RealCustomer extends DataAccessCustomer {
    //public Integer customerNumber;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String birthDate;
    private String NationalId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNationalId() {
        return NationalId;
    }

    public RealCustomer(String firstName, String lastName, String fatherName, String birthDate, String nationalId) {
       // this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.NationalId = nationalId;
    }
}
