package com.jeahn.skyscanner.src.flights;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.jeahn.skyscanner.R;

import java.util.ArrayList;

public class InputCityDialog extends DialogFragment {

    boolean mIsOrigin;

    AutoCompleteTextView mAutoCompleteTextView;

    public InputCityDialog(boolean isOrigin) {
        mIsOrigin = isOrigin;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.FullWidth_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_input_city, null);

        mAutoCompleteTextView = view.findViewById(R.id.input_city_autoCompleteTextView);

        if(!mIsOrigin){
            mAutoCompleteTextView.setHint("도착지");
            mAutoCompleteTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_landing, 0, 0, 0);
        }

        Toolbar toolbar = view.findViewById(R.id.input_city_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        ArrayList<String> list = new ArrayList<>();
        list.add("서울");
        list.add("서울2");
        list.add("서울3");
        list.add("울산");
        list.add("부산");
        list.add("제주");

        mAutoCompleteTextView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, list));

        dialog.setContentView(view);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                dismiss();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
