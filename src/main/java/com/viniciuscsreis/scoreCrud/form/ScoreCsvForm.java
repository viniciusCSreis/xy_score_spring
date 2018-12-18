package com.viniciuscsreis.scoreCrud.form;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCsvForm {

    @CsvBindByName(column = "aluno", required = true)
    String registry;
    @CsvBindByName(column = "nota", required = true)
    Double score;



}
