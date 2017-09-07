package com.framgia.forder.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenhuy95dn on 9/7/2017.
 */

public class NotificationSettingRequest {
    @Expose
    @SerializedName("user")
    private Setting mUserSetting;

    public Setting getUserSetting() {
        return mUserSetting;
    }

    public void setUserSetting(Setting userSetting) {
        mUserSetting = userSetting;
    }

    public static class Setting {
        @Expose
        @SerializedName("notification_settings")
        private NotificationAndEmailSetting mNotificationSettings;
        @Expose
        @SerializedName("email_settings")
        private NotificationAndEmailSetting mEmailSettings;
        @Expose
        @SerializedName("chatwork_settings")
        private ChatworkSetting mChatworkSettings;

        public NotificationAndEmailSetting getNotificationSettings() {
            return mNotificationSettings;
        }

        public void setNotificationSettings(NotificationAndEmailSetting notificationSettings) {
            mNotificationSettings = notificationSettings;
        }

        public NotificationAndEmailSetting getEmailSettings() {
            return mEmailSettings;
        }

        public void setEmailSettings(NotificationAndEmailSetting emailSettings) {
            mEmailSettings = emailSettings;
        }

        public ChatworkSetting getChatworkSettings() {
            return mChatworkSettings;
        }

        public void setChatworkSettings(ChatworkSetting chatworkSettings) {
            mChatworkSettings = chatworkSettings;
        }

        public static class NotificationAndEmailSetting {
            @Expose
            @SerializedName("order_request")
            private int mOrderRequest;
            @Expose
            @SerializedName("order_processed")
            private int mOrderProcessed;
            @Expose
            @SerializedName("send_order")
            private int mSendOrder;

            public int getOrderRequest() {
                return mOrderRequest;
            }

            public void setOrderRequest(int orderRequest) {
                mOrderRequest = orderRequest;
            }

            public int getOrderProcessed() {
                return mOrderProcessed;
            }

            public void setOrderProcessed(int orderProcessed) {
                mOrderProcessed = orderProcessed;
            }

            public int getSendOrder() {
                return mSendOrder;
            }

            public void setSendOrder(int sendOrder) {
                mSendOrder = sendOrder;
            }
        }

        public static class ChatworkSetting {
            @Expose
            @SerializedName("chatwork_processed")
            private int mChatworkProcessed;
            @Expose
            @SerializedName("shop_open")
            private int mShopOpen;

            public int getChatworkProcessed() {
                return mChatworkProcessed;
            }

            public void setChatworkProcessed(int chatworkProcessed) {
                mChatworkProcessed = chatworkProcessed;
            }

            public int getShopOpen() {
                return mShopOpen;
            }

            public void setShopOpen(int shopOpen) {
                mShopOpen = shopOpen;
            }
        }
    }
}
