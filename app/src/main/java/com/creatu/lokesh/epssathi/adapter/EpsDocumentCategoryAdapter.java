package com.creatu.lokesh.epssathi.adapter;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.model_class.EpsDocumentCategoryModelClass;
import com.creatu.lokesh.epssathi.model_class.EpsDocumentModelClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class EpsDocumentCategoryAdapter extends BaseAdapter {

    Context context;
    List<EpsDocumentCategoryModelClass> epsDocumentCategoryModelClasses;

    public EpsDocumentCategoryAdapter(Context context, List<EpsDocumentCategoryModelClass> epsDocumentCategoryModelClasses) {
        this.context = context;
        this.epsDocumentCategoryModelClasses = epsDocumentCategoryModelClasses;
    }

    @Override
    public int getCount() {
        return epsDocumentCategoryModelClasses.size();
    }

    @Override
    public Object getItem(int position) {
        return epsDocumentCategoryModelClasses.get(position);
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

            v = layoutInflator.inflate(R.layout.activity_eps_category, viewGroup, false);
        }

        TextView txt_document_title = (TextView) v.findViewById(R.id.txt_document_title);
        TextView txt_document_date = v.findViewById(R.id.txt_document_date);

        EpsDocumentCategoryModelClass epsDocumentCategoryModelClass = epsDocumentCategoryModelClasses.get(position);

        txt_document_title.setText(Html.fromHtml(epsDocumentCategoryModelClass.getTitle()));

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy hh:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        long time = 0;
        try {
            time = sdf.parse(epsDocumentCategoryModelClass.getPublished_date()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long now = System.currentTimeMillis();

        CharSequence ago =
                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);


        txt_document_date.setText(ago);


        return v;
    }
}