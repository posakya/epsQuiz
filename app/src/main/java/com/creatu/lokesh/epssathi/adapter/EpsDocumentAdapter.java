package com.creatu.lokesh.epssathi.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.model_class.EpsDocumentModelClass;
import com.creatu.lokesh.epssathi.model_class.Results;

import java.util.List;

public class EpsDocumentAdapter extends BaseAdapter {

    Context context;
    List<Results> epsDocumentModelClasses;

    public EpsDocumentAdapter(Context context, List<Results> epsDocumentModelClasses) {
        this.context = context;
        this.epsDocumentModelClasses = epsDocumentModelClasses;
    }

    @Override
    public int getCount() {
        return epsDocumentModelClasses.size();
    }

    @Override
    public Object getItem(int position) {
        return epsDocumentModelClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup viewGroup) {
        if (v == null) {
            LayoutInflater layoutInflator = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = layoutInflator.inflate(R.layout.eps_document, viewGroup, false);
        }

        TextView txt_document_title = (TextView)v.findViewById(R.id.document_title);

        Results epsDocumentModelClass = epsDocumentModelClasses.get(position);

        txt_document_title.setText(Html.fromHtml(epsDocumentModelClass.getTitle()));

        return v;
    }
}
