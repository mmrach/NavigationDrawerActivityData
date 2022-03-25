package com.amm.navigationdraweractivitydata.preferencias;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import navigationdraweractivitydata.R;


public class ColorRVAdapter extends RecyclerView.Adapter<ColorRVAdapter.ColorViewHolder> {
    private ArrayList<Pair<String,Integer>> colors;
    private ColorsItemClickListener colorsItemClickListener;
    private ArrayList<Pair<String,Integer>> bgColors;

    public interface ColorsItemClickListener {
        void onColorItemClicked(RecyclerView.ViewHolder vh, Pair<String,Integer> color, int pos);
    }

    public ColorRVAdapter(ColorsItemClickListener listener) {
        super();
        this.colorsItemClickListener = listener;
        SetupColors();
    }

    private void SetupColors(){
        bgColors = new ArrayList<Pair<String,Integer>>();
        bgColors.add(new Pair<>("RED", Color.RED));
        bgColors.add(new Pair<>("GREEN",Color.GREEN));
        bgColors.add(new Pair<>("BLUE",Color.BLUE));
        bgColors.add(new Pair<>("YELLOW",Color.YELLOW));
        bgColors.add(new Pair<>("BLACK",Color.BLACK));
        bgColors.add(new Pair<>("MAGENTA",Color.MAGENTA));
        bgColors.add(new Pair<>("WHITE",Color.WHITE));
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_row_layout, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        final Pair color = bgColors.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorsItemClickListener.onColorItemClicked(holder,color, holder.getAdapterPosition());
            }
        });

        if (color.first.toString().equals("BLACK") || color.first.toString().equals("BLUE")){
            holder.tvColor.setTextColor(Color.WHITE);
        }
        holder.tvColor.setText(color.first.toString());
        holder.tvColor.setBackgroundColor((Integer) color.second);
    }

    @Override
    public int getItemCount() {
        return bgColors.size();
    }

    // --------- IMPLEMENTACION DE NUESTRO VIEW HOLDER ESPECÍFICO ----------------------------
    // stores and recycles views as they are scrolled off screen
    public class ColorViewHolder extends RecyclerView.ViewHolder {
        TextView tvColor;

        ColorViewHolder(View itemView) {
            super(itemView);
            tvColor = itemView.findViewById(R.id.tvColor);
        }
    }
    // ---------------------------------------------------------------------------------------


}
