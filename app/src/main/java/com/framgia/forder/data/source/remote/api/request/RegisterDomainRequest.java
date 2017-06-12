package com.framgia.forder.data.source.remote.api.request;

import com.framgia.forder.data.model.RegisterDomain;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 6/9/2017.
 */

public class RegisterDomainRequest {
    @Expose
    @SerializedName("domain")
    private RegisterDomain mDomain;

    public RegisterDomain getDomain() {
        return mDomain;
    }

    public void setDomain(RegisterDomain domain) {
        mDomain = domain;
    }
}
