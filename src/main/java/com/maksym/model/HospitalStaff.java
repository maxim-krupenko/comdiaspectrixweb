package com.maksym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "hospital_staff")
public class HospitalStaff {
    private int idHs;
    private String login;
    private String pass;
    private String hsfio;
    private UserRole userRole;
    private Date bDay;
    private Date dateToJoin;
    private String tel;
    private String describes;
    private Post idPost;
    private Category idCat;
    private String email;
    private Set<HistoryOfChanges> historyofchanges;
    private Set<Journal> journals;
    private Set<Seans> seanses;

    @Id
    @Column(name = "IdHS", nullable = false)
    public int getIdHs() {
        return idHs;
    }

    public void setIdHs(int idHs) {
        this.idHs = idHs;
    }

    @Basic
    @Column(name = "Login", length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "Pass", length = 30)
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "HSFIO", length = 70)
    public String getHsfio() {
        return hsfio;
    }

    public void setHsfio(String hsfio) {
        this.hsfio = hsfio;
    }

    @Basic
    @Column(name = "B_day")
    public Date getbDay() {
        return bDay;
    }

    public void setbDay(Date bDay) {
        this.bDay = bDay;
    }

    @Basic
    @Column(name = "Date_to_join")
    public Date getDateToJoin() {
        return dateToJoin;
    }

    public void setDateToJoin(Date dateToJoin) {
        this.dateToJoin = dateToJoin;
    }

    @Basic
    @Column(name = "Tel", length = 25)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "Describes", length = 200)
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPost")
    @JsonIgnore
    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdCat")
    public Category getIdCat() {
        return idCat;
    }

    public void setIdCat(Category idCat) {
        this.idCat = idCat;
    }

    @Basic
    @Column(name = "email", length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idHs")
    @JsonIgnore
    public Set<HistoryOfChanges> getHistoryofchanges() {
        return historyofchanges;
    }

    public void setHistoryofchanges(Set<HistoryOfChanges> historyofchanges) {
        this.historyofchanges = historyofchanges;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idHs")
    @JsonIgnore
    public Set<Journal> getJournals() {
        return journals;
    }

    public void setJournals(Set<Journal> journals) {
        this.journals = journals;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idHs")
    @JsonIgnore
    public Set<Seans> getSeanses() {
        return seanses;
    }

    public void setSeanses(Set<Seans> seanses) {
        this.seanses = seanses;
    }

    @Column(name = "user_role", length = 20)
    @Enumerated(EnumType.STRING)
    public UserRole getUserRole() {
        return this.userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HospitalStaff that = (HospitalStaff) o;

        if (idHs != that.idHs) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (hsfio != null ? !hsfio.equals(that.hsfio) : that.hsfio != null) return false;
        if (bDay != null ? !bDay.equals(that.bDay) : that.bDay != null) return false;
        if (dateToJoin != null ? !dateToJoin.equals(that.dateToJoin) : that.dateToJoin != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (describes != null ? !describes.equals(that.describes) : that.describes != null) return false;
        if (idPost != null ? !idPost.equals(that.idPost) : that.idPost != null) return false;
        if (idCat != null ? !idCat.equals(that.idCat) : that.idCat != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHs;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (hsfio != null ? hsfio.hashCode() : 0);
        result = 31 * result + (bDay != null ? bDay.hashCode() : 0);
        result = 31 * result + (dateToJoin != null ? dateToJoin.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (describes != null ? describes.hashCode() : 0);
        result = 31 * result + (idPost != null ? idPost.hashCode() : 0);
        result = 31 * result + (idCat != null ? idCat.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
