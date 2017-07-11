package com.framgia.forder.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenhuy95dn on 7/11/2017.
 */

public class AddUserInDomainRequest {
    @Expose
    @SerializedName("user_domain")
    private UserInDomain mUserInDomain;

    public UserInDomain getUserInDomain() {
        return mUserInDomain;
    }

    public void setUserInDomain(UserInDomain userInDomain) {
        mUserInDomain = userInDomain;
    }

    public class UserInDomain {
        @Expose
        @SerializedName("domain_id")
        private int mDomainId;
        @Expose
        @SerializedName("user_id")
        private int mUserId;

        public int getDomainId() {
            return mDomainId;
        }

        public void setDomainId(int domainId) {
            mDomainId = domainId;
        }

        public int getUserId() {
            return mUserId;
        }

        public void setUserId(int userId) {
            mUserId = userId;
        }
    }
}
