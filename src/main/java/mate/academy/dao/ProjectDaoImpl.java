package mate.academy.dao;

import mate.academy.model.Developer;
import mate.academy.model.Project;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "SELECT * FROM projects WHERE id = ?";
        Project project = new Project();
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                project.setId(id);
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getDate("startDate"));
                project.setEndDate(resultSet.getDate("endDate"));
                project.setCost(resultSet.getBigDecimal("cost"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public int getProjectId(Project project) {
        int id = 0;
        String sql = "SELECT id FROM projects WHERE name = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, project.getName());
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                id = (resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Project getProjectByName(String name) {
        return null;
    }

    @Override
    public List<Project> getAllProj() {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try {
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()){
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setStartDate(resultSet.getDate("startDate"));
                project.setEndDate(resultSet.getDate("endDate"));
                project.setCost(resultSet.getBigDecimal("cost"));
                list.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int createProj(Project project) {
        int id = 0;
        String sql = "INSERT INTO projects (name, startDate, endDate, cost) VALUES(?, ?, ?, ?)";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, project.getName());
            prepStatement.setDate(2, project.getStartDate());
            prepStatement.setDate(3, project.getEndDate());
            prepStatement.setBigDecimal(4, project.getCost());
            prepStatement.execute();
            id = getProjectId(project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void updateProj(Project project) {

    }

    @Override
    public void deleteProj(Project project) {

    }

    public List<Developer> getDevelopersOnProject(Project project){
        String sql = "SELECT dev_id FROM devinfo WHERE project_id = ?";
        List<Developer> developers = new ArrayList<>();
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, project.getId());
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                developers.add(new DeveloperDaoImpl(connection)
                        .getDevById(resultSet.getInt("dev_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

}
