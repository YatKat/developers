package mate.academy.dao;

import mate.academy.model.Skills;

import java.sql.*;

public class SkillsDaoIml extends AbstractDao implements SkillsDao{
    private PreparedStatement prepStatement;
    private ResultSet resultSet;

    public SkillsDaoIml(Connection connection){
        super(connection);
    }


    @Override
    public Skills getSkillsById(int id) {
        String sql = "SELECT language, level FROM skills WHERE id = ?";
        Skills skill = new Skills();
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                skill.setId(id);
                skill.setLanguage(resultSet.getString("language"));
                skill.setLevel(resultSet.getString("level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public int createSkill(Skills skills) {
        int id = 0;
        String sql = "INSERT INTO skills (language, level) VALUES (?,?)";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, skills.getLanguage());
            prepStatement.setString(2, skills.getLevel());
            prepStatement.execute();
            id = getSkillIdByLangAndLevel(skills.getLanguage(), skills.getLevel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getSkillIdByLangAndLevel(String lang, String level){
        int id = 0;
        String sql = "SELECT id FROM skills WHERE language = ? AND level = ?";
        try {
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, lang);
            prepStatement.setString(2, level);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
               id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
