
package com.ea.Model;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Car {

    @SerializedName("AuctionInfo")
    public AuctionInfo mAuctionInfo;
    @SerializedName("bodyAr")
    private String mBodyAr;
    @SerializedName("bodyEn")
    private String mBodyEn;
    @SerializedName("bodyId")
    private Long mBodyId;
    @SerializedName("carID")
    private int mCarID;
    @SerializedName("descriptionAr")
    private String mDescriptionAr;
    @SerializedName("descriptionEn")
    private String mDescriptionEn;
    @SerializedName("image")
    private String mImage;
    @SerializedName("imgCount")
    private Long mImgCount;
    @SerializedName("makeAr")
    private String mMakeAr;
    @SerializedName("makeEn")
    private String mMakeEn;
    @SerializedName("makeID")
    private Long mMakeID;
    @SerializedName("mileage")
    private String mMileage;
    @SerializedName("modelAr")
    private String mModelAr;
    @SerializedName("modelEn")
    private String mModelEn;
    @SerializedName("modelID")
    private Long mModelID;
    @SerializedName("sharingLink")
    private String mSharingLink;
    @SerializedName("sharingMsgAr")
    private String mSharingMsgAr;
    @SerializedName("sharingMsgEn")
    private String mSharingMsgEn;
    @SerializedName("year")
    private Long mYear;
    private boolean isEn = true;
    private boolean update = false;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isEn() {
        return isEn;
    }

    public void setEn(boolean en) {
        isEn = en;
    }

    public AuctionInfo getAuctionInfo() {
        return mAuctionInfo;
    }

    public void setAuctionInfo(AuctionInfo auctionInfo) {
        mAuctionInfo = auctionInfo;
    }

    public String getBodyAr() {
        return mBodyAr;
    }

    public void setBodyAr(String bodyAr) {
        mBodyAr = bodyAr;
    }

    public String getBodyEn() {
        return mBodyEn;
    }

    public void setBodyEn(String bodyEn) {
        mBodyEn = bodyEn;
    }

    public Long getBodyId() {
        return mBodyId;
    }

    public void setBodyId(Long bodyId) {
        mBodyId = bodyId;
    }

    public int getCarID() {
        return mCarID;
    }

    public void setCarID(int carID) {
        mCarID = carID;
    }

    public String getDescriptionAr() {
        return mDescriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        mDescriptionAr = descriptionAr;
    }

    public String getDescriptionEn() {
        return mDescriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        mDescriptionEn = descriptionEn;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public Long getImgCount() {
        return mImgCount;
    }

    public void setImgCount(Long imgCount) {
        mImgCount = imgCount;
    }

    public String getMakeAr() {
        return mMakeAr;
    }

    public void setMakeAr(String makeAr) {
        mMakeAr = makeAr;
    }

    public String getMakeEn() {
        return mMakeEn;
    }

    public void setMakeEn(String makeEn) {
        mMakeEn = makeEn;
    }

    public Long getMakeID() {
        return mMakeID;
    }

    public void setMakeID(Long makeID) {
        mMakeID = makeID;
    }

    public String getMileage() {
        return mMileage;
    }

    public void setMileage(String mileage) {
        mMileage = mileage;
    }

    public String getModelAr() {
        return mModelAr;
    }

    public void setModelAr(String modelAr) {
        mModelAr = modelAr;
    }

    public String getModelEn() {
        return mModelEn;
    }

    public void setModelEn(String modelEn) {
        mModelEn = modelEn;
    }

    public Long getModelID() {
        return mModelID;
    }

    public void setModelID(Long modelID) {
        mModelID = modelID;
    }

    public String getSharingLink() {
        return mSharingLink;
    }

    public void setSharingLink(String sharingLink) {
        mSharingLink = sharingLink;
    }

    public String getSharingMsgAr() {
        return mSharingMsgAr;
    }

    public void setSharingMsgAr(String sharingMsgAr) {
        mSharingMsgAr = sharingMsgAr;
    }

    public String getSharingMsgEn() {
        return mSharingMsgEn;
    }

    public void setSharingMsgEn(String sharingMsgEn) {
        mSharingMsgEn = sharingMsgEn;
    }

    public Long getYear() {
        return mYear;
    }

    public void setYear(Long year) {
        mYear = year;
    }

}
