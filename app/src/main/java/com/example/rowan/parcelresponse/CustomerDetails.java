package com.example.rowan.parcelresponse;

import com.google.gson.annotations.SerializedName;

public class CustomerDetails {

    @SerializedName("phone") String phone;
    @SerializedName("name") String name;
    @SerializedName("address") String address;

    public CustomerDetails(String phone, String name, String address) {
        this.phone = phone;
        this.name = name;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;

    }

    public String getAddress() {
        return address;
    }
}
