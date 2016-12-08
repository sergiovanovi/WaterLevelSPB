package com.sergiovanovi.service;

import com.sergiovanovi.dao.UserDAO;
import com.sergiovanovi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "transactionManager")
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> listUsers(int page, int meterOnPage) {
        return userDAO.listUsers(page, meterOnPage);
    }

    @Override
    public List<User> listAllUsers() {
        return userDAO.listAllUsers();
    }

    @Override
    @Transactional
    public long numberOfMeters() {
        return userDAO.numberOfMeters();
    }
}
