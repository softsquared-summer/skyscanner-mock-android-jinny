package com.jeahn.skyscanner.src.flights;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.flights.interfaces.FlightsActivityView;
import com.jeahn.skyscanner.src.flights.models.City;
import com.jeahn.skyscanner.src.flights.models.OneFligthResult;

import java.util.ArrayList;
import java.util.List;

public class SearchFlightsResultActivity extends BaseActivity implements FlightsActivityView {
    private static int START_SEARCH_FLIGHTS_ONE_WAY = 100;
    private static int SEARCH_FLIGHTS = 1;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private SearchFlightsResultAdapter mAdapter;

    private TextView mTvCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights_result);

        mToolbar = findViewById(R.id.search_flights_result_toolbar);
        mRecyclerView = findViewById(R.id.search_flights_result_recycler);
        mTvCount = findViewById(R.id.search_flights_result_count);

        setSupportActionBar(mToolbar);
        mToolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setFocusable(false);

        Intent intent = new Intent(SearchFlightsResultActivity.this, SearchFlightsActivity.class);
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
                tryGetOneFlight("GMP", "CJU", "2020-02-12", 0, "price");
            }
        }
    }

    private void tryGetOneFlight(String deAirPortCode, String arAirPortCode, String deDate, int seatCode, String sortBy) {
        final FlightsService flightsService = new FlightsService(this);
        flightsService.getOneFlight(deAirPortCode, arAirPortCode, deDate, seatCode, sortBy);
    }

    public void searchOnClick(View view){
        //검색창 열기
        Intent intent = new Intent(SearchFlightsResultActivity.this, SearchFlightsActivity.class);
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
    public void validateSuccess(List<City> cityList) {

    }

    @Override
    public void validateSuccess(OneFligthResult result) {
        mTvCount.setText(result.getTotalTicketCount() + "개의 결과");
        mAdapter = new SearchFlightsResultAdapter(result.getTicketList());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void validateFailure(String message) {
        Toast.makeText(getApplicationContext(),"검색 실패",Toast.LENGTH_SHORT).show();
    }
}
