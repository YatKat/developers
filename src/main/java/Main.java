import mate.academy.ConnectionUtil;
import mate.academy.dao.DeveloperDao;
import mate.academy.dao.DeveloperDaoImpl;
import mate.academy.dao.ProjectDao;
import mate.academy.dao.ProjectDaoImpl;
import mate.academy.model.Developer;
import mate.academy.model.Project;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DeveloperDao developerDao = new DeveloperDaoImpl(ConnectionUtil.getConnection());
        ProjectDao projectDao = new ProjectDaoImpl(ConnectionUtil.getConnection());
        //was ran only one time to add new column "salary" in developer database
        //developerDao.addColumnInDev("salary");
        //((ProjectDaoImpl) projectDao).addColumnInProj("cost");
        System.out.println(developerDao.createDeveloper(new Developer("Cord", 35,
                Developer.Sex.MALE, new BigDecimal(3000))));
        System.out.println(projectDao.createProj(new Project("OpportunityLabor", LocalDate.of(2019, 02, 01),
                LocalDate.of(2021, 06, 15), new BigDecimal(150000))));
    }
}
