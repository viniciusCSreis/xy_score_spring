package com.viniciuscsreis.scoreCrud.service;

import com.viniciuscsreis.scoreCrud.form.ScoreForm;

import java.util.List;

public interface ScoreService {


    List<ScoreForm> listAll();
    ScoreForm save(ScoreForm scoreForm);
}
