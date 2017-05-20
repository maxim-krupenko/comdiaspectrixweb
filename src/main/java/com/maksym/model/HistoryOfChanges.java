package com.maksym.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "history_of_changes")
public class HistoryOfChanges {
    private int idHc;
    private Date dateOfWork;
    private Time timeOfWork;
    private HospitalStaff idHs;
    private DiagnosticGroup idDg;

    @Id
    @Column(name = "IdHC", nullable = false)
    public int getIdHc() {
        return idHc;
    }

    public void setIdHc(int idHc) {
        this.idHc = idHc;
    }

    @Basic
    @Column(name = "DateOfWork", nullable = true)
    public Date getDateOfWork() {
        return dateOfWork;
    }

    public void setDateOfWork(Date dateOfWork) {
        this.dateOfWork = dateOfWork;
    }

    @Basic
    @Column(name = "TimeOfWork", nullable = true)
    public Time getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(Time timeOfWork) {
        this.timeOfWork = timeOfWork;
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

        HistoryOfChanges that = (HistoryOfChanges) o;

        if (idHc != that.idHc) return false;
        if (dateOfWork != null ? !dateOfWork.equals(that.dateOfWork) : that.dateOfWork != null) return false;
        if (timeOfWork != null ? !timeOfWork.equals(that.timeOfWork) : that.timeOfWork != null) return false;
        if (idHs != null ? !idHs.equals(that.idHs) : that.idHs != null) return false;
        if (idDg != null ? !idDg.equals(that.idDg) : that.idDg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHc;
        result = 31 * result + (dateOfWork != null ? dateOfWork.hashCode() : 0);
        result = 31 * result + (timeOfWork != null ? timeOfWork.hashCode() : 0);
        result = 31 * result + (idHs != null ? idHs.hashCode() : 0);
        result = 31 * result + (idDg != null ? idDg.hashCode() : 0);
        return result;
    }
}
