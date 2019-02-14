package mate.academy.dao;

import mate.academy.model.Project;

import java.util.List;

public interface ProjectDao {
    Project getProjectById(int id);

    int getProjectId(Project project);

    Project getProjectByName(String name);

    List<Project> getAllProj();

    int createProj(Project project);

    void updateProj(Project project);

    void deleteProj(Project project);
}
