package com.creatu.lokesh.epssathi.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.adapter.EpsDocumentCategoryAdapter;
import com.creatu.lokesh.epssathi.adapter.EpsDocumentDetailsAdapter;
import com.creatu.lokesh.epssathi.model_class.EpsDocumentCategoryModelClass;
import com.creatu.lokesh.epssathi.model_class.EpsDocumentDetailsModelClass;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class EpsDetail extends AppCompatActivity {

    ListView listView;
    List<EpsDocumentDetailsModelClass> epsDocumentDetailsModelClasses = new ArrayList<>();
    AVLoadingIndicatorView avi;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.eps_category_list_view);

        listView = findViewById(R.id.listView);
        avi = findViewById(R.id.avi);
        toolbar = findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Details");

        String slug = getIntent().getExtras().getString("slug");

        epsDetail epsDetails = new epsDetail(slug);
        epsDetails.execute();

    }

    public class epsDetail extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            avi.show();
        }

        public epsDetail(String slug) {
            this.slug = slug;
        }

        String slug;

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
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("slug_url", slug)
                    .build();
            Request request = new Request.Builder().url("http://epssathi.com/api/api/document-details").post(requestBody).build();
            okhttp3.Response response = client.newCall(request).execute();
            String result = response.body().string();

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

                            EpsDocumentDetailsModelClass epsDocumentDetailsModelClass = new EpsDocumentDetailsModelClass();

                            epsDocumentDetailsModelClass.setSn(c.optString("sn"));
                            epsDocumentDetailsModelClass.setName(c.optString("name"));
                            epsDocumentDetailsModelClass.setDob(c.optString("dob"));
                            epsDocumentDetailsModelClass.setGender(c.optString("gender"));
                            epsDocumentDetailsModelClass.setAddress(c.optString("address"));
                            epsDocumentDetailsModelClass.setContact_before(c.optString("contact_befor"));


                            epsDocumentDetailsModelClasses.add(epsDocumentDetailsModelClass);

                        }

                        EpsDocumentDetailsAdapter epsDocumentCategoryAdapter = new EpsDocumentDetailsAdapter(EpsDetail.this, epsDocumentDetailsModelClasses);
                        listView.setAdapter(epsDocumentCategoryAdapter);
                        //  avLoadingIndicatorView.hide();

                        avi.hide();

                    } else {
                        Toast.makeText(EpsDetail.this, "Error in fetching data", Toast.LENGTH_SHORT).show();
                        avi.hide();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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

