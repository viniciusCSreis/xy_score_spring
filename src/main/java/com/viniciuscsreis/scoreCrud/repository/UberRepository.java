package com.viniciuscsreis.scoreCrud.repository;

import com.viniciuscsreis.scoreCrud.entity.UberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UberRepository extends JpaRepository<UberEntity,String> {
}
