package com.lunapps.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "city_names")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city_index")
    private int cityIndex;

    @Column(name = "city_ukr_name")
    private String cityUkrName;

    @Column(name = "city_inter_name")
    private String internationalName;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "reg_id")
    private String regionId;

    @Column(name = "region_ukr_name")
    private String regionCyrillicName;

    @Column(name = "region_inter_name")
    private String regionInternationalName;

    @Column(name = "feature_codes")
    private String featureCode;

    @Column(name = "population")
    private String population;

    public Model() {
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

    public String getCityUkrName() {
        return cityUkrName;
    }

    public void setCityUkrName(String cityUkrName) {
        this.cityUkrName = cityUkrName;
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

    public String getRegionCyrillicName() {
        return regionCyrillicName;
    }

    public void setRegionCyrillicName(String regionCyrillicName) {
        this.regionCyrillicName = regionCyrillicName;
    }

    public String getRegionInternationalName() {
        return regionInternationalName;
    }

    public void setRegionInternationalName(String regionInternationalName) {
        this.regionInternationalName = regionInternationalName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", cityIndex=" + cityIndex +
                ", cityUkrName='" + cityUkrName + '\'' +
                ", internationalName='" + internationalName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", regionId='" + regionId + '\'' +
                ", regionCyrillicName='" + regionCyrillicName + '\'' +
                ", regionInternationalName='" + regionInternationalName + '\'' +
                ", featureCode='" + featureCode + '\'' +
                ", population='" + population + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        return new EqualsBuilder()
                .append(cityIndex, model.cityIndex)
                .append(id, model.id)
                .append(cityUkrName, model.cityUkrName)
                .append(internationalName, model.internationalName)
                .append(latitude, model.latitude)
                .append(longitude, model.longitude)
                .append(regionId, model.regionId)
                .append(regionCyrillicName, model.regionCyrillicName)
                .append(regionInternationalName, model.regionInternationalName)
                .append(featureCode, model.featureCode)
                .append(population, model.population)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(cityIndex)
                .append(cityUkrName)
                .append(internationalName)
                .append(latitude)
                .append(longitude)
                .append(regionId)
                .append(regionCyrillicName)
                .append(regionInternationalName)
                .append(featureCode)
                .append(population)
                .toHashCode();
    }
}
