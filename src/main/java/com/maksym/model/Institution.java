package com.maksym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "institution")
public class Institution {
    private int idInst;
    private String instName;
    private String address;

    private Set<Patient> patients;

    @Id
    @Column(name = "IdInst", nullable = false)
    public int getIdInst() {
        return idInst;
    }

    public void setIdInst(int idInst) {
        this.idInst = idInst;
    }

    @Basic
    @Column(name = "Inst_name", nullable = true, length = 60)
    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    @Basic
    @Column(name = "Address", nullable = true, length = 60)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "idInst",fetch = FetchType.EAGER)
    @JsonIgnore
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institution that = (Institution) o;

        if (idInst != that.idInst) return false;
        if (instName != null ? !instName.equals(that.instName) : that.instName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idInst;
        result = 31 * result + (instName != null ? instName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
