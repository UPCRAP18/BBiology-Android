package com.biomaster;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        Button btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(v -> {
            LayoutInflater inflater = getLayoutInflater();
            Toast acercade = new Toast(getApplicationContext());
            View message = inflater.inflate(R.layout.info, null);
            acercade.setDuration(Toast.LENGTH_LONG);
            acercade.setView(message);
            acercade.show();
        });
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        mainLay.setLayoutParams(params);
        TextView txtUsr = loginScreen.findViewById(R.id.txteUser);
        TextView txtPwd = loginScreen.findViewById(R.id.txtePwd);
        Button btnLogin = loginScreen.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            if (!txtUsr.getText().toString().isEmpty() && !txtPwd.getText().toString().isEmpty()) {
                if(txtUsr.getText().toString().equals("admin_bio") && txtPwd.getText().toString().equals("admin")){
                    Toast.makeText(this, "Bienvenido administrador", Toast.LENGTH_SHORT).show();
                    Intent sendConsultas = new Intent(SliderScreen.this, Consultas.class);
                    startActivity(sendConsultas);
                } else {
                    Toast.makeText(this, "Las credenciales son incorrectas", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "No puede dejar los campos en blanco", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    private void renderSlider(){
        anims.addFrame(getResources().getDrawable(R.drawable.w1), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w2), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w3), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w4), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w5), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w6), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w7), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w8), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w9), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w10), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w11), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w12), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w13), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w14), 2000);
        anims.addFrame(getResources().getDrawable(R.drawable.w15), 2000);

        imgSlider.setImageDrawable(anims);
        anims.start();
    }


    public void exitApp(View view) {
        this.finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
