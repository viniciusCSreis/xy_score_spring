package com.viniciuscsreis.scoreCrud.form;


import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UberForm {

//     System.out.println("Nome do motorista : " + nextRecord[0]);
//            System.out.println("Telefone : " + nextRecord[1]);
//            System.out.println("E-mail : " + nextRecord[2]);
//            System.out.println("Data/Hora : " + nextRecord[3]);
//            System.out.println("ID de viagem : " + nextRecord[4]);
//            System.out.println("Tipo : " + nextRecord[5]);
//            System.out.println("Preco Base : " + nextRecord[6]);
//            System.out.println("Distancia : " + nextRecord[7]);
//            System.out.println("Tempo : " + nextRecord[8]);
//            System.out.println("Total : " + nextRecord[9]);

    @CsvBindByName(column = "Nome do motorista")
    String driverName;
    @CsvBindByName(column = "Telefone")
    String phoneNumber;
    @CsvBindByName(column = "E-mail")
    String email;
    @CsvBindByName(column = "Data/Hora")
    String timeAndDate;
    @CsvBindByName(column = "ID de viagem")
    String tripId;
    @CsvBindByName(column = "Tipo")
    String tripType;
    @CsvBindByName(column = "Preco Base")
    String basePrice;
    @CsvBindByName(column = "Preco")
    String price;
    @CsvBindByName(column = "Cancelamento")
    String cancelPrice;
    @CsvBindByName(column = "Preco Dinamico")
    String dynamicPrice;
    @CsvBindByName(column = "Taxa Da Uber")
    String uberTax;
    @CsvBindByName(column = "Distancia")
    String distance;
    @CsvBindByName(column = "Tempo")
    String tripDuration;
    @CsvBindByName(column = "Total")
    String tripTotalPrice;


}
