package com.maksym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    private int idPost;
    private String postName;
    private Set<HospitalStaff> hospitalStaffs;

    @Id
    @Column(name = "IdPost", nullable = false)
    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    @Basic
    @Column(name = "PostName", length = 30)
    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @OneToMany(mappedBy = "idPost",fetch = FetchType.EAGER)
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

        Post post = (Post) o;

        if (idPost != post.idPost) return false;
        if (postName != null ? !postName.equals(post.postName) : post.postName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPost;
        result = 31 * result + (postName != null ? postName.hashCode() : 0);
        return result;
    }
}
