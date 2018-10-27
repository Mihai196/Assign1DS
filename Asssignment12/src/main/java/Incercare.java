import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Incercare {
    public static void main (String []args) throws IOException {
        Date date=new Date(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(date.toString());
//        String USER_AGENT="Chrome/69.0.3497.100";
//
//        String url="http://new.earthtools.org/timezone-1.1/40.71417/-74.00639";
//        URL obj = null;
//        try {
//            obj = new URL(url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        HttpURLConnection con = null;
//        try {
//            con = (HttpURLConnection) obj.openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // optional default is GET
//        try {
//            con.setRequestMethod("GET");
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        }
//
//        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);
//
//        int responseCode = 0;
//        try {
//            responseCode = con.getResponseCode();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = null;
//        try {
//            in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        try {
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FileWriter fw = new FileWriter("queryWebService.xml");
//        fw.write(response.toString());
//        fw.close();
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        DocumentBuilder db = null;
//        try {
//            db = dbf.newDocumentBuilder();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
//        try {
//            Document document = db.parse(new File("queryWebService.xml"));
//            NodeList nodeList=document.getElementsByTagName("localtime");
//            System.out.println(nodeList==null);
//            NodeList subList = nodeList.item(0).getChildNodes();
//            if (subList != null && subList.getLength() > 0) {
//                SimpleDateFormat formatter6=new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
//                String localtimeDate=subList.item(0).getNodeValue();
//                Date date6=formatter6.parse(localtimeDate);
//                System.out.println("Data parsata final"+date6.toString());
//            }
//
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
