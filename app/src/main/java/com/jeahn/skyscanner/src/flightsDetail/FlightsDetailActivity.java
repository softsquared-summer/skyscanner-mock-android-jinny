package com.jeahn.skyscanner.src.flightsDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.city.models.City;
import com.jeahn.skyscanner.src.flights.models.RoundTicket;
import com.jeahn.skyscanner.src.flights.models.Ticket;
import com.jeahn.skyscanner.src.flightsDetail.interfaces.FlightsDetailActivityView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FlightsDetailActivity extends BaseActivity implements View.OnClickListener, FlightsDetailActivityView {
    private static String KEY_TICKET = "ticket";
    private static String KEY_TICKET_TYPE = "ticketType";

    private Toolbar mToolbar;
    private LinearLayout mLinearReturn;
    private RelativeLayout mRelativeSave;
    private ImageView mIvAirLine, mIvAirLineReturn, mIvSave;
    private TextView mTvFromToKr, mTvFromToKrReturn, mTvTime, mTvTimeReturn, mTvFromTo, mTvFromToReturn, mTvAirLineKr, mTvAirLineKrReturn, mTvDuration, mTvDurationReturn, mTvPrice;

    private City mDeCity, mArCity;
    private int mTotalPrice;
    private Ticket mTicket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_detail);

        mToolbar = findViewById(R.id.flight_detail_toolbar);
        setSupportActionBar(mToolbar);

        mRelativeSave = findViewById(R.id.flights_detail_relative_save);
        mRelativeSave.setOnClickListener(this);

        mTvFromToKr = findViewById(R.id.flights_detail_tv_from_to_kr);
        mIvAirLine = findViewById(R.id.flights_detail_iv_airline);
        mTvTime = findViewById(R.id.flights_detail_tv_time);
        mTvFromTo = findViewById(R.id.flights_detail_tv_from_to);
        mTvAirLineKr = findViewById(R.id.flights_detail_tv_airline);
        mTvDuration = findViewById(R.id.flights_detail_tv_duration);
        mTvPrice = findViewById(R.id.flights_detail_tv_price);

        //왕복
        mLinearReturn = findViewById(R.id.flights_detail_linear_return);
        mTvFromToKrReturn = findViewById(R.id.flights_detail_tv_from_to_kr_return);
        mIvAirLineReturn = findViewById(R.id.flights_detail_iv_airline_return);
        mTvTimeReturn = findViewById(R.id.flights_detail_tv_time_return);
        mTvFromToReturn = findViewById(R.id.flights_detail_tv_from_to_return);
        mTvAirLineKrReturn = findViewById(R.id.flights_detail_tv_airline_return);
        mTvDurationReturn = findViewById(R.id.flights_detail_tv_duration_return);

        mIvSave = findViewById(R.id.flights_detail_iv_save);

        Intent intent = getIntent();
        if (intent != null) {
            mDeCity = intent.getParcelableExtra("deCity");
            mArCity = intent.getParcelableExtra("arCity");
            mTotalPrice = intent.getIntExtra("totalPrice", 0);
            switch (intent.getIntExtra(KEY_TICKET_TYPE, 0)) {
                case 1:
                    mTicket = intent.getParcelableExtra(KEY_TICKET);
                    setTicketInfo(mTicket);
                    break;
                case 2:
                    RoundTicket roundTicket = intent.getParcelableExtra(KEY_TICKET);
                    setTicketInfo(roundTicket);
            }
        }
    }

    private void setTicketInfo(RoundTicket roundTicket) {
        Ticket deTicket = roundTicket.getDeTicket();
        Ticket reTicket = roundTicket.getReTicket();
        setTicketInfo(deTicket);

        //왕복
        mLinearReturn.setVisibility(View.VISIBLE);
        mTvFromToKrReturn.setText(mArCity.getCityNameKr() + " - " + mDeCity.getCityNameKr());
        Glide.with(getApplicationContext()).load(reTicket.getAirLineImgUrl()).into(mIvAirLineReturn);
        try {
            SimpleDateFormat stringToTimeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat amPmFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
            Date deTime = stringToTimeFormat.parse(reTicket.getDeTime());
            String deTimeAmPm = amPmFormat.format(deTime);
            Date arTime = stringToTimeFormat.parse(reTicket.getArTime());
            String arTimeAmPm = amPmFormat.format(arTime);

            mTvTimeReturn.setText(deTimeAmPm + " - " + arTimeAmPm);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mTvFromToReturn.setText(mArCity.getAirPortCode() + "-" + mDeCity.getAirPortCode() + ", ");
        mTvAirLineKrReturn.setText(reTicket.getAirLineKr());
        long hour = TimeUnit.MINUTES.toHours(reTicket.getTimeGap());
        long minutes = TimeUnit.MINUTES.toMinutes(reTicket.getTimeGap());
        String strDuration = "";
        if (hour > 0) {
            strDuration += hour + "시간 ";
        }
        if (minutes > 0) {
            strDuration += minutes + "분";
        }
        mTvDurationReturn.setText(strDuration);
        String strPrice = NumberFormat.getCurrencyInstance(Locale.KOREA).format(deTicket.getAdultPrice() + reTicket.getAdultPrice());
        mTvPrice.setText(strPrice);
    }

    private void setTicketInfo(Ticket ticket) {
        mTvFromToKr.setText(mDeCity.getCityNameKr() + " - " + mArCity.getCityNameKr());
        Glide.with(getApplicationContext()).load(ticket.getAirLineImgUrl()).into(mIvAirLine);
        try {
            SimpleDateFormat stringToTimeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat amPmFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
            Date deTime = stringToTimeFormat.parse(ticket.getDeTime());
            String deTimeAmPm = amPmFormat.format(deTime);
            Date arTime = stringToTimeFormat.parse(ticket.getArTime());
            String arTimeAmPm = amPmFormat.format(arTime);

            mTvTime.setText(deTimeAmPm + " - " + arTimeAmPm);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mTvFromTo.setText(mDeCity.getAirPortCode() + "-" + mArCity.getAirPortCode() + ", ");
        mTvAirLineKr.setText(ticket.getAirLineKr());
        long hour = TimeUnit.MINUTES.toHours(ticket.getTimeGap());
        long minutes = TimeUnit.MINUTES.toMinutes(ticket.getTimeGap());
        String strDuration = "";
        if (hour > 0) {
            strDuration += hour + "시간 ";
        }
        if (minutes > 0) {
            strDuration += minutes + "분";
        }
        mTvDuration.setText(strDuration);
        String strPrice = NumberFormat.getCurrencyInstance(Locale.KOREA).format(mTotalPrice);
        mTvPrice.setText(strPrice);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.flights_detail_relative_save:
                tryPostAddSchedule();
                break;
        }
    }

    private void tryPostAddSchedule() {
        FlightsDetailService flightsDetailService = new FlightsDetailService(this);
        flightsDetailService.postAddSchedule(mTicket.getFlightId());
    }

    @Override
    public void postAddScheduleSuccess() {
        showCustomToast("저장 완료");
        mIvSave.setImageDrawable(getDrawable(R.drawable.ic_heart_fill));
    }

    @Override
    public void postAddScheduleFailure(String message) {
        showCustomToast("저장 실패");
    }
}
