package com.sergiovanovi.service;

import com.sergiovanovi.model.Meter;

import java.util.List;

public interface MeterService {

    public void addMeter(Meter meter);

    public List<Meter> listOfMeter(int page, int meterOnPage);

    public long numberOfMeters();

    public void clearDB(long limit);

    public double getLastMeter();
}
