package com.viniciuscsreis.scoreCrud.service;

import com.viniciuscsreis.scoreCrud.entity.ScoreEntity;
import com.viniciuscsreis.scoreCrud.form.ScoreForm;
import com.viniciuscsreis.scoreCrud.repository.ScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService{

    Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);

    private ScoreRepository scoreRepository;
    private final Path rootLocation;

    @Autowired
    public ScoreServiceImpl(ScoreRepository scoreRepository) {

        this.scoreRepository = scoreRepository;
        this.rootLocation = Paths.get(System.getProperty("java.io.tmpdir"));
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

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new IOException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new IOException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            logger.warn(e.getStackTrace().toString());
            logger.warn("Fail to safe file");
        }
    }
}
