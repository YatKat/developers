package mate.academy.dao;

import mate.academy.model.Developer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl extends AbstractDao implements DeveloperDao {
    private Statement statement;
    private PreparedStatement prepStatement;
    private ResultSet resultSet;

    public DeveloperDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void addColumnInDev(String columnName) {
        String sql = "ALTER TABLE developers ADD " + columnName + " int (11)";
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getDevIdByName(String name) {
        String sql = "SELECT id FROM developers WHERE name = ?";
        int id = 0;
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, name);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Developer getDevById(int id) {
        String sql = "SELECT * FROM developers WHERE id = ?";
        Developer developer = new Developer();
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();
            return resultSet.next() ? getDev(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Developer> getAll() {
        String sql = "SELECT * FROM developers";
        List<Developer> list = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(getDev(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean createDeveloper(Developer developer) {
        String sql = "INSERT INTO developers (name, age, sex, salary) VALUES (?, ?, ?, ?)";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, developer.getName());
            prepStatement.setInt(2, developer.getAge());
            prepStatement.setString(3, developer.getSex().toString());
            prepStatement.setBigDecimal(4, developer.getSalary());
            return prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateDevById(int id) {

    }

    @Override
    public void deleteDevById(int id) {

    }

    private Developer getDev(ResultSet resultSet) {
        Developer developer = new Developer();
        try {
            developer.setId(resultSet.getInt("id"));
            developer.setName(resultSet.getString("name"));
            developer.setAge(resultSet.getInt("age"));
            String sex = resultSet.getString("sex");
            if (sex.equals("FEMALE")) {
                developer.setSex(Developer.Sex.FEMALE);
            } else developer.setSex(Developer.Sex.MALE);
            developer.setSalary(resultSet.getBigDecimal("salary"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }
}
