package com.viniciuscsreis.scoreCrud.controller;

import com.viniciuscsreis.scoreCrud.form.ScoreForm;
import com.viniciuscsreis.scoreCrud.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "score")
public class scoreController {


    private ScoreService scoreService;

    @Autowired
    public scoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    public List<ScoreForm> listAll(){
        return scoreService.listAll();
    }

    @PostMapping
    public ScoreForm save(@RequestBody ScoreForm scoreForm){
        return scoreService.save(scoreForm);
    }



}
