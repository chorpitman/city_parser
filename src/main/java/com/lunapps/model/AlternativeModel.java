package com.lunapps.model;

import javax.persistence.*;

@Entity
@Table(name = "alter_names")
public class AlternativeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "city_index")
    private long geoNameId;

    @Column(name = "iso_uk")
    private String isoLang;

    @Column(name = "city_ukr_name")
    private String cyrillicName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGeoNameId() {
        return geoNameId;
    }

    public void setGeoNameId(long geoNameId) {
        this.geoNameId = geoNameId;
    }

    public String getIsoLang() {
        return isoLang;
    }

    public void setIsoLang(String isoLang) {
        this.isoLang = isoLang;
    }

    public String getCyrillicName() {
        return cyrillicName;
    }

    public void setCyrillicName(String cyrillicName) {
        this.cyrillicName = cyrillicName;
    }

    @Override
    public String toString() {
        return "AlternativeModel{" +
                "id=" + id +
                ", geoNameId=" + geoNameId +
                ", isoLang='" + isoLang + '\'' +
                ", cyrillicName='" + cyrillicName + '\'' +
                '}';
    }
}
