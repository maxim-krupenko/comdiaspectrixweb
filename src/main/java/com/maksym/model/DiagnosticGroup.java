package com.maksym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnostic_group")
public class DiagnosticGroup {
    private Integer idDg;
    private String groupName;
    private String description;
    private Kvvvfloat idKv;
    private Set<HistoryOfChanges> historyofchanges;
    private Set<Patient> patients;
    private Set<Seans> seanses;

    @Id
    @Column(name = "IdDG", nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdDg() {
        return idDg;
    }

    public void setIdDg(int idDg) {
        this.idDg = idDg;
    }

    @Basic
    @Column(name = "GroupName", nullable = true, length = 20)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdKV")
    public Kvvvfloat getIdKv() {
        return idKv;
    }

    public void setIdKv(Kvvvfloat idKv) {
        this.idKv = idKv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiagnosticGroup that = (DiagnosticGroup) o;

        if (idDg != that.idDg) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (idKv != null ? !idKv.equals(that.idKv) : that.idKv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDg;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (idKv != null ? idKv.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "idDg", fetch = FetchType.EAGER)
    @JsonIgnore
    public Set<HistoryOfChanges> getHistoryofchanges() {
        return historyofchanges;
    }

    public void setHistoryofchanges(Set<HistoryOfChanges> historyofchanges) {
        this.historyofchanges = historyofchanges;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idDg")
    @JsonIgnore
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idDg")
    @JsonIgnore
    public Set<Seans> getSeanses() {
        return seanses;
    }

    public void setSeanses(Set<Seans> seanses) {
        this.seanses = seanses;
    }
}
