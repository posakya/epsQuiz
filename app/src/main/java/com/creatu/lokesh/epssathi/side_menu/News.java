package com.creatu.lokesh.epssathi.side_menu;


import android.app.Dialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.adapter.NewsAdapter;
import com.creatu.lokesh.epssathi.constant.Constant;
import com.creatu.lokesh.epssathi.db_handler.dbHandler;
import com.creatu.lokesh.epssathi.model_class.NewsModalClass;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class News extends Fragment {


    View view;
    List<NewsModalClass> newsModalClasses = new ArrayList<>();
    ListView listView;
    AVLoadingIndicatorView avLoadingIndicatorView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.list_view, container, false);
        listView = view.findViewById(R.id.listView);
        avLoadingIndicatorView = view.findViewById(R.id.avi);
        getActivity().setTitle("News");

        new JsonNotice().execute();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NewsModalClass newsModalClass1 = newsModalClasses.get(position);

                final Dialog dialog1 = new Dialog(getActivity(),android.R.style.Theme_Holo_Light);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
                dialog1.setContentView(R.layout.detail_page);
                dialog1.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                dialog1.setCanceledOnTouchOutside(true);

                Toolbar toolbar=dialog1.findViewById(R.id.toolbar);
                toolbar.setTitle(newsModalClass1.getTitle());
                TextView textView = dialog1.findViewById(R.id.detail_desc);
                ImageView imageView = dialog1.findViewById(R.id.detail_image);
                final ProgressBar progressBar = dialog1.findViewById(R.id.progressBar);
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
                toolbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });

                textView.setText(Html.fromHtml(newsModalClass1.getDescription()));
                Glide.with(getActivity())
                        .load(newsModalClass1.getImage()).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

                dialog1.show();

            }
        });

        return view;
    }

    class JsonNotice extends AsyncTask<String, Void, String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            avLoadingIndicatorView.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                return JsonData();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @SuppressWarnings("deprecation")
        private String JsonData() throws IOException {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://epssathi.com/api/api/news").build();
            Response response = client.newCall(request).execute();
            String result=response.body().string();

            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.optString("status");

                    if (status.equals("200")) {
                        JSONArray results = jsonObject.getJSONArray("results");

                        for (int i = 0; i < results.length(); i++) {
                            JSONObject c = results.getJSONObject(i);

                            NewsModalClass newsModalClass = new NewsModalClass();

                            newsModalClass.setId(c.optString("id"));
                            newsModalClass.setDescription(c.optString("description"));
                            newsModalClass.setTitle(c.optString("title"));
                            newsModalClass.setNewsdate(c.optString("newsdate"));
                            newsModalClass.setImage(c.optString("image"));

                            newsModalClasses.add(newsModalClass);

                        }
                        if (getActivity()
                                != null){
                            NewsAdapter newsAdapter = new NewsAdapter(getActivity(),R.layout.fragment_news,newsModalClasses);
                            listView.setAdapter(newsAdapter);
                            avLoadingIndicatorView.hide();
                        }


                    } else {
                        Toast.makeText(getActivity(), "Error in fetching data", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void onResume(){
        super.onResume();


        ((MainActivity) getActivity())
                .setActionBarTitle("News");

    }
}
