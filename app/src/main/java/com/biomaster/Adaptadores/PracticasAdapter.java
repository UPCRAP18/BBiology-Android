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
            TextView lblAsistAux = convertView.findViewById(R.id.lblAsistAux);
            TextView lblAsistServ = convertView.findViewById(R.id.lblAsistServ);
            TextView lblMFijas = convertView.findViewById(R.id.lblMFijas);
            TextView lblBata = convertView.findViewById(R.id.lblBata);
            TextView lblMatComp = convertView.findViewById(R.id.lblMatComp);
            TextView lblPuntAlumno = convertView.findViewById(R.id.lblPuntAlumno);
            TextView lblPuntProf = convertView.findViewById(R.id.lblPuntProf);
            TextView lblManual = convertView.findViewById(R.id.lblManual);
            TextView lblPractica = convertView.findViewById(R.id.lblPractica);

            btnShowInfo.setText(String.format(Locale.getDefault(), "%s\n%s -- %s",
                    actualPract.getFecha(), actualPract.getHora_Inicio(), actualPract.getHora_Fin()));

            infoLayout.setVisibility(View.GONE);

            String temp = actualPract.isAsist_Aux() ? "Si" : "No";
            lblAsistAux.setText(String.format(Locale.getDefault(), "Asistió auxiliar: %s", temp));
            temp = actualPract.isAsist_Chico_Serv() ? "Si" : "No";
            lblAsistServ.setText(String.format(Locale.getDefault(), "Asistió chico de servicio: %s", temp));
            temp = actualPract.isMuestras_Fija() ? "Si" : "No";
            lblMFijas.setText(String.format(Locale.getDefault(), "Muestras Fijas: %s", temp));
            temp = actualPract.isBata() ? "Si" : "No";
            lblBata.setText(String.format(Locale.getDefault(), "Bata: %s", temp));
            temp = actualPract.isMateriales_Completos() ? "Si" : "No";
            lblMatComp.setText(String.format(Locale.getDefault(), "Materiales completos: %s", temp));
            temp = actualPract.isPuntualidad_Alumno() ? "Si" : "No";
            lblPuntAlumno.setText(String.format(Locale.getDefault(), "Puntualidad alumno: %s", temp));
            temp = actualPract.isPuntualidad_Profesor() ? "Si" : "No";
            lblPuntProf.setText(String.format(Locale.getDefault(), "Puntualidad profesor: %s", temp));
            temp = actualPract.isManual() ? "Si" : "No";
            lblManual.setText(String.format(Locale.getDefault(), "Manual completo: %s", temp));
            lblPractica.setText(String.format(Locale.getDefault(), "Practica realizada: %s", actualPract.getPractica()));

            lblPractica.setTextColor(this.cntx.getResources().getColor(R.color.colorPrimaryDark));
            lblPractica.setPaintFlags(lblPractica.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

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


            lblPractica.setOnClickListener( v -> {
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
