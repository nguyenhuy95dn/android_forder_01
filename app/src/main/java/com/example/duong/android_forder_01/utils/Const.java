package com.example.duong.android_forder_01.utils;

/**
 * Created by Duong on 2/14/2017.
 */
public class Const {
    public static final int ID_USER = 1;
    public static final int ID_DOMAIN = 1;
    public static final int ID_CATEGORY = 1;
    public static final int ID_ROOT_DOMAIN = 1;
    public static final String FORMAT_PRICE = "%1$,.0f";
    public static final String UNIT_MONEY = " VNĐ";

    public static class ConstantApi {
        public static final String PATH_LOGIN = "PATH_LOGIN";
        public static final String PATH_PRODUCT = "PATH_PRODUCT";
        public static final String URL_FORDER = "https://www.chatwork.com/";
        public static final String PARAM_USER_NAME = "email";
        public static final String PARAM_PASSWORD = "password";
        public static final String MESSAGE_CONTENT = "message";
    }

    public static class KeyIntent {
        public static final String EXTRA_PRODUCT = "product";
        public static final String EXTRA_SHOP = "shop";
        public static final String EXTRA_ID_CATEGORY = "id_category";
        public static final String EXTRA_DOMAIN = "domain";
    }

    public static class StatusCode {
        public static final int PENDING_CODE = 0;
        public static final int ACCEPT_CODE = 1;
        public static final int REJECT_CODE = 2;
    }
}
