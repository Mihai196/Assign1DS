package htmlTable;

import dao.CityDAO;
import dao.FlightDAO;
import entity.City;
import entity.Flight;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HTMLTables {

    public static String getTableFlights(){
        FlightDAO flightDAO=new FlightDAO(new Configuration().configure().buildSessionFactory());
        String table = "<table style=\"width:50%\">\n" +
                "  <tr>\n" +
                "    <th>Id</th>\n" +
                "    <th>Airplane Type</th>\n" +
                "    <th>Departure City</th>\n" +
                "    <th>Departure Time</th>\n" +
                "    <th>Arrival City</th>\n" +
                "    <th>Arrival Time</th>\n" +
                "  </tr>";
        List<Flight> flights=flightDAO.listFlights();
        for(Flight flight: flights){
            table += "<tr>";
            table += "<td>" + flight.getFlightId() + "</td>";
            table += "<td>" + flight.getAirplaneType() + "</td>";
            table += "<td>" + flight.getDepartureCity().getName() + "</td>";
            table += "<td>" + flight.getDepartureDate() + "</td>";
            table += "<td>" + flight.getArrivalCity().getName() + "</td>";
            table += "<td>" + flight.getArrivalDate() + "</td>";
            table += "</tr>";
        }
        return table;
    }

    public static String getTableCities(){
        CityDAO cityDAO=new CityDAO(new Configuration().configure().buildSessionFactory());
        List<City> cities=cityDAO.listAllCities();
        String table ="<table style=\"width:30%\">\n" +
                "  <tr>\n" +
                "    <th>CityId</th>\n" +
                "    <th>Name</th>\n" +
                "    <th>Latitude</th>\n" +
                "    <th>Longitude</th>\n" +
                "  </tr>";
        for(City city : cities){
            table += "<tr>";
            table += "<td>" + city.getCityId() + "</td>";
            table += "<td>" + city.getName() + "</td>";
            table += "<td>" + city.getLatitude() + "</td>";
            table += "<td>" + city.getLongitude() + "</td>";
            table += "</tr>";
        }
        return table;
    }
}
