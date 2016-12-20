package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yasi on 11/21/2016.
 */
public abstract class DataAccessCustomer {

    public Integer customerNumber;

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public static Integer insert(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer lastCustomer=null;
        try {
            connection = ConnectionConfiguration.getConnection();
            lastCustomer = getLastCustomerNumber()+1;
            preparedStatement = connection.prepareStatement("INSERT INTO customers (CUSTOMER_NUMBER) VALUES (?)");
            preparedStatement.setInt(1, lastCustomer);
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
        return lastCustomer;
    }

    public static Integer getLastCustomerNumber(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer lastCustomer = 1000;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("select CUSTOMER_NUMBER from customers where id=(select max(id) from customers)");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                lastCustomer = resultSet.getInt("CUSTOMER_NUMBER");
            }
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
        return lastCustomer;
    }

    public static boolean deleteCustomer(Integer customerNumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE CUSTOMER_NUMBER = ?");
            preparedStatement.setInt(1, customerNumber);
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
