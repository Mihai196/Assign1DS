package servlet;

import dao.FlightDAO;
import entity.Flight;
import htmlTable.HTMLTables;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClientServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("loggedUser")!=null&&
                request.getSession().getAttribute("loggedUserRole").equals("client")) {
            String clientPage =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" +
                            "<html>" +
                            "<body>" +
                            "<div class=\"table\">" +
                            "       Flights:" +
                            "<br>" +
                            request.getSession().getAttribute("displayHtml")+
                            HTMLTables.getTableFlights() +
                            "<div class=\"form\">" +
                            "<form action=\"/client\" method=\"POST\">\n" +
                            "FlightIdTimeDetails: <input type=\"text\" name=\"flightId\">\n" +
                            "<br>\n" +
                            "<input type=\"submit\" name=\"action\" value=\"QueryWebService\">\n" +
                            "</form>"+
                            "</body>" +
                            "</html>";
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.println(clientPage);
        }
        else{
            response.sendRedirect("user");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String action=request.getParameter("action");
        switch (action) {
            case "QueryWebService":
                try {
                    queryForLocalTime(request, response);
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    private void queryForLocalTime(HttpServletRequest request, HttpServletResponse response) throws SAXException, ParserConfigurationException, ParseException, IOException {
        int flightId=Integer.parseInt(request.getParameter("flightId"));
        FlightDAO flightDAO=new FlightDAO(new Configuration().configure().buildSessionFactory());
        Flight flight=flightDAO.findById(flightId);
        Date departureDateLocalTime=queryWebService(flight.getDepartureCity().getLatitude(),flight.getDepartureCity().getLongitude());


        Date arrivalDateLocalTime=queryWebService(flight.getArrivalCity().getLatitude(),flight.getArrivalCity().getLongitude());
        System.out.println("Ora locala departure"+departureDateLocalTime.toString());
        System.out.println("Ora locala arrival"+arrivalDateLocalTime.toString());
        long millisDifference=arrivalDateLocalTime.getTime()-departureDateLocalTime.getTime();
        long localDateTimeForArrival=millisDifference+flight.getArrivalDate().getTime();
        Date finalDateTimeForArrival=new Date(localDateTimeForArrival);
        System.out.println("Ora afisata dupa adaugarea fusului orar"+finalDateTimeForArrival.toString());
        String diaplyedInHtml="Flight " + flight.getFlightId() +" will land in " + flight.getArrivalCity() + " at time " + finalDateTimeForArrival.toString();
        request.getSession().setAttribute("displayHtml",diaplyedInHtml);
        response.sendRedirect("client");
    }

    private Date queryWebService(float latitude,float longitude) throws ParseException, IOException, SAXException, ParserConfigurationException {
        String USER_AGENT="Chrome/69.0.3497.100";

        String url="http://new.earthtools.org/timezone-1.1/"+latitude+"/"+longitude;
        URL obj = null;
        obj = new URL(url);
        HttpURLConnection con = null;
        con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = 0;
        responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fw = new FileWriter("queryWebService.xml");
        fw.write(response.toString());
        fw.close();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        db = dbf.newDocumentBuilder();
        Document document = db.parse(new File("queryWebService.xml"));
        NodeList nodeList=document.getElementsByTagName("localtime");
        System.out.println(nodeList==null);
        NodeList subList = nodeList.item(0).getChildNodes();
        String localtimeDate="";
        if (subList != null && subList.getLength() > 0) {
            localtimeDate=subList.item(0).getNodeValue();
            }
        SimpleDateFormat formatter6=new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date date6=formatter6.parse(localtimeDate);
        System.out.println("Data parsata final"+date6.toString());
        return date6;
    }
}
