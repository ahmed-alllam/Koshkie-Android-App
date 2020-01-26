/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koshkie.koshkieApp.R;
import com.koshkie.koshkieApp.models.ShopModel;

import java.util.ArrayList;
import java.util.List;

public class ShopsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SHOP = 0;
    private static final int VIEW_TYPE_EMPTY = 1;
    private List<ShopModel> shopsList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (VIEW_TYPE_SHOP == viewType) {
            return new ShopViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.shop_item, parent, false));
        }
        return new EmptyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.empty_shop, parent, false));
    }


    @Override
    public int getItemViewType(int position) {
        if (shopsList.size() == 0) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_SHOP;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (VIEW_TYPE_SHOP == getItemViewType(position)) {
            ((ShopViewHolder) holder).shopName.setText(shopsList.get(position).getName());
            ((ShopViewHolder) holder).shopRating.setText(shopsList.get(position).getRating()
                    + " (" + shopsList.get(position).getReviews_count() + ") ");
            // not complete missing photo download
        }
    }

    @Override
    public int getItemCount() {
        if (!shopsList.isEmpty()) {
            return shopsList.size();
        }

        return 8;
    }

    public void setList(List<ShopModel> shopsList) {
        this.shopsList = shopsList;
        notifyDataSetChanged();
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        private EmptyViewHolder(View v) {
            super(v);
        }
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {
        ImageView shopPhoto;
        TextView shopName, shopRating;

        ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            shopPhoto = itemView.findViewById(R.id.shopPhoto);
            shopName = itemView.findViewById(R.id.shopName);
            shopRating = itemView.findViewById(R.id.shopRating);
        }
    }
}
