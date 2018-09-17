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

/**
 * A Staff.
 */
@Entity
@Table(name = "staff")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stafftid")
    private Integer stafftid;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStafftid() {
        return stafftid;
    }

    public Staff stafftid(Integer stafftid) {
        this.stafftid = stafftid;
        return this;
    }

    public void setStafftid(Integer stafftid) {
        this.stafftid = stafftid;
    }

    public String getfName() {
        return fName;
    }

    public Staff fName(String fName) {
        this.fName = fName;
        return this;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public Staff mName(String mName) {
        this.mName = mName;
        return this;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public Staff lName(String lName) {
        this.lName = lName;
        return this;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getuName() {
        return uName;
    }

    public Staff uName(String uName) {
        this.uName = uName;
        return this;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public Staff password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Title getTitle() {
        return title;
    }

    public Staff title(Title title) {
        this.title = title;
        return this;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Gender getGender() {
        return gender;
    }

    public Staff gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Marital getMarital() {
        return marital;
    }

    public Staff marital(Marital marital) {
        this.marital = marital;
        return this;
    }

    public void setMarital(Marital marital) {
        this.marital = marital;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Staff dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public Staff address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaddress() {
        return paddress;
    }

    public Staff paddress(String paddress) {
        this.paddress = paddress;
        return this;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getEmail() {
        return email;
    }

    public Staff email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Staff phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getCert1() {
        return cert1;
    }

    public Staff cert1(byte[] cert1) {
        this.cert1 = cert1;
        return this;
    }

    public void setCert1(byte[] cert1) {
        this.cert1 = cert1;
    }

    public String getCert1ContentType() {
        return cert1ContentType;
    }

    public Staff cert1ContentType(String cert1ContentType) {
        this.cert1ContentType = cert1ContentType;
        return this;
    }

    public void setCert1ContentType(String cert1ContentType) {
        this.cert1ContentType = cert1ContentType;
    }

    public byte[] getCert2() {
        return cert2;
    }

    public Staff cert2(byte[] cert2) {
        this.cert2 = cert2;
        return this;
    }

    public void setCert2(byte[] cert2) {
        this.cert2 = cert2;
    }

    public String getCert2ContentType() {
        return cert2ContentType;
    }

    public Staff cert2ContentType(String cert2ContentType) {
        this.cert2ContentType = cert2ContentType;
        return this;
    }

    public void setCert2ContentType(String cert2ContentType) {
        this.cert2ContentType = cert2ContentType;
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
        Staff staff = (Staff) o;
        if (staff.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), staff.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Staff{" +
            "id=" + getId() +
            ", stafftid=" + getStafftid() +
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
            "}";
    }
}
