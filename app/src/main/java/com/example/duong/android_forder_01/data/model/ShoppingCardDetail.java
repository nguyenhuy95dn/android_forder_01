package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_QUANTITY;

public class ShoppingCardDetail {
    private int mId;
    private Product mProduct;
    private int mQuantity;

    public ShoppingCardDetail(Cursor cursor) {
        mProduct = new Product(cursor);
        mQuantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }
}
