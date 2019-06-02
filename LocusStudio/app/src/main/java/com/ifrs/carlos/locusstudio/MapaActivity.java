package com.ifrs.carlos.locusstudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ifrs.carlos.locusstudio.data.LocusBeacon;
import com.ifrs.carlos.locusstudio.data.LocusBeaconAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapaActivity extends AppCompatActivity {

    private ListView listView;

    private int idPiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        this.setTitle("Mapa");

        Intent intent = getIntent();
        idPiso = Integer.parseInt(intent.getStringExtra(LocusData.ID_PISO));

        try {
            getBeacons();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),
                    e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void getBeacons() throws Exception {// Get a RequestQueue

        RequestQueue mRequestQueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        // Start the queue
        mRequestQueue.start();

        String url = "http://web.farroupilha.ifrs.edu.br/tcc2/locus_service/piso/" + idPiso;



        LocusData.locusBeacons.clear();

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject vlJSONO = new JSONObject(response);

                            JSONArray vlJSONA = vlJSONO.getJSONObject("piso").getJSONArray("beacons");
                            for (int i = 0; i < vlJSONA.length(); i++) {
                                JSONObject objects = vlJSONA.getJSONObject(i);
                                LocusData.locusBeacons.add(new LocusBeacon(
                                        objects.get("UUID").toString(),
                                        objects.get("Major").toString(),
                                        objects.get("Minor").toString())
                                        .setxMetros(Double.parseDouble(objects.get("xMetros").toString()))
                                        .setyMetros(Double.parseDouble(objects.get("yMetros").toString()))
                                        .setzMetros(Double.parseDouble(objects.get("zMetros").toString())));
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    e.getMessage(), Toast.LENGTH_LONG)
                                    .show();
                        }

                        LocusData.locusBeacons.clear();

                        /*Chamada para utilizar beacons fixos no lugar do entregue pelo WS*/
                        LocusData.locusBeacons = Util.getFakeBeacons();

                        montaListView();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                "Deu Merda", Toast.LENGTH_LONG)
                                .show();
                    }
                });



        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);

    }

    private void montaListView(){

        LocusBeaconAdapter adapter = new LocusBeaconAdapter(this,
                R.layout.lstview_beacon, LocusData.locusBeacons);

        listView = (ListView)findViewById(R.id.lstBeacons);

        listView.setAdapter(adapter);


    }

    public void cmdScan(View view) {
        Intent intent = new Intent(this, ScanBeaconsActivity.class);
        startActivity(intent);
    }


}

