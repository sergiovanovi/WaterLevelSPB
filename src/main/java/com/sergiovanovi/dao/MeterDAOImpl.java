package com.sergiovanovi.dao;

import com.sergiovanovi.model.Meter;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeterDAOImpl implements MeterDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public MeterDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addMeter(Meter meter) {
        sessionFactory.getCurrentSession().save(meter);
    }

    @Override
    public List<Meter> listOfMeter(int page, int meterOnPage) {
        int startMeter = page * meterOnPage;
        return (List<Meter>) sessionFactory.getCurrentSession().createQuery("FROM Meter ")
                .setFirstResult(startMeter).setMaxResults(meterOnPage).list();
    }

    @Override
    public long numberOfMeters() {
        return (long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(1) FROM Meter").uniqueResult();
    }

    @Override
    public void clearDB(long limit) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM meter LIMIT " + limit).executeUpdate();
    }

    @Override
    public double getLastMeter() {
        Meter lastMeter = (Meter) sessionFactory.getCurrentSession().createQuery("FROM Meter ORDER BY id DESC").setMaxResults(1).uniqueResult();
        return lastMeter.getMeter();
    }
}
