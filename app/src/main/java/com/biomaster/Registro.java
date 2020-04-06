package com.biomaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.biomaster.Conectores.Requests.RegisterService;
import com.biomaster.Extras.Dialog;
import com.biomaster.Models.User;

import org.json.JSONObject;

public class Registro extends AppCompatActivity {

    private ProgressBar pBar;
    private EditText txtIniciales, txtNombre, txtApellido, txtEmail, txtMatricula, txtPwd;
    private Spinner spTurno;
    private User usr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ActionBar aBar = getSupportActionBar();
        if (aBar != null){
            aBar.setDisplayHomeAsUpEnabled(true);
            aBar.setTitle(getResources().getString(R.string.lblRegistro));
        }

        ImageButton btnRegister = findViewById(R.id.imgbtnAdd);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellidos);
        txtEmail = findViewById(R.id.txtEmail);
        txtIniciales = findViewById(R.id.txtProf);
        txtMatricula = findViewById(R.id.txtMatricula);
        txtPwd = findViewById(R.id.txtePwd);

        spTurno = findViewById(R.id.spTurno);
        pBar = findViewById(R.id.pBar);

        spTurno.setAdapter(new ArrayAdapter<>(Registro.this, R.layout.custom_spinner_item, getResources().getStringArray(R.array.Turnos)));

        btnRegister.setOnClickListener(v -> {
            if(validateFields()){
                sendRequest();
            }else{
                Toast.makeText(Registro.this, "No puede dejar los campos en blanco", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sendRequest(){
        bulkJson();

        pBar.setIndeterminate(true);
        pBar.setVisibility(View.VISIBLE);

        RegisterService register = new RegisterService(usr, getResponseListener());
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(register);

    }

    private Response.Listener<String> getResponseListener(){
        return response1 -> {
            try {
                JSONObject data = new JSONObject(response1);
                if(data.has("message")){
                    Dialog.showAlertDialog(Registro.this,"¡Correcto!", data.getString("message"), (dialog, which) -> onBackPressed());
                }else {
                    Dialog.showAlertDialog(Registro.this,"¡Error!", data.getString("error"), (dialog, which) -> {});
                }
                pBar.setIndeterminate(false);
                pBar.setVisibility(View.INVISIBLE);
            }catch (Exception error){
                pBar.setIndeterminate(false);
                pBar.setVisibility(View.INVISIBLE);
                System.out.println(error.getLocalizedMessage());
                Dialog.showAlertDialog(Registro.this,"¡Error!", "Ha ocurrido un error en la peticion", (dialog, which) -> this.recreate());
            }
        };
    }

    private void bulkJson(){
        usr = new User(
                txtMatricula.getText().toString(),
                txtNombre.getText().toString(),
                txtApellido.getText().toString(),
                txtIniciales.getText().toString(),
                txtEmail.getText().toString(),
                spTurno.getSelectedItem().toString(),
                txtPwd.getText().toString()
        );
    }

    private boolean validateFields(){
        return  txtNombre.getText().length() > 0 &&
                txtPwd.getText().length() > 0 &&
                txtMatricula.getText().length() > 0 &&
                txtEmail.getText().length() > 0 &&
                txtApellido.getText().length() > 0 &&
                txtIniciales.getText().length() > 0;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

}
