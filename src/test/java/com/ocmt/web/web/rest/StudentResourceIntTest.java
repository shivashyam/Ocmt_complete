package com.ocmt.web.web.rest;

import com.ocmt.web.OcmtApp;

import com.ocmt.web.domain.Student;
import com.ocmt.web.repository.StudentRepository;
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

import com.ocmt.web.domain.enumeration.Type;
import com.ocmt.web.domain.enumeration.Title;
import com.ocmt.web.domain.enumeration.Gender;
import com.ocmt.web.domain.enumeration.Marital;
import com.ocmt.web.domain.enumeration.Referral;
import com.ocmt.web.domain.enumeration.Whythiscourse;
import com.ocmt.web.domain.enumeration.Secondary;
import com.ocmt.web.domain.enumeration.Univ;
import com.ocmt.web.domain.enumeration.Programtype;
import com.ocmt.web.domain.enumeration.Courses;
import com.ocmt.web.domain.enumeration.Payments;
/**
 * Test class for the StudentResource REST controller.
 *
 * @see StudentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OcmtApp.class)
public class StudentResourceIntTest {

    private static final Integer DEFAULT_STUDENTID = 1;
    private static final Integer UPDATED_STUDENTID = 2;

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

    private static final Type DEFAULT_TYPE = Type.PROFESSIONAL;
    private static final Type UPDATED_TYPE = Type.CORPORATE;

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

    private static final Referral DEFAULT_REFERRAL = Referral.FRIENDS;
    private static final Referral UPDATED_REFERRAL = Referral.ONLINE;

    private static final Whythiscourse DEFAULT_WHYTHISCOURSE = Whythiscourse.CAREER;
    private static final Whythiscourse UPDATED_WHYTHISCOURSE = Whythiscourse.SECOND;

    private static final Secondary DEFAULT_SECONDARY = Secondary.YES;
    private static final Secondary UPDATED_SECONDARY = Secondary.NO;

    private static final String DEFAULT_SCHOOLNAME = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOLNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SCHOOLGRADDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SCHOOLGRADDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SCHOOLADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOLADDRESS = "BBBBBBBBBB";

    private static final Univ DEFAULT_UNIV = Univ.YES;
    private static final Univ UPDATED_UNIV = Univ.NO;

    private static final String DEFAULT_UNIVNAME = "AAAAAAAAAA";
    private static final String UPDATED_UNIVNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UNIVGRADDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UNIVGRADDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UNIVADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_UNIVADDRESS = "BBBBBBBBBB";

    private static final byte[] DEFAULT_SOP = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_SOP = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_SOP_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SOP_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_SCORECARD = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_SCORECARD = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_SCORECARD_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SCORECARD_CONTENT_TYPE = "image/png";

    private static final Programtype DEFAULT_PROGRAMTYPE = Programtype.DIPLOMA;
    private static final Programtype UPDATED_PROGRAMTYPE = Programtype.CERTIFICATE;

    private static final Courses DEFAULT_COURSES = Courses.CYBERSECURITY;
    private static final Courses UPDATED_COURSES = Courses.SECURITY;

    private static final Float DEFAULT_GPA = 1F;
    private static final Float UPDATED_GPA = 2F;

    private static final Payments DEFAULT_PAYMENTS = Payments.YES;
    private static final Payments UPDATED_PAYMENTS = Payments.NO;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStudentMockMvc;

    private Student student;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StudentResource studentResource = new StudentResource(studentRepository);
        this.restStudentMockMvc = MockMvcBuilders.standaloneSetup(studentResource)
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
    public static Student createEntity(EntityManager em) {
        Student student = new Student()
            .studentid(DEFAULT_STUDENTID)
            .fName(DEFAULT_F_NAME)
            .mName(DEFAULT_M_NAME)
            .lName(DEFAULT_L_NAME)
            .uName(DEFAULT_U_NAME)
            .password(DEFAULT_PASSWORD)
            .type(DEFAULT_TYPE)
            .title(DEFAULT_TITLE)
            .gender(DEFAULT_GENDER)
            .marital(DEFAULT_MARITAL)
            .dob(DEFAULT_DOB)
            .address(DEFAULT_ADDRESS)
            .paddress(DEFAULT_PADDRESS)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .referral(DEFAULT_REFERRAL)
            .whythiscourse(DEFAULT_WHYTHISCOURSE)
            .secondary(DEFAULT_SECONDARY)
            .schoolname(DEFAULT_SCHOOLNAME)
            .schoolgraddate(DEFAULT_SCHOOLGRADDATE)
            .schooladdress(DEFAULT_SCHOOLADDRESS)
            .univ(DEFAULT_UNIV)
            .univname(DEFAULT_UNIVNAME)
            .univgraddate(DEFAULT_UNIVGRADDATE)
            .univaddress(DEFAULT_UNIVADDRESS)
            .sop(DEFAULT_SOP)
            .sopContentType(DEFAULT_SOP_CONTENT_TYPE)
            .scorecard(DEFAULT_SCORECARD)
            .scorecardContentType(DEFAULT_SCORECARD_CONTENT_TYPE)
            .programtype(DEFAULT_PROGRAMTYPE)
            .courses(DEFAULT_COURSES)
            .gpa(DEFAULT_GPA)
            .payments(DEFAULT_PAYMENTS);
        return student;
    }

    @Before
    public void initTest() {
        student = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // Create the Student
        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStudentid()).isEqualTo(DEFAULT_STUDENTID);
        assertThat(testStudent.getfName()).isEqualTo(DEFAULT_F_NAME);
        assertThat(testStudent.getmName()).isEqualTo(DEFAULT_M_NAME);
        assertThat(testStudent.getlName()).isEqualTo(DEFAULT_L_NAME);
        assertThat(testStudent.getuName()).isEqualTo(DEFAULT_U_NAME);
        assertThat(testStudent.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testStudent.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testStudent.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testStudent.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testStudent.getMarital()).isEqualTo(DEFAULT_MARITAL);
        assertThat(testStudent.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testStudent.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testStudent.getPaddress()).isEqualTo(DEFAULT_PADDRESS);
        assertThat(testStudent.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testStudent.getReferral()).isEqualTo(DEFAULT_REFERRAL);
        assertThat(testStudent.getWhythiscourse()).isEqualTo(DEFAULT_WHYTHISCOURSE);
        assertThat(testStudent.getSecondary()).isEqualTo(DEFAULT_SECONDARY);
        assertThat(testStudent.getSchoolname()).isEqualTo(DEFAULT_SCHOOLNAME);
        assertThat(testStudent.getSchoolgraddate()).isEqualTo(DEFAULT_SCHOOLGRADDATE);
        assertThat(testStudent.getSchooladdress()).isEqualTo(DEFAULT_SCHOOLADDRESS);
        assertThat(testStudent.getUniv()).isEqualTo(DEFAULT_UNIV);
        assertThat(testStudent.getUnivname()).isEqualTo(DEFAULT_UNIVNAME);
        assertThat(testStudent.getUnivgraddate()).isEqualTo(DEFAULT_UNIVGRADDATE);
        assertThat(testStudent.getUnivaddress()).isEqualTo(DEFAULT_UNIVADDRESS);
        assertThat(testStudent.getSop()).isEqualTo(DEFAULT_SOP);
        assertThat(testStudent.getSopContentType()).isEqualTo(DEFAULT_SOP_CONTENT_TYPE);
        assertThat(testStudent.getScorecard()).isEqualTo(DEFAULT_SCORECARD);
        assertThat(testStudent.getScorecardContentType()).isEqualTo(DEFAULT_SCORECARD_CONTENT_TYPE);
        assertThat(testStudent.getProgramtype()).isEqualTo(DEFAULT_PROGRAMTYPE);
        assertThat(testStudent.getCourses()).isEqualTo(DEFAULT_COURSES);
        assertThat(testStudent.getGpa()).isEqualTo(DEFAULT_GPA);
        assertThat(testStudent.getPayments()).isEqualTo(DEFAULT_PAYMENTS);
    }

    @Test
    @Transactional
    public void createStudentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // Create the Student with an existing ID
        student.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setAddress(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setPaddress(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSchooladdressIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setSchooladdress(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnivaddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setUnivaddress(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStudents() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList
        restStudentMockMvc.perform(get("/api/students?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].studentid").value(hasItem(DEFAULT_STUDENTID)))
            .andExpect(jsonPath("$.[*].fName").value(hasItem(DEFAULT_F_NAME.toString())))
            .andExpect(jsonPath("$.[*].mName").value(hasItem(DEFAULT_M_NAME.toString())))
            .andExpect(jsonPath("$.[*].lName").value(hasItem(DEFAULT_L_NAME.toString())))
            .andExpect(jsonPath("$.[*].uName").value(hasItem(DEFAULT_U_NAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].marital").value(hasItem(DEFAULT_MARITAL.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].paddress").value(hasItem(DEFAULT_PADDRESS.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].referral").value(hasItem(DEFAULT_REFERRAL.toString())))
            .andExpect(jsonPath("$.[*].whythiscourse").value(hasItem(DEFAULT_WHYTHISCOURSE.toString())))
            .andExpect(jsonPath("$.[*].secondary").value(hasItem(DEFAULT_SECONDARY.toString())))
            .andExpect(jsonPath("$.[*].schoolname").value(hasItem(DEFAULT_SCHOOLNAME.toString())))
            .andExpect(jsonPath("$.[*].schoolgraddate").value(hasItem(DEFAULT_SCHOOLGRADDATE.toString())))
            .andExpect(jsonPath("$.[*].schooladdress").value(hasItem(DEFAULT_SCHOOLADDRESS.toString())))
            .andExpect(jsonPath("$.[*].univ").value(hasItem(DEFAULT_UNIV.toString())))
            .andExpect(jsonPath("$.[*].univname").value(hasItem(DEFAULT_UNIVNAME.toString())))
            .andExpect(jsonPath("$.[*].univgraddate").value(hasItem(DEFAULT_UNIVGRADDATE.toString())))
            .andExpect(jsonPath("$.[*].univaddress").value(hasItem(DEFAULT_UNIVADDRESS.toString())))
            .andExpect(jsonPath("$.[*].sopContentType").value(hasItem(DEFAULT_SOP_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].sop").value(hasItem(Base64Utils.encodeToString(DEFAULT_SOP))))
            .andExpect(jsonPath("$.[*].scorecardContentType").value(hasItem(DEFAULT_SCORECARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].scorecard").value(hasItem(Base64Utils.encodeToString(DEFAULT_SCORECARD))))
            .andExpect(jsonPath("$.[*].programtype").value(hasItem(DEFAULT_PROGRAMTYPE.toString())))
            .andExpect(jsonPath("$.[*].courses").value(hasItem(DEFAULT_COURSES.toString())))
            .andExpect(jsonPath("$.[*].gpa").value(hasItem(DEFAULT_GPA.doubleValue())))
            .andExpect(jsonPath("$.[*].payments").value(hasItem(DEFAULT_PAYMENTS.toString())));
    }
    
    @Test
    @Transactional
    public void getStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(student.getId().intValue()))
            .andExpect(jsonPath("$.studentid").value(DEFAULT_STUDENTID))
            .andExpect(jsonPath("$.fName").value(DEFAULT_F_NAME.toString()))
            .andExpect(jsonPath("$.mName").value(DEFAULT_M_NAME.toString()))
            .andExpect(jsonPath("$.lName").value(DEFAULT_L_NAME.toString()))
            .andExpect(jsonPath("$.uName").value(DEFAULT_U_NAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.marital").value(DEFAULT_MARITAL.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.paddress").value(DEFAULT_PADDRESS.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.referral").value(DEFAULT_REFERRAL.toString()))
            .andExpect(jsonPath("$.whythiscourse").value(DEFAULT_WHYTHISCOURSE.toString()))
            .andExpect(jsonPath("$.secondary").value(DEFAULT_SECONDARY.toString()))
            .andExpect(jsonPath("$.schoolname").value(DEFAULT_SCHOOLNAME.toString()))
            .andExpect(jsonPath("$.schoolgraddate").value(DEFAULT_SCHOOLGRADDATE.toString()))
            .andExpect(jsonPath("$.schooladdress").value(DEFAULT_SCHOOLADDRESS.toString()))
            .andExpect(jsonPath("$.univ").value(DEFAULT_UNIV.toString()))
            .andExpect(jsonPath("$.univname").value(DEFAULT_UNIVNAME.toString()))
            .andExpect(jsonPath("$.univgraddate").value(DEFAULT_UNIVGRADDATE.toString()))
            .andExpect(jsonPath("$.univaddress").value(DEFAULT_UNIVADDRESS.toString()))
            .andExpect(jsonPath("$.sopContentType").value(DEFAULT_SOP_CONTENT_TYPE))
            .andExpect(jsonPath("$.sop").value(Base64Utils.encodeToString(DEFAULT_SOP)))
            .andExpect(jsonPath("$.scorecardContentType").value(DEFAULT_SCORECARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.scorecard").value(Base64Utils.encodeToString(DEFAULT_SCORECARD)))
            .andExpect(jsonPath("$.programtype").value(DEFAULT_PROGRAMTYPE.toString()))
            .andExpect(jsonPath("$.courses").value(DEFAULT_COURSES.toString()))
            .andExpect(jsonPath("$.gpa").value(DEFAULT_GPA.doubleValue()))
            .andExpect(jsonPath("$.payments").value(DEFAULT_PAYMENTS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStudent() throws Exception {
        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student
        Student updatedStudent = studentRepository.findById(student.getId()).get();
        // Disconnect from session so that the updates on updatedStudent are not directly saved in db
        em.detach(updatedStudent);
        updatedStudent
            .studentid(UPDATED_STUDENTID)
            .fName(UPDATED_F_NAME)
            .mName(UPDATED_M_NAME)
            .lName(UPDATED_L_NAME)
            .uName(UPDATED_U_NAME)
            .password(UPDATED_PASSWORD)
            .type(UPDATED_TYPE)
            .title(UPDATED_TITLE)
            .gender(UPDATED_GENDER)
            .marital(UPDATED_MARITAL)
            .dob(UPDATED_DOB)
            .address(UPDATED_ADDRESS)
            .paddress(UPDATED_PADDRESS)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .referral(UPDATED_REFERRAL)
            .whythiscourse(UPDATED_WHYTHISCOURSE)
            .secondary(UPDATED_SECONDARY)
            .schoolname(UPDATED_SCHOOLNAME)
            .schoolgraddate(UPDATED_SCHOOLGRADDATE)
            .schooladdress(UPDATED_SCHOOLADDRESS)
            .univ(UPDATED_UNIV)
            .univname(UPDATED_UNIVNAME)
            .univgraddate(UPDATED_UNIVGRADDATE)
            .univaddress(UPDATED_UNIVADDRESS)
            .sop(UPDATED_SOP)
            .sopContentType(UPDATED_SOP_CONTENT_TYPE)
            .scorecard(UPDATED_SCORECARD)
            .scorecardContentType(UPDATED_SCORECARD_CONTENT_TYPE)
            .programtype(UPDATED_PROGRAMTYPE)
            .courses(UPDATED_COURSES)
            .gpa(UPDATED_GPA)
            .payments(UPDATED_PAYMENTS);

        restStudentMockMvc.perform(put("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStudent)))
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getStudentid()).isEqualTo(UPDATED_STUDENTID);
        assertThat(testStudent.getfName()).isEqualTo(UPDATED_F_NAME);
        assertThat(testStudent.getmName()).isEqualTo(UPDATED_M_NAME);
        assertThat(testStudent.getlName()).isEqualTo(UPDATED_L_NAME);
        assertThat(testStudent.getuName()).isEqualTo(UPDATED_U_NAME);
        assertThat(testStudent.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testStudent.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testStudent.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testStudent.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testStudent.getMarital()).isEqualTo(UPDATED_MARITAL);
        assertThat(testStudent.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testStudent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStudent.getPaddress()).isEqualTo(UPDATED_PADDRESS);
        assertThat(testStudent.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testStudent.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testStudent.getReferral()).isEqualTo(UPDATED_REFERRAL);
        assertThat(testStudent.getWhythiscourse()).isEqualTo(UPDATED_WHYTHISCOURSE);
        assertThat(testStudent.getSecondary()).isEqualTo(UPDATED_SECONDARY);
        assertThat(testStudent.getSchoolname()).isEqualTo(UPDATED_SCHOOLNAME);
        assertThat(testStudent.getSchoolgraddate()).isEqualTo(UPDATED_SCHOOLGRADDATE);
        assertThat(testStudent.getSchooladdress()).isEqualTo(UPDATED_SCHOOLADDRESS);
        assertThat(testStudent.getUniv()).isEqualTo(UPDATED_UNIV);
        assertThat(testStudent.getUnivname()).isEqualTo(UPDATED_UNIVNAME);
        assertThat(testStudent.getUnivgraddate()).isEqualTo(UPDATED_UNIVGRADDATE);
        assertThat(testStudent.getUnivaddress()).isEqualTo(UPDATED_UNIVADDRESS);
        assertThat(testStudent.getSop()).isEqualTo(UPDATED_SOP);
        assertThat(testStudent.getSopContentType()).isEqualTo(UPDATED_SOP_CONTENT_TYPE);
        assertThat(testStudent.getScorecard()).isEqualTo(UPDATED_SCORECARD);
        assertThat(testStudent.getScorecardContentType()).isEqualTo(UPDATED_SCORECARD_CONTENT_TYPE);
        assertThat(testStudent.getProgramtype()).isEqualTo(UPDATED_PROGRAMTYPE);
        assertThat(testStudent.getCourses()).isEqualTo(UPDATED_COURSES);
        assertThat(testStudent.getGpa()).isEqualTo(UPDATED_GPA);
        assertThat(testStudent.getPayments()).isEqualTo(UPDATED_PAYMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Create the Student

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc.perform(put("/api/students")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        // Get the student
        restStudentMockMvc.perform(delete("/api/students/{id}", student.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Student.class);
        Student student1 = new Student();
        student1.setId(1L);
        Student student2 = new Student();
        student2.setId(student1.getId());
        assertThat(student1).isEqualTo(student2);
        student2.setId(2L);
        assertThat(student1).isNotEqualTo(student2);
        student1.setId(null);
        assertThat(student1).isNotEqualTo(student2);
    }
}
