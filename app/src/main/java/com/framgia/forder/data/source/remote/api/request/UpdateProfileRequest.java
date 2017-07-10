package com.framgia.forder.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;

/**
 * Created by ASUS on 19-04-2017.
 */

public class UpdateProfileRequest {
    @SerializedName("user")
    @Expose
    private UpdateProfileUser mUser;
    private InputStream mAvatar;

    public UpdateProfileUser getUser() {
        return mUser;
    }

    public void setUser(UpdateProfileUser user) {
        mUser = user;
    }

    public InputStream getAvatar() {
        return mAvatar;
    }

    public void setAvatar(InputStream avatar) {
        mAvatar = avatar;
    }

    public static class UpdateProfileUser {
        @SerializedName("name")
        @Expose
        private String mName;
        @SerializedName("chatwork_id")
        @Expose
        private String mChatworkId;
        @SerializedName("description")
        @Expose
        private String mDescription;
        @SerializedName("avatar")
        @Expose
        private String mAvatar;

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getChatworkId() {
            return mChatworkId;
        }

        public void setChatworkId(String chatworkId) {
            mChatworkId = chatworkId;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public String getAvatar() {
            return mAvatar;
        }

        public void setAvatar(String avatar) {
            mAvatar = avatar;
        }
    }
}
