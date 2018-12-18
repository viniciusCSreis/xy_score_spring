package com.viniciuscsreis.scoreCrud.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.viniciuscsreis.scoreCrud.entity.UberEntity;
import com.viniciuscsreis.scoreCrud.form.ScoreCsvForm;
import com.viniciuscsreis.scoreCrud.form.ScoreForm;
import com.viniciuscsreis.scoreCrud.form.UberForm;
import com.viniciuscsreis.scoreCrud.service.ScoreService;
import com.viniciuscsreis.scoreCrud.service.UberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@RestController(value = "score")
public class scoreController {


    @Value("${java.io.tmpdir}")
    private String tempDir;

    private ScoreService scoreService;
    private UberService uberService;

    @Autowired
    public scoreController(ScoreService scoreService,UberService uberService) {
        this.scoreService = scoreService;
        this.uberService = uberService;
    }

    @GetMapping
    public List<ScoreForm> listAll(){
        return scoreService.listAll();
    }

    @PostMapping
    public ScoreForm save(@RequestBody ScoreForm scoreForm){
        return scoreService.save(scoreForm);
    }

    @PostMapping("/files")
    public String save(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("columnRegistry") int columnRegistry,
            @RequestParam("columnScore") int columnScore) throws IOException {
        scoreService.store(multipartFile);

        Reader reader = Files.newBufferedReader(Paths.get(tempDir + "/" + multipartFile.getOriginalFilename() ));
//        CSVReader csvReader = new CSVReader(reader);
//        String[] nextRecord;
//        while ((nextRecord = csvReader.readNext()) != null) {
//            System.out.println("Nome do motorista : " + nextRecord[0]);
//            System.out.println("Telefone : " + nextRecord[1]);
//            System.out.println("E-mail : " + nextRecord[2]);
//            System.out.println("Data/Hora : " + nextRecord[3]);
//            System.out.println("ID de viagem : " + nextRecord[4]);
//            System.out.println("Tipo : " + nextRecord[5]);
//            System.out.println("Preco Base : " + nextRecord[6]);
//            System.out.println("Distancia : " + nextRecord[7]);
//            System.out.println("Tempo : " + nextRecord[8]);
//            System.out.println("Total : " + nextRecord[9]);
//            System.out.println("==========================");
//        }

        CsvToBean<ScoreCsvForm> csvToBean = new CsvToBeanBuilder(reader)
                .withType(ScoreCsvForm.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        Iterator<ScoreCsvForm> scoreCsvFormIterator = csvToBean.iterator();

        while (scoreCsvFormIterator.hasNext()) {
            ScoreCsvForm scoreCsvForm = scoreCsvFormIterator.next();
            System.out.println("Registry : " + scoreCsvForm.getRegistry());
            System.out.println("Score : " + scoreCsvForm.getScore());
            System.out.println("==========================");
            save(new ScoreForm(scoreCsvForm.getRegistry(), scoreCsvForm.getScore()));

        }

        return "ok";
    }

    @PostMapping("/uber/files")
    public String save(@RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        for (MultipartFile multipartFile: multipartFiles) {



            scoreService.store(multipartFile);

            Reader reader = Files.newBufferedReader(Paths.get(tempDir + "/" + multipartFile.getOriginalFilename() ));

            CsvToBean<UberForm> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(UberForm.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<UberForm> uberRepositoryIterator = csvToBean.iterator();

            while (uberRepositoryIterator.hasNext()) {
                UberForm uberForm = uberRepositoryIterator.next();
                System.out.println("DriverName : " + uberForm.getDriverName());
                System.out.println("Email : " + uberForm.getEmail());
                System.out.println("PhoneNumber : " + uberForm.getPhoneNumber());
                System.out.println("TimeAndData : " + uberForm.getTimeAndDate());
                System.out.println("TripId : " + uberForm.getTripId());
                System.out.println("TripType : " + uberForm.getTripType());
                System.out.println("BasePrice : " + uberForm.getBasePrice());
                System.out.println("CancelPrice : " + uberForm.getCancelPrice());
                System.out.println("Distance : " + uberForm.getDistance());
                System.out.println("DynamicPrice : " + uberForm.getDynamicPrice());
                System.out.println("Price : " + uberForm.getPrice());
                System.out.println("TripDuration : " + uberForm.getTripDuration());
                System.out.println("TripTotalPRice : " + uberForm.getTripTotalPrice());
                System.out.println("UberTax : " + uberForm.getUberTax());
                System.out.println("==========================");
                uberService.save(uberForm);
            }

        }

        return "ok";
    }



}
