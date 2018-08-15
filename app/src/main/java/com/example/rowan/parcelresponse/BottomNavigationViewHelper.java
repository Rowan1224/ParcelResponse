package com.example.rowan.parcelresponse;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;


import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {
    public static void setupBottomNavigationViewHelper(BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(true);
    }
    public static void enableNavigation(final Context context,BottomNavigationViewEx viewEx){
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.ic_scan:
                        Intent intent1=new Intent(context, MainActivity.class);
                        //intent1.putExtra("Code",1);
                        context.startActivity(intent1);
                        break;
                    case R.id.ic_task:
                        Intent intent2=new Intent(context, EmpTaskList.class);
                        intent2.putExtra("Code",1);
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_request:
                        Intent intent3=new Intent(context, EmpTaskList.class);
                        intent3.putExtra("Code",2);//Activity_Num=1
                        context.startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }
}
