package com.ocmt.web.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "studentid")
    private Integer studentid;

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
    @Column(name = "jhi_type")
    private Type type;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "referral")
    private Referral referral;

    @Enumerated(EnumType.STRING)
    @Column(name = "whythiscourse")
    private Whythiscourse whythiscourse;

    @Enumerated(EnumType.STRING)
    @Column(name = "secondary")
    private Secondary secondary;

    @Column(name = "schoolname")
    private String schoolname;

    @Column(name = "schoolgraddate")
    private LocalDate schoolgraddate;

    @NotNull
    @Size(max = 100)
    @Column(name = "schooladdress", length = 100, nullable = false)
    private String schooladdress;

    @Enumerated(EnumType.STRING)
    @Column(name = "univ")
    private Univ univ;

    @Column(name = "univname")
    private String univname;

    @Column(name = "univgraddate")
    private LocalDate univgraddate;

    @NotNull
    @Size(max = 100)
    @Column(name = "univaddress", length = 100, nullable = false)
    private String univaddress;

    @Lob
    @Column(name = "sop")
    private byte[] sop;

    @Column(name = "sop_content_type")
    private String sopContentType;

    @Lob
    @Column(name = "scorecard")
    private byte[] scorecard;

    @Column(name = "scorecard_content_type")
    private String scorecardContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "programtype")
    private Programtype programtype;

    @Enumerated(EnumType.STRING)
    @Column(name = "courses")
    private Courses courses;

    @Column(name = "gpa")
    private Float gpa;

    @Enumerated(EnumType.STRING)
    @Column(name = "payments")
    private Payments payments;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public Student studentid(Integer studentid) {
        this.studentid = studentid;
        return this;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getfName() {
        return fName;
    }

    public Student fName(String fName) {
        this.fName = fName;
        return this;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public Student mName(String mName) {
        this.mName = mName;
        return this;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public Student lName(String lName) {
        this.lName = lName;
        return this;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getuName() {
        return uName;
    }

    public Student uName(String uName) {
        this.uName = uName;
        return this;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public Student password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public Student type(Type type) {
        this.type = type;
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Title getTitle() {
        return title;
    }

    public Student title(Title title) {
        this.title = title;
        return this;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Gender getGender() {
        return gender;
    }

    public Student gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Marital getMarital() {
        return marital;
    }

    public Student marital(Marital marital) {
        this.marital = marital;
        return this;
    }

    public void setMarital(Marital marital) {
        this.marital = marital;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Student dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public Student address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaddress() {
        return paddress;
    }

    public Student paddress(String paddress) {
        this.paddress = paddress;
        return this;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getEmail() {
        return email;
    }

    public Student email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Student phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Referral getReferral() {
        return referral;
    }

    public Student referral(Referral referral) {
        this.referral = referral;
        return this;
    }

    public void setReferral(Referral referral) {
        this.referral = referral;
    }

    public Whythiscourse getWhythiscourse() {
        return whythiscourse;
    }

    public Student whythiscourse(Whythiscourse whythiscourse) {
        this.whythiscourse = whythiscourse;
        return this;
    }

    public void setWhythiscourse(Whythiscourse whythiscourse) {
        this.whythiscourse = whythiscourse;
    }

    public Secondary getSecondary() {
        return secondary;
    }

    public Student secondary(Secondary secondary) {
        this.secondary = secondary;
        return this;
    }

    public void setSecondary(Secondary secondary) {
        this.secondary = secondary;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public Student schoolname(String schoolname) {
        this.schoolname = schoolname;
        return this;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public LocalDate getSchoolgraddate() {
        return schoolgraddate;
    }

    public Student schoolgraddate(LocalDate schoolgraddate) {
        this.schoolgraddate = schoolgraddate;
        return this;
    }

    public void setSchoolgraddate(LocalDate schoolgraddate) {
        this.schoolgraddate = schoolgraddate;
    }

    public String getSchooladdress() {
        return schooladdress;
    }

    public Student schooladdress(String schooladdress) {
        this.schooladdress = schooladdress;
        return this;
    }

    public void setSchooladdress(String schooladdress) {
        this.schooladdress = schooladdress;
    }

    public Univ getUniv() {
        return univ;
    }

    public Student univ(Univ univ) {
        this.univ = univ;
        return this;
    }

    public void setUniv(Univ univ) {
        this.univ = univ;
    }

    public String getUnivname() {
        return univname;
    }

    public Student univname(String univname) {
        this.univname = univname;
        return this;
    }

    public void setUnivname(String univname) {
        this.univname = univname;
    }

    public LocalDate getUnivgraddate() {
        return univgraddate;
    }

    public Student univgraddate(LocalDate univgraddate) {
        this.univgraddate = univgraddate;
        return this;
    }

    public void setUnivgraddate(LocalDate univgraddate) {
        this.univgraddate = univgraddate;
    }

    public String getUnivaddress() {
        return univaddress;
    }

    public Student univaddress(String univaddress) {
        this.univaddress = univaddress;
        return this;
    }

    public void setUnivaddress(String univaddress) {
        this.univaddress = univaddress;
    }

    public byte[] getSop() {
        return sop;
    }

    public Student sop(byte[] sop) {
        this.sop = sop;
        return this;
    }

    public void setSop(byte[] sop) {
        this.sop = sop;
    }

    public String getSopContentType() {
        return sopContentType;
    }

    public Student sopContentType(String sopContentType) {
        this.sopContentType = sopContentType;
        return this;
    }

    public void setSopContentType(String sopContentType) {
        this.sopContentType = sopContentType;
    }

    public byte[] getScorecard() {
        return scorecard;
    }

    public Student scorecard(byte[] scorecard) {
        this.scorecard = scorecard;
        return this;
    }

    public void setScorecard(byte[] scorecard) {
        this.scorecard = scorecard;
    }

    public String getScorecardContentType() {
        return scorecardContentType;
    }

    public Student scorecardContentType(String scorecardContentType) {
        this.scorecardContentType = scorecardContentType;
        return this;
    }

    public void setScorecardContentType(String scorecardContentType) {
        this.scorecardContentType = scorecardContentType;
    }

    public Programtype getProgramtype() {
        return programtype;
    }

    public Student programtype(Programtype programtype) {
        this.programtype = programtype;
        return this;
    }

    public void setProgramtype(Programtype programtype) {
        this.programtype = programtype;
    }

    public Courses getCourses() {
        return courses;
    }

    public Student courses(Courses courses) {
        this.courses = courses;
        return this;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Float getGpa() {
        return gpa;
    }

    public Student gpa(Float gpa) {
        this.gpa = gpa;
        return this;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public Payments getPayments() {
        return payments;
    }

    public Student payments(Payments payments) {
        this.payments = payments;
        return this;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
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
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", studentid=" + getStudentid() +
            ", fName='" + getfName() + "'" +
            ", mName='" + getmName() + "'" +
            ", lName='" + getlName() + "'" +
            ", uName='" + getuName() + "'" +
            ", password='" + getPassword() + "'" +
            ", type='" + getType() + "'" +
            ", title='" + getTitle() + "'" +
            ", gender='" + getGender() + "'" +
            ", marital='" + getMarital() + "'" +
            ", dob='" + getDob() + "'" +
            ", address='" + getAddress() + "'" +
            ", paddress='" + getPaddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", referral='" + getReferral() + "'" +
            ", whythiscourse='" + getWhythiscourse() + "'" +
            ", secondary='" + getSecondary() + "'" +
            ", schoolname='" + getSchoolname() + "'" +
            ", schoolgraddate='" + getSchoolgraddate() + "'" +
            ", schooladdress='" + getSchooladdress() + "'" +
            ", univ='" + getUniv() + "'" +
            ", univname='" + getUnivname() + "'" +
            ", univgraddate='" + getUnivgraddate() + "'" +
            ", univaddress='" + getUnivaddress() + "'" +
            ", sop='" + getSop() + "'" +
            ", sopContentType='" + getSopContentType() + "'" +
            ", scorecard='" + getScorecard() + "'" +
            ", scorecardContentType='" + getScorecardContentType() + "'" +
            ", programtype='" + getProgramtype() + "'" +
            ", courses='" + getCourses() + "'" +
            ", gpa=" + getGpa() +
            ", payments='" + getPayments() + "'" +
            "}";
    }
}
