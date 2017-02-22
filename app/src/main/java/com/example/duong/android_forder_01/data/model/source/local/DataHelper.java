package com.example.duong.android_forder_01.data.model.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_DOMAIN;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_PRODUCT;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOP;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOPPING_CARD;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_IMAGE;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_NAME;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_PRICE;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_QUANTITY;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_TOTAL;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.TABLE_NAME_SHOPPING_CARD;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.TABLE_NAME_SHOPPING_CARD_ITEM;

/**
 * Created by tri on 21/02/2017.
 */
public class DataHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Forder.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTERGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String COMMA_CREATE_TABLE = "CREATE TABLE ";
    private static final String DROPTABLE_IF_EXIST = "DROP TABLE IF EXISTS ";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String AUTO_CREMENT = " AUTOINCREMENT NOT NULL";
    private static final String CREATE_TABLE_SHOPPING_CARD =
        COMMA_CREATE_TABLE + TABLE_NAME_SHOPPING_CARD + " ("
            + COLUMN_ID_SHOPPING_CARD + INTERGER_TYPE + PRIMARY_KEY + AUTO_CREMENT + COMMA_SEP
            + COLUMN_ID_DOMAIN + INTERGER_TYPE + COMMA_SEP
            + COLUMN_ID_SHOP + INTERGER_TYPE + COMMA_SEP
            + COLUMN_TOTAL + REAL_TYPE + " )";
    private static final String CREATE_TABLE_SHOPPING_CARD_ITEM =
        COMMA_CREATE_TABLE + TABLE_NAME_SHOPPING_CARD_ITEM + " ("
            + COLUMN_ID_PRODUCT + INTERGER_TYPE + PRIMARY_KEY + COMMA_SEP
            + COLUMN_IMAGE + TEXT_TYPE + COMMA_SEP
            + COLUMN_NAME + TEXT_TYPE + COMMA_SEP
            + COLUMN_PRICE + REAL_TYPE + COMMA_SEP
            + COLUMN_QUANTITY + INTERGER_TYPE + COMMA_SEP
            + COLUMN_ID_SHOPPING_CARD + INTERGER_TYPE + " )";
    private static final String DROP_TABLE_SHOPPING_CARD =
        DROPTABLE_IF_EXIST + TABLE_NAME_SHOPPING_CARD;
    private static final String DROP_TABLE_SHOPPING_CARD_DETAIL =
        DROPTABLE_IF_EXIST + TABLE_NAME_SHOPPING_CARD_ITEM;
    protected SQLiteDatabase mDatabase;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    protected void openDatabase() {
        mDatabase = getWritableDatabase();
    }

    protected void closeDatabse() {
        if (mDatabase == null) return;
        mDatabase.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SHOPPING_CARD);
        db.execSQL(CREATE_TABLE_SHOPPING_CARD_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_SHOPPING_CARD);
        db.execSQL(DROP_TABLE_SHOPPING_CARD_DETAIL);
        onCreate(db);
    }
}
