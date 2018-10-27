package dao;

import entity.City;
import org.hibernate.*;

import java.util.List;

public class CityDAO {

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public boolean insertCity(City city) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int cityId=-1;
        try {
            tx = session.beginTransaction();
            cityId = (Integer) session.save(city);
            System.out.println("entity.City id "+cityId);
            city.setCityId(cityId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return cityId>0;
    }
    public List<City> listAllCities(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<City> cities = null;
        try {
            tx = session.beginTransaction();
            Query query=session.createQuery("FROM entity.City");
            cities=query.list();
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
        return cities;
    }
    public City findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<City> cities = null;
        City city = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM entity.City WHERE cityId = :cityId");
            query.setParameter("cityId", id);
            cities = query.list();
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
        return cities.get(0);
    }
    public void deleteCity(City city)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM entity.City WHERE id = :id");
            query.setParameter("id", city.getCityId());
            query.executeUpdate();
            tx.commit();
        }
        finally {
            session.close();
        }
    }
}
