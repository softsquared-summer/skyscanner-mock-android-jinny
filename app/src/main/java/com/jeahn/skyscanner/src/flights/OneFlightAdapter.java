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
import com.jeahn.skyscanner.src.flightsDetail.FlightsDetailActivity;
import com.jeahn.skyscanner.src.flights.models.Ticket;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class OneFlightAdapter extends RecyclerView.Adapter<OneFlightAdapter.ViewHolder> {
    private static String KEY_TICKET = "ticket";
    private static String KEY_TICKET_TYPE = "ticketType";

    private ArrayList<Ticket> mTicketList;
    private City mDeCity, mArCity;
    private int mAdultCount, mInfantCount, mChildCount;

    OneFlightAdapter(ArrayList<Ticket> mTicketList, City deCity, City arCity, int adultCount, int infantCount, int childCount) {
        this.mTicketList = mTicketList;
        mDeCity = deCity;
        mArCity = arCity;
        mAdultCount = adultCount;
        mInfantCount = infantCount;
        mChildCount = childCount;
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
        holder.tvFromTo.setText(String.format("%s-%s, ", mDeCity.getAirPortCode(), mArCity.getAirPortCode()));
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
        int totalPrice = 0;
        if(mAdultCount > 1 || mInfantCount > 0 || mChildCount > 0){
            int totalCount = mAdultCount + mInfantCount + mChildCount;
            totalPrice = item.getAdultPrice() * mAdultCount + item.getInfantPrice() * mInfantCount + item.getChildPrice() * mChildCount;
            String strTotalPrice = NumberFormat.getCurrencyInstance(Locale.KOREA).format(totalPrice);

            holder.tvTotalPrice.setText(totalCount+"명 "+strTotalPrice);
            holder.tvTotalPrice.setVisibility(View.VISIBLE);
        }else{
            totalPrice = item.getAdultPrice();
            holder.tvTotalPrice.setVisibility(View.GONE);
        }

        int finalTotalPrice = totalPrice;
        holder.view.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), FlightsDetailActivity.class);
            intent.putExtra("deCity", mDeCity);
            intent.putExtra("arCity", mArCity);
            intent.putExtra("totalPrice", finalTotalPrice);
            intent.putExtra(KEY_TICKET_TYPE, 1);
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
        TextView tvTime, tvFromTo, tvAirLineKr, tvDuration, tvPrice, tvTotalPrice;
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
            tvTotalPrice = itemView.findViewById(R.id.item_search_flights_tv_total_price);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
