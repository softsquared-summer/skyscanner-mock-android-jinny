package com.jeahn.skyscanner.src.flights;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.flights.interfaces.FlightsActivityView;
import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResult;
import com.jeahn.skyscanner.src.flights.models.OneFligthResult;

public class FlightsActivity extends BaseActivity implements View.OnClickListener, FlightsActivityView {
    private static int START_SEARCH_FLIGHTS_ONE_WAY = 100;
    private static int SEARCH_FLIGHTS = 1;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView, mDailyRecyclerView;
    private FlightsAdapter mAdapter;
    private FlightsDailyAdapter mDailyAdapter;

    private TextView mTvFromTo, mTvCount, mTvDailyCount, mTvDailyTimeGapAvg, mTvMore;
    private ImageView mIvMore;
    private RelativeLayout mRelativeMore;
    private CardView mCardDaily;
    private NestedScrollView mNestedScroll;

    private String mStrDeAirPortCode, mStrArAirPortCode;
    private int mIntCabinClass, mIntDailyCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        mToolbar = findViewById(R.id.flights_toolbar);
        mRecyclerView = findViewById(R.id.flights_recycler);
        mDailyRecyclerView = findViewById(R.id.flights_daily_recycler);
        mTvFromTo = findViewById(R.id.flights_tv_from_to);
        mTvCount = findViewById(R.id.flights_tv_count);
        mTvDailyCount = findViewById(R.id.flights_tv_daily_count);
        mTvDailyTimeGapAvg = findViewById(R.id.flights_tv_daily_time_avg);
        mTvMore = findViewById(R.id.flights_daily_tv_more);
        mRelativeMore = findViewById(R.id.flights_daily_relative_more);
        mCardDaily = findViewById(R.id.flights_daily_card);
        mNestedScroll = findViewById(R.id.flights_nested_scroll);
        mIvMore = findViewById(R.id.flights_daily_iv_more);

        mRelativeMore.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        mToolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setFocusable(false);

        mDailyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDailyRecyclerView.setFocusable(false);

        Intent intent = new Intent(FlightsActivity.this, FlightsSearchActivity.class);
        intent.putExtra("isFirstSearch", true);
        startActivityForResult(intent, SEARCH_FLIGHTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == SEARCH_FLIGHTS){
            //검색창에서 <- 누르면 결과창도 함께 꺼짐
            if(resultCode == Activity.RESULT_FIRST_USER){
                finish();
            }
            else if(resultCode == START_SEARCH_FLIGHTS_ONE_WAY){ //편도 검색 시작
                mStrDeAirPortCode = data.getStringExtra("deAirPortCode");
                mStrArAirPortCode = data.getStringExtra("arAirPortCode");
                mIntCabinClass = data.getIntExtra("cabinClass", 0);
                mTvFromTo.setText(mStrDeAirPortCode + " - " + mStrArAirPortCode);
                tryGetOneFlight(mStrDeAirPortCode, mStrArAirPortCode, "2020-02-12", mIntCabinClass, "price");
                tryGetDailyOneFlight(mStrDeAirPortCode, mStrArAirPortCode, "2020-02-12", mIntCabinClass);
            }
        }
    }

    private void tryGetOneFlight(String deAirPortCode, String arAirPortCode, String deDate, int seatCode, String sortBy) {
        final FlightsService flightsService = new FlightsService(this);
        flightsService.getOneFlight(deAirPortCode, arAirPortCode, deDate, seatCode, sortBy);
    }

    private void tryGetDailyOneFlight(String deAirPortCode, String arAirPortCode, String deDate, int seatCode) {
        final FlightsService flightsService = new FlightsService(this);
        flightsService.getDailyOneFlight(deAirPortCode, arAirPortCode, deDate, seatCode);
    }

    public void searchOnClick(View view){
        //검색창 열기
        Intent intent = new Intent(FlightsActivity.this, FlightsSearchActivity.class);
        intent.putExtra("isFirstSearch", false);
        startActivityForResult(intent, SEARCH_FLIGHTS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //툴바의 <- 버튼 누르면 끝내기
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void validateSuccess(Object data) {
        if(data instanceof OneFligthResult){
            OneFligthResult result = (OneFligthResult) data;
            mTvCount.setText(String.format(getString(R.string.flights_count), result.getTotalTicketCount()));
            mAdapter = new FlightsAdapter(result.getTicketList(), mStrDeAirPortCode, mStrArAirPortCode);
            mRecyclerView.setAdapter(mAdapter);
        }else if(data instanceof DailyOneFlightResult){
            DailyOneFlightResult result = (DailyOneFlightResult)data;
            mIntDailyCount = result.getAirLineList().size();
            if(mIntDailyCount == 0){
                mCardDaily.setVisibility(View.GONE);
            }else{
                mTvDailyCount.setText(String.format(getString(R.string.flights_daily_count), result.getTotalTicketCount()));
                mTvDailyTimeGapAvg.setText(String.format(getString(R.string.flights_daily_time_avg), result.getTimeGapAvg()));

                mDailyAdapter = new FlightsDailyAdapter(result.getAirLineList());
                mDailyRecyclerView.setAdapter(mDailyAdapter);

                if(mIntDailyCount > 3){
                    //더보기 버튼
                    mDailyAdapter.setItemCount(3);
                    mTvMore.setText(String.format("%d개의 항공사 더 보기", mIntDailyCount - 3));
                }else{
                    mRelativeMore.setVisibility(View.GONE);
                }
            }
        }

    }

    @Override
    public void validateFailure(String message) {
        Toast.makeText(getApplicationContext(),"검색 실패",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.flights_daily_relative_more:
                if(mDailyAdapter.getItemCount() == 3){
                    mDailyAdapter.setItemCount(mIntDailyCount);
                    mTvMore.setText("항공사 숨기기");
                    mIvMore.setImageResource(R.drawable.ic_up_arrow);
                }else{
                    mDailyAdapter.setItemCount(3);
                    mTvMore.setText(String.format("%d개의 항공사 더 보기", mIntDailyCount - 3));
                    mIvMore.setImageResource(R.drawable.ic_down_arrow);
                }
                mDailyAdapter.notifyDataSetChanged();
                mNestedScroll.smoothScrollTo(0, 0);
                break;
        }
    }
}
