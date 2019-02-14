package mate.academy.dao;

import mate.academy.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDaoImpl extends AbstractDao implements CompanyDao {
    private PreparedStatement prepStatement;
    private ResultSet resultSet;

    protected CompanyDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Company getCompanyById(int id) {
        Company company = new Company();
        String sql = "SELECT name, employees FROM companies WHERE id = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                company.setId(id);
                company.setName(resultSet.getString("name"));
                company.setEmployees(resultSet.getInt("employees"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }
}
