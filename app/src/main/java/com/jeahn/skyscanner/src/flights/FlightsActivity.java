package com.jeahn.skyscanner.src.flights;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.jeahn.skyscanner.src.city.models.City;
import com.jeahn.skyscanner.src.flightsSearch.FlightsSearchActivity;
import com.jeahn.skyscanner.src.flights.interfaces.FlightsActivityView;
import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResult;
import com.jeahn.skyscanner.src.flights.models.DailyRoundFlightResult;
import com.jeahn.skyscanner.src.flights.models.OneFlightResult;
import com.jeahn.skyscanner.src.flights.models.RoundFlightResult;

import java.util.concurrent.TimeUnit;

public class FlightsActivity extends BaseActivity implements View.OnClickListener, FlightsActivityView {
    private static int SEARCH_FLIGHTS_ONE_WAY = 100;
    private static int SEARCH_FLIGHTS_ROUND_TRIP = 200;

    private static int SEARCH_FLIGHTS = 1;
    private static int FLIGHTS_DAILY_SHOW_COUNT = 3;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView, mDailyRecyclerView;
    private OneFlightAdapter mAdapter;
    private DailyOneFlightAdapter mDailyOneFlightAdapter;
    private DailyRoundFlightAdapter mDailyRoundFlightAdapter;

    private TextView mTvFromTo, mTvCount, mTvDailyCount, mTvDailyTimeGapAvg, mTvMore;
    private ImageView mIvMore;
    private RelativeLayout mRelativeMore;
    private CardView mCardDaily;
    private NestedScrollView mNestedScroll;
    private ProgressBar mProgressBar;

