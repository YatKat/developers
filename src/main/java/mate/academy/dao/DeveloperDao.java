package mate.academy.dao;

import mate.academy.model.Developer;

import java.util.List;

public interface DeveloperDao {
    void addColumnInDev(String columnName);

    int getDevIdByName(String name);

    Developer getDevById(int id);

    List<Developer> getAll();

    boolean createDeveloper(Developer developer);

    void updateDevById(int id);

    void deleteDevById(int id);
}
