package com.maksym.repositories;

import com.maksym.model.DiagnosticGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticGroupRepository extends JpaRepository<DiagnosticGroup, Integer> {
}
