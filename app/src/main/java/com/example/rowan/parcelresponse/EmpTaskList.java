package com.example.rowan.parcelresponse;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class EmpTaskList extends AppCompatActivity {

    private static String TasksUrl=ApiCall.Task;
    private static String NewParcelUrl=ApiCall.NewParcel;
    int Code;
    private static final String TAG = "EmpTaskList";
    ListView list;
    public  static List<TasksDetails> results=new ArrayList<TasksDetails>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_task_list);
        list=(ListView)findViewById(R.id.tasks);
        String url;


       Code=getIntent().getIntExtra("Code",1);



       if(Code==1)
       {
           url=TasksUrl;

       }
       else
           url=NewParcelUrl;

       TasksList(EmpTaskList.this, new VolleyCallback() {
           @Override
           public void onSuccessResponse(List<TasksDetails> response) {
               CustomAdapterTask adapterTask=new CustomAdapterTask(EmpTaskList.this,results);
               adapterTask.notifyDataSetChanged();
               list.setAdapter(adapterTask);
           }
       },url);



       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               TasksDetails details=results.get(i);
               Intent intent=new Intent(EmpTaskList.this,ProfileORrequest.class);
               intent.putExtra("data",details);
               intent.putExtra("Code",Code);
               startActivity(intent);

           }
       });













    }
    public static void TasksList(Context context, final VolleyCallback callback,String url)
    {
        RequestQueue queue= Volley.newRequestQueue(context);
        final ProgressDialog dialog=ProgressDialog.show(context,"Tasks","Loading");
        StringRequest tasks=new StringRequest(Request.Method.GET,
                url,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                results= Arrays.asList(gson.fromJson(response,TasksDetails[].class));
                Log.d(TAG, "onResponse: "+results.size());
                callback.onSuccessResponse(results);

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
}
