package com.biomaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.biomaster.Conectores.Requests.LoginService;
import com.biomaster.Extras.Dialog;
import com.biomaster.Models.User;

import org.json.JSONObject;

public class SliderScreen extends AppCompatActivity {
    private ImageView imgSlider;
    private AnimationDrawable anims = new AnimationDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        imgSlider = findViewById(R.id.imgSlider);
        Button btnConsultar = findViewById(R.id.btnConsultar);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v-> {
            Intent sendLogin = new Intent(SliderScreen.this, Login.class);
            this.startActivity(sendLogin);
        });

        btnRegister.setOnClickListener(v -> {
            Intent sendRegister = new Intent(SliderScreen.this, Registro.class);
            this.startActivity(sendRegister);
        });

        btnConsultar.setOnClickListener(v -> setFloatingScreen() );

        renderSlider();

    }


    private void setFloatingScreen(){
        LayoutInflater li = LayoutInflater.from(SliderScreen.this);
        View loginScreen = li.inflate(R.layout.activity_login, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SliderScreen.this);
        builder.setView(loginScreen);

        LinearLayout mainLay = loginScreen.findViewById(R.id.LayMain);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        mainLay.setLayoutParams(params);

        TextView txtUsr = loginScreen.findViewById(R.id.txtUser);
        TextView txtPwd = loginScreen.findViewById(R.id.txtPwd);
        Button btnLogin = loginScreen.findViewById(R.id.btnLogin);
        ProgressBar pBar = loginScreen.findViewById(R.id.pBar);

        btnLogin.setOnClickListener(v -> {
            if(txtUsr.getText().length() > 0 && txtPwd.getText().length() > 0){
                pBar.setVisibility(View.VISIBLE);
                pBar.setIndeterminate(true);
                User usr = new User();
                usr.setID(txtUsr.getText().toString());
                usr.setPassword(txtPwd.getText().toString());
                pBar.setIndeterminate(true);
                pBar.setVisibility(View.VISIBLE);
                LoginService login = new LoginService(usr, response -> {
                    try {
                        JSONObject data = new JSONObject(response);
                        if(data.getBoolean("success")) {
                            Intent sendConsult = new Intent(SliderScreen.this, Consultas.class);
                            sendConsult.putExtra("id", data.getString("id"));
                            startActivity(sendConsult);
                        }else {
                            Dialog.showAlertDialog(SliderScreen.this,"¡Error!", data.getString("message"), (dialog, which) -> {});
                        }
                        pBar.setIndeterminate(false);
                        pBar.setVisibility(View.INVISIBLE);
                    }catch (Exception ex){
                        pBar.setIndeterminate(false);
                        pBar.setVisibility(View.INVISIBLE);
                        System.out.println(ex.getLocalizedMessage());
                        Dialog.showAlertDialog(SliderScreen.this,"¡Error!", "Ha ocurrido un error en la peticion", (dialog, which) -> this.onBackPressed());
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(SliderScreen.this);
                queue.add(login);

            }else {
                Toast.makeText(this, "No puede dejar los campos en blanco", Toast.LENGTH_SHORT).show();
            }

        });

        builder.create().show();
    }

    private void renderSlider(){
        anims.addFrame(getResources().getDrawable(R.drawable.w1), 3000);
        anims.addFrame(getResources().getDrawable(R.drawable.w2), 3000);
        anims.addFrame(getResources().getDrawable(R.drawable.w3), 3000);
        anims.addFrame(getResources().getDrawable(R.drawable.w4), 3000);
        imgSlider.setImageDrawable(anims);
        anims.start();
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
