package com.itovpinets.dao;

import com.itovpinets.entity.Deal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
public class DealDao {

    public static int addDeal(Deal u) {
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
