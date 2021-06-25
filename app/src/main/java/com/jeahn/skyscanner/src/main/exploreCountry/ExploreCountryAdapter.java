package com.jeahn.skyscanner.src.main.exploreCountry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.main.exploreCountry.models.Country;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ExploreCountryAdapter extends RecyclerView.Adapter<ExploreCountryAdapter.ViewHolder>  {
    private List<Country> mCountryList;
    private BaseActivity mActivity;

    private OnItemClickListener mListener = null;

    public ExploreCountryAdapter(List<Country> mCountryList, BaseActivity activity) {
        this.mCountryList = mCountryList;
        mActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore_country, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = mCountryList.get(position);

        Glide.with(holder.ivCountry.getContext()).load(country.getImgUrl()).into(holder.ivCountry);
        holder.tvCountry.setText(country.getCountry());
        String strPrice = NumberFormat.getCurrencyInstance(Locale.KOREA).format(country.getMinPrice());
        holder.tvPrice.setText(String.format("%s부터", strPrice));

        holder.view.setOnClickListener(view -> {
            mListener.onItemClick(view, position);
        });
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCountry, tvPrice;
        private ImageView ivCountry;
        private View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.explore_country_tv_country);
            ivCountry = itemView.findViewById(R.id.explore_country_iv_country);
            tvPrice = itemView.findViewById(R.id.explore_country_tv_price);
            view = itemView;
        }
    }

    public void setOnItemClickeListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }
}
