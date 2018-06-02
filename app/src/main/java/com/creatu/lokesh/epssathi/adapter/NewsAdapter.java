package com.creatu.lokesh.epssathi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.text.format.DateUtils;
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
import com.creatu.lokesh.epssathi.model_class.NewsModalClass;

import java.text.ParseException;
import java.util.List;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by lokesh on 5/15/18.
 */

public class NewsAdapter extends ArrayAdapter{

    List<NewsModalClass> newsModalClasses;
    int resources;
    Context context;
    LayoutInflater inflater;

    public NewsAdapter(Context context, int resources, List<NewsModalClass> newsModalClasses){
        super(context,resources,newsModalClasses);
        this.context = context;
        this.resources = resources;
        this.newsModalClasses = newsModalClasses;
        inflater =  (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null){
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.fragment_news,null);
            holder.txt_news_date = convertView.findViewById(R.id.news_date);
            holder.txt_news_desc = convertView.findViewById(R.id.news_desc);
            holder.txt_news_title = convertView.findViewById(R.id.news_title);
            holder.news_image = convertView.findViewById(R.id.news_image);
            holder.progressBar = convertView.findViewById(R.id.progressBar);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsModalClass newsModalClass = newsModalClasses.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        long time = 0;
        try {
            time = sdf.parse(newsModalClass.getNewsdate()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long now = System.currentTimeMillis();

        CharSequence ago =
                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);

        holder.txt_news_title.setText(Html.fromHtml(newsModalClass.getTitle()));
        holder.txt_news_desc.setText(Html.fromHtml(newsModalClass.getDescription()));
        holder.txt_news_date.setText(ago);

        Glide.with(context)
                .load(newsModalClass.getImage()).listener(new RequestListener<String, GlideDrawable>() {
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
        }).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.news_image);

        return convertView;
    }

    class ViewHolder {
        private TextView txt_news_title,txt_news_date,txt_news_desc;
        private ImageView news_image;
        ProgressBar progressBar;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
