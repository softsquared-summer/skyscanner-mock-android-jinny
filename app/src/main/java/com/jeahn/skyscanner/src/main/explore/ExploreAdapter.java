package com.jeahn.skyscanner.src.main.explore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.main.models.Product;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    List<Product> mItems;

    public ExploreAdapter(List<Product> mItems) {
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore_rv, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item = mItems.get(position);
        holder.tvItemName.setText(item.getName());
        holder.ivPicture.setImageResource(item.getPicture());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvItemName;
        ImageView ivPicture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.explore_tv_item_name);
            ivPicture = itemView.findViewById(R.id.explore_iv_picture);
        }
    }
}
