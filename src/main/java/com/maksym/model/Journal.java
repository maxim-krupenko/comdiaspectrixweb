package com.maksym.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "journal")
public class Journal {
    private int idJ;
    private Date dateOfWork;
    private Time timeOfWork;
    private HospitalStaff idHs;
    private String enter;
    private String quit;
    private String inputDataInTable;
    private String useQuery;

    @Id
    @Column(name = "IdJ", nullable = false)
    public int getIdJ() {
        return idJ;
    }

    public void setIdJ(int idJ) {
        this.idJ = idJ;
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

    @Basic
    @Column(name = "Enter", nullable = true, length = 1)
    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    @Basic
    @Column(name = "Quit", nullable = true, length = 1)
    public String getQuit() {
        return quit;
    }

    public void setQuit(String quit) {
        this.quit = quit;
    }

    @Basic
    @Column(name = "InputDataInTable", nullable = true, length = 30)
    public String getInputDataInTable() {
        return inputDataInTable;
    }

    public void setInputDataInTable(String inputDataInTable) {
        this.inputDataInTable = inputDataInTable;
    }

    @Basic
    @Column(name = "UseQuery", nullable = true, length = 40)
    public String getUseQuery() {
        return useQuery;
    }

    public void setUseQuery(String useQuery) {
        this.useQuery = useQuery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journal journal = (Journal) o;

        if (idJ != journal.idJ) return false;
        if (dateOfWork != null ? !dateOfWork.equals(journal.dateOfWork) : journal.dateOfWork != null) return false;
        if (timeOfWork != null ? !timeOfWork.equals(journal.timeOfWork) : journal.timeOfWork != null) return false;
        if (idHs != null ? !idHs.equals(journal.idHs) : journal.idHs != null) return false;
        if (enter != null ? !enter.equals(journal.enter) : journal.enter != null) return false;
        if (quit != null ? !quit.equals(journal.quit) : journal.quit != null) return false;
        if (inputDataInTable != null ? !inputDataInTable.equals(journal.inputDataInTable) : journal.inputDataInTable != null)
            return false;
        if (useQuery != null ? !useQuery.equals(journal.useQuery) : journal.useQuery != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idJ;
        result = 31 * result + (dateOfWork != null ? dateOfWork.hashCode() : 0);
        result = 31 * result + (timeOfWork != null ? timeOfWork.hashCode() : 0);
        result = 31 * result + (idHs != null ? idHs.hashCode() : 0);
        result = 31 * result + (enter != null ? enter.hashCode() : 0);
        result = 31 * result + (quit != null ? quit.hashCode() : 0);
        result = 31 * result + (inputDataInTable != null ? inputDataInTable.hashCode() : 0);
        result = 31 * result + (useQuery != null ? useQuery.hashCode() : 0);
        return result;
    }
}
