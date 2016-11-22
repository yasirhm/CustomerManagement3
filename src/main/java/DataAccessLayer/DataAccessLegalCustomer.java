package DataAccessLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

/**
 * Created by Yasi on 11/21/2016.
 */
public class DataAccessLegalCustomer {
    private static Random random = new Random();
   // private static LegalCustomer legalCustomer;

    public static boolean checkRedundantData(String economicalCode) {
        if (null == getCustomerById(economicalCode, "economicalCode")) {
            return true;
        } else return false;
    }

    public static LegalCustomer getCustomerById(String id, String Column) {
        LegalCustomer legalCustomer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            if (Column.equals("economicalCode"))
                preparedStatement = connection.prepareStatement("SELECT * FROM legalcustomer WHERE ECONOMICAL_Code= ?");
            else if (Column.equals("customerNumber"))
                preparedStatement = connection.prepareStatement("SELECT * FROM legalcustomer WHERE CUSTOMER_NUMBER= ?");

            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null)
                while (resultSet.next()) {
                    legalCustomer = new LegalCustomer(resultSet.getString("NAME"), resultSet.getString("REGISTRATION_DATE"), resultSet.getString("ECONOMICAL_Code"));
                    legalCustomer.setCustomerNumber(resultSet.getInt("CUSTOMER_NUMBER"));
                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return legalCustomer;
    }

    public static LegalCustomer addLegalCustomerDataAccess(LegalCustomer legalCustomer) {
      //  DataAccessLegalCustomer.legalCustomer = legalCustomer;
        Integer customerNumber = legalCustomer.insert();
        if (customerNumber != null){
            legalCustomer.setCustomerNumber(customerNumber);
            insertLegal(legalCustomer);
            return legalCustomer;
        }
        else return null;
    }

    public static void insertLegal(LegalCustomer legalCustomer){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO legalcustomer (CUSTOMER_NUMBER,NAME,ECONOMICAL_Code,REGISTRATION_DATE) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, legalCustomer.getCustomerNumber());
            preparedStatement.setString(2,legalCustomer.getName());
            preparedStatement.setString(3,legalCustomer.getEconomicalCode());
            preparedStatement.setString(4,legalCustomer.getRegistrationDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<LegalCustomer> searchIn(LegalCustomer legalCustomer) {
        List<LegalCustomer> legalCustomerList = new ArrayList<LegalCustomer>();
        search(legalCustomerList, legalCustomer);
        return legalCustomerList;
    }

    private static List<LegalCustomer> search(List<LegalCustomer> legalCustomerList, LegalCustomer legalCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            StringBuilder stmt = new StringBuilder("SELECT * FROM legalcustomer WHERE 1=1 ");
            if (legalCustomer.getName() != "")
                stmt.append(" AND NAME = '" + legalCustomer.getName() + "'");

            if (legalCustomer.getEconomicalCode() != "")
                stmt.append(" AND ECONOMICAL_Code = '" + legalCustomer.getEconomicalCode()  + "'");

            if (legalCustomer.getCustomerNumber() != null)
                stmt.append(" AND CUSTOMER_NUMBER = '" + legalCustomer.getCustomerNumber().toString() + "'");

            if (legalCustomer.getRegistrationDate() != "")
                stmt.append(" AND REGISTRATION_DATE = '" + legalCustomer.getRegistrationDate() + "'");
            stmt.append(" ;");
            preparedStatement = connection.prepareStatement(stmt.toString());
            System.out.println("STATE: " + stmt.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null)
                while (resultSet.next()) {
                    LegalCustomer resLegalCustomer = new LegalCustomer(resultSet.getString("NAME"), resultSet.getString("REGISTRATION_DATE"), resultSet.getString("ECONOMICAL_Code"));
                    resLegalCustomer.setCustomerNumber(parseInt(resultSet.getString("CUSTOMER_NUMBER")));//TO DO :getInt
                    legalCustomerList.add(resLegalCustomer);
                }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return legalCustomerList;
    }

    public static LegalCustomer updateLegalCustomer(LegalCustomer legalCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE legalcustomer SET NAME = ?, REGISTRATION_DATE =? WHERE CUSTOMER_NUMBER = ?");

            preparedStatement.setString(1, legalCustomer.getName());
            preparedStatement.setString(2, legalCustomer.getRegistrationDate());
            preparedStatement.setInt(3, legalCustomer.getCustomerNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return legalCustomer ;
    }

    public static boolean deleteLegalCustomer(LegalCustomer legalCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM legalcustomer WHERE CUSTOMER_NUMBER = ?");
            preparedStatement.setInt(1, legalCustomer.getCustomerNumber());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
