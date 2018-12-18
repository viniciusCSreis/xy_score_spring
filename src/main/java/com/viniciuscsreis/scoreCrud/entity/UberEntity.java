package com.viniciuscsreis.scoreCrud.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class UberEntity {

    @Id
    String tripId;
    Timestamp timeAndDate;
    Double tripTotalPrice;


}
