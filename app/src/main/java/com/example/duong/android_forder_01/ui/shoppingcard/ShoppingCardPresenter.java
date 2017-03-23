package com.example.duong.android_forder_01.ui.shoppingcard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.source.ShoppingCardDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.Const.UNIT_MONEY;

public class ShoppingCardPresenter implements ShoppingCardContract.Presenter {
    private ShoppingCardContract.View mShoppingCardView;
    private ShoppingCardDataSource mShoppingCardDataSource;
    private Context mContext;

    public ShoppingCardPresenter(@NonNull ShoppingCardContract.View shoppingCardView,
                                 ShoppingCardDataSource shoppingCardDataSource, Context context) {
        mShoppingCardView = shoppingCardView;
        shoppingCardView.setPresenter(this);
        mShoppingCardDataSource = shoppingCardDataSource;
        mContext = context;
    }

    @Override
    public void start() {
        mShoppingCardView.start();
    }

    @Override
    public void loadShoppingCard(int domainId) {
        mShoppingCardDataSource.getShoppingCard(domainId,
            new ShoppingCardDataSource.GetShoppingCardCallback() {
                @Override
                public void onLoaded(List<ShoppingCard> list, double totalPrice) {
                    mShoppingCardView.loadShoppingCardCompleted(list, totalPrice);
                }

                @Override
                public void onLoadedError() {
                    mShoppingCardView.loadShoppingCardError();
                }
            });
    }

    @Override
    public void reduceQuantity(int productId, int domainId) {
        mShoppingCardDataSource.reduceQuantity(productId, domainId);
        loadShoppingCard(domainId);
        mShoppingCardView
            .updateCard(mShoppingCardDataSource.getNumberItem(domainId));
    }

    @Override
    public void increaseQuantity(int productId, int domainId) {
        mShoppingCardDataSource.increaseQuantity(productId, domainId);
        loadShoppingCard(domainId);
    }

    @Override
    public void deleteItem(int productId, int domainId) {
        mShoppingCardDataSource.deleteShoppingCardItem(productId, domainId);
        loadShoppingCard(domainId);
        mShoppingCardView
            .updateCard(mShoppingCardDataSource.getNumberItem(domainId));
    }

    @Override
    public void deleteShoppingCardOfDomain(int domainId) {
        mShoppingCardDataSource.deleteShoppingCardOfDomain(domainId);
        mShoppingCardView
            .updateCard(mShoppingCardDataSource.getNumberItem(domainId));
    }

    @Override
    public void order(ShoppingCard shoppingCard, final int domainId) {
        if (shoppingCard == null) return;
        List<ShoppingCard> list = new ArrayList<>();
        list.add(shoppingCard);
        mShoppingCardDataSource.deleteAllItemOutOfTime(list, domainId,
            new ShoppingCardDataSource.DeleteAllItemOutOfTimeCardCallback() {
                @Override
                public void onCompleted(List<String> list, double totalPrice) {
                    mShoppingCardView.showConfirmOrder(getAlertMessage(list, totalPrice));
                    mShoppingCardView
                        .updateCard(mShoppingCardDataSource.getNumberItem(domainId));
                }
            });
    }

    @Override
    public void orderAll(List<ShoppingCard> list, final int domainId) {
        if (list == null || list.size() <= 0) return;
        mShoppingCardDataSource.deleteAllItemOutOfTime(list, domainId,
            new ShoppingCardDataSource.DeleteAllItemOutOfTimeCardCallback() {
                @Override
                public void onCompleted(List<String> list, double totalPrice) {
                    mShoppingCardView.showConfirmOrder(getAlertMessage(list, totalPrice));
                    mShoppingCardView
                        .updateCard(mShoppingCardDataSource.getNumberItem(domainId));
                }
            });
    }

    @Override
    public void acceptOrder(List<ShoppingCard> list) {
        // todo send request order
    }

    @Override
    public String getAlertMessage(List<String> list, double total) {
        String message =
            mContext.getString(R.string.title_total_order) + String.format(FORMAT_PRICE, total) +
                UNIT_MONEY + "\n";
        if (list.size() > 0) {
            message += mContext.getString(R.string.title_delete_order) + "\n";
            for (String productName : list) message += "\t\t\t-" + productName + "\n";
        } else {
            message += mContext.getString(R.string.title_continue_order);
        }
        return message;
    }
}
