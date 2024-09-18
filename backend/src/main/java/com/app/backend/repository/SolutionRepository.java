package com.app.backend.repository;

import com.app.backend.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SolutionRepository extends JpaRepository<Solution,Integer> {

    @Query("select s from Solution s where s.forProblem.problemId=?1")
    List<Solution> findByProblem(UUID problemId);

    @Query("select s from Solution s where s.solvedBy.username=?1")
    List<Solution> findByStudent(String username);
}
