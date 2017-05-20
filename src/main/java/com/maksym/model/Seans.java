package com.maksym.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "seans")
public class Seans {
    private int idS;
    private Date sessionDate;
    private String diagnosis;
    private String appointment;
    private Patient idP;
    private HospitalStaff idHs;
    private Kvvvfloat idKv;
    private DiagnosticGroup idDg;

    @Id
    @Column(name = "IdS", nullable = false)
    public int getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    @Basic
    @Column(name = "Session_date", nullable = true)
    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Basic
    @Column(name = "Diagnosis", nullable = true, length = 200)
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Basic
    @Column(name = "Appointment", nullable = true, length = 200)
    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdP")
    public Patient getIdP() {
        return idP;
    }

    public void setIdP(Patient idP) {
        this.idP = idP;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdHS")
    public HospitalStaff getIdHs() {
        return idHs;
    }

    public void setIdHs(HospitalStaff idHs) {
        this.idHs = idHs;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdKV")
    public Kvvvfloat getIdKv() {
        return idKv;
    }

    public void setIdKv(Kvvvfloat idKv) {
        this.idKv = idKv;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdDG")
    public DiagnosticGroup getIdDg() {
        return idDg;
    }

    public void setIdDg(DiagnosticGroup idDg) {
        this.idDg = idDg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seans seans = (Seans) o;

        if (idS != seans.idS) return false;
        if (sessionDate != null ? !sessionDate.equals(seans.sessionDate) : seans.sessionDate != null) return false;
        if (diagnosis != null ? !diagnosis.equals(seans.diagnosis) : seans.diagnosis != null) return false;
        if (appointment != null ? !appointment.equals(seans.appointment) : seans.appointment != null) return false;
        if (idP != null ? !idP.equals(seans.idP) : seans.idP != null) return false;
        if (idHs != null ? !idHs.equals(seans.idHs) : seans.idHs != null) return false;
        if (idKv != null ? !idKv.equals(seans.idKv) : seans.idKv != null) return false;
        if (idDg != null ? !idDg.equals(seans.idDg) : seans.idDg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idS;
        result = 31 * result + (sessionDate != null ? sessionDate.hashCode() : 0);
        result = 31 * result + (diagnosis != null ? diagnosis.hashCode() : 0);
        result = 31 * result + (appointment != null ? appointment.hashCode() : 0);
        result = 31 * result + (idP != null ? idP.hashCode() : 0);
        result = 31 * result + (idHs != null ? idHs.hashCode() : 0);
        result = 31 * result + (idKv != null ? idKv.hashCode() : 0);
        result = 31 * result + (idDg != null ? idDg.hashCode() : 0);
        return result;
    }
}
