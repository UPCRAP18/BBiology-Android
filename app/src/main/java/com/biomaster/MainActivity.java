package com.biomaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(v -> {
            Intent slider_act = new Intent(MainActivity.this, SliderScreen.class);
            this.startActivity(slider_act);
            this.finish();
        });

    }
}
