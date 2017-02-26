package com.lunapps.repository;

import com.lunapps.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ModelRepository extends JpaRepository<Model, Long> {

}
