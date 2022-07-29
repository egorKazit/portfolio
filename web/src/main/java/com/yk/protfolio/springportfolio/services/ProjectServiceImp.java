package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.ProjectDAO;
import com.yk.schema.Project;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to work on project
 */
@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private ImageManager imageManager;

    @Autowired
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
