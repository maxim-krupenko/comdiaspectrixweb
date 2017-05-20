package com.maksym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "kvvv_float")
public class Kvvvfloat {
    private int idKv;
    private Double d2;
    private Double d3;
    private Double d4;
    private Double d5;
    private Double d6;
    private Double d8;
    private Double d11;
    private Double d15;
    private Double d20;
    private Double d26;
    private Double d36;
    private Double d40;
    private Double d65;
    private Double d85;
    private Double d120;
    private Double d150;
    private Double d210;
    private Double d290;
    private Double d300;
    private Double d520;
    private Double d700;
    private Double d950;
    private Double d1300;
    private Double d1700;
    private Double d2300;
    private Double d3100;
    private Double d4200;
    private Double d5600;
    private Double d7600;
    private Double d10200;
    private Double d13800;
    private Double d18500;

    private Set<DiagnosticGroup> diagnosticGroups;
    private Set<Seans> seanses;

    @Id
    @Column(name = "IdKV", nullable = false)
    public int getIdKv() {
        return idKv;
    }

    public void setIdKv(int idKv) {
        this.idKv = idKv;
    }

    @Basic
    @Column(name = "D2", nullable = true, precision = 2)
    public Double getD2() {
        return d2;
    }

    public void setD2(Double d2) {
        this.d2 = d2;
    }

    @Basic
    @Column(name = "D3", nullable = true, precision = 2)
    public Double getD3() {
        return d3;
    }

    public void setD3(Double d3) {
        this.d3 = d3;
    }

    @Basic
    @Column(name = "D4", nullable = true, precision = 2)
    public Double getD4() {
        return d4;
    }

    public void setD4(Double d4) {
        this.d4 = d4;
    }

    @Basic
    @Column(name = "D5", nullable = true, precision = 2)
    public Double getD5() {
        return d5;
    }

    public void setD5(Double d5) {
        this.d5 = d5;
    }

    @Basic
    @Column(name = "D6", nullable = true, precision = 2)
    public Double getD6() {
        return d6;
    }

    public void setD6(Double d6) {
        this.d6 = d6;
    }

    @Basic
    @Column(name = "D8", nullable = true, precision = 2)
    public Double getD8() {
        return d8;
    }

    public void setD8(Double d8) {
        this.d8 = d8;
    }

    @Basic
    @Column(name = "D11", nullable = true, precision = 2)
    public Double getD11() {
        return d11;
    }

    public void setD11(Double d11) {
        this.d11 = d11;
    }

    @Basic
    @Column(name = "D15", nullable = true, precision = 2)
    public Double getD15() {
        return d15;
    }

    public void setD15(Double d15) {
        this.d15 = d15;
    }

    @Basic
    @Column(name = "D20", nullable = true, precision = 2)
    public Double getD20() {
        return d20;
    }

    public void setD20(Double d20) {
        this.d20 = d20;
    }

    @Basic
    @Column(name = "D26", nullable = true, precision = 2)
    public Double getD26() {
        return d26;
    }

    public void setD26(Double d26) {
        this.d26 = d26;
    }

    @Basic
    @Column(name = "D36", nullable = true, precision = 2)
    public Double getD36() {
        return d36;
    }

    public void setD36(Double d36) {
        this.d36 = d36;
    }

    @Basic
    @Column(name = "D40", nullable = true, precision = 2)
    public Double getD40() {
        return d40;
    }

    public void setD40(Double d40) {
        this.d40 = d40;
    }

    @Basic
    @Column(name = "D65", nullable = true, precision = 2)
    public Double getD65() {
        return d65;
    }

    public void setD65(Double d65) {
        this.d65 = d65;
    }

    @Basic
    @Column(name = "D85", nullable = true, precision = 2)
    public Double getD85() {
        return d85;
    }

    public void setD85(Double d85) {
        this.d85 = d85;
    }

    @Basic
    @Column(name = "D120", nullable = true, precision = 2)
    public Double getD120() {
        return d120;
    }

    public void setD120(Double d120) {
        this.d120 = d120;
    }

    @Basic
    @Column(name = "D150", nullable = true, precision = 2)
    public Double getD150() {
        return d150;
    }

    public void setD150(Double d150) {
        this.d150 = d150;
    }

    @Basic
    @Column(name = "D210", nullable = true, precision = 2)
    public Double getD210() {
        return d210;
    }

    public void setD210(Double d210) {
        this.d210 = d210;
    }

    @Basic
    @Column(name = "D290", nullable = true, precision = 2)
    public Double getD290() {
        return d290;
    }

    public void setD290(Double d290) {
        this.d290 = d290;
    }

    @Basic
    @Column(name = "D300", nullable = true, precision = 2)
    public Double getD300() {
        return d300;
    }

    public void setD300(Double d300) {
        this.d300 = d300;
    }

