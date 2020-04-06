package com.biomaster.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.biomaster.DocumentViewer;
import com.biomaster.Models.Practica;
import com.biomaster.R;

import java.util.ArrayList;
import java.util.Locale;

public class PracticasAdapter implements ListAdapter {
    private ArrayList<Practica> mDataset;
    private Context cntx;

    public PracticasAdapter(ArrayList<Practica> mDataset, Context cntx) {
        this.mDataset = mDataset;
        this.cntx = cntx;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Practica actualPract = mDataset.get(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(this.cntx);
            convertView = inflater.inflate(R.layout.practica_container, null);
            Button btnShowInfo = convertView.findViewById(R.id.btnShowInfo);
            LinearLayout infoLayout = convertView.findViewById(R.id.InfoLayout);
            TextView txtAsistAux = convertView.findViewById(R.id.txtAsistAux);
            TextView txtAsistServ = convertView.findViewById(R.id.txtAsistServ);
            TextView txtMFijas = convertView.findViewById(R.id.txtMFijas);
            TextView txtBata = convertView.findViewById(R.id.txtBata);
            TextView txtMatComp = convertView.findViewById(R.id.txtMatComp);
            TextView txtPuntAlumno = convertView.findViewById(R.id.txtPuntAlumno);
            TextView txtPuntProf = convertView.findViewById(R.id.txtPuntProf);
            TextView txtManual = convertView.findViewById(R.id.txtManual);
            TextView txtPractica = convertView.findViewById(R.id.txtPractica);
            TextView txtProf = convertView.findViewById(R.id.txtProfesor);

            btnShowInfo.setText(String.format(Locale.getDefault(), "%s\n%s -- %s",
                    actualPract.getFecha(), actualPract.getHora_Inicio(), actualPract.getHora_Fin()));

            infoLayout.setVisibility(View.GONE);

            String temp = actualPract.isAsist_Aux() ? "Si" : "No";
            txtAsistAux.setText(String.format(Locale.getDefault(), "Asistió auxiliar: %s", temp));
            temp = actualPract.isAsist_Chico_Serv() ? "Si" : "No";
            txtAsistServ.setText(String.format(Locale.getDefault(), "Asistió chico de servicio: %s", temp));
            temp = actualPract.isMuestras_Fija() ? "Si" : "No";
            txtMFijas.setText(String.format(Locale.getDefault(), "Muestras Fijas: %s", temp));
            temp = actualPract.isBata() ? "Si" : "No";
            txtBata.setText(String.format(Locale.getDefault(), "Bata: %s", temp));
            temp = actualPract.isMateriales_Completos() ? "Si" : "No";
            txtMatComp.setText(String.format(Locale.getDefault(), "Materiales completos: %s", temp));
            temp = actualPract.isPuntualidad_Alumno() ? "Si" : "No";
            txtPuntAlumno.setText(String.format(Locale.getDefault(), "Puntualidad alumno: %s", temp));
            temp = actualPract.isPuntualidad_Profesor() ? "Si" : "No";
            txtPuntProf.setText(String.format(Locale.getDefault(), "Puntualidad profesor: %s", temp));
            temp = actualPract.isManual() ? "Si" : "No";
            txtManual.setText(String.format(Locale.getDefault(), "Manual completo: %s", temp));
            txtPractica.setText(String.format(Locale.getDefault(), "Practica realizada: %s", actualPract.getPractica()));
            txtProf.setText(String.format(Locale.getDefault(), "Profesor: %s", actualPract.getNombre_Prof()));

            txtPractica.setTextColor(this.cntx.getResources().getColor(R.color.colorPrimaryDark));
            txtPractica.setPaintFlags(txtPractica.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            if(actualPract.isHubo_Practica()){
                btnShowInfo.setOnClickListener(v -> {
                    if(infoLayout.getVisibility() == View.VISIBLE)
                        infoLayout.setVisibility(View.GONE);
                    else
                        infoLayout.setVisibility(View.VISIBLE);
                });
            }else {
                btnShowInfo.setText(btnShowInfo.getText().toString().concat("\n Sin Practica"));
            }


            txtPractica.setOnClickListener(v -> {
                Intent sendVisualizer = new Intent(this.cntx, DocumentViewer.class);
                sendVisualizer.putExtra("path", actualPract.getRuta());
                this.cntx.startActivity(sendVisualizer);
            });

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return mDataset.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
