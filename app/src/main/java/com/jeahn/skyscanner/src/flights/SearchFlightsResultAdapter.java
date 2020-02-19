package com.jeahn.skyscanner.src.flights;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;

import java.util.ArrayList;

public class SearchFlightsResultAdapter extends RecyclerView.Adapter<SearchFlightsResultAdapter.ViewHolder> {
    ArrayList<String> items;

    public SearchFlightsResultAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_flights_result,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTvAirLineKr.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTvAirLineKr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvAirLineKr = itemView.findViewById(R.id.item_search_flights_airLineKr);
        }
    }
}
