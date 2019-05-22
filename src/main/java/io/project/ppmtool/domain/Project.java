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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Project name is required!")
    private String projectName;
    @Column(updatable=false, unique=true)
    private String projectIdentifier;
    @Lob
    @NotBlank(message="Project Description is required!")
    private String description;
    
    @JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date start_date;
    @JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date end_date;
    
    
    @JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date created_at;
    @JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
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
