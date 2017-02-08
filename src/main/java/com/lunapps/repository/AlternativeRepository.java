package com.lunapps.repository;

import com.lunapps.model.AlternativeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("alterRepository")
public interface AlternativeRepository extends JpaRepository<AlternativeModel, Long> {
}
