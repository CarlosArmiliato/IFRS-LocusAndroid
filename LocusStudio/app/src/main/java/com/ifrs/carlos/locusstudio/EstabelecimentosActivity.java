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
import com.ifrs.carlos.locusstudio.data.Estabelecimento;
import com.ifrs.carlos.locusstudio.data.EstabelecimentoAdapter;
import com.ifrs.carlos.locusstudio.data.LocusBeacon;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EstabelecimentosActivity extends AppCompatActivity {

    private ListView listView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimentos);
        this.setTitle("Estabelecimentos");

        try {
            getEstabelecimentos();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),
                    e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }

    }

    private void getEstabelecimentos() throws Exception {// Get a RequestQueue

        RequestQueue mRequestQueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        // Start the queue
        mRequestQueue.start();

        String url ="http://web.farroupilha.ifrs.edu.br/tcc2/locus_service/estabelecimentos";

        LocusData.estabelecimentos.clear();
        LocusData.pisos.clear();
        LocusData.locusBeacons.clear();

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject vlJSONO = new JSONObject(response);

                            JSONArray vlJSONA = vlJSONO.getJSONArray("estabelecimentos");
                            for (int i = 0; i < vlJSONA.length(); i++) {
                                JSONObject objects = vlJSONA.getJSONObject(i);
                                LocusData.estabelecimentos.add(new Estabelecimento(Integer.parseInt(objects.get("id").toString()), objects.get("nome_curto").toString()));
                            }
                        } catch (Exception e){
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

        EstabelecimentoAdapter adapter = new EstabelecimentoAdapter(this,
                R.layout.lstview_estabelecimento, LocusData.estabelecimentos);

        listView = (ListView)findViewById(R.id.lstEstabelecimentos);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                Estabelecimento itemValue = (Estabelecimento) listView.getItemAtPosition(position);

                getPisos(String.valueOf(itemValue.getId()));
            }

        });

    }

    private void getPisos(String idEstabelecimento) {
        Intent intent = new Intent(this, PisosActivity.class);
        intent.putExtra(LocusData.ID_ESTABELECIMENTO, idEstabelecimento);
        startActivity(intent);
    }

}