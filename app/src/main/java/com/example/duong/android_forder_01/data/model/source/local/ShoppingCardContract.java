package com.example.duong.android_forder_01.data.model.source.local;

import android.provider.BaseColumns;

/**
 * Created by tri on 21/02/2017.
 */
public class ShoppingCardContract {
    public ShoppingCardContract() {
    }

    public static class ShoppingCardEntry implements BaseColumns {
        public static final String TABLE_NAME_SHOPPING_CARD = "shopping_card";
        public static final String TABLE_NAME_SHOPPING_CARD_ITEM = "shopping_card_item";
        public static final String COLUMN_ID_SHOPPING_CARD = "id_shopping_card";
        public static final String COLUMN_ID_DOMAIN = "id_domain";
        public static final String COLUMN_ID_SHOP = "id_shop";
        public static final String COLUMN_TOTAL = "total";
        public static final String COLUMN_ID_PRODUCT = "id_product";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
    }
}
