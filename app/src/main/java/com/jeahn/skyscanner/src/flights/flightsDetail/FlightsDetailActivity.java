package com.jeahn.skyscanner.src.flights.flightsDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.flights.models.Ticket;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FlightsDetailActivity extends BaseActivity {
    private static String KEY_TICKET = "TICKET";

    private Toolbar mToolbar;
    private ImageView mIvAirLine;
    private TextView mTvTime, mTvAirLineKr, mTvDuration, mTvPrice;

    private Ticket mTicket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_detail);

        mToolbar = findViewById(R.id.flight_detail_toolbar);
        setSupportActionBar(mToolbar);

        mIvAirLine = findViewById(R.id.flights_detail_iv_airline);
        mTvTime = findViewById(R.id.flights_detail_tv_time);
        mTvAirLineKr = findViewById(R.id.flights_detail_tv_airline);
        mTvDuration = findViewById(R.id.flights_detail_tv_duration);
        mTvPrice = findViewById(R.id.flights_detail_tv_price);

        Intent intent = getIntent();
        mTicket = intent.getParcelableExtra(KEY_TICKET);

        setTicketInfo();

    }

    private void setTicketInfo() {
        Glide.with(getApplicationContext()).load(mTicket.getAirLineImgUrl()).into(mIvAirLine);
        try {
            SimpleDateFormat stringToTimeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat amPmFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
            Date deTime = stringToTimeFormat.parse(mTicket.getDeTime());
            String deTimeAmPm = amPmFormat.format(deTime);
            Date arTime = stringToTimeFormat.parse(mTicket.getArTime());
            String arTimeAmPm = amPmFormat.format(arTime);

            mTvTime.setText(deTimeAmPm + " - " + arTimeAmPm);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mTvAirLineKr.setText(mTicket.getAirLineKr());
        mTvDuration.setText(mTicket.getTimeGap());
        String strPrice = NumberFormat.getCurrencyInstance(Locale.KOREA).format(mTicket.getPrice());
        mTvPrice.setText(strPrice);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
