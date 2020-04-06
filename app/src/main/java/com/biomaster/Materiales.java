package com.biomaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.biomaster.Adaptadores.MaterialesAdapter;
import com.biomaster.Models.Material;

import java.util.ArrayList;

public class Materiales extends AppCompatActivity {

    private ArrayList<Material> matAlumno = new ArrayList<>();
    private ArrayList<Material> matEquipo = new ArrayList<>();
    private ArrayList<Material> matLab = new ArrayList<>();
    private String practica = "";
    private ListView lvAlumnoMat, lvEquipoMat, lvLabMat;
    Button btnEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiales);

        ActionBar aBar = getSupportActionBar();
        if (aBar != null) {
            aBar.setDisplayHomeAsUpEnabled(true);
            aBar.setTitle("Materiales");
        }

        if (getIntent().getStringExtra("practica") != null) {
            practica = getIntent().getStringExtra("practica");
        }


        TextView txtPractica = findViewById(R.id.txtPractica);
        btnEquipo = findViewById(R.id.btnMatEquip);
        lvAlumnoMat = findViewById(R.id.LVMatAlumno);
        lvEquipoMat = findViewById(R.id.LVMatEquip);
        lvLabMat = findViewById(R.id.LVMatLab);

        txtPractica.setText(getResources().getString(R.string.txtHeaderMateriales, practica));

        boolean setLV1 = true, setLV2 = true;

        switch (practica) {
            case "Practica 1":
                setArrayFor1();
                setLV2 = false;
                break;
            case "Practica 2":
                setArrayFor2();
                setLV1 = false;
                break;
            case "Practica 3":
                setArrayFor3();
                setLV1 = false;
                break;
            case "Practica 4":
                setArrayFor4();
                setLV2 = false;
                break;
            case "Practica 7":
                setArrayFor7();
                break;
            case "Practica 8":
                setArrayFor8();
                break;
        }

        if (setLV1) lvAlumnoMat.setAdapter(new MaterialesAdapter(matAlumno, this));

        if (setLV2) lvEquipoMat.setAdapter(new MaterialesAdapter(matEquipo, this));

        lvLabMat.setAdapter(new MaterialesAdapter(matLab, this));

    }

    public void changeVisibility(View view) {
        switch (view.getId()) {
            case R.id.btnMatAlumn:
                if (matAlumno.size() != 0)
                    if (lvAlumnoMat.getVisibility() == View.VISIBLE)
                        lvAlumnoMat.setVisibility(View.GONE);
                    else lvAlumnoMat.setVisibility(View.VISIBLE);

                break;
            case R.id.btnMatEquip:
                if (matEquipo.size() != 0)
                    if (lvEquipoMat.getVisibility() == View.VISIBLE)
                        lvEquipoMat.setVisibility(View.GONE);
                    else lvEquipoMat.setVisibility(View.VISIBLE);
                break;
            case R.id.btnMatLab:
                if (matLab.size() != 0)
                    if (lvLabMat.getVisibility() == View.VISIBLE) lvLabMat.setVisibility(View.GONE);
                    else lvLabMat.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setArrayFor1() {
        matAlumno.add(new Material("Cuadernillo", R.drawable.p1_manual));
        matAlumno.add(new Material("Aguja de Canevá", R.drawable.p1_aguja));
        matAlumno.add(new Material("5 metros de hilo de Canevá", R.drawable.p1_hilo));
        matAlumno.add(new Material("1/2 metro de plastico cristal", R.drawable.p1_cristal));

        matLab.add(new Material("Una gradilla con tubo de ensalle", R.drawable.p1_gradilla));
        matLab.add(new Material("Pipeta", R.drawable.p1_pipeta));
        matLab.add(new Material("Vaso de precipitado", R.drawable.p1_vaso));
        matLab.add(new Material("Matraz Erlenmeyer", R.drawable.p1_matraz));
        matLab.add(new Material("Caja de petri", R.drawable.p1_petri));
        matLab.add(new Material("Frasco Gotero", R.drawable.p1_gotero));
        matLab.add(new Material("Lampara de alcohol", R.drawable.p1_lampara));
        matLab.add(new Material("2 porta y cubre objetos", R.drawable.p1_porta_cubre));
        matLab.add(new Material("Aceite de inmersion", R.drawable.p1_aceite));
        matLab.add(new Material("Escobillon", R.drawable.p1_escobillon));
        matLab.add(new Material("Papel seda y papel filtro", R.drawable.p1_seda_filtro));

    }

    private void setArrayFor2() {
        matEquipo.add(new Material("Hoja delgada de vegetal", R.drawable.p2_hoja));
        matEquipo.add(new Material("Recortes de palabras con letras pequeñas", R.drawable.p2_recortes));
        matEquipo.add(new Material("Araña", R.drawable.p3_arania));
        matEquipo.add(new Material("Cabello", R.drawable.p2_cabello));

        matLab.add(new Material("Microscopio compuesto", R.drawable.p2_compuesto));
        matLab.add(new Material("Frasco gotero con agua destilada", R.drawable.p3_destilada));

        matLab.add(new Material("Pinzas sencillas", R.drawable.p2_pinzas));
        matLab.add(new Material("Papel seda y papel filtro", R.drawable.p2_papel));
        matLab.add(new Material("Aguja de diseccion", R.drawable.p3_aguja));
        matLab.add(new Material("5 portaobjetos y 5 cubreobjetos", R.drawable.p1_porta_cubre));
        matLab.add(new Material("Caja de petri", R.drawable.p1_petri));

    }

    private void setArrayFor3() {
        matEquipo.add(new Material("1 corcho de botella", R.drawable.p3_corcho));
        matEquipo.add(new Material("1 gusano tenebrio", R.drawable.p3_lombriz));
        matEquipo.add(new Material("1 araña (mediana)", R.drawable.p3_arania));
        matEquipo.add(new Material("1 vegetal", R.drawable.p3_vegetal));
        matEquipo.add(new Material("1 par de guantes de latex", R.drawable.p3_guantes));

        matLab.add(new Material("Microscopio estereoscopico", R.drawable.p3_micro));
        matLab.add(new Material("1 caja de petri", R.drawable.p1_petri));
        matLab.add(new Material("Papel filtro y papel seda", R.drawable.p1_seda_filtro));
        matLab.add(new Material("Frasco gotero con agua destilada", R.drawable.p3_destilada));
        matLab.add(new Material("Navaja y pinzas sencillas", R.drawable.p3_navaja));
        matLab.add(new Material("Aguja de diseccion", R.drawable.p3_aguja));

    }

    private void setArrayFor4() {
        btnEquipo.setText("Materiales por Seccion");
        matEquipo.add(new Material("Rama de Elodea fresca", R.drawable.p4_elodea));
        matLab.add(new Material("Microscopio compuesto", R.drawable.p2_compuesto));
        matLab.add(new Material("Muestras fijas de sangre, bacterias y  tejido humano", R.drawable.p4_muestras));
        matLab.add(new Material("Pinzas sencillas", R.drawable.p2_pinzas));
        matLab.add(new Material("Aguja de diseccion", R.drawable.p3_aguja));
        matLab.add(new Material("Papel seda y papel filtro", R.drawable.p1_seda_filtro));
        matLab.add(new Material("2 portaobjetos y 2 cubreobjetos", R.drawable.p1_porta_cubre));
    }

    private void setArrayFor7() {
        matAlumno.add(new Material("1 par de guantes de latex", R.drawable.p3_guantes));
        matAlumno.add(new Material("1 cubrebocas", R.drawable.p7_cubrebocas));
        matEquipo.add(new Material("Flor Lili con polen en los estambres", R.drawable.p7_lili));
        matLab.add(new Material("Microscopio optico", R.drawable.p7_optico));
        matLab.add(new Material("Muestras fijas de espermatozoides humanos, testiculos de raton y cromosomas humanos", R.drawable.p4_muestras));
        matLab.add(new Material("Frasco gotero con agua destilada", R.drawable.p3_destilada));
        matLab.add(new Material("Navaja", R.drawable.p3_navaja));
        matLab.add(new Material("Pinzas sencillas", R.drawable.p2_pinzas));
        matLab.add(new Material("Aguja de diseccion", R.drawable.p3_aguja));
        matLab.add(new Material("Caja de petri", R.drawable.p1_petri));
        matLab.add(new Material("4 postaobjetos y 4 cubreobjetos", R.drawable.p1_porta_cubre));
        matLab.add(new Material("Papel seda y papel filtro", R.drawable.p1_seda_filtro));

    }

    private void setArrayFor8() {
        btnEquipo.setText("Materiales por seccion");
        matAlumno.add(new Material("3 condones masculinos", R.drawable.p8_condones));
        matEquipo.add(new Material("1 condon femenino", R.drawable.p8_femenino));
        matEquipo.add(new Material("1 película de látex para sexo oral femenino", R.drawable.p7_lili));
        matLab.add(new Material("1 modelo de pene", R.drawable.p8_modelomasc));
        matLab.add(new Material("1 modelo de vagina", R.drawable.p8_modelofem));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }
}
