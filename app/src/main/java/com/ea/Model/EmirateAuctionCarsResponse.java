
package com.ea.Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class EmirateAuctionCarsResponse {

    @SerializedName("alertAr")
    private String mAlertAr;
    @SerializedName("alertEn")
    private String mAlertEn;
    @SerializedName("Cars")
    private List<Car> mCars;
    @SerializedName("count")
    private int mCount;
    @SerializedName("endDate")
    private int mEndDate;
    @SerializedName("Error")
    private Error mError;
    @SerializedName("RefreshInterval")
    private int mRefreshInterval;
    @SerializedName("sortDirection")
    private String mSortDirection;
    @SerializedName("sortOption")
    private String mSortOption;
    @SerializedName("Ticks")
    private String mTicks;

    public String getAlertAr() {
        return mAlertAr;
    }

    public void setAlertAr(String alertAr) {
        mAlertAr = alertAr;
    }

    public String getAlertEn() {
        return mAlertEn;
    }

    public void setAlertEn(String alertEn) {
        mAlertEn = alertEn;
    }

    public List<Car> getCars() {
        return mCars;
    }

    public void setCars(List<Car> cars) {
        mCars = cars;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public int getEndDate() {
        return mEndDate;
    }

    public void setEndDate(int endDate) {
        mEndDate = endDate;
    }

    public Error getError() {
        return mError;
    }

    public void setError(Error error) {
        mError = error;
    }

    public int getRefreshInterval() {
        return mRefreshInterval;
    }

    public void setRefreshInterval(int refreshInterval) {
        mRefreshInterval = refreshInterval;
    }

    public String getSortDirection() {
        return mSortDirection;
    }

    public void setSortDirection(String sortDirection) {
        mSortDirection = sortDirection;
    }

    public String getSortOption() {
        return mSortOption;
    }

    public void setSortOption(String sortOption) {
        mSortOption = sortOption;
    }

    public String getTicks() {
        return mTicks;
    }

    public void setTicks(String ticks) {
        mTicks = ticks;
    }

}
