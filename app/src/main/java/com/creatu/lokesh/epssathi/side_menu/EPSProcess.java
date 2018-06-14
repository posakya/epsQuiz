package com.creatu.lokesh.epssathi.side_menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.activities.EpsCategory;
import com.creatu.lokesh.epssathi.adapter.EpsDocumentAdapter;
import com.creatu.lokesh.epssathi.model_class.EpsDocumentModelClass;
import com.creatu.lokesh.epssathi.model_class.EpsProcessModelClass;
import com.creatu.lokesh.epssathi.model_class.Results;
import com.creatu.lokesh.epssathi.response.RetrofitResponse;
import com.creatu.lokesh.epssathi.retrofit_interfaces.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EPSProcess extends Fragment {

    View view;
    ListView listView;
    AVLoadingIndicatorView avi;
    List<Results> epsDocumentModelClasses = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.list_view, container, false);
        getActivity().setTitle("EPS Process");
        listView = view.findViewById(R.id.listView);
        avi = view.findViewById(R.id.avi);

        avi.show();

        getDocumentDetail();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Results epsDocumentModelClass = epsDocumentModelClasses.get(position);
                Intent intent = new Intent(getActivity(), EpsCategory.class);
                intent.putExtra("slug",epsDocumentModelClass.getSlug());
                intent.putExtra("title",epsDocumentModelClass.getTitle());
                startActivity(intent);
            }
        });

        return view;
    }

    public void getDocumentDetail(){
        ApiInterface documentApi = RetrofitResponse.getRetrofit().create(ApiInterface.class);

        Call<EpsProcessModelClass> listCall = documentApi.getDocument();

        listCall.enqueue(new Callback<EpsProcessModelClass>() {
            @Override
            public void onResponse(Call<EpsProcessModelClass> call, Response<EpsProcessModelClass> response) {
                System.out.println("Response : "+response.body().getResults());


                List<Results> results = response.body().getResults();


                for (Results result : results){
                    epsDocumentModelClasses.add(result);
                }


                EpsDocumentAdapter adapter = new EpsDocumentAdapter(getActivity(),epsDocumentModelClasses);
                listView.setAdapter(adapter);

                avi.hide();
            }

            @Override
            public void onFailure(Call<EpsProcessModelClass> call, Throwable t) {
                System.out.println("ResponseError : "+t.getMessage());
                avi.hide();

            }
        });


    }

    public void onResume(){
        super.onResume();


        ((MainActivity) getActivity())
                .setActionBarTitle("EPS Process");

    }
}