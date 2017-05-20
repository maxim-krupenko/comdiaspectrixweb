package com.maksym.model;


import javax.persistence.*;

@Entity
public class City {
    private int id;
    private int countryId;
    private String name;
    private String state;
    private String region;
    private Byte biggestCity;
    private String street;
    private String village;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "country_id", nullable = false)
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "region", nullable = false)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "biggest_city")
    public Byte getBiggestCity() {
        return biggestCity;
    }

    public void setBiggestCity(Byte biggestCity) {
        this.biggestCity = biggestCity;
    }

    @Basic
    @Column(name = "street", length = 100)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "village", length = 100)
    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != city.id) return false;
        if (countryId != city.countryId) return false;
        if (this.name != null ? !this.name.equals(city.name) : city.name != null) return false;
        if (state != null ? !state.equals(city.state) : city.state != null) return false;
        if (region != null ? !region.equals(city.region) : city.region != null) return false;
        if (biggestCity != null ? !biggestCity.equals(city.biggestCity) : city.biggestCity != null) return false;
        if (street != null ? !street.equals(city.street) : city.street != null) return false;
        if (village != null ? !village.equals(city.village) : city.village != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + countryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (biggestCity != null ? biggestCity.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (village != null ? village.hashCode() : 0);
        return result;
    }
}
