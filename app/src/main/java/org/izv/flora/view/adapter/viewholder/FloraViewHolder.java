package org.izv.flora.view.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.flora.R;

public class FloraViewHolder extends RecyclerView.ViewHolder {

public ImageView rvImagen;
public TextView rvNombre, rvFamilia;

public FloraViewHolder(@NonNull View itemView) {
        super(itemView);
        rvImagen = itemView.findViewById(R.id.rvImagen);
        rvNombre = itemView.findViewById(R.id.rvNombre);
        rvFamilia = itemView.findViewById(R.id.rvFamilia);
        }
        }
