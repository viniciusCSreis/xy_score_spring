package com.viniciuscsreis.scoreCrud.service;

import com.viniciuscsreis.scoreCrud.entity.UberEntity;
import com.viniciuscsreis.scoreCrud.form.UberForm;
import com.viniciuscsreis.scoreCrud.repository.UberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Service
public class UberServiceImpl implements UberService {


    private UberRepository uberRepository;

    @Autowired
    public UberServiceImpl(UberRepository uberRepository) {
        this.uberRepository = uberRepository;
    }

    @Override
    public UberEntity save(UberForm uberForm) {

        UberEntity uberEntity = new UberEntity();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = null;
        try {
            String[] temp = uberForm.getTimeAndDate().split(",");
            String[] tempYYYYandTime = temp[2].split(" ");
            String yyyy = tempYYYYandTime[1];
            String MMMM = temp[1].split(" ")[1];
            Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(MMMM);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int MM = cal.get(Calendar.MONTH) + 1;
            String dd = temp[1].split(" ")[2];
            String hh = tempYYYYandTime[2].split(":")[0];
            if(tempYYYYandTime[2].split(":")[0] == "PM"){
                int hhInteger = Integer.valueOf(hh) + 12;
                if(hhInteger == 12)
                    hhInteger = 0;
                hh=String.valueOf(hhInteger);
            }
            String mm =  tempYYYYandTime[2].split(":")[1];
            String finalData = yyyy+"-"+ MM +"-"+dd+" "+hh+":"+mm+":00.000";
            parsedDate = dateFormat.parse(finalData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        uberEntity.setTimeAndDate(timestamp);
        uberEntity.setTripId(uberForm.getTripId());
        String total = uberForm.getTripTotalPrice().replace("R$","");
        total = total.replace(",",".");
        uberEntity.setTripTotalPrice(Double.parseDouble(total));

        return uberRepository.save(uberEntity);
    }
}
