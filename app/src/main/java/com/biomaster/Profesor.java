package com.biomaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.biomaster.Conectores.DBHelper;
import com.biomaster.Conectores.Requests.AddPracticaRequest;
import com.biomaster.Conectores.Requests.GetPracticasRequest;
import com.biomaster.Extras.Dialog;
import com.biomaster.Models.Document;
import com.biomaster.Models.Practica;
import com.biomaster.Models.User;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Profesor extends AppCompatActivity {
    private DBHelper helper;
    private User usr;
    private Spinner spHoras, spPracticas;
    private RadioGroup rgStatusPractica, rgAsistAux, rgAsistServ, rgPuntAlumno;
    private EditText txteDate;
    private LinearLayout layOptions;
    private CheckBox cbPuntAsist, cbMatComp, cbMuesFij, cbManComp, cbPuntProf, cbBata;
    private Practica practica = new Practica();
    private ArrayList<Document> docs = new ArrayList<>();
    private String[] docsName;
    private Document practSelected;
    private final Calendar cldr = Calendar.getInstance();
    int daySel = cldr.get(Calendar.DAY_OF_MONTH);
    int montSel = cldr.get(Calendar.MONTH);
    int yearSel = cldr.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);
        ActionBar aBar = getSupportActionBar();
        if (aBar != null){
            aBar.setDisplayHomeAsUpEnabled(true);
            aBar.setTitle(getResources().getString(R.string.lblProfesor));
        }
        helper = new DBHelper(this);
        usr = helper.getData_Usuario();

        TextView txtWelcome = findViewById(R.id.txtWelcome);
        spHoras = findViewById(R.id.spHoras);
        spPracticas = findViewById(R.id.spPractica);

        rgStatusPractica = findViewById(R.id.rgStatusPract);
        rgAsistAux = findViewById(R.id.rgAsistAux);
        rgAsistServ = findViewById(R.id.rgAsistServ);
        rgPuntAlumno = findViewById(R.id.rgPuntAlumn);

        layOptions = findViewById(R.id.LayOptions);

        cbMuesFij = findViewById(R.id.cbMuestrasFijas);
        cbMatComp = findViewById(R.id.cbMatCompleto);
        cbManComp = findViewById(R.id.cbManualCompleto);
        cbPuntProf = findViewById(R.id.cbPuntProf);
        cbPuntAsist = findViewById(R.id.cbPuntAsist);
        cbBata = findViewById(R.id.cbBata);

        txteDate = findViewById(R.id.txteFecha);

        txteDate.setText(String.format(Locale.getDefault(), "%s/%s/%s", daySel, montSel, yearSel));

        configureDateEntry();

        txtWelcome.setText(getResources().getString(R.string.lblWelcome, usr.getNombre()));

        Button btnPrev = findViewById(R.id.btnPrev);
        ImageButton btnAddReg = findViewById(R.id.btnSaveConfig);

        setHorarioSpinner();
        setPractSpinner();

        btnPrev.setOnClickListener(v->{
            if(practSelected != null){
                Intent sendPrev = new Intent(Profesor.this, DocumentViewer.class);
                sendPrev.putExtra("path", practSelected.getPath());
                startActivity(sendPrev);
            }
        });

        rgStatusPractica.setOnCheckedChangeListener((group, checkedId) -> {
            int isOptionsVisible = checkedId == R.id.rdPractYes ?
                    View.VISIBLE : View.GONE;
            layOptions.setVisibility(isOptionsVisible);
        });

        btnAddReg.setOnClickListener(v ->{
            bulkDataToPractica();
            ProgressDialog pDialog = ProgressDialog.show(Profesor.this, "", "Cargando...", true);
            pDialog.show();

            AddPracticaRequest addPract = new AddPracticaRequest(practica, getRequestListener(pDialog));

            RequestQueue queue = Volley.newRequestQueue(Profesor.this);
            queue.add(addPract);

        });

    }

    private void bulkDataToPractica(){
        practica = new Practica();
        int practicaID = practSelected.getID();
        if(rgStatusPractica.getCheckedRadioButtonId() == R.id.rdPractNo){
            rgPuntAlumno.check(R.id.rdAlumnNo);
            rgAsistAux.check(R.id.rdAuxNo);
            rgAsistServ.check(R.id.rdServNo);
            cbBata.setChecked(false);
            cbPuntAsist.setChecked(false);
            cbPuntProf.setChecked(false);
            cbManComp.setChecked(false);
            cbMatComp.setChecked(false);
            cbMuesFij.setChecked(false);
            practicaID = 0;
        }
        practica.setID_Prof(helper.getData_Usuario().getID());
        RadioButton rdTrue = findViewById(R.id.rdAuxYes);
        practica.setAsist_Aux(rdTrue.isChecked());
        rdTrue = findViewById(R.id.rdServYes);
        practica.setAsist_Chico_Serv(rdTrue.isChecked());
        practica.setPractica_Realizar(practicaID);
        rdTrue = findViewById(R.id.rdPractYes);
        practica.setHubo_Practica(rdTrue.isChecked());
        practica.setMuestras_Fija(cbMuesFij.isChecked());
        practica.setMateriales_Completos(cbMatComp.isChecked());
        practica.setBata(cbBata.isChecked());
        rdTrue = findViewById(R.id.rdAlumnYes);
        practica.setPuntualidad_Alumno(rdTrue.isChecked());
        practica.setPuntualidad_Profesor(cbPuntProf.isChecked());
        practica.setManual(cbManComp.isChecked());
        practica.setFecha(txteDate.getText().toString());
        String[] horas = spHoras.getSelectedItem().toString().split("-");
        practica.setHora_Inicio(horas[0].trim());
        practica.setHora_Fin(horas[1].trim());

    }

    public void sendMatInfo(View view) {
        Intent sendMateriales = new Intent(Profesor.this, Materiales.class);
        sendMateriales.putExtra("practica", spPracticas.getSelectedItem().toString());
        startActivity(sendMateriales);

    }

    private Response.Listener<String> getRequestListener(ProgressDialog dialog){
        return response -> {
            try{
                JSONObject data = new JSONObject(response);
                if(data.getBoolean("success")){
                    Dialog.showAlertDialog(Profesor.this, "¡Correcto!",
                            data.getString("message"), (dialog1, which) -> {});
                }else{
                    Dialog.showAlertDialog(Profesor.this, "¡Error!",
                            data.getString("message"), (dialog1, which) -> {});
                }
                dialog.dismiss();
            }catch(Exception ex){
                System.out.println(ex.getLocalizedMessage());
                dialog.dismiss();
                Dialog.showAlertDialog(Profesor.this,"¡Error!",
                        "Ha ocurrido un error en la peticion", (dialog1, which) -> this.recreate());
            }
        };
    }

    private void configureDateEntry(){
        txteDate.setInputType(InputType.TYPE_NULL);
        txteDate.setOnClickListener(v -> {
            DatePickerDialog picker = DatePickerDialog.newInstance(getListener(), yearSel, montSel, daySel);
            picker.show(getSupportFragmentManager(), "Fecha de practica");
        });
    }

    private DatePickerDialog.OnDateSetListener getListener(){
        return (view, year1, month, dayOfMonth) ->
                txteDate.setText(String.format(Locale.getDefault(), "%d/%d/%d", dayOfMonth, month + 1, year1));
    }

    private void setHorarioSpinner(){
        ArrayAdapter<String> horasAdapter = usr.getTurno().equals("Matutino") ?
                new ArrayAdapter<>(this, R.layout.custom_spinner_item, getResources().getStringArray(R.array.spHorasMat)) :
                new ArrayAdapter<>(this, R.layout.custom_spinner_item, getResources().getStringArray(R.array.spHorasVesp));

        spHoras.setAdapter(horasAdapter);
    }

    private void setPractSpinner(){
        GetPracticasRequest practicasRequest = new GetPracticasRequest(getPractsListener());

        RequestQueue queue = Volley.newRequestQueue(Profesor.this);
        queue.add(practicasRequest);

    }

    private Response.Listener<String> getPractsListener(){
        return response -> {
            try {
                JSONObject data = new JSONObject(response);
                if(data.getBoolean("success")){
                    JSONArray objects = data.getJSONArray("practicas");
                    docsName = new String[objects.length()];
                    for(int i = 0; i < objects.length(); i++){
                        JSONObject actual = (JSONObject) objects.get(i);
                        Document add = new Document(actual.getInt("ID"),
                                actual.getString("Nombre"), actual.getString("Ruta"));
                        docs.add(add);
                        docsName[i] = actual.getString("Nombre");
                    }
                    spPracticas.setAdapter(new ArrayAdapter<>(Profesor.this, R.layout.custom_spinner_item, docsName));
                    setPractsSpinnerListener();
                }
            }catch(Exception ex){
                System.out.println(ex.getLocalizedMessage());
                Dialog.showAlertDialog(Profesor.this,"¡Error!",
                        "Ha ocurrido un error en la peticion", (dialog, which) -> this.recreate());
            }
        };
    }

    private void setPractsSpinnerListener(){
        spPracticas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                practSelected = docs.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
