package com.viniciuscsreis.scoreCrud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String registry;

    Double score;

}
