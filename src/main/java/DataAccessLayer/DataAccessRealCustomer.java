package DataAccessLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

/**
 * Created by Yasi on 11/8/2016.
 */
public class DataAccessRealCustomer {
    //private static HashMap<Integer, RealCustomer> realCustomers = new HashMap<Integer, RealCustomer>();
    private static Random random = new Random();

    public static boolean checkRedundantData(String nationalId) {
        if (searchById(nationalId, "nationalId") == null) {
            return true;
        } else return false;
    }

    public static RealCustomer addRealCustomerDataAccess(RealCustomer realCustomer) {
        /*Integer customerNumber = 0;
        while (customerNumber <= 0) {
            customerNumber = random.nextInt();
            if (customerNumber > 0)
                if (searchById(customerNumber.toString(), "customerNumber") != null) {
                    customerNumber = -1;
                }
        }*/
        Integer customerNumber = realCustomer.insert();
        if (customerNumber != null){
            realCustomer.setCustomerNumber(customerNumber);
            insertReal(realCustomer);
            return realCustomer;
        }
        else return null;

    }

    public static List<RealCustomer> searchIn(RealCustomer realCustomer) {
        List<RealCustomer> realCustomers = new ArrayList<RealCustomer>();
        search(realCustomers, realCustomer);
        return realCustomers;
    }
/*
    public static void printOut(String id) {
        System.out.println("We are HEREEEE...Seee Us");
        for (Integer number : realCustomers.keySet()) {
            String key = number.toString();
            System.out.println(key + " ");
        }
    }*/

    public static void insertReal(RealCustomer realCustomer) {
        //TO DO : return just customer number and override parent insert

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO realcustomer " +
                    "(CUSTOMER_NUMBER,FIRST_NAME,LAST_NAME,FATHER_NAME,NATIONAL_ID,BIRTH_DATE) VALUES (?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, realCustomer.getCustomerNumber());
            preparedStatement.setString(2, realCustomer.getFirstName());
            preparedStatement.setString(3, realCustomer.getLastName());
            preparedStatement.setString(4, realCustomer.getFatherName());
            preparedStatement.setString(5, realCustomer.getNationalId());
            preparedStatement.setString(6, realCustomer.getBirthDate());
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

    public static RealCustomer searchById(String id, String type) {
        RealCustomer realCustomer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            if (type.equals("nationalId"))
                preparedStatement = connection.prepareStatement("SELECT * FROM realcustomer WHERE NATIONAL_ID= ?");
            else if (type.equals("customerNumber"))
                preparedStatement = connection.prepareStatement("SELECT * FROM realcustomer WHERE CUSTOMER_NUMBER= ?");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null)
                while (resultSet.next()) {
                    realCustomer = new RealCustomer(resultSet.getString("FIRST_NAME"), resultSet.getString("LAST_NAME"),
                            resultSet.getString("FATHER_NAME"), resultSet.getString("BIRTH_DATE"), resultSet.getString("NATIONAL_ID"));
                    realCustomer.setCustomerNumber(resultSet.getInt("CUSTOMER_NUMBER"));
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
        return realCustomer;
    }

    private static List<RealCustomer> search(List<RealCustomer> realCustomers, RealCustomer realCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            StringBuilder stmt = new StringBuilder("SELECT * FROM realcustomer WHERE 1=1 ");
            if (realCustomer.getFirstName() != "")
                stmt.append(" AND FIRST_NAME = '" + realCustomer.getFirstName() + "'");

            if (realCustomer.getLastName() != "")
                stmt.append(" AND LAST_NAME = '" + realCustomer.getLastName() + "'");

            if (realCustomer.getCustomerNumber() != null)
                stmt.append(" AND CUSTOMER_NUMBER = '" + realCustomer.getCustomerNumber().toString() + "'");

            if (realCustomer.getNationalId() != "")
                stmt.append(" AND NATIONAL_ID = '" + realCustomer.getNationalId() + "'");

            stmt.append(" ;");
            preparedStatement = connection.prepareStatement(stmt.toString());
            System.out.println("STATE: " + stmt.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null)
                while (resultSet.next()) {
                    RealCustomer resRealCustomer = new RealCustomer(resultSet.getString("FIRST_NAME"), resultSet.getString("LAST_NAME"),
                            resultSet.getString("FATHER_NAME"), resultSet.getString("BIRTH_DATE"), resultSet.getString("NATIONAL_ID"));
                    resRealCustomer.setCustomerNumber(parseInt(resultSet.getString("CUSTOMER_NUMBER")));//TO DO :getInt
                    realCustomers.add(resRealCustomer);
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
        return realCustomers;
    }

    public static RealCustomer updateRealCustomer(RealCustomer realCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE realcustomer SET " +
                    "FIRST_NAME = ?, LAST_NAME =?, FATHER_NAME =?,BIRTH_DATE =?" +
                    "WHERE CUSTOMER_NUMBER = ?");

            preparedStatement.setString(1, realCustomer.getFirstName());
            preparedStatement.setString(2, realCustomer.getLastName());
            preparedStatement.setString(3, realCustomer.getFatherName());
            preparedStatement.setString(4, realCustomer.getBirthDate());
            preparedStatement.setInt(5, realCustomer.getCustomerNumber());
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
        return realCustomer;
    }

    public static boolean deleteRealCustomer(RealCustomer realCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM realcustomer WHERE CUSTOMER_NUMBER = ?");
            preparedStatement.setInt(1, realCustomer.getCustomerNumber());
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM realcustomer WHERE id = ?");

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
