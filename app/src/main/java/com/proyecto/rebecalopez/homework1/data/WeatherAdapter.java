package com.proyecto.rebecalopez.homework1.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.proyecto.rebecalopez.homework1.R;
import com.proyecto.rebecalopez.homework1.data.entities.List;

import java.util.ArrayList;

/**
 * Created by rebecalopez on 29/04/18.
 *
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private ArrayList<List> weatherList = new ArrayList<>();

    public WeatherAdapter(ArrayList<List> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(weatherList.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView temp;
        TextView tempMin;
        TextView tempMax;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            temp = itemView.findViewById(R.id.rr_txt_temp);
            tempMin = itemView.findViewById(R.id.rr_txt_mintemp);
            tempMax = itemView.findViewById(R.id.rr_txt_maxtemp);
            date = itemView.findViewById(R.id.rr_txt_date);
        }

        public void bind(List list) {
            temp.setText("temp: "+list.getMain().getTemp().toString());
            tempMin.setText("temp. min. ; "+list.getMain().getTempMin().toString());
            tempMax.setText("temp. max.: "+list.getMain().getTempMax().toString());
            date.setText("fecha: "+list.getDtTxt());
        }
    }
}
