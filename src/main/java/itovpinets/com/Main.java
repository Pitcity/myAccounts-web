package itovpinets.com;

import com.itovpinets.entity.*;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

import java.math.BigDecimal;

public class Main {
        public static void main(String[] args) {
            Session session = new AnnotationConfiguration()
                    .configure().buildSessionFactory().openSession();

            Transaction t=session.beginTransaction();

            Account e1=new Account("dffdddds", BigDecimal.valueOf(1000),"ddd");

            Account e2=new Account("dffds",BigDecimal.valueOf(1010),"ddd");
            Account e3=new Account("dffds");

            Deal d1 = Deal.createDeal(e1,e2,"ddf",BigDecimal.valueOf(550),"data1");
            Deal d2 = Deal.createDeal(e1,e2,"ddf",BigDecimal.valueOf(550),"data1");

try {
    session.persist(d1);
    session.persist(d2);

} catch (Exception e) {
    System.out.println(e.getStackTrace());
}

            session.persist(e1);
            session.persist(e2);
            session.persist(e3);

            t.commit();
            session.close();
            System.out.println("successfully saved");
        }
    }
