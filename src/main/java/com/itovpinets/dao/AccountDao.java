package com.itovpinets.dao;

import com.itovpinets.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

public abstract class AccountDao {
        @Autowired
        SessionFactory sessionFactory;

        protected Session getCurrentSession(){
            return sessionFactory.getCurrentSession();
        }



    public static int addAcc(Account u) {
        int i=0;
        Session session=new Configuration().
                configure().buildSessionFactory().openSession();

        Transaction t=session.beginTransaction();
        t.begin();

        i=(Integer)session.save(u);

        t.commit();
        session.close();

        return i;
    }
}
