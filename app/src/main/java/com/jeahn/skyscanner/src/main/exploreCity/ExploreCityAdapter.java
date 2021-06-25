package com.jeahn.skyscanner.src.main.exploreCity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.main.exploreCity.models.City;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ExploreCityAdapter extends RecyclerView.Adapter<ExploreCityAdapter.ViewHolder>  {
    private List<City> mCityList;

    private Random mRandom = new Random();

    public ExploreCityAdapter(List<City> mCityList) {
        this.mCityList = mCityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore_city, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = mCityList.get(position);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getRandomIntInRange(200, 140));
        holder.v.setLayoutParams(layoutParams);
        Glide.with(holder.ivCity.getContext()).load(city.getImgUrl()).into(holder.ivCity);
        holder.tvCityName.setText(city.getCityNameKr());
        String strPrice = NumberFormat.getCurrencyInstance(Locale.KOREA).format(city.getMinPrice());
        holder.tvPrice.setText(strPrice);
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    protected int getRandomIntInRange(int max, int min){
        return mRandom.nextInt(max - min)+min;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View v;
        TextView tvCityName, tvPrice;
        ImageView ivCity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            tvCityName = itemView.findViewById(R.id.explore_city_tv_name);
            tvPrice = itemView.findViewById(R.id.explore_city_tv_price);
            ivCity = itemView.findViewById(R.id.explore_city_iv_city);
        }
    }
}
