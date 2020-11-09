package com.ea.Presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.ea.Client.ApiClient;
import com.ea.Components.InternetCheckHelper;
import com.ea.Interface.MainInterface;
import com.ea.Model.EmirateAuctionCarsResponse;
import com.ea.R;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private Activity activity;
    private MainInterface mainInterface;

    public MainPresenter(Activity activity, MainInterface mainInterface) {
        this.activity = activity;
        this.mainInterface = mainInterface;
    }

    public void getCars(final String tick) {

        try {
            mainInterface.progress(true);
            if (!InternetCheckHelper.isConnected()) {
                mainInterface.error(activity.getString(R.string.internet_rrror));
                return;
            } else {

                Call<EmirateAuctionCarsResponse> call = ApiClient
                        .getInstance(activity)
                        .getApi()
                        .getCars(tick);
                call.enqueue(new Callback<EmirateAuctionCarsResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<EmirateAuctionCarsResponse> call, @NotNull Response<EmirateAuctionCarsResponse> response) {
                        mainInterface.progress(false);
                        if (response.code() == 200) {
                            if (response.body() != null) {
                                mainInterface.success(response.body(), tick);
                            } else {
                                mainInterface.error("Empty Body");
                            }
                        } else {
                            mainInterface.error("Code : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<EmirateAuctionCarsResponse> call, @NotNull Throwable t) {
                        mainInterface.progress(false);
                        if (!TextUtils.isEmpty(t.getMessage())) {
                            mainInterface.error(t.getMessage());
                        }
                    }
                });
            }
        } catch (Exception t) {
            if (!TextUtils.isEmpty(t.getMessage())) {
                mainInterface.error(t.getMessage());
            }
        }
    }
}
