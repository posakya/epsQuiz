package com.creatu.lokesh.epssathi.side_menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback extends Fragment {


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.recycler_view, container, false);
        getActivity().setTitle("Feedback");

        return view;
    }
    public void onResume(){
        super.onResume();


        ((MainActivity) getActivity())
                .setActionBarTitle("Feed Back");

    }
}
