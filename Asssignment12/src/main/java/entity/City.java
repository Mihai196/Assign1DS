package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "city", schema = "flights")
public class City {

    private int cityId;

    private String name;
    private float latitude;
    private float longitude;

    public City(){

    }

    public City(int cityId) {
        this.cityId = cityId;
    }

    public City(int cityId, String name, float latitude, float longitude) {
        this.cityId = cityId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
