package DataAccessLayer;

/**
 * Created by Yasi on 11/8/2016.
 */
public class LegalCustomer extends DataAccessCustomer {
   // public Integer customerNumber;
    public String name;
    private String registrationDate;
    private String economicalCode;

    public LegalCustomer(String name, String registrationDate, String economicalCode) {
        //this.customerNumber = customerNumber;
        this.name = name;
        this.registrationDate = registrationDate;
        this.economicalCode = economicalCode;
    }
   /* public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }
*/
    public String getName() {
        return name;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getEconomicalCode() {
        return economicalCode;
    }
}
