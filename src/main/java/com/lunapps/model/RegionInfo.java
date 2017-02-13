package com.lunapps.model;

public class RegionInfo {
    private static long NEXT_ID = 0;

    private long id;
    private String regionId;
    private int cityIndex;
    private String regionCyrillicName;
    private String regionInternationalName;
    private String featureCode;

    public RegionInfo() {
        this.id = ++RegionInfo.NEXT_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getCityIndex() {
        return cityIndex;
    }

    public void setCityIndex(int cityIndex) {
        this.cityIndex = cityIndex;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    @Override
    public String toString() {
        return "RegionInfo{" +
                "id=" + id +
                ", regionId='" + regionId + '\'' +
                ", cityIndex=" + cityIndex +
                ", regionCyrillicName='" + regionCyrillicName + '\'' +
                ", regionInternationalName='" + regionInternationalName + '\'' +
                ", featureCode='" + featureCode + '\'' +
                '}';
    }
}
