/**
 *
 */
package io.project.ppmtool.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.project.ppmtool.domain.Project;
import io.project.ppmtool.exceptions.CustomException;
import io.project.ppmtool.repositories.ProjectRepository;
/**
 * @author Bernard A. Santos Jr.  12 Apr 2019
 */
@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    
    public Project save(Project project) { 
        Project pj = projectRepository.findByProjectName(project.getProjectName());
        if (pj != null) {
            throw new CustomException("Project name "+pj.getProjectName()+" already exist!", 10);
        } else {
            pj = new Project();
            pj.setProjectName(project.getProjectName());
            pj.setDescription(project.getDescription());
            pj.setProjectIdentifier(project.getProjectIdentifier());
            pj.setStart_date(project.getStart_date());
            pj.setEnd_date(project.getEnd_date());
        }
       
        return projectRepository.save(pj);
    }
    
    public Project getProjectByIdentifier(String identifier) throws Exception{
        Project project = projectRepository.findByProjectIdentifier(identifier);
        if (project == null) {
            throw new CustomException("project with identifier "+identifier+" does not exist", 10);
        }
        return project;
    }
    
    public Map<String, Object> getList() throws Exception {
        Map<String, Object> response = new HashMap<>();
        List<Project> projects = new ArrayList<>();
        projects = (List<Project>) projectRepository.findAll();
        response.put("data", projects);
        return response;
    }
    
    public void deleteProjectByIdentifier(String identifier) {
        Project project = projectRepository.findByProjectIdentifier(identifier);
        if(project == null) {
            throw new CustomException("Project with identifier "+identifier+ " does not exist!", 10);
        }
        projectRepository.delete(project);
    } 
    
    public Project updateProject(Project proj, String identifier) throws Exception {
        
        Project project = projectRepository.findByProjectIdentifier(identifier);
        if (project == null) {
            throw new CustomException("Project with Identifier "+identifier+" does not exist", 10);
        }
        Boolean exist = projectRepository.findByProjectNameAndIdNot(proj.getProjectName(), project.getId()) != null;
        if (exist == true) {
            throw new CustomException("Project with projectName "+proj.getProjectName()+" already exist!", 10);
        } 
        project.setProjectName(proj.getProjectName());
        project.setProjectIdentifier(proj.getProjectIdentifier());
        project.setDescription(proj.getDescription());
        project.setStart_date(proj.getStart_date() != null ? proj.getStart_date() : null);
        project.setEnd_date(proj.getEnd_date() != null ? proj.getEnd_date() : null);
       return projectRepository.save(project);
    }
}
