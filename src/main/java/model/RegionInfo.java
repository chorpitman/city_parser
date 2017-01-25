package model;

public class RegionInfo {
    private int id;
    private String regionId;
    private String regionName;
    private String regionNameInternational;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionNameInternational() {
        return regionNameInternational;
    }

    public void setRegionNameInternational(String regionNameInternational) {
        this.regionNameInternational = regionNameInternational;
    }

    @Override
    public String toString() {
        return "RegionInfo{" +
                "id=" + id +
                ", regionId='" + regionId + '\'' +
                ", regionName='" + regionName + '\'' +
                ", regionNameInternational='" + regionNameInternational + '\'' +
                '}';
    }
}
