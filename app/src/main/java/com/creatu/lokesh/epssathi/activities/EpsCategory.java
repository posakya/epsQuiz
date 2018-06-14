package com.creatu.lokesh.epssathi.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.adapter.EpsDocumentAdapter;
import com.creatu.lokesh.epssathi.adapter.EpsDocumentCategoryAdapter;
import com.creatu.lokesh.epssathi.adapter.NewsAdapter;
import com.creatu.lokesh.epssathi.model_class.EpsDocumentCategoryModelClass;
import com.creatu.lokesh.epssathi.model_class.EpsDocumentModelClass;
import com.creatu.lokesh.epssathi.model_class.EpsProcessModelClass;
import com.creatu.lokesh.epssathi.model_class.NewsModalClass;
import com.creatu.lokesh.epssathi.response.RetrofitResponse;
import com.creatu.lokesh.epssathi.retrofit_interfaces.ApiInterface;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpsCategory extends AppCompatActivity {

    ListView listView;
    List<EpsDocumentCategoryModelClass> epsDocumentCategoryModelClasses = new ArrayList<>();
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



        String slug = getIntent().getExtras().getString("slug");
        String title = getIntent().getExtras().getString("title");
        getSupportActionBar().setTitle(Html.fromHtml(title));

        epsCategory category = new epsCategory("http://epsnepal.gov.np/document-cat/employment-contract/");
        category.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EpsDocumentCategoryModelClass epsDocumentModelClass = epsDocumentCategoryModelClasses.get(position);
                Intent intent = new Intent(EpsCategory.this, EpsDetail.class);
                intent.putExtra("slug",epsDocumentModelClass.getSlug());
                startActivity(intent);
            }
        });

       // getDocumentDetail();

    }

//    public void getDocumentDetail(){
//        ApiInterface documentApi = RetrofitResponse.getRetrofit().create(ApiInterface.class);
//
//        Call<EpsProcessModelClass> listCall = documentApi.getDocument();
//
//        listCall.enqueue(new Callback<EpsProcessModelClass>() {
//            @Override
//            public void onResponse(Call<EpsProcessModelClass> call, Response<EpsProcessModelClass> response) {
//                System.out.println("Response : "+response.body().getResults());
//                EpsDocumentCategoryAdapter adapter = new EpsDocumentCategoryAdapter(EpsCategory.this,response.body().getResults1());
//                listView.setAdapter(adapter);
//
//                avi.hide();
//            }
//
//            @Override
//            public void onFailure(Call<EpsProcessModelClass> call, Throwable t) {
//                System.out.println("ResponseError : "+t.getMessage());
//                avi.hide();
//
//            }
//        });
//
//
//    }

    public class epsCategory extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            avi.show();
        }

        public epsCategory(String slug) {
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
                    .addFormDataPart("slug_url",slug)
                    .build();
            Request request = new Request.Builder().url("http://epssathi.com/api/api/document-cat").post(requestBody).build();
            okhttp3.Response response = client.newCall(request).execute();
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

                            EpsDocumentCategoryModelClass epsDocumentCategoryModelClass = new EpsDocumentCategoryModelClass();

                            epsDocumentCategoryModelClass.setTitle(c.optString("title"));
                            epsDocumentCategoryModelClass.setPublished_date(c.optString("published_date"));
                            epsDocumentCategoryModelClass.setSlug(c.optString("slug"));

                            epsDocumentCategoryModelClasses.add(epsDocumentCategoryModelClass);

                        }

                            EpsDocumentCategoryAdapter epsDocumentCategoryAdapter = new EpsDocumentCategoryAdapter(EpsCategory.this,epsDocumentCategoryModelClasses);
                            listView.setAdapter(epsDocumentCategoryAdapter);
                          //  avLoadingIndicatorView.hide();

                        avi.hide();

                    } else {
                        Toast.makeText(EpsCategory.this, "Error in fetching data", Toast.LENGTH_SHORT).show();
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
