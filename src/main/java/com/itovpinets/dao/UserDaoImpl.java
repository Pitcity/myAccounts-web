package com.itovpinets.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
/*
    public void save(User user) {
        getHibernateTemplate().save(user);
    }

    public void update(User user) {
        getHibernateTemplate().save(user);
    }

    public void delete(User user) {
        getHibernateTemplate().save(user);
    }

    public User findByUserName(String userName) {
        return (User) getHibernateTemplate().find(
                "from User where userName=?", userName
        ).get(0);
    }*/
}
