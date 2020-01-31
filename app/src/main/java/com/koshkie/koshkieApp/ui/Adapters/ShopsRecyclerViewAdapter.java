/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koshkie.koshkieApp.R;
import com.koshkie.koshkieApp.data.ShopsClient;
import com.koshkie.koshkieApp.models.ShopModel;

import java.util.ArrayList;
import java.util.List;

public class ShopsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SHOP = 0;
    private static final int VIEW_TYPE_EMPTY = 1;
    private Context context;
    private List<ShopModel> shopsList = new ArrayList<>();

    public ShopsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

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
            ((ShopViewHolder) holder).shopRating.setRating((float) shopsList.get(position).getRating());
            ((ShopViewHolder) holder).shopRatingCount.setText("(" +
                    shopsList.get(position).getReviews_count() + ")");
            ((ShopViewHolder) holder).shopVat.setText(shopsList.get(position).getVat() + "%");
            ((ShopViewHolder) holder).shopMinCharge.setText(shopsList.get(position).getMinimum_charge()
                    + " " + shopsList.get(position).getCurrency());
            ((ShopViewHolder) holder).shopDeliveryFee.setText(shopsList.get(position).getDelivery_fee()
                    + " " + shopsList.get(position).getCurrency());

            Glide.with(context)
                    .load(ShopsClient.BASE_URL + shopsList.get(position).getProfile_photo())
                    .into(((ShopViewHolder) holder).shopPhoto);
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
        TextView shopName, shopRatingCount, shopVat, shopMinCharge, shopDeliveryFee;
        ImageView shopPhoto;
        RatingBar shopRating;

        ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            shopPhoto = itemView.findViewById(R.id.shopPhoto);
            shopName = itemView.findViewById(R.id.shopName);
            shopRating = itemView.findViewById(R.id.shopRating);
            shopRatingCount = itemView.findViewById(R.id.shopRatingCount);
            shopVat = itemView.findViewById(R.id.shopVat);
            shopMinCharge = itemView.findViewById(R.id.shopMinCharge);
            shopDeliveryFee = itemView.findViewById(R.id.shopDeliveryFee);
        }
    }
}
