package com.maksym.repositories;

import com.maksym.model.KvvvGroupRelation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KvvpGroupRelationRepository extends JpaRepository<KvvvGroupRelation, Integer> {
	List<KvvvGroupRelation> findByGroupId(int groupId);
}
