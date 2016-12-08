package com.sergiovanovi.service;

import com.sergiovanovi.dao.MeterDAO;
import com.sergiovanovi.model.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "transactionManager")
public class MeterServiceImpl implements MeterService {

    private final MeterDAO meterDAO;

    @Autowired
    public MeterServiceImpl(MeterDAO meterDAO) {
        this.meterDAO = meterDAO;
    }

    @Override
    @Transactional
    public void addMeter(Meter meter) {
        meterDAO.addMeter(meter);
    }

    @Override
    @Transactional
    public List<Meter> listOfMeter(int page, int meterOnPage) {
        return meterDAO.listOfMeter(page, meterOnPage);
    }

    @Override
    @Transactional
    public long numberOfMeters() {
        return meterDAO.numberOfMeters();
    }

    @Override
    public void clearDB(long limit) {
        meterDAO.clearDB(limit);
    }

    @Override
    public double getLastMeter() {
        return meterDAO.getLastMeter();
    }
}
