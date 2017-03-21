package com.example.duong.android_forder_01.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.model.ShoppingCardItem;
import com.example.duong.android_forder_01.data.source.ShoppingCardDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_COUNT;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_DOMAIN;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_PRODUCT;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOP;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_IMAGE;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_NAME;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_NUMBER;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_PRICE;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_QUANTITY;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_SHOP_NAME;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_SUM;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_TOTAL;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.DEFAULT_QUANTITY;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.TABLE_NAME_SHOPPING_CARD;

public class ShoppingCardLocalDataSource extends DataHelper implements
    ShoppingCardDataSource {
    private static ShoppingCardLocalDataSource sLocalDataSource;

    public ShoppingCardLocalDataSource(Context context) {
        super(context);
    }

    public static ShoppingCardLocalDataSource getInstance(Context context) {
        if (sLocalDataSource == null) {
            sLocalDataSource = new ShoppingCardLocalDataSource(context);
        }
        return sLocalDataSource;
    }

    @Override
    public void addShoppingCardItem(Product product, int idDomain) {
        if (isExistsItem(product.getId(), idDomain)) {
            increaseQuantity(product.getId(), idDomain);
            return;
        }
        openDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID_DOMAIN, idDomain);
            values.put(COLUMN_ID_SHOP, product.getShop().getId());
            values.put(COLUMN_SHOP_NAME, product.getShop().getName());
            values.put(COLUMN_ID_PRODUCT, product.getId());
            values.put(COLUMN_NAME, product.getName());
            values.put(COLUMN_PRICE, product.getPrice());
            values.put(COLUMN_QUANTITY, DEFAULT_QUANTITY);
            values
                .put(COLUMN_IMAGE, product.getCollectionImage().getImage().getThumbnail().getUrl());
            mDatabase.insertOrThrow(TABLE_NAME_SHOPPING_CARD, null, values);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void deleteShoppingCardItem(int productId, int domainId) {
        openDatabase();
        try {
            String selection = COLUMN_ID_DOMAIN + "=? AND " + COLUMN_ID_PRODUCT + "=?";
            String[] arguments = new String[]{String.valueOf(domainId),
                String.valueOf(productId)};
            mDatabase.delete(TABLE_NAME_SHOPPING_CARD, selection, arguments);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public boolean isExistsItem(int productId, int domainId) {
        openDatabase();
        String selection = COLUMN_ID_DOMAIN + "=? AND " + COLUMN_ID_PRODUCT + "=?";
        String[] arguments = new String[]{String.valueOf(domainId),
            String.valueOf(productId)};
        Cursor cursor = mDatabase.query(TABLE_NAME_SHOPPING_CARD, null, selection, arguments, null,
            null, null);
        if (cursor == null) return false;
        return cursor.getCount() > 0;
    }

    @Override
    public void reduceQuantity(int productId, int domainId) {
        openDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_QUANTITY, getQuantity(productId, domainId) - DEFAULT_QUANTITY);
            String selections = COLUMN_ID_DOMAIN + "=? AND " + COLUMN_ID_PRODUCT + "=?";
            String[] arguments = new String[]{String.valueOf(domainId),
                String.valueOf(productId)};
            mDatabase.update(TABLE_NAME_SHOPPING_CARD, values, selections, arguments);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void increaseQuantity(int productId, int domainId) {
        openDatabase();
        try {
            ContentValues values = new ContentValues();
            String selections = COLUMN_ID_DOMAIN + "=? AND " + COLUMN_ID_PRODUCT + "=?";
            String[] arguments = new String[]{String.valueOf(domainId),
                String.valueOf(productId)};
            values.put(COLUMN_QUANTITY, getQuantity(productId, domainId) + DEFAULT_QUANTITY);
            mDatabase.update(TABLE_NAME_SHOPPING_CARD, values, selections, arguments);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public int getQuantity(int productId, int domainId) {
        int quantity = 0;
        try {
            String[] columns = new String[]{COLUMN_QUANTITY};
            String selections = COLUMN_ID_DOMAIN + "=? AND " + COLUMN_ID_PRODUCT + "=?";
            String[] arguments = new String[]{String.valueOf(domainId),
                String.valueOf(productId)};
            Cursor cursor = mDatabase.query(TABLE_NAME_SHOPPING_CARD, columns, selections,
                arguments, null, null, null);
            cursor.moveToFirst();
            quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    @Override
    public void getShoppingCard(int domainId, GetShoppingCardCallback callback
    ) {
        openDatabase();
        try {
            List<ShoppingCard> shoppingCards = new ArrayList<>();
            String[] columns = new String[]{COLUMN_ID_DOMAIN, COLUMN_ID_SHOP, COLUMN_SHOP_NAME,
                COLUMN_SUM};
            String selections = COLUMN_ID_DOMAIN + "=?";
            String[] arguments = new String[]{String.valueOf(domainId)};
            String groupBy = COLUMN_ID_DOMAIN + "," + COLUMN_ID_SHOP + "," + COLUMN_SHOP_NAME;
            Cursor cursorShoppingCard = mDatabase.query(TABLE_NAME_SHOPPING_CARD, columns,
                selections, arguments, groupBy, null, null);
            if (cursorShoppingCard != null && cursorShoppingCard.moveToFirst()) {
                while (!cursorShoppingCard.isAfterLast()) {
                    ShoppingCard shoppingCard = new ShoppingCard(cursorShoppingCard);
                    shoppingCards.add(shoppingCard);
                    cursorShoppingCard.moveToNext();
                }
            }
            for (ShoppingCard shoppingCard : shoppingCards) {
                List<ShoppingCardItem> shoppingCardItems = new ArrayList<>();
                String selectionItem = COLUMN_ID_DOMAIN + "=? AND " + COLUMN_ID_SHOP + "=?";
                String[] argumentItem = new String[]{String.valueOf(shoppingCard.getDomainId()),
                    String.valueOf(shoppingCard.getShopId())};
                Cursor cursorItem = mDatabase.query(TABLE_NAME_SHOPPING_CARD, null, selectionItem,
                    argumentItem, null, null, null);
                if (cursorItem != null && cursorItem.moveToFirst()) {
                    while (!cursorItem.isAfterLast()) {
                        ShoppingCardItem shoppingCardItem = new ShoppingCardItem(cursorItem);
                        shoppingCardItems.add(shoppingCardItem);
                        cursorItem.moveToNext();
                    }
                    shoppingCard.setShoppingCardDetails(shoppingCardItems);
                }
            }
            if (shoppingCards != null) callback.onLoaded(shoppingCards, getTotalPrice(domainId));
            else callback.onLoadedError();
        } catch (SQLiteException e) {
            callback.onLoadedError();
        } finally {
            close();
        }
    }

    @Override
    public int getNumberItem(int domainId) {
        int numberItem = 0;
        openDatabase();
        try {
            String[] columns = new String[]{COLUMN_COUNT};
            String selections = COLUMN_ID_DOMAIN + "=?";
            String[] arguments = new String[]{String.valueOf(domainId)};
            Cursor cursor = mDatabase.query(TABLE_NAME_SHOPPING_CARD, columns, selections,
                arguments, null, null, null);
            cursor.moveToFirst();
            numberItem = cursor.getInt(cursor.getColumnIndex(COLUMN_NUMBER));
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return numberItem;
    }

    private double getTotalPrice(int domainId) {
        double totalPrice = 0;
        openDatabase();
        try {
            String[] columns = new String[]{COLUMN_SUM};
            String selections = COLUMN_ID_DOMAIN + "=?";
            String[] arguments = new String[]{String.valueOf(domainId)};
            Cursor cursor = mDatabase.query(TABLE_NAME_SHOPPING_CARD, columns, selections,
                arguments, null, null, null);
            cursor.moveToFirst();
            totalPrice = cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL));
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return totalPrice;
    }
}
