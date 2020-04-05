package com.biomaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.biomaster.Adaptadores.PracticasAdapter;
import com.biomaster.Conectores.Requests.GetPracticasRequest;
import com.biomaster.Conectores.Requests.RecordsService;
import com.biomaster.Extras.Dialog;
import com.biomaster.Models.Practica;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Consultas extends AppCompatActivity {

    ArrayList<Practica> practicas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        ActionBar aBar = getSupportActionBar();
        if (aBar != null){
            aBar.setDisplayHomeAsUpEnabled(true);
            aBar.setTitle(getResources().getString(R.string.headerConsultas));
        }
        String id = "";
        if(getIntent().getStringExtra("id") != null){
            id = getIntent().getStringExtra("id");
        }

        ListView lView = findViewById(R.id.listRecords);

        ProgressDialog pDialog = ProgressDialog.show(Consultas.this, "", "Cargando...", true);
        pDialog.show();

        RecordsService recordsService = new RecordsService(id, response -> {
            try {
                JSONObject data = new JSONObject(response);
                if(data.getBoolean("success")){
                    JSONArray records = data.getJSONArray("records");
                    for(int i = 0; i < records.length(); i++){
                        JSONObject actual = records.getJSONObject(i);
                        Practica adding = new Practica();
                        adding.setID(actual.getInt("ID"));
                        adding.setNombre_Prof(actual.getString("Profesor"));
                        adding.setAsist_Aux(actual.getString("Asist_Aux").equals("1"));
                        adding.setAsist_Chico_Serv(actual.getString("Asist_Serv").equals("1"));
                        adding.setPractica(actual.getString("Practica"));
                        adding.setRuta(actual.getString("Ruta"));
                        adding.setHubo_Practica(actual.getString("Hubo_Practica").equals("1"));
                        adding.setMuestras_Fija(actual.getString("Muestras_Fija").equals("1"));
                        adding.setBata(actual.getString("Bata").equals("1"));
                        adding.setMateriales_Completos(actual.getString("Materiales_Completos").equals("1"));
                        adding.setPuntualidad_Alumno(actual.getString("Puntualidad_Alumno").equals("1"));
                        adding.setPuntualidad_Profesor(actual.getString("Puntualidad_Profesor").equals("1"));
                        adding.setManual(actual.getString("Manual").equals("1"));
                        adding.setFecha(actual.get("Fecha").toString());
                        adding.setHora_Inicio(actual.getString("Hora_Inicio"));
                        adding.setHora_Fin(actual.getString("Hora_Fin"));

                        practicas.add(adding);

                    }

                    lView.setAdapter(new PracticasAdapter(practicas, Consultas.this));

                }else {
                    Dialog.showAlertDialog(Consultas.this,"¡Error!", data.getString("message"), (dialog, which) -> this.onBackPressed());
                }
                pDialog.dismiss();
            }catch (Exception ex){
                pDialog.dismiss();
                System.out.println(ex.getLocalizedMessage());
                Dialog.showAlertDialog(Consultas.this,"¡Error!", "Ha ocurrido un error en la peticion", (dialog, which) -> this.onBackPressed());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(Consultas.this);
        queue.add(recordsService);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }
}
