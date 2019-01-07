package mate.academy.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Developer {
    private int id;
    private String name;
    private int age;
    private Sex sex;
    private Map<String, String> skills;
    private List<Project> projects;
    private BigDecimal salary;

    public Developer() {
    }

    public Developer(String name, int age, Sex sex, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Map<String, String> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, String> skills) {
        this.skills = skills;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public enum Sex{
        MALE,
        FEMALE
    }

}
