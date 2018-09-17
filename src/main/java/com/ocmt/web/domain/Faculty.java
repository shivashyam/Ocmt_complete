package com.ocmt.web.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.ocmt.web.domain.enumeration.Title;

import com.ocmt.web.domain.enumeration.Gender;

import com.ocmt.web.domain.enumeration.Marital;

import com.ocmt.web.domain.enumeration.Dept;

import com.ocmt.web.domain.enumeration.Courses;

/**
 * A Faculty.
 */
@Entity
@Table(name = "faculty")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Faculty implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facultyid")
    private Integer facultyid;

    @Column(name = "f_name")
    private String fName;

    @Column(name = "m_name")
    private String mName;

    @Column(name = "l_name")
    private String lName;

    @Column(name = "u_name")
    private String uName;

    @Column(name = "jhi_password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private Title title;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital")
    private Marital marital;

    @Column(name = "dob")
    private LocalDate dob;

    @NotNull
    @Size(max = 100)
    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @NotNull
    @Size(max = 100)
    @Column(name = "paddress", length = 100, nullable = false)
    private String paddress;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Lob
    @Column(name = "cert_1")
    private byte[] cert1;

    @Column(name = "cert_1_content_type")
    private String cert1ContentType;

    @Lob
    @Column(name = "cert_2")
    private byte[] cert2;

    @Column(name = "cert_2_content_type")
    private String cert2ContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "dept")
    private Dept dept;

    @Enumerated(EnumType.STRING)
    @Column(name = "courses")
    private Courses courses;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFacultyid() {
        return facultyid;
    }

    public Faculty facultyid(Integer facultyid) {
        this.facultyid = facultyid;
        return this;
    }

    public void setFacultyid(Integer facultyid) {
        this.facultyid = facultyid;
    }

    public String getfName() {
        return fName;
    }

    public Faculty fName(String fName) {
        this.fName = fName;
        return this;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public Faculty mName(String mName) {
        this.mName = mName;
        return this;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public Faculty lName(String lName) {
        this.lName = lName;
        return this;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getuName() {
        return uName;
    }

    public Faculty uName(String uName) {
        this.uName = uName;
        return this;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public Faculty password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Title getTitle() {
        return title;
    }

    public Faculty title(Title title) {
        this.title = title;
        return this;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Gender getGender() {
        return gender;
    }

    public Faculty gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Marital getMarital() {
        return marital;
    }

    public Faculty marital(Marital marital) {
        this.marital = marital;
        return this;
    }

    public void setMarital(Marital marital) {
        this.marital = marital;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Faculty dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public Faculty address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaddress() {
        return paddress;
    }

    public Faculty paddress(String paddress) {
        this.paddress = paddress;
        return this;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getEmail() {
        return email;
    }

    public Faculty email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Faculty phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getCert1() {
        return cert1;
    }

    public Faculty cert1(byte[] cert1) {
        this.cert1 = cert1;
        return this;
    }

    public void setCert1(byte[] cert1) {
        this.cert1 = cert1;
    }

    public String getCert1ContentType() {
        return cert1ContentType;
    }

    public Faculty cert1ContentType(String cert1ContentType) {
        this.cert1ContentType = cert1ContentType;
        return this;
    }

    public void setCert1ContentType(String cert1ContentType) {
        this.cert1ContentType = cert1ContentType;
    }

    public byte[] getCert2() {
        return cert2;
    }

    public Faculty cert2(byte[] cert2) {
        this.cert2 = cert2;
        return this;
    }

    public void setCert2(byte[] cert2) {
        this.cert2 = cert2;
    }

    public String getCert2ContentType() {
        return cert2ContentType;
    }

    public Faculty cert2ContentType(String cert2ContentType) {
        this.cert2ContentType = cert2ContentType;
        return this;
    }

    public void setCert2ContentType(String cert2ContentType) {
        this.cert2ContentType = cert2ContentType;
    }

    public Dept getDept() {
        return dept;
    }

    public Faculty dept(Dept dept) {
        this.dept = dept;
        return this;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Courses getCourses() {
        return courses;
    }

    public Faculty courses(Courses courses) {
        this.courses = courses;
        return this;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Faculty faculty = (Faculty) o;
        if (faculty.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), faculty.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Faculty{" +
            "id=" + getId() +
            ", facultyid=" + getFacultyid() +
            ", fName='" + getfName() + "'" +
            ", mName='" + getmName() + "'" +
            ", lName='" + getlName() + "'" +
            ", uName='" + getuName() + "'" +
            ", password='" + getPassword() + "'" +
            ", title='" + getTitle() + "'" +
            ", gender='" + getGender() + "'" +
            ", marital='" + getMarital() + "'" +
            ", dob='" + getDob() + "'" +
            ", address='" + getAddress() + "'" +
            ", paddress='" + getPaddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", cert1='" + getCert1() + "'" +
            ", cert1ContentType='" + getCert1ContentType() + "'" +
            ", cert2='" + getCert2() + "'" +
            ", cert2ContentType='" + getCert2ContentType() + "'" +
            ", dept='" + getDept() + "'" +
            ", courses='" + getCourses() + "'" +
            "}";
    }
}
