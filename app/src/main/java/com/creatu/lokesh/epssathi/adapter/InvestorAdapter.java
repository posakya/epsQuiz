package com.creatu.lokesh.epssathi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.model_class.InvestorClass;

import java.util.ArrayList;

/**
 * Created by lokesh on 4/4/18.
 */

public class InvestorAdapter extends ArrayAdapter {

    LayoutInflater inflater;
    Context context;
    int resource;
    ArrayList<InvestorClass> investorClasses = new ArrayList<>();

    public InvestorAdapter(@NonNull Context context, int resource, ArrayList<InvestorClass> investorClasses) {
        super(context, resource,investorClasses);
        this.context = context;
        this.resource = resource;
        this.investorClasses = investorClasses;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        final ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.all_grid_item,null);
            viewHolder.txtTitle = convertView.findViewById(R.id.txtTitle);
            viewHolder.imageView = convertView.findViewById(R.id.imageView_investor);
            viewHolder.progressBar = convertView.findViewById(R.id.progressBar);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        InvestorClass investorClass = investorClasses.get(position);

        viewHolder.txtTitle.setText(investorClass.getTitle());

        Glide.with(context)
                .load(investorClass.getImage()).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                viewHolder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                viewHolder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.imageView);


        return convertView;

    }

    private class ViewHolder{
        private TextView txtTitle;
        ImageView imageView;
        ProgressBar progressBar;
    }

}
