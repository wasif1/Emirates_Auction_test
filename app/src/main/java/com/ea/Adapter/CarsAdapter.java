package com.ea.Adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ea.Components.Utils;
import com.ea.Model.Car;
import com.ea.R;

import java.util.ArrayList;

import cn.iwgang.countdownview.CountdownView;


public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {
    private Activity mContext;
    private ArrayList<Car> carModel;
    private static final int FIVE_MINUTES = 5 * 60 * 1000;

    public CarsAdapter(Activity mContext, ArrayList<Car> carModel) {
        this.mContext = mContext;
        this.carModel = carModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Car car = carModel.get(position);
        if (car != null) {

            Log.e("DATECHK", "date : " + Utils.getDate(car.getAuctionInfo().getEndDateEn()));
            if (!TextUtils.isEmpty(car.getImage())) {
                Glide.with(mContext)
                        .load(car.getImage().replace("[w]", "0").replace("[h]", "0"))
                        .into(holder.image);
            }

            holder.time_left.start(car.mAuctionInfo.getEndDate());
            holder.time_left.setOnCountdownIntervalListener(1000, new CountdownView.OnCountdownIntervalListener() {
                @Override
                public void onInterval(CountdownView cv, long remainTime) {
                    if (remainTime < FIVE_MINUTES) {
                        cv.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                    } else {
                        cv.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                    }
                }
            });

            if (car.getAuctionInfo() != null) {
                holder.car_price.setText("" + car.getAuctionInfo().getCurrentPrice());
                holder.lot_number.setText("" + car.getAuctionInfo().getLot());
                holder.bids.setText("" + car.getAuctionInfo().getBids());
            }

            if (car.isEn()) {

                holder.car_name.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                if (!TextUtils.isEmpty(car.getMakeEn()) && !TextUtils.isEmpty(car.getModelEn()) && !TextUtils.isEmpty(car.getBodyEn())) {
                    holder.car_name.setText(car.getMakeEn() + " " + car.getModelEn() + " " + car.getBodyEn());
                } else if (!TextUtils.isEmpty(car.getMakeEn()) && !TextUtils.isEmpty(car.getBodyEn())) {
                    holder.car_name.setText(car.getMakeEn() + " " + car.getBodyEn());
                } else if (!TextUtils.isEmpty(car.getMakeEn()) && !TextUtils.isEmpty(car.getModelEn())) {
                    holder.car_name.setText(car.getMakeEn() + " " + car.getModelEn());
                } else if (!TextUtils.isEmpty(car.getMakeEn())) {
                    holder.car_name.setText(car.getMakeEn());
                } else if (!TextUtils.isEmpty(car.getModelEn())) {
                    holder.car_name.setText(car.getModelEn());
                } else if (!TextUtils.isEmpty(car.getBodyEn())) {
                    holder.car_name.setText(car.getBodyEn());
                }

                holder.price_main.setGravity(Gravity.START);
                if (car.getAuctionInfo() != null) {
                    holder.currency.setText("" + car.getAuctionInfo().getCurrencyEn());
                }
            } else {

                holder.car_name.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                holder.price_main.setGravity(Gravity.END);

                if (!TextUtils.isEmpty(car.getMakeAr()) && !TextUtils.isEmpty(car.getModelAr()) && !TextUtils.isEmpty(car.getBodyAr())) {
                    holder.car_name.setText(car.getBodyAr() + " " + car.getModelAr() + " " + car.getMakeAr());
                } else if (!TextUtils.isEmpty(car.getMakeAr()) && !TextUtils.isEmpty(car.getBodyAr())) {
                    holder.car_name.setText(car.getBodyAr() + " " + car.getMakeAr());
                } else if (!TextUtils.isEmpty(car.getMakeAr()) && !TextUtils.isEmpty(car.getModelAr())) {
                    holder.car_name.setText(car.getModelAr() + " " + car.getMakeAr());
                } else if (!TextUtils.isEmpty(car.getMakeAr())) {
                    holder.car_name.setText(car.getMakeAr());
                } else if (!TextUtils.isEmpty(car.getModelAr())) {
                    holder.car_name.setText(car.getModelAr());
                } else if (!TextUtils.isEmpty(car.getBodyAr())) {
                    holder.car_name.setText(car.getBodyAr());
                }

                if (car.getAuctionInfo() != null) {
                    holder.currency.setText("" + car.getAuctionInfo().getCurrencyAr());
                }
            }

            if (car.isUpdate()) {
                manageBlinkEffect(holder.blinkView);
                car.setUpdate(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return carModel.size();
    }

    private void manageBlinkEffect(View view) {
        ObjectAnimator anim = ObjectAnimator.ofInt(view, "backgroundColor", Color.WHITE, Color.LTGRAY, Color.WHITE);
        anim.setDuration(500);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatCount(4);
        anim.start();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private ImageView fav;
        private TextView car_name;
        private TextView car_price;
        private TextView lot_number;
        private CountdownView time_left;
        private TextView bids;
        private TextView currency;
        private CardView carItem;
        private LinearLayout blinkView;
        private RelativeLayout price_main;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blinkView = itemView.findViewById(R.id.blinkView);
            carItem = itemView.findViewById(R.id.carItem);
            image = itemView.findViewById(R.id.image);
            fav = itemView.findViewById(R.id.fav);
            car_name = itemView.findViewById(R.id.car_name);
            car_price = itemView.findViewById(R.id.car_price);
            lot_number = itemView.findViewById(R.id.lot_number);
            bids = itemView.findViewById(R.id.bids);
            time_left = itemView.findViewById(R.id.time_left);
            currency = itemView.findViewById(R.id.currency);
            price_main = itemView.findViewById(R.id.price_main);
        }
    }
}
