package com.jeahn.skyscanner.src.preferences;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

public class PreferencesActivity extends BaseActivity implements NestedScrollView.OnScrollChangeListener {
    private static int LOGOUT_SUCCESS = 300;

    private Toolbar mToolbar;
    private View mToolbarSeparator;
    private TextView mTvLogout, mTvDeleteAccount;
    private NestedScrollView mScrollView;
    private LinearLayout mLinearAccountManagements;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        
        mToolbar = findViewById(R.id.preferences_toolbar);
        mToolbarSeparator = findViewById(R.id.preferences_toolbar_separator);
        mTvLogout = findViewById(R.id.preferences_tv_logout);
        mTvDeleteAccount = findViewById(R.id.preferences_tv_delete_account);
        mScrollView = findViewById(R.id.preferences_scroll);
        mLinearAccountManagements = findViewById(R.id.preferences_linear_account_managements);

        mScrollView.setOnScrollChangeListener(this);

        setToolbar();

        Intent intent = getIntent();
        if(intent != null){
            if(intent.getBooleanExtra("isLogin", false)){
                mTvLogout.setOnClickListener(view -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(getString(R.string.preferences_ask_logout));
                    builder.setPositiveButton(getString(R.string.preferences_logout_button), (dialogInterface, i) -> {
                        setResult(LOGOUT_SUCCESS);
                        finish();
                    });
                    builder.setNegativeButton(getString(R.string.preferences_cancel_button), (dialogInterface, i) -> {

                    });
                    AlertDialog dialog = builder.create();

                    dialog.setOnShowListener(dialogInterface -> {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.skyscannerColor));
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.skyscannerColor));
                    });

                    dialog.show();

                    TextView textView = dialog.findViewById(android.R.id.message);
                    textView.setTextSize(20);
                });

                mTvDeleteAccount.setOnClickListener(view -> {

                });
            }else{
                mLinearAccountManagements.setVisibility(View.GONE);
            }
        }
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.mainTextColor), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY < 40) {
            mToolbar.setTitle("");
            mToolbarSeparator.setVisibility(View.INVISIBLE);
        } else {
            mToolbar.setTitle(getString(R.string.preferences_title));
            mToolbarSeparator.setVisibility(View.VISIBLE);
        }
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
}
