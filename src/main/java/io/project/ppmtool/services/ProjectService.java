/**
 *
 */
package io.project.ppmtool.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.project.ppmtool.domain.Project;
import io.project.ppmtool.repositories.ProjectRepository;

/**
 * @author Bernard A. Santos Jr.  12 Apr 2019
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Project save(Project project) {
        Project pj = new Project();
        pj.setProjectName(project.getProjectName());
        pj.setDescription(project.getDescription());
        pj.setProjectIdentifier(RandomStringUtils.randomAlphanumeric(6).toUpperCase());
        return projectRepository.save(pj);
    }
}
