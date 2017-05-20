package com.maksym.model;

import javax.persistence.*;

@Entity
@Table(name = "f2_accommodation")
public class F2Accommodation {
    private int idFa2;
    private String country;
    private Integer postIndex;

    @Id
    @Column(name = "IdFA2", nullable = false)
    public int getIdFa2() {
        return idFa2;
    }

    public void setIdFa2(int idFa2) {
        this.idFa2 = idFa2;
    }

    @Basic
    @Column(name = "Country", nullable = true, length = 25)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "PostIndex", nullable = true)
    public Integer getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(Integer postIndex) {
        this.postIndex = postIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        F2Accommodation that = (F2Accommodation) o;

        if (idFa2 != that.idFa2) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (postIndex != null ? !postIndex.equals(that.postIndex) : that.postIndex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFa2;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (postIndex != null ? postIndex.hashCode() : 0);
        return result;
    }
}
