package com.creatu.lokesh.epssathi.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.creatu.lokesh.epssathi.R;
import com.creatu.lokesh.epssathi.model_class.EpsDocumentDetailsModelClass;

import java.util.List;

public class EpsDocumentDetailsAdapter extends BaseAdapter {

    Context context;
    List<EpsDocumentDetailsModelClass> epsDocumentDetailsModelClasses ;

    public EpsDocumentDetailsAdapter(Context context, List<EpsDocumentDetailsModelClass> epsDocumentDetailsModelClasses) {
        this.context = context;
        this.epsDocumentDetailsModelClasses = epsDocumentDetailsModelClasses;
    }

    @Override
    public int getCount() {
        return epsDocumentDetailsModelClasses.size();
    }

    @Override
    public Object getItem(int position) {
        return epsDocumentDetailsModelClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if (v == null) {
            LayoutInflater layoutInflator = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = layoutInflator.inflate(R.layout.eps_detail_item, parent, false);
        }

        TextView txt_sn = v.findViewById(R.id.txt_sn);
        TextView txt_name = v.findViewById(R.id.txt_name);
        TextView txt_dob = v.findViewById(R.id.txt_dob);
        TextView txt_gender = v.findViewById(R.id.txt_gender);
        TextView txt_address = v.findViewById(R.id.txt_address);
        TextView txt_contact = v.findViewById(R.id.txt_contact_before);

        EpsDocumentDetailsModelClass epsDocumentDetailsModelClass = epsDocumentDetailsModelClasses.get(position);

        txt_sn.setText("SN: "+Html.fromHtml(epsDocumentDetailsModelClass.getSn()));
        txt_name.setText("Name: "+Html.fromHtml(epsDocumentDetailsModelClass.getName()));
        txt_dob.setText("DOB: "+Html.fromHtml(epsDocumentDetailsModelClass.getDob()));
        txt_gender.setText("Gender: "+Html.fromHtml(epsDocumentDetailsModelClass.getGender()));
        txt_address.setText("Address: "+Html.fromHtml(epsDocumentDetailsModelClass.getAddress()));
        txt_contact.setText("Contact Before: "+Html.fromHtml(epsDocumentDetailsModelClass.getContact_before()));

        return v;
    }
}