    @Basic
    @Column(name = "D520", nullable = true, precision = 2)
    public Double getD520() {
        return d520;
    }

    public void setD520(Double d520) {
        this.d520 = d520;
    }

    @Basic
    @Column(name = "D700", nullable = true, precision = 2)
    public Double getD700() {
        return d700;
    }

    public void setD700(Double d700) {
        this.d700 = d700;
    }

    @Basic
    @Column(name = "D950", nullable = true, precision = 2)
    public Double getD950() {
        return d950;
    }

    public void setD950(Double d950) {
        this.d950 = d950;
    }

    @Basic
    @Column(name = "D1300", nullable = true, precision = 2)
    public Double getD1300() {
        return d1300;
    }

    public void setD1300(Double d1300) {
        this.d1300 = d1300;
    }

    @Basic
    @Column(name = "D1700", nullable = true, precision = 2)
    public Double getD1700() {
        return d1700;
    }

    public void setD1700(Double d1700) {
        this.d1700 = d1700;
    }

    @Basic
    @Column(name = "D2300", nullable = true, precision = 2)
    public Double getD2300() {
        return d2300;
    }

    public void setD2300(Double d2300) {
        this.d2300 = d2300;
    }

    @Basic
    @Column(name = "D3100", nullable = true, precision = 2)
    public Double getD3100() {
        return d3100;
    }

    public void setD3100(Double d3100) {
        this.d3100 = d3100;
    }

    @Basic
    @Column(name = "D4200", nullable = true, precision = 2)
    public Double getD4200() {
        return d4200;
    }

    public void setD4200(Double d4200) {
        this.d4200 = d4200;
    }

    @Basic
    @Column(name = "D5600", nullable = true, precision = 2)
    public Double getD5600() {
        return d5600;
    }

    public void setD5600(Double d5600) {
        this.d5600 = d5600;
    }

    @Basic
    @Column(name = "D7600", nullable = true, precision = 2)
    public Double getD7600() {
        return d7600;
    }

    public void setD7600(Double d7600) {
        this.d7600 = d7600;
    }

    @Basic
    @Column(name = "D10200", nullable = true, precision = 2)
    public Double getD10200() {
        return d10200;
    }

    public void setD10200(Double d10200) {
        this.d10200 = d10200;
    }

    @Basic
    @Column(name = "D13800", nullable = true, precision = 2)
    public Double getD13800() {
        return d13800;
    }

    public void setD13800(Double d13800) {
        this.d13800 = d13800;
    }

    @Basic
    @Column(name = "D18500", nullable = true, precision = 2)
    public Double getD18500() {
        return d18500;
    }

    public void setD18500(Double d18500) {
        this.d18500 = d18500;
    }

    @OneToMany(mappedBy = "idKv", fetch = FetchType.EAGER)
    @JsonIgnore
    public Set<DiagnosticGroup> getDiagnosticGroups() {
        return diagnosticGroups;
    }

    public void setDiagnosticGroups(Set<DiagnosticGroup> diagnosticGroups) {
        this.diagnosticGroups = diagnosticGroups;
    }

    @OneToMany(mappedBy = "idKv")
    @JsonIgnore
    public Set<Seans> getSeanses() {
        return seanses;
    }

