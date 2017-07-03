package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shop implements Parcelable {

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("domain_id")
    private int mDomainId;
    @Expose
    @SerializedName("shop_id")
    private int mShopId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("avatar")
    private CollectionAvatar mCollectionAvatar;
    @Expose
    @SerializedName("averate_rating")
    private float mAverageRating;
    @Expose
    @SerializedName("owner_id")
    private int mOwnerId;
    @Expose
    @SerializedName("user")
    private User mUser;
    @Expose
    @SerializedName("time_auto_reject")
    private String mTimeAutoReject;
    @Expose
    @SerializedName("cover_image")
    private CollectionImage mCoverImage;
    @Expose
    @SerializedName("created_at")
    private String mCreateTime;
    @Expose
    @SerializedName("updated_at")
    private String mUpdateTime;
    @Expose
    @SerializedName("slug")
    private String mSlug;
    @Expose
    @SerializedName("status_on_off")
    private String mStatusShop;
    @Expose
    @SerializedName("openforever")
    private boolean mOpenForever;
    @Expose
    @SerializedName("time_auto_close")
    private String mTimeAutoClose;
    @Expose
    @SerializedName("owner_name")
    private String mNameOwner;
    @Expose
    @SerializedName("owner_email")
    private String mEmailOwner;
    @Expose
    @SerializedName("owner_avatar")
    private CollectionAvatar mOwnerAvatar;

    public Shop() {
    }

    public Shop(int id, int domainId, int shopId, String name, String description, String status,
            CollectionAvatar collectionAvatar, float averageRating, int ownerId, User user,
            String timeAutoReject) {
        mId = id;
        mDomainId = domainId;
        mShopId = shopId;
        mName = name;
        mDescription = description;
        mStatus = status;
        mCollectionAvatar = collectionAvatar;
        mAverageRating = averageRating;
        mOwnerId = ownerId;
        mUser = user;
        mTimeAutoReject = timeAutoReject;
    }

    public Shop(String name, String description, CollectionAvatar collectionAvatar,
            String timeAutoReject, CollectionImage coverImage, boolean openForever,
            String timeAutoClose) {
        mName = name;
        mDescription = description;
        mCollectionAvatar = collectionAvatar;
        mTimeAutoReject = timeAutoReject;
        mCoverImage = coverImage;
        mOpenForever = openForever;
        mTimeAutoClose = timeAutoClose;
    }

    private Shop(Parcel in) {
        mId = in.readInt();
        mDomainId = in.readInt();
        mShopId = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mStatus = in.readString();
        mCollectionAvatar = in.readParcelable(CollectionAvatar.class.getClassLoader());
        mAverageRating = in.readFloat();
        mOwnerId = in.readInt();
        mUser = in.readParcelable(User.class.getClassLoader());
        mTimeAutoReject = in.readString();
        mCoverImage = in.readParcelable(Image.class.getClassLoader());
        mCreateTime = in.readString();
        mUpdateTime = in.readString();
        mSlug = in.readString();
        mStatusShop = in.readString();
        mOpenForever = in.readByte() != 0;
        mTimeAutoClose = in.readString();
        mNameOwner = in.readString();
        mEmailOwner = in.readString();
        mOwnerAvatar = in.readParcelable(Image.class.getClassLoader());
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public float getAverageRating() {
        return mAverageRating;
    }

    public void setAverageRating(float averageRating) {
        mAverageRating = averageRating;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public int getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(int ownerId) {
        mOwnerId = ownerId;
    }

    public String getTimeAutoReject() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mTimeAutoReject,
                Utils.INPUT_TIME_FORMAT, Utils.OUTPUT_TIME_FORMAT);
    }

    public void setTimeAutoReject(String timeAutoReject) {
        mTimeAutoReject = timeAutoReject;
    }

    public CollectionAvatar getCollectionAvatar() {
        return mCollectionAvatar;
    }

    public void setCollectionAvatar(CollectionAvatar collectionAvatar) {
        mCollectionAvatar = collectionAvatar;
    }

    public CollectionImage getCoverImage() {
        return mCoverImage;
    }

    public void setCoverImage(CollectionImage coverImage) {
        mCoverImage = coverImage;
    }

    public CollectionAvatar getOwnerAvatar() {
        return mOwnerAvatar;
    }

    public void setOwnerAvatar(CollectionAvatar ownerAvatar) {
        mOwnerAvatar = ownerAvatar;
    }

    public String getTimeOpenShop() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mTimeAutoClose,
                Utils.INPUT_TIME_FORMAT, Utils.OUTPUT_TIME_FORMAT);
    }

    public String getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(String createTime) {
        mCreateTime = createTime;
    }

    public String getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        mUpdateTime = updateTime;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getStatusShop() {
        return mStatusShop;
    }

    public void setStatusShop(String statusShop) {
        mStatusShop = statusShop;
    }

    public boolean isOpenForever() {
        return mOpenForever;
    }

    public void setOpenForever(boolean openForever) {
        mOpenForever = openForever;
    }

    public String getTimeAutoClose() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mTimeAutoClose,
                Utils.INPUT_TIME_FORMAT, Utils.OUTPUT_TIME_FORMAT);
    }

    public void setTimeAutoClose(String timeAutoClose) {
        mTimeAutoClose = timeAutoClose;
    }

    public String getNameOwner() {
        return mNameOwner;
    }

    public void setNameOwner(String nameOwner) {
        mNameOwner = nameOwner;
    }

    public String getEmailOwner() {
        return mEmailOwner;
    }

    public void setEmailOwner(String emailOwner) {
        mEmailOwner = emailOwner;
    }

    public boolean isFormatStatus() {
        if ("on".equals(mStatusShop)) {
            return true;
        }
        if ("off".equals(mStatusShop)) {
            return false;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mDomainId);
        dest.writeInt(mShopId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mStatus);
        dest.writeParcelable(mCollectionAvatar, flags);
        dest.writeFloat(mAverageRating);
        dest.writeInt(mOwnerId);
        dest.writeParcelable(mUser, flags);
        dest.writeString(mTimeAutoReject);
        dest.writeParcelable(mCoverImage, flags);
        dest.writeString(mCreateTime);
        dest.writeString(mUpdateTime);
        dest.writeString(mSlug);
        dest.writeString(mStatusShop);
        dest.writeByte((byte) (mOpenForever ? 1 : 0));
        dest.writeString(mTimeAutoClose);
        dest.writeString(mNameOwner);
        dest.writeString(mEmailOwner);
        dest.writeParcelable(mOwnerAvatar, flags);
    }
}
