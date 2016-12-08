package com.sergiovanovi.dao;

import com.sergiovanovi.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = sessionFactory.getCurrentSession().load(User.class, id);
        if (user != null) sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User getUserById(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> listUsers(int page, int meterOnPage) {
        int startMeter = page * meterOnPage;
        return (List<User>) sessionFactory.getCurrentSession().createQuery("FROM User")
                .setFirstResult(startMeter).setMaxResults(meterOnPage).list();
    }

    @Override
    public List<User> listAllUsers() {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("FROM User").list();
    }

    @Override
    public long numberOfMeters() {
        return (long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(1) FROM User ").uniqueResult();
    }
}
