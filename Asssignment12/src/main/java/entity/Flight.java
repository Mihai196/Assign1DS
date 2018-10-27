package entity;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "flight", schema = "flights")
public class Flight {

    private int flightId;
    private String airplaneType;
    private City departureCity;
    private Date departureDate;
    private City arrivalCity;
    private Date arrivalDate;

    public Flight(int flightId) {
        this.flightId = flightId;
    }

    public Flight(){

    }

    public Flight(int flightId, String airplaneType, City departureCity, Date departureDate, City arrivalCity, Date arrivalDate) {
        this.flightId = flightId;
        this.airplaneType = airplaneType;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
    }

    public Flight(String airplaneType, City departureCity, Date departureDate, City arrivalCity, Date arrivalDate) {
        this.airplaneType = airplaneType;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + flightId +
                ", airplaneType='" + airplaneType + '\'' +
                ", departureCity=" + departureCity +
                ", departureDate=" + departureDate +
                ", arrivalCity=" + arrivalCity +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
