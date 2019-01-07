package mate.academy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Developer> developers;
    private String company;
    private String customer;
    private BigDecimal cost;

    public Project() {
    }

    public Project(String name, LocalDate startDate, LocalDate endDate, BigDecimal cost) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
