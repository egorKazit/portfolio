package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.ProjectDAO;
import com.yk.schema.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to work on project
 */
@Service
@AllArgsConstructor
public class ProjectServiceImp implements ProjectService {

    private ProjectDAO projectDAO;
    private ImageManager imageManager;
    private HyperlinkService hyperlinkService;

    /**
     * gets list of projects
     *
     * @return list of projects
     */
    @Override
    public List<Project> getProjects() {
        List<Project> projects = projectDAO.getProjects();
        imageManager.uploadImage(projects, Project::getPicture, Project::getImage);
        return projects;
    }

    /**
     * gets project by id
     *
     * @return project
     */
    @Override
    public Project getProject(int id) {
        Project project = projectDAO.getProject(id);
        if (project.getText() != null)
            project.setText(hyperlinkService.replaceWithHyperLink(project.getText()));
        imageManager.uploadImage(List.of(project), Project::getPicture, Project::getImage);
        return project;
    }
}
