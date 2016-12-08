package com.sergiovanovi.dao;

import com.sergiovanovi.model.User;

import java.util.List;

public interface UserDAO {

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);

    public User getUserById(int id);

    public List<User> listUsers(int page, int meterOnPage);

    public List<User> listAllUsers();

    public long numberOfMeters();
}
