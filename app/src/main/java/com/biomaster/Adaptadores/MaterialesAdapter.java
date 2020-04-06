package com.biomaster.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.biomaster.DocumentViewer;
import com.biomaster.Models.Material;
import com.biomaster.Models.Practica;
import com.biomaster.R;

import java.util.ArrayList;
import java.util.Locale;

public class MaterialesAdapter implements ListAdapter {
    private ArrayList<Material> mDataset;
    private Context cntx;

    public MaterialesAdapter(ArrayList<Material> mDataset, Context cntx) {
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
        Material actualMat = mDataset.get(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.cntx);
            convertView = inflater.inflate(R.layout.material_info, null);
            TextView txtNombreMat = convertView.findViewById(R.id.txtNombreMaterial);
            ImageView imgMaterial = convertView.findViewById(R.id.imgMaterial);

            txtNombreMat.setText(String.format(Locale.getDefault(), "%d. %s", position + 1, actualMat.getNombre()));
            imgMaterial.setImageDrawable(this.cntx.getResources().getDrawable(actualMat.getImagen()));

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
