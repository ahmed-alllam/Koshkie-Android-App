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
            ShopViewHolder ShopView = (ShopViewHolder) holder;

            ShopView.shopName.setText(shopsList.get(position).getName());

            ShopView.shopRating.setRating((float) shopsList.get(position).getRating());

            ShopView.shopRatingCount.setText("(" +
                    shopsList.get(position).getReviews_count() + ")");

            ShopView.shopTime.setText(put_time_to_prepare(shopsList.get(position).getTime_to_prepare()));

            ShopView.shopMinCharge.setText(shopsList.get(position).getMinimum_charge()
                    + " " + shopsList.get(position).getCurrency());

            ShopView.shopDeliveryFee.setText(shopsList.get(position).getDelivery_fee()
                    + " " + shopsList.get(position).getCurrency());

            ShopView.shopTags.setText(listToString(shopsList.get(position).getTags()));

            if (shopsList.get(position).isHas_offers()) {
                ShopView.hasOffers.setVisibility(View.VISIBLE);
                ShopView.view.setVisibility(View.VISIBLE);
            }

            Glide.with(context)
                    .load(ShopsClient.BASE_URL + shopsList.get(position).getProfile_photo())
                    .into(ShopView.shopPhoto);
            Glide.with(context)
                    .load(ShopsClient.BASE_URL + shopsList.get(position).getCover_photo())
                    .into(ShopView.shopCover);                                                      
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

    private String listToString(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String s : list) {
            if (list.indexOf(s) != 0) {
                result.append(" · ");
            }
            result.append(s);
        }
        return result.toString();
    }

    private String put_time_to_prepare(int time) {
        return (time - 5) + "-" + (time + 5);
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        private EmptyViewHolder(View v) {
            super(v);
        }
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {
        TextView shopName, shopRatingCount, shopTime,
                shopMinCharge, shopDeliveryFee, hasOffers, shopTags;
        ImageView shopPhoto, shopCover;
        RatingBar shopRating;
        View view;

        ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            shopPhoto = itemView.findViewById(R.id.shopPhoto);
            shopCover = itemView.findViewById(R.id.shopCover);
            shopName = itemView.findViewById(R.id.shopName);
            shopRating = itemView.findViewById(R.id.shopRating);
            shopRatingCount = itemView.findViewById(R.id.shopRatingCount);
            shopTime = itemView.findViewById(R.id.shopTime);
            shopMinCharge = itemView.findViewById(R.id.shopMinCharge);
            shopDeliveryFee = itemView.findViewById(R.id.shopDeliveryFee);
            hasOffers = itemView.findViewById(R.id.hasOffers);
            view = itemView.findViewById(R.id.view);
            shopTags = itemView.findViewById(R.id.shopTags);
        }
    }
}
