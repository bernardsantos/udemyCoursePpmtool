/**
 *
 */
package io.project.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.project.ppmtool.domain.Project;

/**
 * @author Bernard A. Santos Jr.  12 Apr 2019
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}
