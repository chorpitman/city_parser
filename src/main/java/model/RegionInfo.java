package model;

public class RegionInfo {
    private static long NEXT_ID = 0;

    private long id;
    private String regionId;
    private String regionCyrillicName;
    private String regionInternationalName;

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

    @Override
    public String toString() {
        return "RegionInfo{" +
                "id=" + id +
                ", regionId='" + regionId + '\'' +
                ", regionCyrillicName='" + regionCyrillicName + '\'' +
                ", regionInternationalName='" + regionInternationalName + '\'' +
                '}';
    }
}
