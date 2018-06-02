package com.creatu.lokesh.epssathi.side_menu;


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

import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.adapter.RecyclerViewAdapter;
import com.creatu.lokesh.epssathi.model_class.NewsModal;
import com.creatu.lokesh.epssathi.model_class.NewsModalClass;
import com.creatu.lokesh.epssathi.model_class.Results;
import com.creatu.lokesh.epssathi.response.NewsResponse;
import com.creatu.lokesh.epssathi.retrofit_interfaces.NewsInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class EPSProcess extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<Results> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.recycler_view, container, false);
        getActivity().setTitle("EPS Process");
        recyclerView = view.findViewById(R.id.recyclerView);

        getNewsJson();

        return view;
    }

    public void getNewsJson(){

        NewsInterface newsInterface = NewsResponse.getNewsResponse().create(NewsInterface.class);


        Observable<NewsModal> observable = newsInterface.getNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<NewsModal>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsModal newsModal) {

                list = new ArrayList<>();
                List<Results> results = newsModal.getResults();
                Log.d("size", String.valueOf(results.size()));

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

            }

            @Override
            public void onError(Throwable e) {

                System.out.println("Error : "+e);

            }

            @Override
            public void onComplete() {

            }

       });


    }

    public void onResume(){
        super.onResume();


        ((MainActivity) getActivity())
                .setActionBarTitle("EPS Process");

    }
}