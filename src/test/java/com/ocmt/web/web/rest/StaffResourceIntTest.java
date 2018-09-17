package com.ocmt.web.web.rest;

import com.ocmt.web.OcmtApp;

import com.ocmt.web.domain.Staff;
import com.ocmt.web.repository.StaffRepository;
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
/**
 * Test class for the StaffResource REST controller.
 *
 * @see StaffResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OcmtApp.class)
public class StaffResourceIntTest {

    private static final Integer DEFAULT_STAFFTID = 1;
    private static final Integer UPDATED_STAFFTID = 2;

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

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStaffMockMvc;

    private Staff staff;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StaffResource staffResource = new StaffResource(staffRepository);
        this.restStaffMockMvc = MockMvcBuilders.standaloneSetup(staffResource)
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
    public static Staff createEntity(EntityManager em) {
        Staff staff = new Staff()
            .stafftid(DEFAULT_STAFFTID)
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
            .cert2ContentType(DEFAULT_CERT_2_CONTENT_TYPE);
        return staff;
    }

    @Before
    public void initTest() {
        staff = createEntity(em);
    }

    @Test
    @Transactional
    public void createStaff() throws Exception {
        int databaseSizeBeforeCreate = staffRepository.findAll().size();

        // Create the Staff
        restStaffMockMvc.perform(post("/api/staff")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staff)))
            .andExpect(status().isCreated());

        // Validate the Staff in the database
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeCreate + 1);
        Staff testStaff = staffList.get(staffList.size() - 1);
        assertThat(testStaff.getStafftid()).isEqualTo(DEFAULT_STAFFTID);
        assertThat(testStaff.getfName()).isEqualTo(DEFAULT_F_NAME);
        assertThat(testStaff.getmName()).isEqualTo(DEFAULT_M_NAME);
        assertThat(testStaff.getlName()).isEqualTo(DEFAULT_L_NAME);
        assertThat(testStaff.getuName()).isEqualTo(DEFAULT_U_NAME);
        assertThat(testStaff.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testStaff.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testStaff.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testStaff.getMarital()).isEqualTo(DEFAULT_MARITAL);
        assertThat(testStaff.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testStaff.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testStaff.getPaddress()).isEqualTo(DEFAULT_PADDRESS);
        assertThat(testStaff.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testStaff.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testStaff.getCert1()).isEqualTo(DEFAULT_CERT_1);
        assertThat(testStaff.getCert1ContentType()).isEqualTo(DEFAULT_CERT_1_CONTENT_TYPE);
        assertThat(testStaff.getCert2()).isEqualTo(DEFAULT_CERT_2);
        assertThat(testStaff.getCert2ContentType()).isEqualTo(DEFAULT_CERT_2_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createStaffWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = staffRepository.findAll().size();

        // Create the Staff with an existing ID
        staff.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStaffMockMvc.perform(post("/api/staff")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staff)))
            .andExpect(status().isBadRequest());

        // Validate the Staff in the database
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = staffRepository.findAll().size();
        // set the field null
        staff.setAddress(null);

        // Create the Staff, which fails.

        restStaffMockMvc.perform(post("/api/staff")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staff)))
            .andExpect(status().isBadRequest());

        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = staffRepository.findAll().size();
        // set the field null
        staff.setPaddress(null);

        // Create the Staff, which fails.

        restStaffMockMvc.perform(post("/api/staff")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staff)))
            .andExpect(status().isBadRequest());

        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStaff() throws Exception {
        // Initialize the database
        staffRepository.saveAndFlush(staff);

        // Get all the staffList
        restStaffMockMvc.perform(get("/api/staff?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(staff.getId().intValue())))
            .andExpect(jsonPath("$.[*].stafftid").value(hasItem(DEFAULT_STAFFTID)))
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
            .andExpect(jsonPath("$.[*].cert2").value(hasItem(Base64Utils.encodeToString(DEFAULT_CERT_2))));
    }
    
    @Test
    @Transactional
    public void getStaff() throws Exception {
        // Initialize the database
        staffRepository.saveAndFlush(staff);

        // Get the staff
        restStaffMockMvc.perform(get("/api/staff/{id}", staff.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(staff.getId().intValue()))
            .andExpect(jsonPath("$.stafftid").value(DEFAULT_STAFFTID))
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
            .andExpect(jsonPath("$.cert2").value(Base64Utils.encodeToString(DEFAULT_CERT_2)));
    }

    @Test
    @Transactional
    public void getNonExistingStaff() throws Exception {
        // Get the staff
        restStaffMockMvc.perform(get("/api/staff/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStaff() throws Exception {
        // Initialize the database
        staffRepository.saveAndFlush(staff);

        int databaseSizeBeforeUpdate = staffRepository.findAll().size();

        // Update the staff
        Staff updatedStaff = staffRepository.findById(staff.getId()).get();
        // Disconnect from session so that the updates on updatedStaff are not directly saved in db
        em.detach(updatedStaff);
        updatedStaff
            .stafftid(UPDATED_STAFFTID)
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
            .cert2ContentType(UPDATED_CERT_2_CONTENT_TYPE);

        restStaffMockMvc.perform(put("/api/staff")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStaff)))
            .andExpect(status().isOk());

        // Validate the Staff in the database
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeUpdate);
        Staff testStaff = staffList.get(staffList.size() - 1);
        assertThat(testStaff.getStafftid()).isEqualTo(UPDATED_STAFFTID);
        assertThat(testStaff.getfName()).isEqualTo(UPDATED_F_NAME);
        assertThat(testStaff.getmName()).isEqualTo(UPDATED_M_NAME);
        assertThat(testStaff.getlName()).isEqualTo(UPDATED_L_NAME);
        assertThat(testStaff.getuName()).isEqualTo(UPDATED_U_NAME);
        assertThat(testStaff.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testStaff.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testStaff.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testStaff.getMarital()).isEqualTo(UPDATED_MARITAL);
        assertThat(testStaff.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testStaff.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStaff.getPaddress()).isEqualTo(UPDATED_PADDRESS);
        assertThat(testStaff.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testStaff.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testStaff.getCert1()).isEqualTo(UPDATED_CERT_1);
        assertThat(testStaff.getCert1ContentType()).isEqualTo(UPDATED_CERT_1_CONTENT_TYPE);
        assertThat(testStaff.getCert2()).isEqualTo(UPDATED_CERT_2);
        assertThat(testStaff.getCert2ContentType()).isEqualTo(UPDATED_CERT_2_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingStaff() throws Exception {
        int databaseSizeBeforeUpdate = staffRepository.findAll().size();

        // Create the Staff

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStaffMockMvc.perform(put("/api/staff")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staff)))
            .andExpect(status().isBadRequest());

        // Validate the Staff in the database
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStaff() throws Exception {
        // Initialize the database
        staffRepository.saveAndFlush(staff);

        int databaseSizeBeforeDelete = staffRepository.findAll().size();

        // Get the staff
        restStaffMockMvc.perform(delete("/api/staff/{id}", staff.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Staff.class);
        Staff staff1 = new Staff();
        staff1.setId(1L);
        Staff staff2 = new Staff();
        staff2.setId(staff1.getId());
        assertThat(staff1).isEqualTo(staff2);
        staff2.setId(2L);
        assertThat(staff1).isNotEqualTo(staff2);
        staff1.setId(null);
        assertThat(staff1).isNotEqualTo(staff2);
    }
}
