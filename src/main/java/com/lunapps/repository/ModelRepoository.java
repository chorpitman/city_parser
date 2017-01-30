package com.lunapps.repository;

import com.lunapps.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface ModelRepoository extends JpaRepository<Model, Long> {

    Model findModelByName(final String email);
}
