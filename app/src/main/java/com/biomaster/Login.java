package com.biomaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.biomaster.Conectores.DBHelper;
import com.biomaster.Conectores.Requests.LoginService;
import com.biomaster.Extras.Dialog;
import com.biomaster.Models.User;

import org.json.JSONObject;

public class Login extends AppCompatActivity {

    User usr = new User();
    EditText txtUser, txtPwd;
    ProgressBar pBar;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar aBar = getSupportActionBar();
        if (aBar != null){
            aBar.setDisplayHomeAsUpEnabled(true);
            aBar.setTitle(getResources().getString(R.string.lblLogin));
        }
        helper = new DBHelper(this);
        txtUser = findViewById(R.id.txtUser);
        txtPwd = findViewById(R.id.txtPwd);
        pBar = findViewById(R.id.pBar);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            if(validateFields()){
                pBar.setVisibility(View.VISIBLE);
                pBar.setIndeterminate(true);
                usr.setID(txtUser.getText().toString());
                usr.setPassword(txtPwd.getText().toString());

                LoginService login = new LoginService(usr, getResponseListener());

                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(login);

            }else {
                Toast.makeText(this, "No puede dejar los campos en blanco", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private Response.Listener<String> getResponseListener (){
        return response1 -> {
            try {
                JSONObject data = new JSONObject(response1);
                if(data.getBoolean("success")){
                    helper = new DBHelper(Login.this);
                    helper.dropUsr();
                    usr.setID(data.getString("id"));
                    usr.setNombre(data.getString("nombre"));
                    usr.setApellido(data.getString("apellido"));
                    usr.setIniciales(data.getString("iniciales"));
                    usr.setCorreo(data.getString("email"));
                    usr.setTurno(data.getString("turno"));
                    usr.setPassword(txtPwd.getText().toString());
                    if(helper.setDataUser(usr)){
                        pBar.setIndeterminate(false);
                        pBar.setVisibility(View.INVISIBLE);
                        Dialog.showAlertDialog(Login.this,
                                "¡Correcto!",
                                "Se ha iniciado sesion correctamente",
                                (dialog, which) -> {
                                    Intent sendProf = new Intent(Login.this, Profesor.class);
                                    startActivity(sendProf);
                                });
                    }else{
                        pBar.setIndeterminate(false);
                        pBar.setVisibility(View.INVISIBLE);
                        Dialog.showAlertDialog(Login.this,
                                "?Error!",
                                "Ha ocurrido un error al guardar los datos",
                                (dialog, which) -> {this.recreate();});
                    }
                }else {
                    pBar.setIndeterminate(false);
                    pBar.setVisibility(View.INVISIBLE);
                    Dialog.showAlertDialog(Login.this,"¡Error!", data.getString("message"), (dialog, which) -> {});
                }
            }catch (Exception error){
                pBar.setIndeterminate(false);
                pBar.setVisibility(View.INVISIBLE);
                System.out.println(error.getLocalizedMessage());
                Dialog.showAlertDialog(Login.this,"¡Error!", "Ha ocurrido un error en la peticion", (dialog, which) -> this.recreate());
            }
        };
    }

    private boolean validateFields(){
        return (txtUser.getText().length() > 0 &&
                txtPwd.getText().length() > 0 );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }


}
