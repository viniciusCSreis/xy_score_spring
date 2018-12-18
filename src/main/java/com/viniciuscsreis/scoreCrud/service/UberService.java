package com.viniciuscsreis.scoreCrud.service;

import com.viniciuscsreis.scoreCrud.entity.UberEntity;
import com.viniciuscsreis.scoreCrud.form.ScoreForm;
import com.viniciuscsreis.scoreCrud.form.UberForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UberService {

//    List<ScoreForm> listAll();
      UberEntity save(UberForm uberForm);
//    void store(MultipartFile file);
}
