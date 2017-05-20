package com.maksym.repositories;

import com.maksym.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer>{

    Accommodation findByPostIndex(Integer index);
}
