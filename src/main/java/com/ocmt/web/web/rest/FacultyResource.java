package com.ocmt.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ocmt.web.domain.Faculty;
import com.ocmt.web.repository.FacultyRepository;
import com.ocmt.web.web.rest.errors.BadRequestAlertException;
import com.ocmt.web.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Faculty.
 */
@RestController
@RequestMapping("/api")
public class FacultyResource {

    private final Logger log = LoggerFactory.getLogger(FacultyResource.class);

    private static final String ENTITY_NAME = "faculty";

    private final FacultyRepository facultyRepository;

    public FacultyResource(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    /**
     * POST  /faculties : Create a new faculty.
     *
     * @param faculty the faculty to create
     * @return the ResponseEntity with status 201 (Created) and with body the new faculty, or with status 400 (Bad Request) if the faculty has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/faculties")
    @Timed
    public ResponseEntity<Faculty> createFaculty(@Valid @RequestBody Faculty faculty) throws URISyntaxException {
        log.debug("REST request to save Faculty : {}", faculty);
        if (faculty.getId() != null) {
            throw new BadRequestAlertException("A new faculty cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Faculty result = facultyRepository.save(faculty);
        return ResponseEntity.created(new URI("/api/faculties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /faculties : Updates an existing faculty.
     *
     * @param faculty the faculty to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated faculty,
     * or with status 400 (Bad Request) if the faculty is not valid,
     * or with status 500 (Internal Server Error) if the faculty couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/faculties")
    @Timed
    public ResponseEntity<Faculty> updateFaculty(@Valid @RequestBody Faculty faculty) throws URISyntaxException {
        log.debug("REST request to update Faculty : {}", faculty);
        if (faculty.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Faculty result = facultyRepository.save(faculty);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, faculty.getId().toString()))
            .body(result);
    }

    /**
     * GET  /faculties : get all the faculties.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of faculties in body
     */
    @GetMapping("/faculties")
    @Timed
    public List<Faculty> getAllFaculties() {
        log.debug("REST request to get all Faculties");
        return facultyRepository.findAll();
    }

    /**
     * GET  /faculties/:id : get the "id" faculty.
     *
     * @param id the id of the faculty to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the faculty, or with status 404 (Not Found)
     */
    @GetMapping("/faculties/{id}")
    @Timed
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        log.debug("REST request to get Faculty : {}", id);
        Optional<Faculty> faculty = facultyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(faculty);
    }

    /**
     * DELETE  /faculties/:id : delete the "id" faculty.
     *
     * @param id the id of the faculty to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/faculties/{id}")
    @Timed
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        log.debug("REST request to delete Faculty : {}", id);

        facultyRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
