package model;

public class RegionInfo {
    private static long NEXT_ID = 0;

    private long id;
    private String regionId;
    private String regionName;
    private String regionNameInternational;

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
