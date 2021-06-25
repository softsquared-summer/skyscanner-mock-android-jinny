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
import com.jeahn.skyscanner.src.city.models.City;
import com.jeahn.skyscanner.src.flights.models.RoundTicket;
import com.jeahn.skyscanner.src.flights.models.Ticket;
import com.jeahn.skyscanner.src.flightsDetail.FlightsDetailActivity;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class RoundFlightAdapter extends RecyclerView.Adapter<RoundFlightAdapter.ViewHolder> {
    private static String KEY_TICKET = "ticket";
    private static String KEY_TICKET_TYPE = "ticketType";

    private List<RoundTicket> mTicketList;
    private City mDeCity, mArCity;

    RoundFlightAdapter(List<RoundTicket> mTicketList, City deCity, City arCity) {
        this.mTicketList = mTicketList;
        mDeCity = deCity;
        mArCity = arCity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_round_flights, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoundTicket item = mTicketList.get(position);
        Ticket deTicket = item.getDeTicket();
        Ticket reTicket = item.getReTicket();

        Glide.with(holder.ivDeAirLine.getContext()).load(deTicket.getAirLineImgUrl()).into(holder.ivDeAirLine);
        try {
            SimpleDateFormat stringToTimeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat amPmFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
            Date deTime = stringToTimeFormat.parse(deTicket.getDeTime());
            String deTimeAmPm = amPmFormat.format(deTime);
            Date arTime = stringToTimeFormat.parse(deTicket.getArTime());
            String arTimeAmPm = amPmFormat.format(arTime);

            holder.tvDeTime.setText(String.format("%s - %s", deTimeAmPm, arTimeAmPm));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvDeFromTo.setText(String.format("%s-%s, ", mDeCity.getAirPortCode(), mArCity.getAirPortCode()));
        holder.tvDeAirLine.setText(deTicket.getAirLineKr());
        long hour = TimeUnit.MINUTES.toHours(deTicket.getTimeGap());
        long minutes = TimeUnit.MINUTES.toMinutes(deTicket.getTimeGap());
        String strDuration = "";
        if (hour > 0) {
            strDuration += hour + holder.view.getContext().getString(R.string.flights_hour);
        }
        if (minutes > 0) {
            strDuration += minutes + holder.view.getContext().getString(R.string.flights_minutes);
        }
        holder.tvDeDuration.setText(strDuration);

        Glide.with(holder.ivReAirLine.getContext()).load(reTicket.getAirLineImgUrl()).into(holder.ivReAirLine);
        try {
            SimpleDateFormat stringToTimeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat amPmFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
            Date deTime = stringToTimeFormat.parse(reTicket.getDeTime());
            String deTimeAmPm = amPmFormat.format(deTime);
            Date arTime = stringToTimeFormat.parse(reTicket.getArTime());
            String arTimeAmPm = amPmFormat.format(arTime);

            holder.tvReTime.setText(String.format("%s - %s", deTimeAmPm, arTimeAmPm));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvReFromTo.setText(String.format("%s-%s, ", mDeCity.getAirPortCode(), mArCity.getAirPortCode()));
        holder.tvReAirLine.setText(reTicket.getAirLineKr());
        hour = TimeUnit.MINUTES.toHours(reTicket.getTimeGap());
        minutes = TimeUnit.MINUTES.toMinutes(reTicket.getTimeGap());
        strDuration = "";
        if (hour > 0) {
            strDuration += hour + holder.view.getContext().getString(R.string.flights_hour);
        }
        if (minutes > 0) {
            strDuration += minutes + holder.view.getContext().getString(R.string.flights_minutes);
        }
        holder.tvReDuration.setText(strDuration);
        String strPrice = NumberFormat.getCurrencyInstance(Locale.KOREA).format(deTicket.getAdultPrice() + reTicket.getAdultPrice());
        holder.tvPrice.setText(strPrice);

        holder.view.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), FlightsDetailActivity.class);
            intent.putExtra("deCity", mDeCity);
            intent.putExtra("arCity", mArCity);
            intent.putExtra(KEY_TICKET_TYPE, 2);
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
        TextView tvDeTime, tvDeFromTo, tvDeAirLine, tvDeDuration, tvReTime, tvReFromTo, tvReAirLine, tvReDuration, tvPrice;
        ImageView ivDeAirLine, ivReAirLine;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ivDeAirLine = itemView.findViewById(R.id.round_flight_iv_de_airline);
            tvDeAirLine = itemView.findViewById(R.id.round_flight_tv_de_airline);
            tvDeTime = itemView.findViewById(R.id.round_flight_tv_de_time);
            tvDeFromTo = itemView.findViewById(R.id.round_flight_tv_de_from_to);
            tvDeDuration = itemView.findViewById(R.id.round_flight_tv_de_duration);
            ivReAirLine = itemView.findViewById(R.id.round_flight_iv_re_airline);
            tvReAirLine = itemView.findViewById(R.id.round_flight_tv_re_airline);
            tvReTime = itemView.findViewById(R.id.round_flight_tv_re_time);
            tvReFromTo = itemView.findViewById(R.id.round_flight_tv_re_from_to);
            tvReDuration = itemView.findViewById(R.id.round_flight_tv_re_duration);
            tvPrice = itemView.findViewById(R.id.round_flight_tv_price);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
