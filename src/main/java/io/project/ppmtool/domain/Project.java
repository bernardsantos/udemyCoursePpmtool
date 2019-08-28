package io.project.ppmtool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Entity
@Data
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Project name is required!")
    @Column(name = "project_name")
    private String projectName;
    
    @NotBlank(message="Project identifier is required!")
    @Column(name="project_identifier", updatable=false, unique=true)
    @Size(min = 6, max = 6, message="must be 6 characters")
    private String projectIdentifier;
    
    @Lob
    @NotBlank(message="Project Description is required!")
    @Column(name="description")
    private String description;
    
    @NotNull(message="Start date is required!")
    private Date start_date;
    @NotNull(message="End date is required!")
    private Date end_date;
    
    private Date created_at;
    private Date updated_at;

    public Project() {
    }

    @PrePersist
    protected void onCreate() {
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    }
}
