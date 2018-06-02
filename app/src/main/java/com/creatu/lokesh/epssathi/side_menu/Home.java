package com.creatu.lokesh.epssathi.side_menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.adapter.HomeViewAdapter;
import com.creatu.lokesh.epssathi.model_class.HomeModelClass;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private GridView gridView;
    private HomeViewAdapter pAdapter;
    private View view;
    private ArrayList<HomeModelClass> homeModelClasses = new ArrayList<>();
//    android.support.v4.app.FragmentManager fragmentManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = view.findViewById(R.id.gridView);

        homeModelClasses.add(new HomeModelClass("L","Learn Korean"));
        homeModelClasses.add(new HomeModelClass("P","Practice Korean"));
        homeModelClasses.add(new HomeModelClass("N","News"));
        homeModelClasses.add(new HomeModelClass("E","EPS Process"));
        homeModelClasses.add(new HomeModelClass("S","Shelters In Korea"));
        homeModelClasses.add(new HomeModelClass("I","Investors From Korea"));
        homeModelClasses.add(new HomeModelClass("R","Restaurants In Korea"));
        homeModelClasses.add(new HomeModelClass("R","Remittance"));
        homeModelClasses.add(new HomeModelClass("S","Shopping In Korea"));
        homeModelClasses.add(new HomeModelClass("K","Korean Returnees"));





        pAdapter = new HomeViewAdapter(getActivity(),R.layout.home_list_item,homeModelClasses);
        gridView.setAdapter(pAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeModelClass homeModelClass = homeModelClasses.get(position);
                String title = homeModelClass.getHome_title();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();

                switch (title){
                    case "Learn Korean" :

                        fragmentManager.beginTransaction().replace(R.id.main_content,new LearnKorean()).addToBackStack(null).commit();
                    break;

                    case "Practice Korean" :
                        fragmentManager.beginTransaction().replace(R.id.main_content,new PracticeKorean()).addToBackStack(null).commit();
                        break;

                    case "News" :
                        fragmentManager.beginTransaction().replace(R.id.main_content,new News()).addToBackStack(null).commit();
                        break;

                    case "EPS Process" :
                        fragmentManager.beginTransaction().replace(R.id.main_content,new EPSProcess()).addToBackStack(null).commit();
                        break;

                    case "Shelters In Korea" :
                        fragmentManager.beginTransaction().replace(R.id.main_content,new SheltersInKorea()).addToBackStack(null).commit();
                        break;

                    case "Investors From Korea" :
                        fragmentManager.beginTransaction().replace(R.id.main_content,new InvestorsFromKorea()).addToBackStack(null).commit();
                        break;

                    case "Restaurants In Korea" :
                        fragmentManager.beginTransaction().replace(R.id.main_content,new RestaurantsFromKorea()).addToBackStack(null).commit();
                        break;

                    case "Remittance" :
                        fragmentManager.beginTransaction().replace(R.id.main_content,new Remittance()).addToBackStack(null).commit();
                        break;

                    case "Shopping In Korea" :
                        fragmentManager.beginTransaction().replace(R.id.main_content,new ShoppingInKorea()).addToBackStack(null).commit();
                        break;

                    case "Korean Returnees" :
                        fragmentManager.beginTransaction().replace(R.id.main_content,new KoreaReturnees()).addToBackStack(null).commit();
                        break;

                    default:
                        fragmentManager.beginTransaction().replace(R.id.main_content,new KoreaReturnees()).addToBackStack(null).commit();
                        break;
                }
            }
        });


        return view;
    }
    public void onResume(){
        super.onResume();


        ((MainActivity) getActivity())
                .setActionBarTitle("Home");

    }
}
