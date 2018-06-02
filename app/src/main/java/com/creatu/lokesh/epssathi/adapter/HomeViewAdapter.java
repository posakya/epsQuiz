package com.creatu.lokesh.epssathi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.model_class.HomeModelClass;

import java.util.ArrayList;



public class HomeViewAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<HomeModelClass> homeModelClasses = new ArrayList<>();
    LayoutInflater inflater;

    public HomeViewAdapter(@NonNull Context context, int resource, ArrayList<HomeModelClass> homeModelClasses) {
        super(context, resource,homeModelClasses);
        this.context = context;
        this.resource = resource;
        this.homeModelClasses = homeModelClasses;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.home_list_item,null);
            viewHolder.homeText = convertView.findViewById(R.id.txt_home);
            viewHolder.homeTitle = convertView.findViewById(R.id.txt_home_title);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HomeModelClass homeModelClass = homeModelClasses.get(position);

        viewHolder.homeText.setText(homeModelClass.getHome_text());
        viewHolder.homeTitle.setText(homeModelClass.getHome_title());


        return convertView;

    }

    private class ViewHolder{
        private TextView homeText,homeTitle;
    }


}
