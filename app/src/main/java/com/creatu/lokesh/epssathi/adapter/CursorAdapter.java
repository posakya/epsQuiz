package com.creatu.lokesh.epssathi.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.db_handler.dbHandler;

/**
 * Created by lokesh on 4/8/18.
 */

public class CursorAdapter extends SimpleCursorAdapter {

    private dbHandler db;

    public CursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.all_grid_item, parent, false);
        ViewHolder holder = new ViewHolder();
        convertView.setTag(holder);

        holder.txt_desc =  convertView.findViewById(R.id.txtDesc);
        holder.txt_title = convertView.findViewById(R.id.txtTitle);
        holder.imageView = convertView.findViewById(R.id.imageView_investor);

        holder.progressBar = convertView.findViewById(R.id.progressBar);
        db=new dbHandler(context);
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        final ViewHolder holder = (ViewHolder) view.getTag();

        String title = cursor.getString(cursor.getColumnIndex(dbHandler.title));
        String desc = cursor.getString(cursor.getColumnIndex(dbHandler.description));
        String image = cursor.getString(cursor.getColumnIndex(dbHandler.image));
        String flag = cursor.getString(cursor.getColumnIndex(dbHandler.flag));

        holder.txt_title.setText(Html.fromHtml(title));
        holder.txt_desc.setText(desc);

        Glide.with(context)
                .load(image).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).thumbnail(0.7f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);


    }

    private class ViewHolder{

        private ProgressBar progressBar;
        private TextView txt_desc,txt_title;

        private ImageView imageView;

    }
}
