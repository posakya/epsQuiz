package com.creatu.lokesh.epssathi.side_menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.model_class.DisclaimerModelClass;
import com.creatu.lokesh.epssathi.response.ApiClient;
import com.creatu.lokesh.epssathi.retrofit_interfaces.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Disclaimer extends Fragment {


     View view;
     TextView txt_about_us;
     List<DisclaimerModelClass> disclaimerModelClasses;
     AVLoadingIndicatorView avi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_us, container, false);
        txt_about_us = view.findViewById(R.id.txt_about_us);
        avi = view.findViewById(R.id.avi);

        avi.show();
        getActivity().setTitle("Disclaimer");


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<DisclaimerModelClass>> call = apiInterface.getDisclaimer();

        call.enqueue(new Callback<List<DisclaimerModelClass>>() {
            @Override
            public void onResponse(Call<List<DisclaimerModelClass>> call, Response<List<DisclaimerModelClass>> response) {
                disclaimerModelClasses = response.body();

                for (final DisclaimerModelClass d : disclaimerModelClasses){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            avi.hide();
                            txt_about_us.setText(d.getDescription());
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<List<DisclaimerModelClass>> call, Throwable t) {

            }
        });

        return view;
    }
    public void onResume(){
        super.onResume();


        ((MainActivity) getActivity())
                .setActionBarTitle("Disclaimer");

    }
}
