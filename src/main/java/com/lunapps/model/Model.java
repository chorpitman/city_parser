package com.lunapps.model;

import javax.persistence.*;

@Entity
@Table(name = "geo_names")
public class Model {
    private static long NEXT_ID = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private int cityIndex;
    @Column
    private String name;
    @Column
    private String internationalName;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column
    private String regionId;
    @Column
    private String cityCyrillicName;
    @Column
    private String cityInternationalName;

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

    public String getCityCyrillicName() {
        return cityCyrillicName;
    }

    public void setCityCyrillicName(String cityCyrillicName) {
        this.cityCyrillicName = cityCyrillicName;
    }

    public String getCityInternationalName() {
        return cityInternationalName;
    }

    public void setCityInternationalName(String cityInternationalName) {
        this.cityInternationalName = cityInternationalName;
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
                ", cityCyrillicName='" + cityCyrillicName + '\'' +
                ", cityInternationalName='" + cityInternationalName + '\'' +
                '}';
    }
}