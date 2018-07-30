package com.example.rowan.parcelresponse;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileORrequest extends AppCompatActivity {

    private static final String TAG = "ProfileORrequest";
    public static List<CustomerDetails> results=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_orrequest);

        TasksDetails details=(TasksDetails) getIntent().getSerializableExtra("data");

        String url1=ApiCall.Customer+details.getPhone();
        String url2=ApiCall.Customer+details.getReceiver_phone();
        int Code=getIntent().getIntExtra("Code",1);
        
        Customer(ProfileORrequest.this, new VolleyCallback() {
            @Override
            public void onSuccessResponse(CustomerDetails response) {


                Log.d(TAG, "onSuccessResponse: "+"Sender: "+response.getPhone());

            }
        },url1);

        Customer(ProfileORrequest.this, new VolleyCallback() {
            @Override
            public void onSuccessResponse(CustomerDetails response) {

                // handle receiver info
                Log.d(TAG, "onSuccessResponse: "+"Receiver: "+response.getPhone());

            }
        },url2);

        if(Code==1)
        {
            //change the view of accept-request
        }
        else
        {

            String pk=details.getPk();
            //if emp accepts the req
            ApiCall.ParselRequest(ProfileORrequest.this,pk);
        }


    }


    public static void Customer(Context context, final VolleyCallback callback, String url)
    {
        RequestQueue queue= Volley.newRequestQueue(context);
        final ProgressDialog dialog=ProgressDialog.show(context,"Tasks","Loading");
        StringRequest tasks=new StringRequest(Request.Method.GET,
                url,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
               CustomerDetails details= (gson.fromJson(response,CustomerDetails.class));

                callback.onSuccessResponse(details);

                dialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
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
        }
        );
        queue.add(tasks);
    }
    public interface VolleyCallback {

        void onSuccessResponse(CustomerDetails response);
        
    }

}
