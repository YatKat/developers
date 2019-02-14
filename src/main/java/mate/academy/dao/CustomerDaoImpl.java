package mate.academy.dao;

import mate.academy.model.Company;
import mate.academy.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl extends AbstractDao implements CustomerDao {
    private PreparedStatement prepStatement;
    private ResultSet resultSet;

    protected CustomerDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = new Customer();
        String sql = "SELECT name, branch FROM customers WHERE id = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                customer.setId(id);
                customer.setName(resultSet.getString("name"));
                customer.setBranch(resultSet.getString("branch"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
