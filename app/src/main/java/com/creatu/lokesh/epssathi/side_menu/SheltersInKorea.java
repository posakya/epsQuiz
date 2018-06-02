package com.creatu.lokesh.epssathi.side_menu;


import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.creatu.lokesh.epssathi.MainActivity;
import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.adapter.CursorAdapter;
import com.creatu.lokesh.epssathi.db_handler.dbHandler;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SheltersInKorea extends Fragment {


    View view;

    GridView grid_view;

    AVLoadingIndicatorView avLoadingIndicatorView;

    CursorAdapter cursorAdapter;

    dbHandler db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        grid_view = view.findViewById(R.id.gridView);

        avLoadingIndicatorView = view.findViewById(R.id.avi);

        db = new dbHandler(getActivity());

        Cursor cursor = db.getData("shelter");

        getActivity().setTitle("Shelters in Korea");

        System.out.println("Cursor Size :"+cursor.getCount());

        String[] from = {dbHandler.title, dbHandler.description, dbHandler.image};
        int[] to = {R.id.txtTitle, R.id.txtDesc,R.id.imageView_investor};
        cursorAdapter=new CursorAdapter(getActivity(),R.layout.all_grid_item,cursor,from,to,0);
        grid_view.setAdapter(cursorAdapter);


        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("Clicked ");

                Cursor cursor1 = null;
                cursor1 = (Cursor) parent.getItemAtPosition(position);

                final Dialog dialog1 = new Dialog(getActivity(),android.R.style.Theme_Holo_Light);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
                dialog1.setContentView(R.layout.detail_page);
                dialog1.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                dialog1.setCanceledOnTouchOutside(true);

                Toolbar toolbar=dialog1.findViewById(R.id.toolbar);
                toolbar.setTitle(cursor1.getString(cursor1.getColumnIndex(dbHandler.title)));
                TextView textView = dialog1.findViewById(R.id.detail_desc);
                ImageView imageView = dialog1.findViewById(R.id.detail_image);
                final ProgressBar progressBar = dialog1.findViewById(R.id.progressBar);
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
                toolbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });

                textView.setText(Html.fromHtml(cursor1.getString(cursor1.getColumnIndex(dbHandler.description))));
                Glide.with(getActivity())
                        .load(cursor1.getString(cursor1.getColumnIndex(dbHandler.image))).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

                dialog1.show();

            }
        });


        return view;
    }
    public void onResume(){
        super.onResume();


        ((MainActivity) getActivity())
                .setActionBarTitle("Shelters in Korea");

    }
}