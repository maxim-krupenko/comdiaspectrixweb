package com.maksym.model;

import javax.persistence.*;

@Entity
@Table(name = "f1_accommodation")
public class F1Accommodation {
    private int idFa;
    private Integer postIndex;
    private String country;

    @Id
    @Column(name = "IdFA", nullable = false)
    public int getIdFa() {
        return idFa;
    }

    public void setIdFa(int idFa) {
        this.idFa = idFa;
    }

    @Basic
    @Column(name = "PostIndex", nullable = true)
    public Integer getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(Integer postIndex) {
        this.postIndex = postIndex;
    }

    @Basic
    @Column(name = "Country", nullable = true, length = 25)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        F1Accommodation that = (F1Accommodation) o;

        if (idFa != that.idFa) return false;
        if (postIndex != null ? !postIndex.equals(that.postIndex) : that.postIndex != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFa;
        result = 31 * result + (postIndex != null ? postIndex.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
