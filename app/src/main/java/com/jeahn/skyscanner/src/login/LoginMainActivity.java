package com.jeahn.skyscanner.src.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

import java.util.regex.Pattern;

public class LoginMainActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private TextView mTvTos;
    private Button mBtnEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        mToolbar = findViewById(R.id.login_toolbar);
        mTvTos = findViewById(R.id.login_tv_tos);
        mBtnEmail = findViewById(R.id.login_btn_email);

        mBtnEmail.setOnClickListener(this);

        setToolbar();
        setTosText();
    }

    //약관 링크
    private void setTosText() {
        Linkify.TransformFilter transform = (matcher, s) -> "";

        Pattern pattern1 = Pattern.compile(getString(R.string.login_tos_pattern_1));
        Pattern pattern2 = Pattern.compile(getString(R.string.login_tos_pattern_2));

        Linkify.addLinks(mTvTos, pattern1, "https://www.skyscanner.co.kr/media/%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%95%BD%EA%B4%80", null, transform);
        Linkify.addLinks(mTvTos, pattern2, "https://www.skyscanner.co.kr/media/privacy-policy?previousCultureSource=COOKIE&redirectedFrom=www.skyscanner.net", null, transform);

        //밑줄 제거
        Spannable s = new SpannableString(mTvTos.getText());
        for (URLSpan u : s.getSpans(0, s.length(), URLSpan.class)) {
            s.setSpan(new UnderlineSpan() {
                public void updateDrawState(TextPaint tp) {
                    tp.setUnderlineText(false);
                }
            }, s.getSpanStart(u), s.getSpanEnd(u), 0);
        }
        mTvTos.setText(s);

        mTvTos.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_email:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
