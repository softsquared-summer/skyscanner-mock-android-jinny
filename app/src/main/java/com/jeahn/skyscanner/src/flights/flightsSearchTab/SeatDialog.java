package com.jeahn.skyscanner.src.flights.flightsSearchTab;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jeahn.skyscanner.R;

public class SeatDialog {
    private Context mContext;

    private  RadioButton mRadioEconomy, mRadioPremiumEconomy, mRadioBusiness, mRadioFirstClass;

    private SeatDialogListener mSeatDialogListener;


    public SeatDialog(Context context) {
        mContext = context;
    }

    public void showDialog(int cabinClass, TextView tvCabinClass){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.seat_dialog, null);
        mRadioEconomy = view.findViewById(R.id.seat_dialog_radio_economy);
        mRadioPremiumEconomy = view.findViewById(R.id.seat_dialog_radio_premium_economy);
        mRadioBusiness = view.findViewById(R.id.seat_dialog_radio_business);
        mRadioFirstClass = view.findViewById(R.id.seat_dialog_radio_first_class);
        //좌석 등급 선택
        switch (cabinClass){
            case 0 :
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
        AlertDialog dialog = builder.setPositiveButton(mContext.getString(R.string.seat_dialog_apply), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(mRadioEconomy.isChecked()){
                    mSeatDialogListener.onApplyButtonClick(0);
                    tvCabinClass.setText(mContext.getText(R.string.seat_dialog_economy));
                }else if(mRadioPremiumEconomy.isChecked()){
                    mSeatDialogListener.onApplyButtonClick(1);
                    tvCabinClass.setText(mContext.getText(R.string.seat_dialog_premium_economy));
                }else if(mRadioBusiness.isChecked()){
                    mSeatDialogListener.onApplyButtonClick(2);
                    tvCabinClass.setText(mContext.getText(R.string.seat_dialog_business));
                }else if(mRadioFirstClass.isChecked()){
                    mSeatDialogListener.onApplyButtonClick(3);
                    tvCabinClass.setText(mContext.getText(R.string.seat_dialog_first_class));
                }
            }
        }).setNegativeButton(mContext.getString(R.string.seat_dialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {


                //라디오 버튼 컬러 설정
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(mContext.getResources().getColor(R.color.skyscannerColor));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(mContext.getResources().getColor(R.color.skyscannerColor));
            }
        });
        dialog.show();
    }

    public void setDialogListener(SeatDialogListener seatDialogListener){
        mSeatDialogListener = seatDialogListener;
    }

    interface SeatDialogListener{
        void onApplyButtonClick(int cabinClass);
    }
}
