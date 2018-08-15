package com.example.rowan.parcelresponse;

import com.google.gson.annotations.SerializedName;

public class LocationDetails {


    @SerializedName("pk") String pk;
    @SerializedName("employee_id") String employee_id;
    @SerializedName("latitude") String latitude;
    @SerializedName("longitude") String longitude;
    @SerializedName("active")String active;

    public LocationDetails(String pk, String employee_id, String latitude, String longitude, String active) {
        this.pk = pk;
        this.employee_id = employee_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.active = active;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
