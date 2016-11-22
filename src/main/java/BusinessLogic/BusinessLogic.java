package BusinessLogic;

import DataAccessLayer.*;

import java.util.List;

/**
 * Created by Yasi on 11/8/2016.
 */
public class BusinessLogic {

    //*****REAL DataAccessCustomer*******//
    public static RealCustomer addRealCustomerBiz(RealCustomer realCustomer) throws ConflictInDataException {
        if(DataAccessRealCustomer.checkRedundantData(realCustomer.getNationalId())){
            return DataAccessRealCustomer.addRealCustomerDataAccess(realCustomer);
        }
        else throw new ConflictInDataException("رکوردی با کد ملی مشابه وجود دارد.");
    }

    public static List<RealCustomer> searchRealCustomerBiz(RealCustomer realCustomer){
        return DataAccessRealCustomer.searchIn(realCustomer);
    }

    public static RealCustomer getRealCustomerBiz(String id){
        return DataAccessRealCustomer.searchById(id,"customerNumber");
    }

    public static boolean deleteRealCustomerBiz(RealCustomer realCustomer){
        return DataAccessRealCustomer.deleteRealCustomer(realCustomer);
    }

    public static RealCustomer updateRealCustomerBiz(RealCustomer realCustomer) throws ConflictInDataException {
        return DataAccessRealCustomer.updateRealCustomer(realCustomer);
       /*
        if(DataAccessRealCustomer.checkRedundantData(realCustomer.getNationalId())){
            return DataAccessRealCustomer.updateRealCustomer(realCustomer);
        }
        else throw new ConflictInDataException("رکوردی با کد ملی مشابه وجود دارد.");
        */
    }

    //**LEGAL CUSTOMER***///
    public static LegalCustomer addLegalCustomerBiz(LegalCustomer legalCustomer)throws ConflictInDataException{
        if(DataAccessLegalCustomer.checkRedundantData(legalCustomer.getEconomicalCode())){
            return DataAccessLegalCustomer.addLegalCustomerDataAccess(legalCustomer);
        }
        else throw new ConflictInDataException("رکوردی با کد اقتصادی مشابه وجود دارد.");
    }

    public static List<LegalCustomer> searchLegalCustomerBiz(LegalCustomer legalCustomer){
        return DataAccessLegalCustomer.searchIn(legalCustomer);
    }

    public static LegalCustomer getLegalCustomerBiz(String id){
        return DataAccessLegalCustomer.getCustomerById(id,"customerNumber");
    }

    public static LegalCustomer updateLegalCustomerBiz(LegalCustomer legalCustomer){
        return DataAccessLegalCustomer.updateLegalCustomer(legalCustomer);
    }

    public static boolean deleteLegalCustomerBiz(LegalCustomer legalCustomer){
        DataAccessCustomer.deleteCustomer(legalCustomer.getCustomerNumber());
        return DataAccessLegalCustomer.deleteLegalCustomer(legalCustomer);
    }


}