    public void setSeanses(Set<Seans> seanses) {
        this.seanses = seanses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kvvvfloat kvvvfloat = (Kvvvfloat) o;

        if (idKv != kvvvfloat.idKv) return false;
        if (d2 != null ? !d2.equals(kvvvfloat.d2) : kvvvfloat.d2 != null) return false;
        if (d3 != null ? !d3.equals(kvvvfloat.d3) : kvvvfloat.d3 != null) return false;
        if (d4 != null ? !d4.equals(kvvvfloat.d4) : kvvvfloat.d4 != null) return false;
        if (d5 != null ? !d5.equals(kvvvfloat.d5) : kvvvfloat.d5 != null) return false;
        if (d6 != null ? !d6.equals(kvvvfloat.d6) : kvvvfloat.d6 != null) return false;
        if (d8 != null ? !d8.equals(kvvvfloat.d8) : kvvvfloat.d8 != null) return false;
        if (d11 != null ? !d11.equals(kvvvfloat.d11) : kvvvfloat.d11 != null) return false;
        if (d15 != null ? !d15.equals(kvvvfloat.d15) : kvvvfloat.d15 != null) return false;
        if (d20 != null ? !d20.equals(kvvvfloat.d20) : kvvvfloat.d20 != null) return false;
        if (d26 != null ? !d26.equals(kvvvfloat.d26) : kvvvfloat.d26 != null) return false;
        if (d36 != null ? !d36.equals(kvvvfloat.d36) : kvvvfloat.d36 != null) return false;
        if (d40 != null ? !d40.equals(kvvvfloat.d40) : kvvvfloat.d40 != null) return false;
        if (d65 != null ? !d65.equals(kvvvfloat.d65) : kvvvfloat.d65 != null) return false;
        if (d85 != null ? !d85.equals(kvvvfloat.d85) : kvvvfloat.d85 != null) return false;
        if (d120 != null ? !d120.equals(kvvvfloat.d120) : kvvvfloat.d120 != null) return false;
        if (d150 != null ? !d150.equals(kvvvfloat.d150) : kvvvfloat.d150 != null) return false;
        if (d210 != null ? !d210.equals(kvvvfloat.d210) : kvvvfloat.d210 != null) return false;
        if (d290 != null ? !d290.equals(kvvvfloat.d290) : kvvvfloat.d290 != null) return false;
        if (d300 != null ? !d300.equals(kvvvfloat.d300) : kvvvfloat.d300 != null) return false;
        if (d520 != null ? !d520.equals(kvvvfloat.d520) : kvvvfloat.d520 != null) return false;
        if (d700 != null ? !d700.equals(kvvvfloat.d700) : kvvvfloat.d700 != null) return false;
        if (d950 != null ? !d950.equals(kvvvfloat.d950) : kvvvfloat.d950 != null) return false;
        if (d1300 != null ? !d1300.equals(kvvvfloat.d1300) : kvvvfloat.d1300 != null) return false;
        if (d1700 != null ? !d1700.equals(kvvvfloat.d1700) : kvvvfloat.d1700 != null) return false;
        if (d2300 != null ? !d2300.equals(kvvvfloat.d2300) : kvvvfloat.d2300 != null) return false;
        if (d3100 != null ? !d3100.equals(kvvvfloat.d3100) : kvvvfloat.d3100 != null) return false;
        if (d4200 != null ? !d4200.equals(kvvvfloat.d4200) : kvvvfloat.d4200 != null) return false;
        if (d5600 != null ? !d5600.equals(kvvvfloat.d5600) : kvvvfloat.d5600 != null) return false;
        if (d7600 != null ? !d7600.equals(kvvvfloat.d7600) : kvvvfloat.d7600 != null) return false;
        if (d10200 != null ? !d10200.equals(kvvvfloat.d10200) : kvvvfloat.d10200 != null) return false;
        if (d13800 != null ? !d13800.equals(kvvvfloat.d13800) : kvvvfloat.d13800 != null) return false;
        if (d18500 != null ? !d18500.equals(kvvvfloat.d18500) : kvvvfloat.d18500 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idKv;
        result = 31 * result + (d2 != null ? d2.hashCode() : 0);
        result = 31 * result + (d3 != null ? d3.hashCode() : 0);
        result = 31 * result + (d4 != null ? d4.hashCode() : 0);
        result = 31 * result + (d5 != null ? d5.hashCode() : 0);
        result = 31 * result + (d6 != null ? d6.hashCode() : 0);
        result = 31 * result + (d8 != null ? d8.hashCode() : 0);
        result = 31 * result + (d11 != null ? d11.hashCode() : 0);
        result = 31 * result + (d15 != null ? d15.hashCode() : 0);
        result = 31 * result + (d20 != null ? d20.hashCode() : 0);
        result = 31 * result + (d26 != null ? d26.hashCode() : 0);
        result = 31 * result + (d36 != null ? d36.hashCode() : 0);
        result = 31 * result + (d40 != null ? d40.hashCode() : 0);
        result = 31 * result + (d65 != null ? d65.hashCode() : 0);
        result = 31 * result + (d85 != null ? d85.hashCode() : 0);
        result = 31 * result + (d120 != null ? d120.hashCode() : 0);
        result = 31 * result + (d150 != null ? d150.hashCode() : 0);
        result = 31 * result + (d210 != null ? d210.hashCode() : 0);
        result = 31 * result + (d290 != null ? d290.hashCode() : 0);
        result = 31 * result + (d300 != null ? d300.hashCode() : 0);
        result = 31 * result + (d520 != null ? d520.hashCode() : 0);
        result = 31 * result + (d700 != null ? d700.hashCode() : 0);
        result = 31 * result + (d950 != null ? d950.hashCode() : 0);
        result = 31 * result + (d1300 != null ? d1300.hashCode() : 0);
        result = 31 * result + (d1700 != null ? d1700.hashCode() : 0);
        result = 31 * result + (d2300 != null ? d2300.hashCode() : 0);
        result = 31 * result + (d3100 != null ? d3100.hashCode() : 0);
        result = 31 * result + (d4200 != null ? d4200.hashCode() : 0);
        result = 31 * result + (d5600 != null ? d5600.hashCode() : 0);
        result = 31 * result + (d7600 != null ? d7600.hashCode() : 0);
        result = 31 * result + (d10200 != null ? d10200.hashCode() : 0);
        result = 31 * result + (d13800 != null ? d13800.hashCode() : 0);
        result = 31 * result + (d18500 != null ? d18500.hashCode() : 0);
        return result;
    }
}
