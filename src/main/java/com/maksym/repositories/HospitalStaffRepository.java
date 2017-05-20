package com.maksym.repositories;

import com.maksym.model.Category;
import com.maksym.model.HospitalStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalStaffRepository extends JpaRepository<HospitalStaff, Integer> {
    List<HospitalStaff> findByIdCat(Category idCat);
    HospitalStaff findByLogin(String login);
}
