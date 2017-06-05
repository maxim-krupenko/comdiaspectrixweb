package com.maksym.model;

import javax.persistence.*;

@Entity
@Table(name="kvvv_group_relation")
public class KvvvGroupRelation {
    private int id;
    private int groupId;
    private Kvvvfloat kvvvId;

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
    @Column(name = "group_id", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kvvv_id")
    public Kvvvfloat getKvvvId() {
        return kvvvId;
    }

    public void setKvvvId(Kvvvfloat kvvvId) {
        this.kvvvId = kvvvId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KvvvGroupRelation other = (KvvvGroupRelation) o;

        if (id != other.id) return false;
        if (groupId != other.groupId) return false;
        if (!kvvvId.equals(other)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + groupId;
        result = 31 * result + kvvvId.hashCode();
        return result;
    }
}
