package com.app.backend.repository;

import com.app.backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test , Integer> {

    @Query("select t from Test t where t.title=?1")
    Optional<Test> findByTitle(String _title);
}
