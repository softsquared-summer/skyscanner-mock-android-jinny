package com.jeahn.skyscanner.src.main.trips;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.OneFlightAdapter;
import com.jeahn.skyscanner.src.main.trips.models.Schedule;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {
    private List<Schedule> mScheduleList;

    public TripsAdapter(List<Schedule> mScheduleList) {
        this.mScheduleList = mScheduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule = mScheduleList.get(position);

        holder.tvTitle.setText(schedule.getTitle());
        Glide.with(holder.ivCity.getContext()).load(schedule.getCityImgUrl()).into(holder.ivCity);
    }

    @Override
    public int getItemCount() {
        return mScheduleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivCity;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.schedule_tv_title);
            ivCity = itemView.findViewById(R.id.schedule_iv_city);
        }
    }
}
