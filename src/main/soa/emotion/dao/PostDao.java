package emotion.dao;

import emotion.impl.PostImpl;
import emotion.impl.UserImpl;
import emotion.service.Post;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by mayezhou on 2017/4/24.
 */
public class PostDao  {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int save(PostImpl post) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int id = -1;
        try {
            transaction = session.beginTransaction();
            id = (int) session.save(post);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public List<Post> getMyOwnPosts(int userID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Post> posts = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM PostImpl p WHERE p.userID = :userID";
            Query query = session.createQuery(hql);
            query.setParameter("userID",userID);
            posts = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return posts;
    }

    public List<Post> getSurroundingPosts(float longtitude, float latitude, float range) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Post> posts = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(PostImpl.class);
            criteria.add(Restrictions.between("longtitude", longtitude-range, longtitude+range));
            criteria.add(Restrictions.between("latitude", latitude-range, latitude+range));
            posts = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return posts;
    }
}
