package BusinessLogic;

import DataAccess.*;

import java.util.List;

/**
 * Created by Yasi on 11/8/2016.
 */
public class BusinessLogic {

    //*****REAL DataAccessCustomer*******//
    public static RealCustomer addRealCustomerBiz(RealCustomer realCustomer) throws ConflictInDataException {
        if(RealCustomerCRUD.checkRedundantData(realCustomer.getNationalId())){
            return RealCustomerCRUD.addRealCustomerDataAccess(realCustomer);
        }
        else throw new ConflictInDataException("رکوردی با کد ملی مشابه وجود دارد.");
    }

    public static List<RealCustomer> searchRealCustomerBiz(RealCustomer realCustomer){
        return RealCustomerCRUD.searchIn(realCustomer);
    }

    public static RealCustomer getRealCustomerBiz(String id){
        return RealCustomerCRUD.searchById(id,"customerNumber");
    }

    public static boolean deleteRealCustomerBiz(Integer customerNumber){
        return DataAccessCustomer.deleteCustomer(customerNumber);
    }

    public static RealCustomer updateRealCustomerBiz(RealCustomer realCustomer) throws ConflictInDataException {
        return RealCustomerCRUD.updateRealCustomer(realCustomer);
       /*
        if(RealCustomerCRUD.checkRedundantData(realCustomer.getNationalId())){
            return RealCustomerCRUD.updateRealCustomer(realCustomer);
        }
        else throw new ConflictInDataException("رکوردی با کد ملی مشابه وجود دارد.");
        */
    }

    //**LEGAL CUSTOMER***///
    public static LegalCustomer addLegalCustomerBiz(LegalCustomer legalCustomer)throws ConflictInDataException{
        if(LegalCustomerCRUD.checkRedundantData(legalCustomer.getEconomicalCode())){
            return LegalCustomerCRUD.addLegalCustomerDataAccess(legalCustomer);
        }
        else throw new ConflictInDataException("رکوردی با کد اقتصادی مشابه وجود دارد.");
    }

    public static List<LegalCustomer> searchLegalCustomerBiz(LegalCustomer legalCustomer){
        return LegalCustomerCRUD.searchIn(legalCustomer);
    }

    public static LegalCustomer getLegalCustomerBiz(String id){
        return LegalCustomerCRUD.getCustomerById(id,"customerNumber");
    }

    public static LegalCustomer updateLegalCustomerBiz(LegalCustomer legalCustomer){
        return LegalCustomerCRUD.updateLegalCustomer(legalCustomer);
    }

    public static boolean deleteLegalCustomerBiz(Integer customerNumber){
        return DataAccessCustomer.deleteCustomer(customerNumber);
    }


}
