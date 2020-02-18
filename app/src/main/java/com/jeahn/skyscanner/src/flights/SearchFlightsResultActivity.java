package com.jeahn.skyscanner.src.flights;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

import java.util.ArrayList;

public class SearchFlightsResultActivity extends BaseActivity {
    private static int SEARCH_FLIGHTS = 1;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private SearchFlightsResultAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights_result);

        mToolbar = findViewById(R.id.search_flights_result_toolbar);
        mRecyclerView = findViewById(R.id.search_flights_result_recycler);

        setSupportActionBar(mToolbar);
        mToolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> items = new ArrayList<>();
        items.add("test");
        items.add("test");
        items.add("test");
        items.add("test");
        items.add("test");
        items.add("test");
        items.add("test");
        items.add("test");
        items.add("test");
        items.add("test");
        items.add("test");

        mAdapter = new SearchFlightsResultAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setFocusable(false);

        Intent intent = new Intent(SearchFlightsResultActivity.this, SearchFlightsActivity.class);
        startActivityForResult(intent, SEARCH_FLIGHTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == SEARCH_FLIGHTS){
            //검색창에서 <- 누르면 결과창도 함께 꺼짐
            if(resultCode == Activity.RESULT_CANCELED){
                finish();
            }
        }
    }

    public void searchOnClick(View view){
        //검색창 열기
        Intent intent = new Intent(SearchFlightsResultActivity.this, SearchFlightsActivity.class);
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
}
