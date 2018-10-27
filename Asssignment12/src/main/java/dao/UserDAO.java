package dao;

import entity.User;
import org.hibernate.*;

import java.util.List;

public class UserDAO {

    private SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean insertUser(User user) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int userId=-1;
        try {
            tx = session.beginTransaction();
            userId = (Integer) session.save(user);
            System.out.println("User id "+userId);
            user.setUserId(userId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return userId>0;
    }
    public List<User> listAllUsers(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<User> users = null;
        try {
            tx = session.beginTransaction();
            Query query=session.createQuery("FROM User");
            users=query.list();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        return users;
    }
    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<User> users = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE username = :username");
            query.setParameter("username", username);
            users = query.list();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        return users.get(0);
    }
}
