import mate.academy.ConnectionUtil;
import mate.academy.dao.DeveloperDao;
import mate.academy.dao.DeveloperDaoImpl;
import mate.academy.dao.ProjectDao;
import mate.academy.dao.ProjectDaoImpl;
import mate.academy.dao.SkillsDao;
import mate.academy.dao.SkillsDaoIml;
import mate.academy.model.Developer;
import mate.academy.model.Project;
import mate.academy.model.Skills;
import mate.academy.service.DeveloperService;
import mate.academy.service.DeveloperServiceImpl;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeveloperDao developerDao = new DeveloperDaoImpl(ConnectionUtil.getConnection());
        ProjectDao projectDao = new ProjectDaoImpl(ConnectionUtil.getConnection());
        SkillsDao skillsDao = new SkillsDaoIml(ConnectionUtil.getConnection());
        DeveloperService developerService = new DeveloperServiceImpl(developerDao, projectDao, skillsDao);
        //was ran only one time to add new column "salary" in developer database
        //developerDao.addColumnInDev("salary");
        //((ProjectDaoImpl) projectDao).addColumnInProj("cost");

        ArrayList<Project> projList = new ArrayList<>();
        ArrayList<Skills> skills = new ArrayList<>();
        //create Skills
        Skills skill = new Skills("JAVA", "SENIOR");
        skills.add(skill);
        //create Project
        Project project = new Project("Utility56", new Date(2003,12,05), new Date(2020,10,13), new BigDecimal(230000));
        projList.add(project);
        //create Developer
        Developer developer = new Developer();
        developer.setName("Crash4");
        developer.setAge(26);
        developer.setSalary(new BigDecimal(1200));
        developer.setSex(Developer.Sex.MALE);
        developer.setProjects(projList);
        developer.setSkills(skills);
        //developerService.createDeveloper(developer);
//       List<Developer> list =  developerDao.getAll();
//       list.forEach(System.out::println);
       // return sum of salary of developers on defined project
        System.out.println("Sum of dev's salary on project: "+developerDao.getSumOfDevSalaryByProject(5));
        System.out.println("--------------------------------------------");
        System.out.println("Developers list on defined project: "+developerDao.getAllDevsOnProject(1));
        System.out.println("--------------------------------------------");
        System.out.println("List of Java developers: "+ developerDao.getAllDevelopersByLanguage("JAVA"));
        System.out.println("--------------------------------------------");
        System.out.println("List of MIDDLE developers: "+ developerDao.getAllDevelopersByLevel("MIDDLE"));
        System.out.println("--------------------------------------------");
        List<Project> listOfProjects = developerService.getProjListWithDevs();
        for (Project p: listOfProjects) {
            System.out.println("------------------------------------------");
            System.out.println("On project " + p.getName( )+ " started on "+ p.getStartDate() +
                     " are working " + p.getDevelopers().size() + " developer(s)!");
        }

//OUTPUT
//Sum of dev's salary on project: 3000
//--------------------------------------------
//Developers list on defined project: [Crash2, Bob, Cosy]
//--------------------------------------------
//List of Java developers: [Bob, Cord, Rob, Artur, Crash4]
//--------------------------------------------
//List of MIDDLE developers: [Cord, Artur]
//--------------------------------------------
//------------------------------------------
//On project JunJavaDev started on 2019-01-01 are working 3 developer(s)!
//------------------------------------------
//On project JunJavaDev1 started on 2018-10-15 are working 3 developer(s)!
//------------------------------------------
//On project LawConsBase started on 2019-02-01 are working 3 developer(s)!
//------------------------------------------
//On project OpportunityLabor started on 2019-02-01 are working 2 developer(s)!
//------------------------------------------
//On project Utility started on 3904-01-04 are working 1 developer(s)!
//
//Process finished with exit code 0
    }
}
