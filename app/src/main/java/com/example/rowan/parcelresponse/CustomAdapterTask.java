package com.example.rowan.parcelresponse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterTask extends ArrayAdapter<TasksDetails>{

    Context context;
    private List<TasksDetails> TaskList=new ArrayList<>();

    public CustomAdapterTask(@NonNull Context context, List<TasksDetails> taskList) {
        super(context,R.layout.layout_custom_task,taskList);
        this.context = context;
        TaskList = taskList;
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
            convertView=inflater.inflate(R.layout.layout_custom_task,parent,false);

            viewHolder.rphone=(TextView)convertView.findViewById(R.id.Rphone);
            viewHolder.sphone=(TextView)convertView.findViewById(R.id.Sphone);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();

        }

        assert tasksDetails != null;
        viewHolder.sphone.setText(tasksDetails.getDescriptions());
        viewHolder.rphone.setText(tasksDetails.getDestination_code());

        return convertView;

    }
}
