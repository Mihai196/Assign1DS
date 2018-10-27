package servlet;

import dao.CityDAO;
import dao.FlightDAO;
import entity.City;
import entity.Flight;
import htmlTable.HTMLTables;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.cfg.Configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class FlightServlet extends HttpServlet{

//    public static void main (String[] args){
//        dao.FlightDAO flightDAO=new dao.FlightDAO(new Configuration().configure().buildSessionFactory());
//        dao.CityDAO cityDAO=new dao.CityDAO(new Configuration().configure().buildSessionFactory());
//        City departureCity=cityDAO.findById(1);
//        City arrivalCity=cityDAO.findById(52);
//        System.out.println("Departure City" + departureCity.toString());
//        System.out.println("Arrival City" + arrivalCity.toString());
//        Flight flight=new Flight("Boeing",departureCity,new Date(),arrivalCity,new Date());
//        boolean flightok=flightDAO.insertFlight(flight);
//        System.out.println(flightok);
//        List<Flight> flights=flightDAO.listFlights();
//        for(Flight flightt: flights){
//            System.out.println(flightt.toString());
//        }

//    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action=request.getParameter("action");
        try {
            switch (action) {
                case "Insert":
                    insertFlight(request, response);
                    break;
                case "Delete":
                    deleteFlight(request,response);
                    break;
                case "Update":
                    updateFlight(request,response);
                    break;
                    default:
                        break;
            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("loggedUser")!=null&&
                request.getSession().getAttribute("loggedUserRole").equals("administrator")) {
            String adminPage =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" +
                            "<html>" +
                            "<body>" +
                            "<div class=\"table\">" +
                            "       Flights:" +
                            "<br>" +

                            HTMLTables.getTableFlights() +
                            "<div>" +
                            "Cities" +
                            HTMLTables.getTableCities() +
                            "</div>" +
                            "</div>" +
                            "<br>" +
                            "<br>" +

                            "<div class=\"form\">" +
                            "<form action=\"/flight\" method=\"POST\">\n" +
                            "Id: <input type=\"text\" name=\"flightId\">\n" +
                            "<br>\n" +
                            "Airplane Type: <input type=\"text\" name=\"airplaneType\">\n" +
                            "<br>\n" +
                            "DepartureCity: <input type=\"text\" name=\"departureCity\">\n" +
                            "<br>\n" +
                            "Departure time: <input type=\"datetime-local\" name=\"departureDate\"/>" +
                            "<br>\n" +
                            "Arrival city: <input type=\"text\" name=\"arrivalCity\">\n " +
                            "<br>\n" +
                            "Arrival time: <input type=\"datetime-local\" name=\"arrivalDate\"/>" +
                            "<br>\n" +
                            "<input type=\"submit\" name=\"action\" value=\"Insert\">\n" +
                            "<input type=\"submit\" name=\"action\" value=\"Update\">\n" +
                            "<input type=\"submit\" name=\"action\" value=\"Delete\">\n" +
                            "</form>" +
                            "</div>" +
                            "</body>" +
                            "</html>";
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.println(adminPage);
        }
        else{
            response.sendRedirect("user");
        }
    }
    private void insertFlight(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("airplaneType");

        String departureDate = request.getParameter("departureDate");
        if (StringUtils.countMatches(departureDate, ":") == 1) {
            departureDate += ":00";
        }
        Date departureDateDB=Timestamp.valueOf(departureDate.replace("T"," "));

        int departureCityId = Integer.parseInt(request.getParameter("departureCity"));

        String arrivalDate = request.getParameter("arrivalDate");
        if (StringUtils.countMatches(arrivalDate, ":") == 1) {
            arrivalDate += ":00";
        }
        Date arrivalDateDB=Timestamp.valueOf(arrivalDate.replace("T"," "));

        int arrivalCityId = Integer.parseInt(request.getParameter("arrivalCity"));
        FlightDAO flightDAO=new FlightDAO(new Configuration().configure().buildSessionFactory());
        CityDAO cityDAO=new CityDAO(new Configuration().configure().buildSessionFactory());
        City departureCity=cityDAO.findById(departureCityId);
        City arrivalCity=cityDAO.findById(arrivalCityId);
        Flight flight=new Flight(name,departureCity,departureDateDB,arrivalCity,arrivalDateDB);
        boolean flightok=flightDAO.insertFlight(flight);
        System.out.println(flightok);
        response.sendRedirect("flight");
    }

    private void deleteFlight(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("flightId"));
        FlightDAO flightDAO=new FlightDAO(new Configuration().configure().buildSessionFactory());
        Flight flight=new Flight(id);
        flightDAO.deleteFlight(flight);
        response.sendRedirect("flight");
    }

    private void updateFlight(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        FlightDAO flightDAO=new FlightDAO(new Configuration().configure().buildSessionFactory());
        CityDAO cityDAO=new CityDAO(new Configuration().configure().buildSessionFactory());
        int flightId=Integer.parseInt(request.getParameter("flightId"));
        String name = request.getParameter("airplaneType");
        String departureDate = request.getParameter("departureDate");
        int departureCityId = Integer.parseInt(request.getParameter("departureCity"));
        String arrivalDate = request.getParameter("arrivalDate");
        int arrivalCityId = Integer.parseInt(request.getParameter("arrivalCity"));
        City departureCity=cityDAO.findById(departureCityId);
        City arrivalCity=cityDAO.findById(arrivalCityId);
        Flight flight=new Flight(flightId,name,departureCity,new Date(),arrivalCity,new Date());
        flightDAO.updateFlight(flight);
        response.sendRedirect("flight");
    }
}
