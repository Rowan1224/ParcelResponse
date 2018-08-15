package com.example.rowan.parcelresponse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAdapterTask extends ArrayAdapter<TasksDetails>{

    Context context;
    private List<TasksDetails> TaskList=new ArrayList<>();

    Map<String,String> price=new HashMap<>();


    public CustomAdapterTask(@NonNull Context context, List<TasksDetails> taskList) {
        super(context,R.layout.task_list_item,taskList);
        this.context = context;
        TaskList = taskList;
        price.put("Yellow","Yellow (Tk 80)");
        price.put("White","White (Tk 150)");
        price.put("Box","Box (Tk 220)");
        price.put("Container","Container (Tk 1000)");
    }

    public static class ViewHolder{

        TextView sphone;
        TextView rphone;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TasksDetails tasksDetails=getItem(position);
        ViewHolder viewHolder;

        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.task_list_item,parent,false);

            viewHolder.rphone=(TextView)convertView.findViewById(R.id.txtDestinationCode);
            viewHolder.sphone=(TextView)convertView.findViewById(R.id.txtDescription);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();

        }

        assert tasksDetails != null;
        CardView cardView=(CardView)convertView.findViewById(R.id.card);

        if(position%2==0)
        {
            cardView.setBackgroundResource(R.color.exp1);
        }
        else
            cardView.setBackgroundResource(R.color.white);

      if(price.containsKey(tasksDetails.getDescriptions())){
            viewHolder.sphone.setText("Description : "+price.get(tasksDetails.getDescriptions()));
        }

      else
        viewHolder.sphone.setText("Description : "+tasksDetails.getDescriptions());
        viewHolder.rphone.setText("Destination Code: "+tasksDetails.getDestination_code());

        return convertView;

    }
}
