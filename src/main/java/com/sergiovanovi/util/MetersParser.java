package com.sergiovanovi.util;

import com.sergiovanovi.model.Meter;
import com.sergiovanovi.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class MetersParser {

    private final MeterService meterService;

    @Autowired
    public MetersParser(MeterService meterService) {
        this.meterService = meterService;
    }

    @Scheduled(fixedDelay = 3600000) //1hour
    public void parsMeter() {
        try {
            URL url = new URL("http://www.pasp.ru/op-info-weather?mode=current");
            try {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String string = reader.readLine();

                while (string != null){
                    if (reader.getLineNumber() == 184){
                        string = string.trim();
                        string = string.substring(string.indexOf(":") + 2, string.lastIndexOf(" "));
                        double waterLevel = Double.parseDouble(string);
                        Meter meter = new Meter();
                        meter.setDate(new Date());
                        meter.setMeter(waterLevel);
                        meterService.addMeter(meter);
                        break;
                    }
                    string = reader.readLine();
                }

            } catch (IOException e) {
                System.out.println("can not open a stream");
            }
        } catch (MalformedURLException e) {
            System.out.println("web-site does not respond");
        }
    }

    @Scheduled(fixedDelay = 1728000000)//20days
    public void clearMeters(){
        int maxSize = 26000;
        if (meterService.numberOfMeters() > maxSize) {
            long limit = meterService.numberOfMeters() - maxSize;
            meterService.clearDB(limit);
        }
    }
}
