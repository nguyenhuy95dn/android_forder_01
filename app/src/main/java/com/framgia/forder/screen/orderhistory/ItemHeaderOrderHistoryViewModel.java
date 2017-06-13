package com.framgia.forder.screen.orderhistory;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.utils.Utils;

/**
 * Created by ASUS on 28-04-2017.
 */

public class ItemHeaderOrderHistoryViewModel extends BaseObservable {
    private Order mOrder;

    ItemHeaderOrderHistoryViewModel(Order order) {
        mOrder = order;
    }

    public String getFormatOrderDate() {
        if (mOrder != null) {
            return Utils.DateTimeUntils.convertUiFormatToDataFormat(mOrder.getTimeCreateOrder(),
                    Utils.INPUT_TIME_FORMAT, Utils.OUTPUT_DATE_FORMAT);
        }
        return "";
    }

    public String getTotalPrice() {
        return mOrder != null ? mOrder.getTotalPriceFormat() : "";
    }

    @Bindable
    public int getStatusColor() {
        return mOrder.getStatusColor();
    }

    public String getStatus() {
        return mOrder.getStatusOrder().getValue();
    }
}
