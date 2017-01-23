package model;

/**
 * Created by olegchorpita on 1/20/17.
 */
public class Model {
    private Long id;
    private int cityIndex;
    private String name;
    private String internationalName;
    private Double latitude;
    private Double longtitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCityIndex() {
        return cityIndex;
    }

    public void setCityIndex(int cityIndex) {
        this.cityIndex = cityIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternationalName() {
        return internationalName;
    }

    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public String toString() {
        return "model.Model{" +
                "id=" + id +
                ", cityIndex=" + cityIndex +
                ", name='" + name + '\'' +
                ", internationalName='" + internationalName + '\'' +
                ", latitude=" + latitude +
                ", longtitude=" + longtitude +
                '}';
    }
}
