package com.creatu.lokesh.epssathi.activities;

import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.creatu.lokesh.epssathi.HttpHandler.HttpHandler;
import com.creatu.lokesh.epssathi.R;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Chapter extends AppCompatActivity {

    ListView listView;
    Toolbar toolbar;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ArrayList<HashMap<String, String>> lessonList;
    String id,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chapter_list);

        listView = (ListView)findViewById(R.id.listView);
        avLoadingIndicatorView = (AVLoadingIndicatorView)findViewById(R.id.avi);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getExtras().getString("id");
        title = getIntent().getExtras().getString("title");

        lessonList = new ArrayList<>();

        toolbar.setTitle(title);

        new GetLessons().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> lessonList1 = lessonList.get(position);
                String id1 = lessonList1.get("id");
                String title1 = lessonList1.get("chapter_title");
                String desc = lessonList1.get("description");


                final Dialog dialog1 = new Dialog(Chapter.this,android.R.style.Theme_Holo_Light);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
                dialog1.setContentView(R.layout.detail_page);
                dialog1.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                dialog1.setCanceledOnTouchOutside(true);

                Toolbar toolbar=dialog1.findViewById(R.id.toolbar);
                toolbar.setTitle(title1);
                TextView textView = dialog1.findViewById(R.id.detail_desc);
//                ImageView imageView = dialog1.findViewById(R.id.detail_image);
//                final ProgressBar progressBar = dialog1.findViewById(R.id.progressBar);
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
                toolbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });

                textView.setText(Html.fromHtml(desc));

//                Glide.with(Chapter.this)
//                        .load().listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        progressBar.setVisibility(View.GONE);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        progressBar.setVisibility(View.GONE);
//                        return false;
//                    }
//                }).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

                dialog1.show();

            }
        });

    }

    private class GetLessons extends AsyncTask<String, Object, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            avLoadingIndicatorView.show();

        }

        @Override
        protected String doInBackground(String... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall("http://epssathi.com/api/api/unit/"+id);


            if (jsonStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    String status = jsonObject.optString("status");

                    if (status.equals("200")){
                        JSONArray results = jsonObject.getJSONArray("results");

                        for (int i = 0; i<results.length(); i++){
                            JSONObject c = results.getJSONObject(i);
                            String id = c.optString("id");
                            String chapter_title = c.optString("chapter_title");
                            String description = c.optString("description");

                            HashMap<String, String> lessonLists = new HashMap<>();

                            // adding each child node to HashMap key => value
                            lessonLists.put("id", id);
                            lessonLists.put("chapter_title",chapter_title);
                            lessonLists.put("description",description);


                            lessonList.add(lessonLists);
                        }
                    }else {
                        Toast.makeText(Chapter.this, "Error in fetching data", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            return jsonStr;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ListAdapter adapter = new SimpleAdapter(
                    Chapter.this, lessonList,
                    R.layout.lesson_list_item, new String[]{ "id", "chapter_title"}, new int[]{
                    R.id.lesson_id,R.id.lesson_name});

            listView.setAdapter(adapter);

            avLoadingIndicatorView.hide();

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                invalidateOptionsMenu();
                return super.onOptionsItemSelected(item);
        }
    }
}
