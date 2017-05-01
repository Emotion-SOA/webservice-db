package emotion.dao;

import emotion.impl.UserImpl;
import emotion.service.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by mayezhou on 2017/4/23.
 */
public class UserDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int save(UserImpl user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int id = -1;
        try {
            transaction = session.beginTransaction();
            id = (int) session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public List<User> get(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List results = null;
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(UserImpl.class);
            cr.add(Restrictions.eq("email", email));
            results = cr.list();
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }
}
