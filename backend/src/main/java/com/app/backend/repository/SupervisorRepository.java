package com.app.backend.repository;

import com.app.backend.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor,Integer> {

    @Query("select s from Supervisor s where s.username=?1")
    Optional<Supervisor> findByUsername(String username);
}
