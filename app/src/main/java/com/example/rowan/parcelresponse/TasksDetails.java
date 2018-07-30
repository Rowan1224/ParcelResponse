package com.example.rowan.parcelresponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TasksDetails implements Serializable {

    @SerializedName("pk") String pk;
    @SerializedName("sender_phone") String phone;
    @SerializedName("receiver_phone")String receiver_phone;
    @SerializedName("tracking_code")String tracking_code;
    @SerializedName("description") String descriptions;
    @SerializedName("destination_code")String destination_code;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public TasksDetails(String pk, String phone, String receiver_phone, String tracking_code, String description, String destination_code) {
        this.phone = phone;
        this.receiver_phone = receiver_phone;
        this.tracking_code = tracking_code;
        this.descriptions = description;
        this.destination_code = destination_code;
        this.pk=pk;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getTracking_code() {
        return tracking_code;
    }

    public void setTracking_code(String tracking_code) {
        this.tracking_code = tracking_code;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String description) {
        this.descriptions = description;
    }

    public String getDestination_code() {
        return destination_code;
    }

    public void setDestination_code(String destination_code) {
        this.destination_code = destination_code;
    }
}
