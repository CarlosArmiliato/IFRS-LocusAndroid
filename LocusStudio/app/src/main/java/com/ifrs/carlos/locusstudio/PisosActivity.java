package com.ifrs.carlos.locusstudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ifrs.carlos.locusstudio.data.Piso;
import com.ifrs.carlos.locusstudio.data.PisoAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PisosActivity extends AppCompatActivity {

    private ListView listView;

    private int idEstabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pisos);
        this.setTitle("Pisos");

        Intent intent = getIntent();
        idEstabelecimento = Integer.parseInt(intent.getStringExtra(LocusData.ID_ESTABELECIMENTO));

        try {
            getPisos();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),
                    e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void getPisos() throws Exception {// Get a RequestQueue

        RequestQueue mRequestQueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        // Start the queue
        mRequestQueue.start();

        String url = "http://web.farroupilha.ifrs.edu.br/tcc2/locus_service/pisos/" +  idEstabelecimento;


        LocusData.pisos.clear();
        LocusData.locusBeacons.clear();

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject vlJSONO = new JSONObject(response);

                            JSONArray vlJSONA = vlJSONO.getJSONArray("pisos");
                            for (int i = 0; i < vlJSONA.length(); i++) {
                                JSONObject objects = vlJSONA.getJSONObject(i);
                                LocusData.pisos.add(new Piso(Integer.parseInt(objects.get("id").toString()), objects.get("nome_curto").toString()));
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    e.getMessage(), Toast.LENGTH_LONG)
                                    .show();
                        }

                        montaListView();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                "Erro Desconhecido" , Toast.LENGTH_LONG)
                                .show();
                    }
                });

        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);

    }

    private void montaListView(){

        PisoAdapter adapter = new PisoAdapter(this,
                R.layout.lstview_piso, LocusData.pisos);

        listView = (ListView)findViewById(R.id.lstPisos);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                Piso itemValue = (Piso) listView.getItemAtPosition(position);

                getMapa(String.valueOf(itemValue.getId()));
            }

        });

    }

    private void getMapa(String idPiso) {
        Intent intent = new Intent(this, MapaActivity.class);
        intent.putExtra(LocusData.ID_PISO, idPiso);
        startActivity(intent);
    }
}






