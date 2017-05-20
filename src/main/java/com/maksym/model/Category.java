package com.maksym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    private int idCat;
    private String categoryName;
    private Set<HospitalStaff> hospitalStaffs;

    @Id
    @Column(name = "IdCat", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    @Basic
    @Column(name = "CategoryName", length = 30)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @OneToMany(mappedBy = "idCat")
    @JsonIgnore
    public Set<HospitalStaff> getHospitalStaffs() {
        return hospitalStaffs;
    }

    public void setHospitalStaffs(Set<HospitalStaff> hospitalStaffs) {
        this.hospitalStaffs = hospitalStaffs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (idCat != category.idCat) return false;
        if (categoryName != null ? !categoryName.equals(category.categoryName) : category.categoryName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCat;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }
}
