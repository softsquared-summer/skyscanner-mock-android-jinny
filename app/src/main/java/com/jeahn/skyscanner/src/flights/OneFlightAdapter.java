package com.jeahn.skyscanner.src.flights;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.flightsDetail.FlightsDetailActivity;
import com.jeahn.skyscanner.src.flights.models.Ticket;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class OneFlightAdapter extends RecyclerView.Adapter<OneFlightAdapter.ViewHolder> {
    private static String KEY_TICKET = "TICKET";

    private ArrayList<Ticket> mTicketList;
    private String mStrFrom, mStrTo;

    OneFlightAdapter(ArrayList<Ticket> mTicketList, String from, String to) {
        this.mTicketList = mTicketList;
        mStrFrom = from;
        mStrTo = to;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flights, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket item = mTicketList.get(position);

        Glide.with(holder.ivAirLine.getContext()).load(item.getAirLineImgUrl()).into(holder.ivAirLine);
        try {
            SimpleDateFormat stringToTimeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat amPmFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
            Date deTime = stringToTimeFormat.parse(item.getDeTime());
            String deTimeAmPm = amPmFormat.format(deTime);
            Date arTime = stringToTimeFormat.parse(item.getArTime());
            String arTimeAmPm = amPmFormat.format(arTime);

            holder.tvTime.setText(String.format("%s - %s", deTimeAmPm, arTimeAmPm));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvFromTo.setText(String.format("%s-%s, ", mStrFrom, mStrTo));
        holder.tvAirLineKr.setText(item.getAirLineKr());
        long hour = TimeUnit.MINUTES.toHours(item.getTimeGap());
        long minutes = TimeUnit.MINUTES.toMinutes(item.getTimeGap());
        String strDuration = "";
        if (hour > 0) {
            strDuration += hour + holder.view.getContext().getString(R.string.flights_hour);
        }
        if (minutes > 0) {
            strDuration += minutes + holder.view.getContext().getString(R.string.flights_minutes);
        }
        holder.tvDuration.setText(strDuration);
        String strPrice = NumberFormat.getCurrencyInstance(Locale.KOREA).format(item.getAdultPrice());
        holder.tvPrice.setText(strPrice);

        holder.view.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), FlightsDetailActivity.class);
            intent.putExtra(KEY_TICKET, item);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.mTicketList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvTime, tvFromTo, tvAirLineKr, tvDuration, tvPrice;
        ImageView ivAirLine;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvTime = itemView.findViewById(R.id.item_search_flights_tv_time);
            tvFromTo = itemView.findViewById(R.id.item_search_flights_tv_from_to);
            tvAirLineKr = itemView.findViewById(R.id.item_search_flights_tv_airline);
            tvDuration = itemView.findViewById(R.id.item_search_flights_tv_duration);
            tvPrice = itemView.findViewById(R.id.item_search_flights_tv_price);
            ivAirLine = itemView.findViewById(R.id.item_search_flights_iv_airline);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
