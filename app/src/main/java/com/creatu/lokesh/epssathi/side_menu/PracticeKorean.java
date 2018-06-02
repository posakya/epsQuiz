package com.creatu.lokesh.epssathi.side_menu;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.creatu.lokesh.epssathi.HttpHandler.HttpHandler;
import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.quiz.QuizActivity;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class PracticeKorean extends Fragment {


    View view;
    ListView listView;
    Toolbar toolbar;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ArrayList<HashMap<String, String>> lessonList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.list_view, container, false);
        getActivity().setTitle("Practise Korean");
        listView = view.findViewById(R.id.listView);
        avLoadingIndicatorView = view.findViewById(R.id.avi);

        lessonList = new ArrayList<>();

        new GetLessons().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> lessonList1 = lessonList.get(position);
                String id1 = lessonList1.get("id");
                String title1 = lessonList1.get("name");
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                intent.putExtra("id",id1);
                intent.putExtra("name",title1);
                startActivity(intent);

            }
        });

        return view;
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
            String jsonStr = sh.makeServiceCall("http://epssathi.com/api/api/lesson");


            if (jsonStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    String status = jsonObject.optString("status");

                    if (status.equals("200")){
                        JSONArray results = jsonObject.getJSONArray("results");

                        for (int i = 0; i<results.length(); i++){
                            JSONObject c = results.getJSONObject(i);
                            String id = c.optString("id");
                            String name = c.optString("name");

                            HashMap<String, String> lessonLists = new HashMap<>();

                            // adding each child node to HashMap key => value
                            lessonLists.put("id", id);
                            lessonLists.put("name",name);


                            lessonList.add(lessonLists);
                        }
                    }else {
                        Toast.makeText(getActivity(), "Error in fetching data", Toast.LENGTH_SHORT).show();
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

            if (getActivity()
                    != null){
                ListAdapter adapter = new SimpleAdapter(
                        getActivity(), lessonList,
                        R.layout.practice_page, new String[]{ "id", "name"}, new int[]{
                        R.id.lesson_id,R.id.lesson_name});

                listView.setAdapter(adapter);

                avLoadingIndicatorView.hide();
            }



        }

    }
    public void onResume(){
        super.onResume();


        ((MainActivity) getActivity())
                .setActionBarTitle("Practise Korean");

    }
}
