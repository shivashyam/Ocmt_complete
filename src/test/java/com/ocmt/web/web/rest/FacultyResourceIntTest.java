package com.ocmt.web.web.rest;

import com.ocmt.web.OcmtApp;

import com.ocmt.web.domain.Faculty;
import com.ocmt.web.repository.FacultyRepository;
import com.ocmt.web.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.ocmt.web.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ocmt.web.domain.enumeration.Title;
import com.ocmt.web.domain.enumeration.Gender;
import com.ocmt.web.domain.enumeration.Marital;
import com.ocmt.web.domain.enumeration.Dept;
import com.ocmt.web.domain.enumeration.Courses;
/**
 * Test class for the FacultyResource REST controller.
 *
 * @see FacultyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OcmtApp.class)
public class FacultyResourceIntTest {

    private static final Integer DEFAULT_FACULTYID = 1;
    private static final Integer UPDATED_FACULTYID = 2;

    private static final String DEFAULT_F_NAME = "AAAAAAAAAA";
    private static final String UPDATED_F_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_M_NAME = "AAAAAAAAAA";
    private static final String UPDATED_M_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_L_NAME = "AAAAAAAAAA";
    private static final String UPDATED_L_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_U_NAME = "AAAAAAAAAA";
    private static final String UPDATED_U_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Title DEFAULT_TITLE = Title.MR;
    private static final Title UPDATED_TITLE = Title.MS;

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final Marital DEFAULT_MARITAL = Marital.SINGLE;
    private static final Marital UPDATED_MARITAL = Marital.MARRIED;

    private static final LocalDate DEFAULT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final byte[] DEFAULT_CERT_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CERT_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CERT_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CERT_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CERT_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CERT_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CERT_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CERT_2_CONTENT_TYPE = "image/png";

    private static final Dept DEFAULT_DEPT = Dept.CYBERSECURITY;
    private static final Dept UPDATED_DEPT = Dept.SECURITY;

    private static final Courses DEFAULT_COURSES = Courses.CYBERSECURITY;
    private static final Courses UPDATED_COURSES = Courses.SECURITY;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFacultyMockMvc;

    private Faculty faculty;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FacultyResource facultyResource = new FacultyResource(facultyRepository);
        this.restFacultyMockMvc = MockMvcBuilders.standaloneSetup(facultyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Faculty createEntity(EntityManager em) {
        Faculty faculty = new Faculty()
            .facultyid(DEFAULT_FACULTYID)
            .fName(DEFAULT_F_NAME)
            .mName(DEFAULT_M_NAME)
            .lName(DEFAULT_L_NAME)
            .uName(DEFAULT_U_NAME)
            .password(DEFAULT_PASSWORD)
            .title(DEFAULT_TITLE)
            .gender(DEFAULT_GENDER)
            .marital(DEFAULT_MARITAL)
            .dob(DEFAULT_DOB)
            .address(DEFAULT_ADDRESS)
            .paddress(DEFAULT_PADDRESS)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .cert1(DEFAULT_CERT_1)
            .cert1ContentType(DEFAULT_CERT_1_CONTENT_TYPE)
            .cert2(DEFAULT_CERT_2)
            .cert2ContentType(DEFAULT_CERT_2_CONTENT_TYPE)
            .dept(DEFAULT_DEPT)
            .courses(DEFAULT_COURSES);
        return faculty;
    }

    @Before
    public void initTest() {
        faculty = createEntity(em);
    }

    @Test
    @Transactional
    public void createFaculty() throws Exception {
        int databaseSizeBeforeCreate = facultyRepository.findAll().size();

        // Create the Faculty
        restFacultyMockMvc.perform(post("/api/faculties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faculty)))
            .andExpect(status().isCreated());

        // Validate the Faculty in the database
        List<Faculty> facultyList = facultyRepository.findAll();
        assertThat(facultyList).hasSize(databaseSizeBeforeCreate + 1);
        Faculty testFaculty = facultyList.get(facultyList.size() - 1);
        assertThat(testFaculty.getFacultyid()).isEqualTo(DEFAULT_FACULTYID);
        assertThat(testFaculty.getfName()).isEqualTo(DEFAULT_F_NAME);
        assertThat(testFaculty.getmName()).isEqualTo(DEFAULT_M_NAME);
        assertThat(testFaculty.getlName()).isEqualTo(DEFAULT_L_NAME);
        assertThat(testFaculty.getuName()).isEqualTo(DEFAULT_U_NAME);
        assertThat(testFaculty.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testFaculty.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testFaculty.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testFaculty.getMarital()).isEqualTo(DEFAULT_MARITAL);
        assertThat(testFaculty.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testFaculty.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testFaculty.getPaddress()).isEqualTo(DEFAULT_PADDRESS);
        assertThat(testFaculty.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFaculty.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testFaculty.getCert1()).isEqualTo(DEFAULT_CERT_1);
        assertThat(testFaculty.getCert1ContentType()).isEqualTo(DEFAULT_CERT_1_CONTENT_TYPE);
        assertThat(testFaculty.getCert2()).isEqualTo(DEFAULT_CERT_2);
        assertThat(testFaculty.getCert2ContentType()).isEqualTo(DEFAULT_CERT_2_CONTENT_TYPE);
        assertThat(testFaculty.getDept()).isEqualTo(DEFAULT_DEPT);
        assertThat(testFaculty.getCourses()).isEqualTo(DEFAULT_COURSES);
    }

    @Test
    @Transactional
    public void createFacultyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = facultyRepository.findAll().size();

        // Create the Faculty with an existing ID
        faculty.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFacultyMockMvc.perform(post("/api/faculties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faculty)))
            .andExpect(status().isBadRequest());

        // Validate the Faculty in the database
        List<Faculty> facultyList = facultyRepository.findAll();
        assertThat(facultyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = facultyRepository.findAll().size();
        // set the field null
        faculty.setAddress(null);

        // Create the Faculty, which fails.

        restFacultyMockMvc.perform(post("/api/faculties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faculty)))
            .andExpect(status().isBadRequest());

        List<Faculty> facultyList = facultyRepository.findAll();
        assertThat(facultyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = facultyRepository.findAll().size();
        // set the field null
        faculty.setPaddress(null);

        // Create the Faculty, which fails.

        restFacultyMockMvc.perform(post("/api/faculties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faculty)))
            .andExpect(status().isBadRequest());

        List<Faculty> facultyList = facultyRepository.findAll();
        assertThat(facultyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFaculties() throws Exception {
        // Initialize the database
        facultyRepository.saveAndFlush(faculty);

        // Get all the facultyList
        restFacultyMockMvc.perform(get("/api/faculties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(faculty.getId().intValue())))
            .andExpect(jsonPath("$.[*].facultyid").value(hasItem(DEFAULT_FACULTYID)))
            .andExpect(jsonPath("$.[*].fName").value(hasItem(DEFAULT_F_NAME.toString())))
            .andExpect(jsonPath("$.[*].mName").value(hasItem(DEFAULT_M_NAME.toString())))
            .andExpect(jsonPath("$.[*].lName").value(hasItem(DEFAULT_L_NAME.toString())))
            .andExpect(jsonPath("$.[*].uName").value(hasItem(DEFAULT_U_NAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].marital").value(hasItem(DEFAULT_MARITAL.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].paddress").value(hasItem(DEFAULT_PADDRESS.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].cert1ContentType").value(hasItem(DEFAULT_CERT_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].cert1").value(hasItem(Base64Utils.encodeToString(DEFAULT_CERT_1))))
            .andExpect(jsonPath("$.[*].cert2ContentType").value(hasItem(DEFAULT_CERT_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].cert2").value(hasItem(Base64Utils.encodeToString(DEFAULT_CERT_2))))
            .andExpect(jsonPath("$.[*].dept").value(hasItem(DEFAULT_DEPT.toString())))
            .andExpect(jsonPath("$.[*].courses").value(hasItem(DEFAULT_COURSES.toString())));
    }
    
    @Test
    @Transactional
    public void getFaculty() throws Exception {
        // Initialize the database
        facultyRepository.saveAndFlush(faculty);

        // Get the faculty
        restFacultyMockMvc.perform(get("/api/faculties/{id}", faculty.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(faculty.getId().intValue()))
            .andExpect(jsonPath("$.facultyid").value(DEFAULT_FACULTYID))
            .andExpect(jsonPath("$.fName").value(DEFAULT_F_NAME.toString()))
            .andExpect(jsonPath("$.mName").value(DEFAULT_M_NAME.toString()))
            .andExpect(jsonPath("$.lName").value(DEFAULT_L_NAME.toString()))
            .andExpect(jsonPath("$.uName").value(DEFAULT_U_NAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.marital").value(DEFAULT_MARITAL.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.paddress").value(DEFAULT_PADDRESS.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.cert1ContentType").value(DEFAULT_CERT_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.cert1").value(Base64Utils.encodeToString(DEFAULT_CERT_1)))
            .andExpect(jsonPath("$.cert2ContentType").value(DEFAULT_CERT_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.cert2").value(Base64Utils.encodeToString(DEFAULT_CERT_2)))
            .andExpect(jsonPath("$.dept").value(DEFAULT_DEPT.toString()))
            .andExpect(jsonPath("$.courses").value(DEFAULT_COURSES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFaculty() throws Exception {
        // Get the faculty
        restFacultyMockMvc.perform(get("/api/faculties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFaculty() throws Exception {
        // Initialize the database
        facultyRepository.saveAndFlush(faculty);

        int databaseSizeBeforeUpdate = facultyRepository.findAll().size();

        // Update the faculty
        Faculty updatedFaculty = facultyRepository.findById(faculty.getId()).get();
        // Disconnect from session so that the updates on updatedFaculty are not directly saved in db
        em.detach(updatedFaculty);
        updatedFaculty
            .facultyid(UPDATED_FACULTYID)
            .fName(UPDATED_F_NAME)
            .mName(UPDATED_M_NAME)
            .lName(UPDATED_L_NAME)
            .uName(UPDATED_U_NAME)
            .password(UPDATED_PASSWORD)
            .title(UPDATED_TITLE)
            .gender(UPDATED_GENDER)
            .marital(UPDATED_MARITAL)
            .dob(UPDATED_DOB)
            .address(UPDATED_ADDRESS)
            .paddress(UPDATED_PADDRESS)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .cert1(UPDATED_CERT_1)
            .cert1ContentType(UPDATED_CERT_1_CONTENT_TYPE)
            .cert2(UPDATED_CERT_2)
            .cert2ContentType(UPDATED_CERT_2_CONTENT_TYPE)
            .dept(UPDATED_DEPT)
            .courses(UPDATED_COURSES);

        restFacultyMockMvc.perform(put("/api/faculties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFaculty)))
            .andExpect(status().isOk());

        // Validate the Faculty in the database
        List<Faculty> facultyList = facultyRepository.findAll();
        assertThat(facultyList).hasSize(databaseSizeBeforeUpdate);
        Faculty testFaculty = facultyList.get(facultyList.size() - 1);
        assertThat(testFaculty.getFacultyid()).isEqualTo(UPDATED_FACULTYID);
        assertThat(testFaculty.getfName()).isEqualTo(UPDATED_F_NAME);
        assertThat(testFaculty.getmName()).isEqualTo(UPDATED_M_NAME);
        assertThat(testFaculty.getlName()).isEqualTo(UPDATED_L_NAME);
        assertThat(testFaculty.getuName()).isEqualTo(UPDATED_U_NAME);
        assertThat(testFaculty.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testFaculty.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFaculty.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testFaculty.getMarital()).isEqualTo(UPDATED_MARITAL);
        assertThat(testFaculty.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testFaculty.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testFaculty.getPaddress()).isEqualTo(UPDATED_PADDRESS);
        assertThat(testFaculty.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFaculty.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testFaculty.getCert1()).isEqualTo(UPDATED_CERT_1);
        assertThat(testFaculty.getCert1ContentType()).isEqualTo(UPDATED_CERT_1_CONTENT_TYPE);
        assertThat(testFaculty.getCert2()).isEqualTo(UPDATED_CERT_2);
        assertThat(testFaculty.getCert2ContentType()).isEqualTo(UPDATED_CERT_2_CONTENT_TYPE);
        assertThat(testFaculty.getDept()).isEqualTo(UPDATED_DEPT);
        assertThat(testFaculty.getCourses()).isEqualTo(UPDATED_COURSES);
    }

    @Test
    @Transactional
    public void updateNonExistingFaculty() throws Exception {
        int databaseSizeBeforeUpdate = facultyRepository.findAll().size();

        // Create the Faculty

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFacultyMockMvc.perform(put("/api/faculties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faculty)))
            .andExpect(status().isBadRequest());

        // Validate the Faculty in the database
        List<Faculty> facultyList = facultyRepository.findAll();
        assertThat(facultyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFaculty() throws Exception {
        // Initialize the database
        facultyRepository.saveAndFlush(faculty);

        int databaseSizeBeforeDelete = facultyRepository.findAll().size();

        // Get the faculty
        restFacultyMockMvc.perform(delete("/api/faculties/{id}", faculty.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Faculty> facultyList = facultyRepository.findAll();
        assertThat(facultyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Faculty.class);
        Faculty faculty1 = new Faculty();
        faculty1.setId(1L);
        Faculty faculty2 = new Faculty();
        faculty2.setId(faculty1.getId());
        assertThat(faculty1).isEqualTo(faculty2);
        faculty2.setId(2L);
        assertThat(faculty1).isNotEqualTo(faculty2);
        faculty1.setId(null);
        assertThat(faculty1).isNotEqualTo(faculty2);
    }
}
