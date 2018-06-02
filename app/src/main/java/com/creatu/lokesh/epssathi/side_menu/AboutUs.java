package com.creatu.lokesh.epssathi.side_menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.adapter.RecyclerViewAdapter;
import com.creatu.lokesh.epssathi.model_class.DisclaimerModelClass;
import com.creatu.lokesh.epssathi.model_class.NewsModal;
import com.creatu.lokesh.epssathi.model_class.Results;
import com.creatu.lokesh.epssathi.response.ApiClient;
import com.creatu.lokesh.epssathi.response.NewsResponse;
import com.creatu.lokesh.epssathi.retrofit_interfaces.ApiInterface;
import com.creatu.lokesh.epssathi.retrofit_interfaces.NewsInterface;
import com.creatu.lokesh.epssathi.retrofit_interfaces.NewsInterfaceRetrofit;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {



    TextView txt_about_us;
    List<DisclaimerModelClass> disclaimerModelClasses;
    AVLoadingIndicatorView avi;

    View view;
    RecyclerView recyclerView;
    List<Results> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view, container, false);
//        txt_about_us = view.findViewById(R.id.txt_about_us);
        avi = view.findViewById(R.id.avi);

        recyclerView = view.findViewById(R.id.recyclerView);

        avi.show();

        getActivity().setTitle("About Us");


//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<List<DisclaimerModelClass>> call = apiInterface.getDisclaimer();
//
//        call.enqueue(new Callback<List<DisclaimerModelClass>>() {
//            @Override
//            public void onResponse(Call<List<DisclaimerModelClass>> call, Response<List<DisclaimerModelClass>> response) {
//                disclaimerModelClasses = response.body();
//
//                for (final DisclaimerModelClass d : disclaimerModelClasses){
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            avi.hide();
//                            txt_about_us.setText(d.getDescription());
//                        }
//                    });
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<DisclaimerModelClass>> call, Throwable t) {
//
//            }
//        });

        getJsonData();

        return view;
    }


    public void getJsonData(){
        NewsInterfaceRetrofit newsInterface = NewsResponse.getNewsResponse().create(NewsInterfaceRetrofit.class);

        final Call<NewsModal> newsModalCall =  newsInterface.getNews();

        newsModalCall.enqueue(new Callback<NewsModal>() {

            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                list = new ArrayList<>();
                List<Results> results = response.body().getResults();

                for (int i=0; i<results.size(); i++){
                    Results results1 = new Results();
                    results1.setId(results.get(i).getId());
                    results1.setDescription(results.get(i).getDescription());
                    results1.setImage(results.get(i).getImage());
                    results1.setNewsdate(results.get(i).getNewsdate());
                    results1.setTitle(results.get(i).getTitle());
                    list.add(results1);
                }

                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list,getActivity());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();

                avi.hide();

            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {

            }
        });

    }

    public void onResume(){
        super.onResume();


            ((MainActivity) getActivity())
                    .setActionBarTitle("About Us");

    }
}
