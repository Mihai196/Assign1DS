package dao;

import entity.City;
import entity.Flight;
import org.hibernate.*;

import java.util.List;

public class FlightDAO {
    private SessionFactory sessionFactory;
    public FlightDAO(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public boolean insertFlight(Flight flight) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int flightId=-1;
        try {
            tx = session.beginTransaction();
            flightId = (Integer) session.save(flight);
            flight.setFlightId(flightId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return flightId>0;
    }
    public void updateFlight(Flight flight) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(flight);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
    public List<Flight> listFlights(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Flight> flights = null;
        try {
            tx = session.beginTransaction();
            Query query=session.createQuery("FROM Flight");
            flights=query.list();
            tx.commit();
            for(Flight flight:flights){
                Hibernate.initialize(flight.getDepartureCity());
                Hibernate.initialize(flight.getArrivalCity());
            }
        }
        catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        return flights;
    }
    public void deleteFlight(Flight flight)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Flight WHERE flightId = :flightId");
            query.setParameter("flightId", flight.getFlightId());
            query.executeUpdate();
            tx.commit();
        }
        finally {
            session.close();
        }
    }

    public Flight findById(int flightId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Flight> flights = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Flight WHERE flightId = :flightId");
            query.setParameter("flightId", flightId);
            flights = query.list();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        finally {
//            session.close();
        }
        return flights.get(0);
    }
}
