package com.jeahn.skyscanner.src.flights.flightsSearchTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.models.City;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {

    private List<City> mCityList;

    public CityAdapter(Context context, List<City> cityList){
        super(context, 0, cityList);
        mCityList = new ArrayList<>(cityList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return cityFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_city, parent, false);
        }

        TextView tvCityName = convertView.findViewById(R.id.city_tv_city_name);
        TextView tvCountry = convertView.findViewById(R.id.city_tv_country);

        City city = getItem(position);

        if(city != null){
            tvCityName.setText(city.getCityNameKr() +" (" + city.getAirPortCode() + ")");
            tvCountry.setText(city.getCountry());
        }

        return convertView;
    }

    private Filter cityFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<City> suggestions = new ArrayList<>();

            if(charSequence != null && charSequence.length() > 0){
                String filterPattern = charSequence.toString().trim();

                for(City item : mCityList){
                    if(item.getCityNameKr().contains(filterPattern)
                    || item.getAirPortCode().contains(filterPattern)
                    || item.getCountry().contains(filterPattern)){
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            if(filterResults.count > 0){
                addAll((List)filterResults.values);
            }
            notifyDataSetChanged();
        }
    };
}
