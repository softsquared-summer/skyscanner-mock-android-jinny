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
import com.jeahn.skyscanner.src.flights.models.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FlightsAdapter extends RecyclerView.Adapter<FlightsAdapter.ViewHolder> {
    private static String KEY_TICKET = "TICKET";

    private ArrayList<Ticket> mTicketList;
    private String mStrFrom, mStrTo;

    public FlightsAdapter(ArrayList<Ticket> mTicketList, String from, String to) {
        this.mTicketList = mTicketList;
        mStrFrom = from;
        mStrTo = to;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flights,null);
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

             holder.tvTime.setText(deTimeAmPm + " - " + arTimeAmPm);
         } catch (ParseException e) {
            e.printStackTrace();
         }
         holder.tvFromTo.setText(mStrFrom + "-" + mStrTo + ", ");
         holder.tvAirLineKr.setText(item.getAirLineKr());
         holder.tvDuration.setText(item.getTimeGap());
         holder.tvPrice.setText(item.getPrice());


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FlightsDetailActivity.class);
                intent.putExtra(KEY_TICKET, item);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mTicketList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView tvTime, tvFromTo, tvAirLineKr, tvDuration, tvPrice;
        public ImageView ivAirLine;
        public ViewHolder(@NonNull View itemView) {
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
}
