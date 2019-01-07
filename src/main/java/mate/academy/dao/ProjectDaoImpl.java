package mate.academy.dao;

import mate.academy.model.Project;

import java.sql.*;
import java.util.List;

public class ProjectDaoImpl extends AbstractDao implements ProjectDao {
    private Statement statement;
    private PreparedStatement prepStatement;
    private ResultSet resultSet;

    public ProjectDaoImpl(Connection connection){
        super(connection);
    }

    public void addColumnInProj (String cost){
        String sql = "ALTER TABLE projects ADD " + cost + " int (11)";
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project getProjectById(int id) {
        return null;
    }

    @Override
    public int getProjectId(Project project) {
        return 0;
    }

    @Override
    public Project getProjectByName(String name) {
        return null;
    }

    @Override
    public List<Project> getAllProj() {
        return null;
    }

    @Override
    public boolean createProj(Project project) {
        String sql = "INSERT INTO projects (name, startDate, endDate, cost) VALUES(?, ?, ?, ?)";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, project.getName());
            prepStatement.setString(2, project.getStartDate().toString());
            prepStatement.setString(3, project.getEndDate().toString());
            prepStatement.setBigDecimal(4, project.getCost());
            return prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateProj(Project project) {

    }

    @Override
    public void deleteProj(Project project) {

    }
}
