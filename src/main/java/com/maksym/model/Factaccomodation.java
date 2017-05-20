package com.maksym.model;

import javax.persistence.*;

@Entity
@Table(name = "fact_accomodation")
public class Factaccomodation {
    private int idP;
    private Integer postIndex;
    private String country;
    private String area;
    private String district;
    private String locality;
    private String city;
    private String street;
    private String house;
    private Integer flat;

    @Id
    @Column(name = "IdP", nullable = false)
    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
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

    @Basic
    @Column(name = "Area", nullable = true, length = 40)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "District", nullable = true, length = 50)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "Locality", nullable = true, length = 50)
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Basic
    @Column(name = "City", nullable = true, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "Street", nullable = true, length = 30)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "House", nullable = true, length = 10)
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Basic
    @Column(name = "Flat", nullable = true)
    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Factaccomodation that = (Factaccomodation) o;

        if (idP != that.idP) return false;
        if (postIndex != null ? !postIndex.equals(that.postIndex) : that.postIndex != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (locality != null ? !locality.equals(that.locality) : that.locality != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (house != null ? !house.equals(that.house) : that.house != null) return false;
        if (flat != null ? !flat.equals(that.flat) : that.flat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idP;
        result = 31 * result + (postIndex != null ? postIndex.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        return result;
    }
}
