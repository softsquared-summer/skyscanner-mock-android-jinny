package com.jeahn.skyscanner.src.flightsSearch;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.jeahn.skyscanner.R;

public class SeatDialog {
    private Context mContext;

    private RadioButton mRadioEconomy, mRadioPremiumEconomy, mRadioBusiness, mRadioFirstClass;
    private Spinner mSpinnerAdult, mSpinnerInfant, mSpinnerChild;

    private SeatDialogListener mSeatDialogListener;


    public SeatDialog(Context context) {
        mContext = context;
    }

    public void showDialog(int cabinClass, int adultCount, int infantCount, int childCount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.seat_dialog, null);
        mSpinnerAdult = view.findViewById(R.id.seat_dialog_spinner_adult);
        mSpinnerInfant = view.findViewById(R.id.seat_dialog_spinner_infant);
        mSpinnerChild= view.findViewById(R.id.seat_dialog_spinner_child);
        mRadioEconomy = view.findViewById(R.id.seat_dialog_radio_economy);
        mRadioPremiumEconomy = view.findViewById(R.id.seat_dialog_radio_premium_economy);
        mRadioBusiness = view.findViewById(R.id.seat_dialog_radio_business);
        mRadioFirstClass = view.findViewById(R.id.seat_dialog_radio_first_class);

        setSpinner(adultCount, infantCount, childCount);

        //좌석 등급 선택
        switch (cabinClass) {
            case 0:
                mRadioEconomy.setChecked(true);
                break;
            case 1:
                mRadioPremiumEconomy.setChecked(true);
                break;
            case 2:
                mRadioBusiness.setChecked(true);
                break;
            case 3:
                mRadioFirstClass.setChecked(true);
                break;
        }
        builder.setView(view);
        AlertDialog dialog = builder.setPositiveButton(mContext.getString(R.string.seat_dialog_apply), (dialogInterface, i) -> {
            int cablinClass = 0;
            if (mRadioEconomy.isChecked()) {
                cablinClass = 0;
            } else if (mRadioPremiumEconomy.isChecked()) {
                cablinClass = 1;
            } else if (mRadioBusiness.isChecked()) {
                cablinClass = 2;
            } else if (mRadioFirstClass.isChecked()) {
                cablinClass = 3;
            }

            mSeatDialogListener.onApplyButtonClick(cablinClass,
                    Integer.parseInt(mSpinnerAdult.getSelectedItem().toString()),
                    Integer.parseInt(mSpinnerInfant.getSelectedItem().toString()),
                    Integer.parseInt(mSpinnerChild.getSelectedItem().toString()));
        }).setNegativeButton(mContext.getString(R.string.seat_dialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create();
        dialog.setOnShowListener(dialogInterface -> {
            //라디오 버튼 컬러 설정
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(mContext.getResources().getColor(R.color.skyscannerColor));
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(mContext.getResources().getColor(R.color.skyscannerColor));
        });
        dialog.show();
    }

    private void setSpinner(int adultCount, int infantCount, int childCount) {
        ArrayAdapter<CharSequence> adultAdapter= ArrayAdapter.createFromResource(mContext, R.array.adult_count, android.R.layout.simple_spinner_dropdown_item);
        mSpinnerAdult.setAdapter(adultAdapter);
        mSpinnerAdult.setSelection(adultCount - 1);

        ArrayAdapter<CharSequence> infantAdapter= ArrayAdapter.createFromResource(mContext, R.array.infant_count, android.R.layout.simple_spinner_dropdown_item);
        mSpinnerInfant.setAdapter(infantAdapter);
        mSpinnerInfant.setSelection(infantCount);

        ArrayAdapter<CharSequence> childAdapter= ArrayAdapter.createFromResource(mContext, R.array.child_count, android.R.layout.simple_spinner_dropdown_item);
        mSpinnerChild.setAdapter(childAdapter);
        mSpinnerChild.setSelection(childCount);
    }

    public void setDialogListener(SeatDialogListener seatDialogListener) {
        mSeatDialogListener = seatDialogListener;
    }

    interface SeatDialogListener {
        void onApplyButtonClick(int cabinClass, int adultCount, int infantCount, int childCount);
    }
}
