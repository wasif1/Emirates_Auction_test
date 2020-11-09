
package com.ea.Model;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

@SuppressWarnings("unused")
public class AuctionInfo {

    @SerializedName("bids")
    private Long mBids;
    @SerializedName("currencyAr")
    private String mCurrencyAr;
    @SerializedName("currencyEn")
    private String mCurrencyEn;
    @SerializedName("currentPrice")
    private Long mCurrentPrice;
    @SerializedName("endDate")
    private Long mEndDate;
    @SerializedName("endDateAr")
    private String mEndDateAr;
    @SerializedName("endDateEn")
    private String mEndDateEn;
    @SerializedName("iCarId")
    private Long mICarId;
    @SerializedName("iVinNumber")
    private String mIVinNumber;
    @SerializedName("isModified")
    private Long mIsModified;
    @SerializedName("itemAuctionType")
    private Long mItemAuctionType;
    @SerializedName("itemid")
    private int mItemid;
    @SerializedName("lot")
    private Long mLot;
    @SerializedName("minIncrement")
    private Long mMinIncrement;
    @SerializedName("priority")
    private Long mPriority;
    @SerializedName("VATPercent")
    private Long mVATPercent;

    public Long getBids() {
        return mBids;
    }

    public void setBids(Long bids) {
        mBids = bids;
    }

    public String getCurrencyAr() {
        return mCurrencyAr;
    }

    public void setCurrencyAr(String currencyAr) {
        mCurrencyAr = currencyAr;
    }

    public String getCurrencyEn() {
        return mCurrencyEn;
    }

    public void setCurrencyEn(String currencyEn) {
        mCurrencyEn = currencyEn;
    }

    public Long getCurrentPrice() {
        return mCurrentPrice;
    }

    public void setCurrentPrice(Long currentPrice) {
        mCurrentPrice = currentPrice;
    }

    public Long getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Long endDate) {
        mEndDate = endDate;
    }

    public String getEndDateAr() {
        return mEndDateAr;
    }

    public void setEndDateAr(String endDateAr) {
        mEndDateAr = endDateAr;
    }

    public String getEndDateEn() {
        return mEndDateEn;
    }

    public void setEndDateEn(String endDateEn) {
        mEndDateEn = endDateEn;
    }

    public Long getICarId() {
        return mICarId;
    }

    public void setICarId(Long iCarId) {
        mICarId = iCarId;
    }

    public String getIVinNumber() {
        return mIVinNumber;
    }

    public void setIVinNumber(String iVinNumber) {
        mIVinNumber = iVinNumber;
    }

    public Long getIsModified() {
        return mIsModified;
    }

    public void setIsModified(Long isModified) {
        mIsModified = isModified;
    }

    public Long getItemAuctionType() {
        return mItemAuctionType;
    }

    public void setItemAuctionType(Long itemAuctionType) {
        mItemAuctionType = itemAuctionType;
    }

    public int getItemid() {
        return mItemid;
    }

    public void setItemid(int itemid) {
        mItemid = itemid;
    }

    public Long getLot() {
        return mLot;
    }

    public void setLot(Long lot) {
        mLot = lot;
    }

    public Long getMinIncrement() {
        return mMinIncrement;
    }

    public void setMinIncrement(Long minIncrement) {
        mMinIncrement = minIncrement;
    }

    public Long getPriority() {
        return mPriority;
    }

    public void setPriority(Long priority) {
        mPriority = priority;
    }

    public Long getVATPercent() {
        return mVATPercent;
    }

    public void setVATPercent(Long vATPercent) {
        mVATPercent = vATPercent;
    }

}
