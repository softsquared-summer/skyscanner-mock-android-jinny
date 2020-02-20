package com.jeahn.skyscanner.src.flights;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.models.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SearchFlightsResultAdapter extends RecyclerView.Adapter<SearchFlightsResultAdapter.ViewHolder> {
    ArrayList<Ticket> mTicketList;
    private String mStrFrom, mStrTo;

    public SearchFlightsResultAdapter(ArrayList<Ticket> mTicketList, String from, String to) {
        this.mTicketList = mTicketList;
        mStrFrom = from;
        mStrTo = to;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_flights_result,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket item = mTicketList.get(position);

         try {
             SimpleDateFormat stringToTimeFormat = new SimpleDateFormat("HH:mm");
             SimpleDateFormat amPmFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
             Date deTime = stringToTimeFormat.parse(item.getDeTime());
             String deTimeAmPm = amPmFormat.format(deTime);
             Date arTime = stringToTimeFormat.parse(item.getArTime());
             String arTimeAmPm = amPmFormat.format(arTime);

             holder.mTvTime.setText(deTimeAmPm + " - " + arTimeAmPm);
         } catch (ParseException e) {
            e.printStackTrace();
         }
         holder.mTvFromTo.setText(mStrFrom + "-" + mStrTo + ", ");
         holder.mTvAirLineKr.setText(item.getAirLineKr());
         holder.mTvDuration.setText(item.getTimeGap());
         holder.mTvPrice.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return this.mTicketList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTvTime, mTvFromTo, mTvAirLineKr, mTvDuration, mTvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTime = itemView.findViewById(R.id.item_search_flights_time);
            mTvFromTo = itemView.findViewById(R.id.item_search_flights_from_to);
            mTvAirLineKr = itemView.findViewById(R.id.item_search_flights_airline);
            mTvDuration = itemView.findViewById(R.id.item_search_flights_duration);
            mTvPrice = itemView.findViewById(R.id.item_search_flights_price);
        }
    }
}
