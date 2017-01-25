package model;

public class Model {
    private static long NEXT_ID = 0;

    private Long id;
    private int cityIndex;
    private String name;
    private String internationalName;
    private Double latitude;
    private Double longitude;
    private String regionId;
    private String region;

    public Model() {
        this.id = ++Model.NEXT_ID;
    }

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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", cityIndex=" + cityIndex +
                ", name='" + name + '\'' +
                ", internationalName='" + internationalName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", regionId='" + regionId + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
