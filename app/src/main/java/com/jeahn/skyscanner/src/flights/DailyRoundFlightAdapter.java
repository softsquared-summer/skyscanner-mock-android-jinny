package com.jeahn.skyscanner.src.flights;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.models.DailyRoundAirLine;
import com.jeahn.skyscanner.src.flights.models.Ticket;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DailyRoundFlightAdapter extends RecyclerView.Adapter<DailyRoundFlightAdapter.ViewHolder> {
    private List<DailyRoundAirLine> mAirLineList;

    private int mItemCount;

    public DailyRoundFlightAdapter(List<DailyRoundAirLine> mAirLineList) {
        this.mAirLineList = mAirLineList;
        mItemCount = mAirLineList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_round_flight, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyRoundAirLine airLine = mAirLineList.get(position);
        Glide.with(holder.ivAirLine.getContext()).load(airLine.getAirLineImgUrl()).into(holder.ivAirLine);
        holder.tvAirLine.setText(airLine.getAirLineKr());
        String strPrice = NumberFormat.getCurrencyInstance(Locale.KOREA).format(airLine.getDeMinAdultPrice() + airLine.getReMinAdultPrice());
        holder.tvPrice.setText(strPrice);

        //출국 시간
        String deTimes = "";
        for (Ticket ticket : airLine.getDeTicketList()) {
            try {
                SimpleDateFormat stringToTimeFormat = new SimpleDateFormat("HH:mm");
                SimpleDateFormat amPmFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
                Date deTime = stringToTimeFormat.parse(ticket.getDeTime());
                String deTimeAmPm = amPmFormat.format(deTime);

                deTimes += deTimeAmPm + "\t\t";
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        holder.tvDeTimes.setText(deTimes.trim());

        //귀국 시간
        String reTimes = "";
        for (Ticket ticket : airLine.getReTicketList()) {
            try {
                SimpleDateFormat stringToTimeFormat = new SimpleDateFormat("HH:mm");
                SimpleDateFormat amPmFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
                Date deTime = stringToTimeFormat.parse(ticket.getDeTime());
                String deTimeAmPm = amPmFormat.format(deTime);

                reTimes += deTimeAmPm + "\t\t";
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        holder.tvReTimes.setText(reTimes.trim());
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setItemCount(int itemCount) {
        mItemCount = itemCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAirLine, tvPrice, tvDeTimes, tvReTimes;
        public ImageView ivAirLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAirLine = itemView.findViewById(R.id.daily_round_tv_airline);
            tvPrice = itemView.findViewById(R.id.daily_round_tv_price);
            tvDeTimes = itemView.findViewById(R.id.daily_round_tv_de_times);
            tvReTimes = itemView.findViewById(R.id.daily_round_tv_re_times);
            ivAirLine = itemView.findViewById(R.id.daily_round_iv_airline);
        }
    }
}
