package com.viniciuscsreis.scoreCrud.repository;

import com.viniciuscsreis.scoreCrud.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
}
