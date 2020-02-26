package com.jeahn.skyscanner.src.flights;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

    private String mStrDeAirPortCode, mStrArAirPortCode;
    private int mSearchType, mIntCabinClass, mIntDailyCount;

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
        if (requestCode == SEARCH_FLIGHTS) {
            if (resultCode == Activity.RESULT_FIRST_USER) { //검색창에서 <- 누르면 결과창도 함께 꺼짐
                finish();
            } else if (resultCode == SEARCH_FLIGHTS_ONE_WAY) { //편도 검색 시작
                mSearchType = SEARCH_FLIGHTS_ONE_WAY;
                mStrDeAirPortCode = data.getStringExtra("deAirPortCode");
                mStrArAirPortCode = data.getStringExtra("arAirPortCode");
                mIntCabinClass = data.getIntExtra("cabinClass", 0);
                mTvFromTo.setText(mStrDeAirPortCode + " - " + mStrArAirPortCode);
                tryGetOneFlight(mStrDeAirPortCode, mStrArAirPortCode, "2020-02-12", mIntCabinClass, "price");
                tryGetDailyOneFlight(mStrDeAirPortCode, mStrArAirPortCode, "2020-02-12", mIntCabinClass);
            } else if (resultCode == SEARCH_FLIGHTS_ROUND_TRIP){ //왕복 검색 시작
                mSearchType = SEARCH_FLIGHTS_ROUND_TRIP;
                mStrDeAirPortCode = data.getStringExtra("deAirPortCode");
                mStrArAirPortCode = data.getStringExtra("arAirPortCode");
                mIntCabinClass = data.getIntExtra("cabinClass", 0);
                mTvFromTo.setText(mStrDeAirPortCode + " - " + mStrArAirPortCode);
                tryGetRoundFlight(mStrDeAirPortCode, mStrArAirPortCode, "2020-02-12","2020-02-12", mIntCabinClass, "price");
                tryGetDailyRoundFlight(mStrDeAirPortCode, mStrArAirPortCode, "2020-02-12","2020-02-12", mIntCabinClass);
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
    }

    @Override
    public void getDailyOneFlightFailure(String message) {
        Toast.makeText(getApplicationContext(), "일일 편도 항공권 검색 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getOneFlightSuccess(OneFlightResult result) {
        mTvCount.setText(String.format(getString(R.string.flights_count), result.getTotalTicketCount()));
        mAdapter = new OneFlightAdapter(result.getTicketList(), mStrDeAirPortCode, mStrArAirPortCode);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getOneFlightFailure(String message) {
        Toast.makeText(getApplicationContext(), "편도 항공권 검색 실패", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void getDailyRoundFlightFailure(String message) {
        Toast.makeText(getApplicationContext(), "일일 왕복 항공권 검색 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getRoundFlightSuccess(RoundFlightResult result) {
        mTvCount.setText(String.format(getString(R.string.flights_count), result.getTotalTicketCount()));
        RoundFlightAdapter roundFlightAdapter = new RoundFlightAdapter(result.getTicketList(), mStrDeAirPortCode, mStrArAirPortCode);
        mRecyclerView.setAdapter(roundFlightAdapter);
    }

    @Override
    public void getRoundFlightFailure(String message) {
        Toast.makeText(getApplicationContext(), "왕복 항공권 검색 실패", Toast.LENGTH_SHORT).show();
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
