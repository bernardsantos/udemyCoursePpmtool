/**
 *
 */
package io.project.ppmtool.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    
    @PostMapping("/project")
    public ResponseEntity<?> save(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;
        return new ResponseEntity<Project>(projectService.save(project), HttpStatus.CREATED);
    }
}
