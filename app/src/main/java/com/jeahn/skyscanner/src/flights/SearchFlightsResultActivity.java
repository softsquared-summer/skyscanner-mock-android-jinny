package com.jeahn.skyscanner.src.flights;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

public class SearchFlightsResultActivity extends BaseActivity {
    private static int SEARCH_FLIGHTS = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights_result);

        Intent intent = new Intent(SearchFlightsResultActivity.this, SearchFlightsActivity.class);
        startActivityForResult(intent, SEARCH_FLIGHTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == SEARCH_FLIGHTS){
            if(resultCode == Activity.RESULT_CANCELED){
                finish();
            }
        }
    }
}
