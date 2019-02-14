package mate.academy.service;

import mate.academy.dao.DeveloperDao;
import mate.academy.dao.DeveloperDaoImpl;
import mate.academy.dao.ProjectDao;
import mate.academy.dao.ProjectDaoImpl;
import mate.academy.dao.SkillsDao;
import mate.academy.model.Developer;
import mate.academy.model.Project;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperDao developerDao;
    private final ProjectDao projectDao;
    private final SkillsDao skillsDao;

    public DeveloperServiceImpl(DeveloperDao developerDao, ProjectDao projectDao, SkillsDao skillsDao) {
        this.developerDao = developerDao;
        this.projectDao = projectDao;
        this.skillsDao = skillsDao;
    }

    @Override
    public int createDeveloper(Developer developer) {
        int dev_id = developerDao.createDeveloper(developer);
        int proj_id = projectDao.createProj(developer.getProjects().get(developer.getProjects().size() - 1));
        int skill_id = skillsDao.createSkill(developer.getSkills().get(developer.getSkills().size()-1));
        System.out.println(dev_id+"-----------"+proj_id+"----------"+skill_id);
        developerDao.developerAddProject(dev_id, proj_id);
        developerDao.developerAddSkill(dev_id, skill_id);
        return dev_id;
    }

    @Override
    public BigDecimal getSalaryByProject(ProjectDaoImpl projectDao, Project project) {
        List<Developer> allDevsOnProject = getDevsListByProject(projectDao, project);
        BigDecimal sumOfSalary = new BigDecimal(0);
        allDevsOnProject.stream()
                .map(Developer::getSalary).forEach(s -> sumOfSalary.plus());
        return sumOfSalary;
    }

    @Override
    public List<Developer> getDevsListByProject(ProjectDaoImpl projDao, Project project) {
        List<Developer> developers = projDao.getDevelopersOnProject(project);
        return developers;
    }

    @Override
    public List<Developer> getDevsListByLanguage(String language, DeveloperDaoImpl developerDao) {
        List<Developer> developers = developerDao.getAll();
        developerDao.getSetOfSkills(developers.get(0));
        return null;
    }

    @Override
    public List<Developer> getDevsListByLevel(String level) {
        return null;
    }

    @Override
    public List<Project> getProjListWithDevs() {
        List<Project> list = projectDao.getAllProj();
        //list.forEach(System.out::println);
        for (Project proj : list) {
            int x = proj.getId();
            List<String> listOfNames = developerDao.getAllDevsOnProject(x);
            List<Developer> listOfDevs = new ArrayList<>();
            //listOfNames.forEach(System.out::println);
            for (String s : listOfNames) {
                Developer developer = developerDao.getDevByName(s);
                listOfDevs.add(developer);
            }
            proj.setDevelopers(listOfDevs);
            //System.out.println(proj.toString());
        }
        return list;
    }
}
