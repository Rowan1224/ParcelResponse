package com.example.rowan.parcelresponse;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ApiCall {
    private static final String TAG = "ApiCall";


    public static String EmpId="625";
    public static final String LocationUpdateUrl = "http://192.168.0.108:8080/api/LocationUpdate/1/";
    public static final String Task = "http://192.168.0.108:8080/api/TrackingCode/"+EmpId;
    public static final String NewParcel = "http://192.168.0.108:8080/api/Parceltemp";
    public static final String Customer = "http://192.168.0.108:8080/api/CustomerInfo/";
    public static final String UpdateReq = "http://192.168.0.108:8080/api/ParcelUpdate/";



    public static void LocationUpdate(final Context context, final String Latitude,
                                      final String Longitude, String key) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest put = new StringRequest(Request.Method.PUT, LocationUpdateUrl,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



                String body="check";
                //get status code here
                String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                if(error.networkResponse.data!=null) {
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "onErrorResponse: "+body);

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("employee_id","625");
                params.put("latitude", Latitude);
                params.put("longitude", Longitude);




                return params;


            }
        };

        queue.add(put);
    }

    public static void ParselRequest(final Context context, String pk){

        RequestQueue queue=Volley.newRequestQueue(context);
        StringRequest update=new StringRequest(Request.Method.PUT, UpdateReq+pk,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Request Updated", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String body="check";
                //get status code here
                String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                if(error.networkResponse.data!=null) {
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "onErrorResponse: "+body);

            }
        });

    }









}