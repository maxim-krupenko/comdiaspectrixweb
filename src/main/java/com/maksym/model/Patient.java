package com.maksym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {
    private int idP;
    private String nMedCard;
    private String fio;
    private String gender;
    private Date bDay;
    private String tel;
    private String passport;
    private Date accDate;
    private String disease;
    private String allergy;
    private String eMail;
    private Institution idInst;
    private DiagnosticGroup idDg;
    private String surname;
    private String name;
    private String middlename;
    private Set<Seans> seanses;

    @Id
    @Column(name = "IdP", nullable = false)
    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    @Basic
    @Column(name = "N_med_card", length = 11)
    public String getnMedCard() {
        return nMedCard;
    }

    public void setnMedCard(String nMedCard) {
        this.nMedCard = nMedCard;
    }

    @Basic
    @Column(name = "FIO", length = 70)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "Gender", length = 1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "B_day")
    public Date getbDay() {
        return bDay;
    }

    public void setbDay(Date bDay) {
        this.bDay = bDay;
    }

    @Basic
    @Column(name = "Tel", length = 25)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "Passport", length = 15)
    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Basic
    @Column(name = "Acc_date")
    public Date getAccDate() {
        return accDate;
    }

    public void setAccDate(Date accDate) {
        this.accDate = accDate;
    }

    @Basic
    @Column(name = "Disease", length = 100)
    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Basic
    @Column(name = "Allergy", length = 100)
    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    @Basic
    @Column(name = "E_mail", length = 30)
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdInst")
    public Institution getIdInst() {
        return idInst;
    }

    public void setIdInst(Institution idInst) {
        this.idInst = idInst;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdDG")
    public DiagnosticGroup getIdDg() {
        return idDg;
    }

    public void setIdDg(DiagnosticGroup idDg) {
        this.idDg = idDg;
    }

    @Basic
    @Column(name = "surname", length = 45)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "middlename", length = 45)
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @OneToMany(mappedBy = "idP")
    @JsonIgnore
    public Set<Seans> getSeanses() {
        return seanses;
    }

    public void setSeanses(Set<Seans> seanses) {
        this.seanses = seanses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (idP != patient.idP) return false;
        if (nMedCard != null ? !nMedCard.equals(patient.nMedCard) : patient.nMedCard != null) return false;
        if (fio != null ? !fio.equals(patient.fio) : patient.fio != null) return false;
        if (gender != null ? !gender.equals(patient.gender) : patient.gender != null) return false;
        if (bDay != null ? !bDay.equals(patient.bDay) : patient.bDay != null) return false;
        if (tel != null ? !tel.equals(patient.tel) : patient.tel != null) return false;
        if (passport != null ? !passport.equals(patient.passport) : patient.passport != null) return false;
        if (accDate != null ? !accDate.equals(patient.accDate) : patient.accDate != null) return false;
        if (disease != null ? !disease.equals(patient.disease) : patient.disease != null) return false;
        if (allergy != null ? !allergy.equals(patient.allergy) : patient.allergy != null) return false;
        if (eMail != null ? !eMail.equals(patient.eMail) : patient.eMail != null) return false;
        if (idInst != null ? !idInst.equals(patient.idInst) : patient.idInst != null) return false;
        if (idDg != null ? !idDg.equals(patient.idDg) : patient.idDg != null) return false;
        if (surname != null ? !surname.equals(patient.surname) : patient.surname != null) return false;
        if (name != null ? !name.equals(patient.name) : patient.name != null) return false;
        if (middlename != null ? !middlename.equals(patient.middlename) : patient.middlename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idP;
        result = 31 * result + (nMedCard != null ? nMedCard.hashCode() : 0);
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (bDay != null ? bDay.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (accDate != null ? accDate.hashCode() : 0);
        result = 31 * result + (disease != null ? disease.hashCode() : 0);
        result = 31 * result + (allergy != null ? allergy.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (idInst != null ? idInst.hashCode() : 0);
        result = 31 * result + (idDg != null ? idDg.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        return result;
    }
}
