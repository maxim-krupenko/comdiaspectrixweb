package com.maksym.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="accommodation")
public class Accommodation implements Serializable {

    @Id
    @Column(name = "IdFA", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFa;

    @Basic
    @Column(name = "postIndex")
    private Integer postIndex;
    @Basic
    @Column(name = "country", length = 25)
    private String country;

    @Basic
    @Column(name = "area", length = 40)
    private String area;

    @Basic
    @Column(name = "district", length = 40)
    private String district;

    @Basic
    @Column(name = "locality", length = 50)
    private String locality;

    @Basic
    @Column(name = "street", length = 30)
    private String street;

    @Basic
    @Column(name = "house", length = 10)
    private String house;

    @Basic
    @Column(name = "flat")
    private Integer flat;

    @Basic
    @Column(name = "idP")
    private Integer idP;

    public Accommodation() {
    }

    public int getIdFa() {
        return idFa;
    }

    public void setIdFa(int idFa) {
        this.idFa = idFa;
    }

    public Integer getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(Integer postIndex) {
        this.postIndex = postIndex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public Integer getIdP() {
        return idP;
    }

    public void setIdP(Integer idP) {
        this.idP = idP;
    }

}
