package com.framgia.forder.data.model;

import com.framgia.forder.utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.framgia.forder.utils.Constant.VERTICAL_COLUMN;

/**
 * Created by ASUS on 24-04-2017.
 */

public class Notification {

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("eventable_type")
    private String mTypeEvenTable;
    @Expose
    @SerializedName("eventable_id")
    private int mIdEvenTable;
    @Expose
    @SerializedName("read")
    private boolean mRead;
    @Expose
    @SerializedName("user_id")
    private int mIdUser;
    @Expose
    @SerializedName("created_at")
    private String mTimeCreate;
    @Expose
    @SerializedName("updated_at")
    private String mTimeUpdated;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage = mMessage.replace("\n", "")
                .replace("           ", " ")
                .replace("          ", " ")
                .replace("      ", " ")
                .replace("  ", " ");
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getTypeEvenTable() {
        return mTypeEvenTable;
    }

    public void setTypeEvenTable(String typeEvenTable) {
        mTypeEvenTable = typeEvenTable;
    }

    public int getIdEvenTable() {
        return mIdEvenTable;
    }

    public void setIdEvenTable(int idEvenTable) {
        mIdEvenTable = idEvenTable;
    }

    public boolean isRead() {
        return mRead;
    }

    public void setRead(boolean read) {
        mRead = read;
    }

    public int getIdUser() {
        return mIdUser;
    }

    public void setIdUser(int idUser) {
        mIdUser = idUser;
    }

    public String getTimeCreate() {
        return mTimeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        mTimeCreate = timeCreate;
    }

    public String getTimeUpdated() {
        return mTimeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        mTimeUpdated = timeUpdated;
    }

    public String getTimeNotificationFormat() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mTimeCreate,
                Utils.INPUT_TIME_FORMAT,
                Utils.OUTPUT_TIME_FORMAT + VERTICAL_COLUMN + Utils.OUTPUT_DATE_FORMAT);
    }
}
