package com.app.backend.repository;

import com.app.backend.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SolutionRepository extends JpaRepository<Solution,Integer> {

    @Query("select s from Solution s where s.forProblem.problemId=?1")
    List<Solution> findByProblem(UUID problemId);

    @Query("select s from Solution s where s.forProblem.problemId=?1 and s.accepted is true")
    List<Solution> findByProblemSolved(UUID problemId);

    @Query("select s from Solution s where s.forProblem.problemId=?1 and s.accepted is null")
    List<Solution> findByProblemUnsolved(UUID problemId);
    @Query("select s from Solution s where s.solvedBy.username=?1 and s.accepted is not null")
    List<Solution> findByStudent(String username);

    @Query("select s from Solution s where s.forProblem.problemId=:problemId and s.solvedBy.username=:username and s.accepted is true")
    Optional<Solution> findIfUserSolved(@Param("problemId") UUID problemId,@Param("username") String username);

    @Query("select s from Solution  s where s.solutionId=?1")
    Optional<Solution> findBySolutionId(UUID solutionId);
}
