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

public class LocusBeaconAdapter extends ArrayAdapter<LocusBeacon> {

    Context context;
    int layoutResourceId;
    ArrayList<LocusBeacon> data = null;

    public LocusBeaconAdapter(Context context, int layoutResourceId, ArrayList<LocusBeacon> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LocusBeaconHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new LocusBeaconHolder();
            holder.txtUUID = (TextView) row.findViewById(R.id.txtUUID);
            holder.txtMajor = (TextView) row.findViewById(R.id.txtMajor);
            holder.txtMinor = (TextView) row.findViewById(R.id.txtMinor);
            holder.txtDistance = (TextView) row.findViewById(R.id.txtDistance);
            holder.txtRSSI = (TextView) row.findViewById(R.id.txtRSSI);
            holder.txtX= (TextView) row.findViewById(R.id.txtX);
            holder.txtY = (TextView) row.findViewById(R.id.txtY);

            row.setTag(holder);
        } else {
            holder = (LocusBeaconHolder) row.getTag();
        }

        LocusBeacon lcBeacon = data.get(position);
        holder.txtUUID.setText("UUID: " + lcBeacon.getUUID());
        holder.txtMajor.setText("Major: " + lcBeacon.getMajor());
        holder.txtMinor.setText("Minor: " + lcBeacon.getMinor());
        holder.txtDistance.setText("Distance: " + String.valueOf(lcBeacon.getDistance()));
        holder.txtRSSI.setText("RSSI: " + String.valueOf(lcBeacon.getRSSI()));
        holder.txtX.setText("xMetros: " + String.valueOf(lcBeacon.getxMetros()));
        holder.txtY.setText("yMetros: " + String.valueOf(lcBeacon.getyMetros()));

        return row;
    }

    static class LocusBeaconHolder {
        TextView txtUUID;
        TextView txtMajor;
        TextView txtMinor;
        TextView txtDistance;
        TextView txtRSSI;
        TextView txtX;
        TextView txtY;
    }
}