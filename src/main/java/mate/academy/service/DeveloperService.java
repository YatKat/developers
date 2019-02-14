package mate.academy.service;

import mate.academy.dao.DeveloperDaoImpl;
import mate.academy.dao.ProjectDao;
import mate.academy.dao.ProjectDaoImpl;
import mate.academy.model.Developer;
import mate.academy.model.Project;

import java.math.BigDecimal;
import java.util.List;

public interface DeveloperService {

    int createDeveloper(Developer developer);

    BigDecimal getSalaryByProject(ProjectDaoImpl projDao, Project project);

    List<Developer> getDevsListByProject(ProjectDaoImpl projDao, Project project);

    List<Developer> getDevsListByLanguage(String language, DeveloperDaoImpl developerDao);

    List<Developer> getDevsListByLevel(String level);

    List<Project> getProjListWithDevs();


}
