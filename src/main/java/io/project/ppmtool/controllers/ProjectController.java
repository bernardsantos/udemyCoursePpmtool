/**
 *
 */
package io.project.ppmtool.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.project.ppmtool.domain.Project;
import io.project.ppmtool.services.MapValidationErrorService;
import io.project.ppmtool.services.ProjectService;

/**
 * @author Bernard A. Santos Jr.  12 Apr 2019
 */
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    
    @PostMapping("/projects")
    public ResponseEntity<?> save(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;
        return new ResponseEntity<Project>(projectService.save(project), HttpStatus.CREATED);
    }
    
    @GetMapping("/projects/{identifier}")
    public ResponseEntity<?> getProjectById(@PathVariable String identifier) throws Exception {
        return new ResponseEntity<Project>(projectService.getProjectByIdentifier(identifier), HttpStatus.OK);
    }
    
    @GetMapping("/projects")
    public ResponseEntity<?> getList() throws Exception {
        return new ResponseEntity<Map<String, Object>>(projectService.getList(), HttpStatus.OK);
    }
}
