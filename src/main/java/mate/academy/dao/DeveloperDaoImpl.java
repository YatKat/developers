package mate.academy.dao;

import mate.academy.model.Developer;
import mate.academy.model.Project;
import mate.academy.model.Skills;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Developer getDevByName(String name) {
        String sql = "SELECT * FROM developers WHERE name = ?";
        Developer developer = new Developer();
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, name);
            resultSet = prepStatement.executeQuery();
            return resultSet.next() ? getDev(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    public int createDeveloper(Developer developer) {
        int id = 0;
        String sql = "INSERT INTO developers (name, age, sex, salary) VALUES (?, ?, ?, ?)";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, developer.getName());
            prepStatement.setInt(2, developer.getAge());
            prepStatement.setString(3, developer.getSex().toString());
            prepStatement.setBigDecimal(4, developer.getSalary());
            prepStatement.execute();
           id = getDevIdByName(developer.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void updateDevById(int id, Developer developer) {

    }

    @Override
    public void deleteDevById(int id) {

    }

    @Override
    public void developerAddProject(int dev_id, int proj_id) {
        String sql = "INSERT INTO developers_projects(dev_id, proj_id) VALUES(?,?)";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, dev_id);
            prepStatement.setInt(2, proj_id);
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void developerAddSkill(int dev_id, int skill_id) {
        String sql = "INSERT INTO developer_skills(skill_id, developer_id) VALUES(?,?)";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1,skill_id);
            prepStatement.setInt(2, dev_id);
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    public Set<Skills> getSetOfSkills (Developer developer){
        Set<Skills> skills = new HashSet<>();
        int id = getDevIdByName(developer.getName());
        String sql = "SELECT skills_id FROM devinfo WHERE dev_id = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                skills.add(new SkillsDaoIml(connection)
                        .getSkillsById(resultSet.getInt("skills_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    public Set<Project> getSetOfProjects (Developer developer){
        Set<Project> projects = new HashSet<>();
        int id = getDevIdByName(developer.getName());
        String sql = "SELECT projects_id FROM devinfo WHERE dev_id = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                projects.add(new ProjectDaoImpl(connection)
                        .getProjectById(resultSet.getInt("project_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    // get the sum of developers' salary on defined project

    public int getSumOfDevSalaryByProject(int project_id){
        int sumOfSalary = 0;
        String sql = "SELECT SUM(salary) AS maxProject FROM developers" +
                " INNER JOIN developers_projects AS dp ON dp.dev_id = developers.id WHERE dp.proj_id = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, project_id);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                sumOfSalary = resultSet.getInt("maxProject");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sumOfSalary;
    }

   public List<String> getAllDevsOnProject(int proj_id){
        List<String> developersOnProjectList = new ArrayList<>();
String sql = "SELECT name AS devsOnProject FROM developers INNER JOIN " +
        "developers_projects AS dp ON dp.dev_id = developers.id WHERE dp.proj_id = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, proj_id);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                developersOnProjectList.add(resultSet.getString("devsOnProject"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developersOnProjectList;
    }

    public List<String> getAllDevelopersByLanguage(String language){
        List<String> list = new ArrayList<>();
        String sql = " SELECT name AS listOfDevs FROM developers INNER JOIN developer_skills AS ds ON ds.developer_id = developers.id " +
                "INNER JOIN skills ON ds.skill_id = skills.id WHERE skills.language = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, language);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                list.add(resultSet.getString("listOfDevs"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getAllDevelopersByLevel(String level){
        List<String> list = new ArrayList<>();
        String sql = " SELECT name AS listOfDevs FROM developers INNER JOIN developer_skills AS ds ON ds.developer_id = developers.id " +
                "INNER JOIN skills ON ds.skill_id = skills.id WHERE skills.level = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, level);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                list.add(resultSet.getString("listOfDevs"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }





}
