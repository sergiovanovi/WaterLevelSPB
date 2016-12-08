package com.sergiovanovi.model;

import javax.persistence.*;

@Entity
@Table(name = "mail", schema = "test")
public class User {
    private int id;
    private String mail;
    private int min;
    private int max;
    private int util;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MAIL", nullable = false, length = 45)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "MIN", nullable = false)
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Basic
    @Column(name = "MAX", nullable = false)
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Basic
    @Column(name = "UTIL", nullable = false)
    public int getUtil() {
        return util;
    }

    public void setUtil(int util) {
        this.util = util;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (min != user.min) return false;
        if (max != user.max) return false;
        if (util != user.util) return false;
        return mail.equals(user.mail);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mail.hashCode();
        result = 31 * result + min;
        result = 31 * result + max;
        result = 31 * result + util;
        return result;
    }
}
