package com.example.rowan.parcelresponse;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ApiCall {

    public static final String LocationUpdateUrl="192.168.0.104:8080/api/LocationUpdate/";

    public static void LocationUpdate(final Context context, final String Latitude, final String Longitude, String key)
    {
        RequestQueue queue= Volley.newRequestQueue(context);

        StringRequest put=new StringRequest(Request.Method.PUT, LocationUpdateUrl+key, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context,"Updated Successfully",Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,"Error on Update location",Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("Latitude",Latitude);
                params.put("Longitude",Longitude);


                return params;


            }
        };

        queue.add(put);
    }


}
