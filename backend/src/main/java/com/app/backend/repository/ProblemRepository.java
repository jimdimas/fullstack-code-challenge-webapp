package com.app.backend.repository;

import com.app.backend.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Integer> {

    @Query("select p from Problem  p where p.problemId=?1")
    Optional<Problem> findByProblemId(UUID problemId);
    @Query("select p from Problem p where p.uploadedBy.username=?1")
    List<Problem> findBySupervisor(String username);

    @Query("select p from Problem p where p.difficulty=?1")
    List<Problem> findByDifficulty(String difficulty);
}
