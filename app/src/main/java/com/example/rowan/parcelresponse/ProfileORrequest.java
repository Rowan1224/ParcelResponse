package com.example.rowan.parcelresponse;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileORrequest extends AppCompatActivity {

    private static final String TAG = "ProfileORrequest";
    private static final int Activity_Num=1;
    public TextView PhoneSend,PhoneRec,AddRec,btnAcc,btnCan,NameSend;
    CustomerDetails sender,receiver;
    TasksDetails details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_orrequest);

        init();
        details=(TasksDetails) getIntent().getSerializableExtra("data");

        String url1=ApiCall.Customer+details.getPhone();
        String url2=ApiCall.Customer+details.getReceiver_phone();
        int Code=getIntent().getIntExtra("Code",1);


        
        Customer(ProfileORrequest.this, new VolleyCallback() {
            @Override
            public void onSuccessResponse(CustomerDetails response) {

                sender=response;
                Log.d(TAG, "onSuccessResponse: "+"Sender: "+response.getPhone());
                PhoneSend.setText(sender.getPhone());
                NameSend.setText(sender.getName());

            }
        },url1);

        Customer(ProfileORrequest.this, new VolleyCallback() {
            @Override
            public void onSuccessResponse(CustomerDetails response) {

                receiver=response;
                Log.d(TAG, "onSuccessResponse: "+"Receiver: "+response.getPhone());
                PhoneRec.setText(receiver.getPhone());
                AddRec.setText(receiver.getAddress());

            }
        },url2);

        if(Code==1)
        {


          btnAcc.setText("Call Parcel Receiver");
          btnAcc.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent CallIntent=new Intent(Intent.ACTION_DIAL);
                  CallIntent.setData(Uri.parse("tel:"+receiver.getPhone()));
                  if(CallIntent.resolveActivity(getPackageManager())!=null)
                  {
                      startActivity(CallIntent);
                  }

              }
          });


        }
        else
        {
            ImageView img=(ImageView)findViewById(R.id.call);
            img.setVisibility(View.INVISIBLE);

     btnAcc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String pk=details.getPk();

                    Map<String,String > params=new HashMap<>();
                    params.put("tracking_code",ApiCall.EmpId);
                    params.put("temporary_parcel","true");

                    ApiCall.ParcelRequest(ProfileORrequest.this,pk,params);
                    finish();
                    Intent intent=new Intent(ProfileORrequest.this,MainActivity.class);
                    startActivity(intent);
                }
            });

        }


    }

    public void init()
    {
        PhoneRec =(TextView)findViewById(R.id.PhoneRec);
        PhoneSend=(TextView) findViewById(R.id.PhoneSender);
        AddRec=(TextView)findViewById(R.id.AddressRec);
        NameSend=(TextView)findViewById(R.id.NameSender);
        btnAcc=(TextView)findViewById(R.id.btnAccept);

        //setupBottomNavigationViewEx();
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
    private void setupBottomNavigationViewEx(){
        BottomNavigationViewEx bottomNavigationViewEx= findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationViewHelper(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(ProfileORrequest.this,bottomNavigationViewEx);
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(Activity_Num);
        menuItem.setChecked(true);
    }
    public interface VolleyCallback {

        void onSuccessResponse(CustomerDetails response);
        
    }

}