    private int mSearchType, mIntCabinClass, mIntDailyCount, mProgress, mAdultCount, mInfantCount, mChildCount;
    private City mDeCity, mArCity;
    private Thread mProgressThread;

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
        mProgressBar = findViewById(R.id.flights_progress);

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
        if (requestCode == SEARCH_FLIGHTS) {
            if (resultCode == Activity.RESULT_FIRST_USER) { //검색창에서 <- 누르면 결과창도 함께 꺼짐
                finish();
                return;
            } else if (resultCode == SEARCH_FLIGHTS_ONE_WAY) { //편도 검색 시작
                mSearchType = SEARCH_FLIGHTS_ONE_WAY;
                mDeCity = data.getParcelableExtra("deCity");
                mArCity = data.getParcelableExtra("arCity");
                mIntCabinClass = data.getIntExtra("cabinClass", 0);
                mAdultCount = data.getIntExtra("adultCount", 1);
                mInfantCount = data.getIntExtra("infantCount", 0);
                mChildCount = data.getIntExtra("childCount", 0);
                mTvFromTo.setText(mDeCity.getAirPortCode() + " - " + mArCity.getAirPortCode());
                startProgress();
                tryGetOneFlight(mDeCity.getAirPortCode(), mArCity.getAirPortCode(),"2020-02-12", mIntCabinClass, "price");
                tryGetDailyOneFlight(mDeCity.getAirPortCode(), mArCity.getAirPortCode(), "2020-02-12", mIntCabinClass);
            } else if (resultCode == SEARCH_FLIGHTS_ROUND_TRIP){ //왕복 검색 시작
                mSearchType = SEARCH_FLIGHTS_ROUND_TRIP;
                mDeCity = data.getParcelableExtra("deCity");
                mArCity = data.getParcelableExtra("arCity");
                mIntCabinClass = data.getIntExtra("cabinClass", 0);
                mAdultCount = data.getIntExtra("adultCount", 1);
                mInfantCount = data.getIntExtra("infantCount", 0);
                mChildCount = data.getIntExtra("childCount", 0);
                mTvFromTo.setText(mDeCity.getAirPortCode() + " - " + mArCity.getAirPortCode());
                startProgress();
                tryGetRoundFlight(mDeCity.getAirPortCode(), mArCity.getAirPortCode(),"2020-02-12","2020-02-12", mIntCabinClass, "price");
                tryGetDailyRoundFlight(mDeCity.getAirPortCode(), mArCity.getAirPortCode(),"2020-02-12","2020-02-12", mIntCabinClass);
            }
        }
    }

    private void startProgress() {
        mProgress = 0;
        mProgressThread = new Thread(() -> {
            while(true){
                if(!Thread.currentThread().isInterrupted()){
                    mProgress += 1;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    mProgressBar.setProgress(mProgress);

                    if(mProgress > 1000){
                        Thread.currentThread().interrupt();
                        break;
                    }
                }else{
                    mProgressBar.setProgress(1000);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    mProgressBar.setProgress(0);
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        mProgressThread.start();
        Log.d("test", "start");
    }

    private void stopProgress(){
        mProgressThread.interrupt();
    }

    private void tryGetOneFlight(String deAirPortCode, String arAirPortCode, String deDate, int seatCode, String sortBy) {
        final FlightsService flightsService = new FlightsService(this);
        flightsService.getOneFlight(deAirPortCode, arAirPortCode, deDate, seatCode, sortBy);
    }

    private void tryGetDailyOneFlight(String deAirPortCode, String arAirPortCode, String deDate, int seatCode) {
        final FlightsService flightsService = new FlightsService(this);
        flightsService.getDailyOneFlight(deAirPortCode, arAirPortCode, deDate, seatCode);
    }

    private void tryGetRoundFlight(String deAirPortCode, String arAirPortCode, String deDate, String arDate, int seatCode, String sortBy) {
        final FlightsService flightsService = new FlightsService(this);
        flightsService.getRoundFlight(deAirPortCode, arAirPortCode, deDate, arDate, seatCode, sortBy);
    }

    private void tryGetDailyRoundFlight(String deAirPortCode, String arAirPortCode, String deDate, String arDate, int seatCode) {
        final FlightsService flightsService = new FlightsService(this);
        flightsService.getDailyRoundFlight(deAirPortCode, arAirPortCode, deDate, arDate, seatCode);
    }

    public void searchOnClick(View view) {
        //검색창 열기
        Intent intent = new Intent(FlightsActivity.this, FlightsSearchActivity.class);
        intent.putExtra("isFirstSearch", false);
        startActivityForResult(intent, SEARCH_FLIGHTS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //툴바의 <- 버튼 누르면 끝내기
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getDailyOneFlightSuccess(DailyOneFlightResult result) {
        mIntDailyCount = result.getAirLineList().size();
        if (mIntDailyCount == 0) {
            mCardDaily.setVisibility(View.GONE);
        } else {
            if (result.getTotalTicketCount() > 10) {
                mTvDailyCount.setText(getString(R.string.flights_daily_count_more_10));

            } else {
                mTvDailyCount.setText(String.format(getString(R.string.flights_daily_count), result.getTotalTicketCount()));
            }
            long hour = TimeUnit.MINUTES.toHours(result.getTimeGapAvg());
            long minutes = TimeUnit.MINUTES.toMinutes(result.getTimeGapAvg());
            String strDuration = getString(R.string.flights_daily_time_avg);
            if (hour > 0) {
                strDuration += hour + getString(R.string.flights_hour);
            }
            if (minutes > 0) {
                strDuration += minutes + getString(R.string.flights_minutes);
            }
            mTvDailyTimeGapAvg.setText(strDuration);

            mDailyOneFlightAdapter = new DailyOneFlightAdapter(result.getAirLineList());
            mDailyRecyclerView.setAdapter(mDailyOneFlightAdapter);

            if (mIntDailyCount > FLIGHTS_DAILY_SHOW_COUNT) {
                //더보기 버튼
                mDailyOneFlightAdapter.setItemCount(FLIGHTS_DAILY_SHOW_COUNT);
                mTvMore.setText(String.format(getString(R.string.flights_daily_see_more), mIntDailyCount - FLIGHTS_DAILY_SHOW_COUNT));
            } else {
                mRelativeMore.setVisibility(View.GONE);
            }
        }
        stopProgress();
    }

    @Override
    public void getDailyOneFlightFailure(String message) {
        Toast.makeText(getApplicationContext(), "일일 편도 항공권 검색 실패", Toast.LENGTH_SHORT).show();
        stopProgress();
    }

    @Override
    public void getOneFlightSuccess(OneFlightResult result) {
        mTvCount.setText(String.format(getString(R.string.flights_count), result.getTotalTicketCount()));
        mAdapter = new OneFlightAdapter(result.getTicketList(), mDeCity, mArCity, mAdultCount, mInfantCount, mChildCount);
        mRecyclerView.setAdapter(mAdapter);
        stopProgress();
    }

    @Override
    public void getOneFlightFailure(String message) {
        Toast.makeText(getApplicationContext(), "편도 항공권 검색 실패", Toast.LENGTH_SHORT).show();
        stopProgress();
    }

    @Override
    public void getDailyRoundFlightSuccess(DailyRoundFlightResult result) {
        mIntDailyCount = result.getAirLineList().size();
        if (mIntDailyCount == 0) {
            mCardDaily.setVisibility(View.GONE);
        } else {
            if (result.getTotalTicketCount() > 10) {
                mTvDailyCount.setText(getString(R.string.flights_daily_count_more_10));

            } else {
                mTvDailyCount.setText(String.format(getString(R.string.flights_daily_count), result.getTotalTicketCount()));
            }
            long hour = TimeUnit.MINUTES.toHours(result.getTimeGapAvg());
            long minutes = TimeUnit.MINUTES.toMinutes(result.getTimeGapAvg());
            String strDuration = getString(R.string.flights_daily_time_avg);
            if (hour > 0) {
                strDuration += hour + getString(R.string.flights_hour);
            }
            if (minutes > 0) {
                strDuration += minutes + getString(R.string.flights_minutes);
            }
            mTvDailyTimeGapAvg.setText(strDuration);

            mDailyRoundFlightAdapter= new DailyRoundFlightAdapter(result.getAirLineList());
            mDailyRecyclerView.setAdapter(mDailyRoundFlightAdapter);

            if (mIntDailyCount > FLIGHTS_DAILY_SHOW_COUNT) {
                //더보기 버튼
                mDailyRoundFlightAdapter.setItemCount(FLIGHTS_DAILY_SHOW_COUNT);
                mTvMore.setText(String.format(getString(R.string.flights_daily_see_more), mIntDailyCount - FLIGHTS_DAILY_SHOW_COUNT));
            } else {
                mRelativeMore.setVisibility(View.GONE);
            }
        }
        //stopProgress();
    }

    @Override
    public void getDailyRoundFlightFailure(String message) {
        Toast.makeText(getApplicationContext(), "일일 왕복 항공권 검색 실패", Toast.LENGTH_SHORT).show();
        //stopProgress();
    }

    @Override
    public void getRoundFlightSuccess(RoundFlightResult result) {
        mTvCount.setText(String.format(getString(R.string.flights_count), result.getTotalTicketCount()));
        RoundFlightAdapter roundFlightAdapter = new RoundFlightAdapter(result.getTicketList(), mDeCity, mArCity);
        mRecyclerView.setAdapter(roundFlightAdapter);
        stopProgress();
    }

    @Override
    public void getRoundFlightFailure(String message) {
        Toast.makeText(getApplicationContext(), "왕복 항공권 검색 실패", Toast.LENGTH_SHORT).show();
        stopProgress();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flights_daily_relative_more:
                if(mSearchType == SEARCH_FLIGHTS_ONE_WAY){
                    if (mDailyOneFlightAdapter.getItemCount() == FLIGHTS_DAILY_SHOW_COUNT) {
                        mDailyOneFlightAdapter.setItemCount(mIntDailyCount);
                        mTvMore.setText(getString(R.string.flights_daily_hide));
                        mIvMore.setImageResource(R.drawable.ic_up_arrow);
                    } else {
                        mDailyOneFlightAdapter.setItemCount(FLIGHTS_DAILY_SHOW_COUNT);
                        mTvMore.setText(String.format(getString(R.string.flights_daily_see_more), mIntDailyCount - FLIGHTS_DAILY_SHOW_COUNT));
                        mIvMore.setImageResource(R.drawable.ic_down_arrow);
                    }
                    mDailyOneFlightAdapter.notifyDataSetChanged();
                }else if(mSearchType == SEARCH_FLIGHTS_ROUND_TRIP){
                    if (mDailyRoundFlightAdapter.getItemCount() == FLIGHTS_DAILY_SHOW_COUNT) {
                        mDailyRoundFlightAdapter.setItemCount(mIntDailyCount);
                        mTvMore.setText(getString(R.string.flights_daily_hide));
                        mIvMore.setImageResource(R.drawable.ic_up_arrow);
                    } else {
                        mDailyRoundFlightAdapter.setItemCount(FLIGHTS_DAILY_SHOW_COUNT);
                        mTvMore.setText(String.format(getString(R.string.flights_daily_see_more), mIntDailyCount - FLIGHTS_DAILY_SHOW_COUNT));
                        mIvMore.setImageResource(R.drawable.ic_down_arrow);
                    }
                    mDailyRoundFlightAdapter.notifyDataSetChanged();
                }

                mNestedScroll.smoothScrollTo(0, 0);
                break;
        }
    }
}
