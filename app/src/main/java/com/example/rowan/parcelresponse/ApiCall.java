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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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


    public static final String EmpId="emp-625";
    public static final String URL="http://192.168.0.118:8080/";
    public static final String LocationUpdateUrl = URL+"api/LocationUpdate/";
    public static final String Task = URL+"api/TrackingCode/"+EmpId;
    public static final String NewParcel =URL+ "api/Parceltemp";
    public static final String Customer = URL+"api/CustomerInfo/";
    public static final String UpdateReq = URL+"api/ParcelUpdate/";
    public static final String pk=URL+"api/EmpId/";
    public static final String Id=URL+"api/UpdateReq/";



    public static void LocationUpdate(final Context context, final String Latitude,
                                      final String Longitude, final String key) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest put = new StringRequest(Request.Method.PUT, LocationUpdateUrl+key+'/',
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();

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

    public static void ParcelRequest(final Context context, String pk, final Map<String,String> params){

        RequestQueue queue=Volley.newRequestQueue(context);
        StringRequest update=new StringRequest(Request.Method.PUT, UpdateReq+pk+'/',
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

            Map<String,String> map=params;

                return map;
            }
        };

        queue.add(update);

    }

    public static void getInstance(Context context)
    {
        RequestQueue queue =Volley.newRequestQueue(context);
        StringRequest get=new StringRequest(Request.Method.GET, pk+EmpId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               Gson gson=new Gson();

        try {
            LocationDetails details = Arrays.asList(gson.fromJson(response, LocationDetails[].class)).get(0);
            MainActivity.key = details.getPk();
            Log.d(TAG, "onResponse: " + MainActivity.key);
        }catch (Exception e)
        {
            e.printStackTrace();
        }



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
        queue.add(get);
    }

    public static void getId(final Context context, String qr)
    {
        RequestQueue queue=Volley.newRequestQueue(context);
        StringRequest get=new StringRequest(Request.Method.GET, Id+qr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Map<String,String > params=new HashMap<>();
                params.put("tracking_code",ApiCall.EmpId);
                params.put("temporary_parcel","false");
                JsonParser parser=new JsonParser();
                JsonArray result= (JsonArray) parser.parse(response);
                String pk=result.get(0).getAsJsonObject().get("pk").getAsString();

                ApiCall.ParcelRequest(context,pk,params);

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
        queue.add(get);

    }









}