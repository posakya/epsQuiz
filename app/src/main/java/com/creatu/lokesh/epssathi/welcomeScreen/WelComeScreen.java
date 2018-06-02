package com.creatu.lokesh.epssathi.welcomeScreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.db_handler.dbHandler;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WelComeScreen extends AppCompatActivity {

    private dbHandler db;
    AVLoadingIndicatorView avi;
    private Handler mTimerHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wel_come_screen);
        avi = (AVLoadingIndicatorView)findViewById(R.id.avi);


//        Runnable mTimerExecutor = new Runnable() {
//
//            @Override
//            public void run() {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//
//                avi.hide();
//            }
//        };
//
//        mTimerHandler.postDelayed(mTimerExecutor, 7000);

        new jsonParsingInvestor().execute();
        new jsonParsingRemitance().execute();
        new jsonParsingRestaurant().execute();
        new jsonParsingReturnee().execute();
        new jsonParsingShelter().execute();
        new jsonParsingShopping().execute();

        avi.show();

        db = new dbHandler(WelComeScreen.this);
    }

    @SuppressLint("StaticFieldLeak")
    class jsonParsingInvestor extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            try {
                return jsonData();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        private String jsonData() throws IOException {

            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("http://epssathi.com/api/api/investor")
                    .build();

            Response response = client.newCall(request).execute();
            String result=response.body().string();
            System.out.println("Result " +result);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.optString("status");

                    if (status.equals("200")){
                        JSONArray results = jsonObject.getJSONArray("results");

                        for (int i = 0; i<results.length(); i++){
                            JSONObject c = results.getJSONObject(i);
                            String id = c.optString("id");
                            String title = c.optString("title");
                            String image = c.optString("image");
                            String description = c.optString("description");

                            db.insertData(title,description,image,"investor");
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Error in fetching data", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @SuppressLint("StaticFieldLeak")
    class jsonParsingShelter extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();



        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                return jsonData();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        private String jsonData() throws IOException {

            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("http://epssathi.com/api/api/shelter")
                    .build();

            Response response = client.newCall(request).execute();
            String result=response.body().string();
            System.out.println("Result " +result);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.optString("status");

                    if (status.equals("200")){
                        JSONArray results = jsonObject.getJSONArray("results");

                        for (int i = 0; i<results.length(); i++){
                            JSONObject c = results.getJSONObject(i);
                            String id = c.optString("id");
                            String title = c.optString("title");
                            String image = c.optString("image");
                            String description = c.optString("description");

                            db.insertData(title,description,image,"shelter");

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            avi.hide();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Error in fetching data", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @SuppressLint("StaticFieldLeak")
    class jsonParsingRestaurant extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();



        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                return jsonData();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        private String jsonData() throws IOException {

            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("http://epssathi.com/api/api/restaurant")
                    .build();

            Response response = client.newCall(request).execute();
            String result=response.body().string();
            System.out.println("Result " +result);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.optString("status");

                    if (status.equals("200")){
                        JSONArray results = jsonObject.getJSONArray("results");

                        for (int i = 0; i<results.length(); i++){
                            JSONObject c = results.getJSONObject(i);
                            String id = c.optString("id");
                            String title = c.optString("title");
                            String image = c.optString("image");
                            String description = c.optString("description");

                            db.insertData(title,description,image,"restaurant");
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Error in fetching data", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }



    @SuppressLint("StaticFieldLeak")
    class jsonParsingRemitance extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();



        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                return jsonData();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        private String jsonData() throws IOException {

            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("http://epssathi.com/api/api/remitance")
                    .build();

            Response response = client.newCall(request).execute();
            String result=response.body().string();
            System.out.println("Result " +result);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.optString("status");

                    if (status.equals("200")){
                        JSONArray results = jsonObject.getJSONArray("results");

                        for (int i = 0; i<results.length(); i++){
                            JSONObject c = results.getJSONObject(i);
                            String id = c.optString("id");
                            String title = c.optString("title");
                            String image = c.optString("image");
                            String description = c.optString("description");

                            db.insertData(title,description,image,"remitance");
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Error in fetching data", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }



    @SuppressLint("StaticFieldLeak")
    class jsonParsingShopping extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... strings) {

            try {
                return jsonData();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        private String jsonData() throws IOException {

            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("http://epssathi.com/api/api/shopping")
                    .build();

            Response response = client.newCall(request).execute();
            String result=response.body().string();
            System.out.println("Result " +result);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.optString("status");

                    if (status.equals("200")){
                        JSONArray results = jsonObject.getJSONArray("results");

                        for (int i = 0; i<results.length(); i++){
                            JSONObject c = results.getJSONObject(i);
                            String id = c.optString("id");
                            String title = c.optString("title");
                            String image = c.optString("image");
                            String description = c.optString("description");

                            db.insertData(title,description,image,"shopping");


                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Error in fetching data", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @SuppressLint("StaticFieldLeak")
    class jsonParsingReturnee extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            try {
                return jsonData();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        private String jsonData() throws IOException {

            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("http://epssathi.com/api/api/returnee")
                    .build();

            Response response = client.newCall(request).execute();
            String result=response.body().string();
            System.out.println("Result " +result);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.optString("status");

                    if (status.equals("200")){
                        JSONArray results = jsonObject.getJSONArray("results");

                        for (int i = 0; i<results.length(); i++){
                            JSONObject c = results.getJSONObject(i);
                            String id = c.optString("id");
                            String title = c.optString("title");
                            String image = c.optString("image");
                            String description = c.optString("description");

                            db.insertData(title,description,image,"returnee");

                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Error in fetching data", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
