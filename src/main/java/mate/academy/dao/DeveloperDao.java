package mate.academy.dao;

import mate.academy.model.Developer;
import mate.academy.model.Project;
import mate.academy.model.Skills;

import java.util.List;

public interface DeveloperDao {
    void addColumnInDev(String columnName);

    int getDevIdByName(String name);

    Developer getDevByName(String name);

    Developer getDevById(int id);

    List<Developer> getAll();

    int createDeveloper(Developer developer);

    void updateDevById(int id, Developer developer);

    void deleteDevById(int id);

    void developerAddProject(int dev_id, int proj_id);

    void developerAddSkill(int dev_id, int skill_id);

    int getSumOfDevSalaryByProject(int project_id);

    List<String> getAllDevsOnProject(int proj_id);

    List<String> getAllDevelopersByLanguage(String language);

    List<String> getAllDevelopersByLevel(String level);
}
