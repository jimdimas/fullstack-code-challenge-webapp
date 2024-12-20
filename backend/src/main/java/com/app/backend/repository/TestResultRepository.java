package com.app.backend.repository;

import com.app.backend.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult,Integer> {

    @Query("select t from TestResult t where t.test.title=:title and t.student.username=:username")
    Optional<TestResult> findByTestTitleAndUsername(@Param("title") String title,@Param("username") String username);

    @Query("select t from TestResult t where t.student.username=?1")
    List<TestResult> findByUsername(String username);
}
