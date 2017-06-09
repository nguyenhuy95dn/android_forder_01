package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.model.DomainManagement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Age on 6/8/2017.
 */

public class DomainManagementResponse {
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<DomainManagement> mListDomainManagement;

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<DomainManagement> getListDomainManagement() {
        return mListDomainManagement;
    }

    public void setListDomainManagement(List<DomainManagement> listDomainManagement) {
        mListDomainManagement = listDomainManagement;
    }
}
