package mate.academy.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<Developer> developers;
    private Company company;
    private Customer customer;
    private BigDecimal cost;

    public Project() {
    }

    public Project(String name, Date startDate, Date endDate, BigDecimal cost) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", developers=" + developers +
                ", company=" + company +
                ", customer=" + customer +
                ", cost=" + cost +
                '}';
    }
}
