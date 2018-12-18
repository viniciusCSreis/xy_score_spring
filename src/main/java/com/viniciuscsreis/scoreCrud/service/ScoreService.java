package com.viniciuscsreis.scoreCrud.service;

import com.viniciuscsreis.scoreCrud.form.ScoreForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScoreService {


    List<ScoreForm> listAll();
    ScoreForm save(ScoreForm scoreForm);
    void store(MultipartFile file);
}
