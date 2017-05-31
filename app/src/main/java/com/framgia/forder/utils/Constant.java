package com.framgia.forder.utils;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public final class Constant {
    public static final String END_POINT_URL = "http://172.16.4.157:3000/";
    // For bundle
    public static final String ARGUMENT_LIST_USER = "ARGUMENT_LIST_USER";
    public static final String BREAK_LINE = "\n";

    public static final String FORMAT_PRICE = "%1$,.0f";

    public static final String FORMAT_TIME = "HH:mm";

    public static final String FORMAT_DATE = "dd-MM-yyyy";

    public static final String UNIT_MONEY = " VNĐ";

    public static final int DEFAULT_QUANTITY = 1;

    public static final int NUMBER_COMPRESS = 100;

    public static final int FLAG_OPEN_HOUR = 1;

    public static final int FLAG_END_HOUR = 2;

    public static final int REQUEST_SELECT_IMAGE = 100;

    private Constant() {
        // No-op
    }

    public static class StatusCode {
        public static final int PENDING_CODE = 0;
        public static final int ACCEPT_CODE = 1;
        public static final int REJECT_CODE = 2;
    }
}
