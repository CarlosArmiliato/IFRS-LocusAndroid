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

/**
 * Created by carlos on 14/01/18.
 */

public class PisoAdapter  extends ArrayAdapter<Piso> {

    Context context;
    int layoutResourceId;
    ArrayList<Piso> data = null;

    public PisoAdapter(Context context, int layoutResourceId, ArrayList<Piso> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PisoHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PisoHolder();
            holder.txtId = (TextView)row.findViewById(R.id.txtId);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (PisoHolder)row.getTag();
        }

        Piso piso = data.get(position);
        holder.txtId.setText(String.valueOf(piso.getId()));
        holder.txtTitle.setText(piso.getNomeCurto());

        return row;
    }

    static class PisoHolder
    {
        TextView txtId;
        TextView txtTitle;
    }
}
