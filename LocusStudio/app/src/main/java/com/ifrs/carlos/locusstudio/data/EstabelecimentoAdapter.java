package com.ifrs.carlos.locusstudio.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ifrs.carlos.locusstudio.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by carlos on 14/01/18.
 */

public class EstabelecimentoAdapter extends ArrayAdapter<Estabelecimento> {

    Context context;
    int layoutResourceId;
    ArrayList<Estabelecimento> data = null;

    public EstabelecimentoAdapter(Context context, int layoutResourceId, ArrayList<Estabelecimento> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EstabelecimentoHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EstabelecimentoHolder();
            holder.txtId = (TextView) row.findViewById(R.id.txtId);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        } else {
            holder = (EstabelecimentoHolder) row.getTag();
        }

        Estabelecimento estab = data.get(position);
        holder.txtId.setText(String.valueOf(estab.getId()));
        holder.txtTitle.setText(estab.getNomeCurto());

        return row;
    }

    static class EstabelecimentoHolder {
        TextView txtId;
        TextView txtTitle;
    }
}
