package com.viniciuscsreis.scoreCrud.service;

import com.viniciuscsreis.scoreCrud.entity.ScoreEntity;
import com.viniciuscsreis.scoreCrud.form.ScoreForm;
import com.viniciuscsreis.scoreCrud.repository.ScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService{

    Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);

    private ScoreRepository scoreRepository;

    @Autowired
    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }


    @Override
    public List<ScoreForm> listAll() {

        List<ScoreEntity> scoreEntity = scoreRepository.findAll();
        List<ScoreForm> scoreFormList = new ArrayList<>();
        scoreEntity.forEach(entity -> {
            ScoreForm scoreForm =  new ScoreForm();
            scoreForm.setRegistry( entity.getRegistry());
            scoreForm.setScore( entity.getScore() );
            scoreFormList.add(scoreForm);
        });
        return scoreFormList;
    }

    @Override
    public ScoreForm save(ScoreForm scoreForm) {


        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setRegistry(scoreForm.getRegistry());
        scoreEntity.setScore(scoreForm.getScore());


        scoreEntity = scoreRepository.save(scoreEntity);
        if(scoreEntity == null){
            return null;
        }

        ScoreForm response = new ScoreForm();
        response.setRegistry( scoreEntity.getRegistry() );
        response.setScore( scoreEntity.getScore() );

        return response;

    }
}
