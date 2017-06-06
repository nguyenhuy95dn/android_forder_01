package com.framgia.forder.screen.ordershop;

import android.content.Context;
import android.databinding.ObservableInt;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OrderDetail;

/**
 * Created by levutantuan on 6/1/17.
 */

public class ItemChildOrderViewModel {
    private final OrderDetail mOrderDetail;
    private final Context mContext;
    private ObservableInt mTextStatus;

    ItemChildOrderViewModel(Context context, OrderDetail orderDetail) {
        mContext = context;
        mTextStatus = new ObservableInt();
        mOrderDetail = orderDetail;
        initValueStatus();
    }

    private void initValueStatus() {

        switch (mOrderDetail.getStatusOrder()) {
            case Rejected:
                mTextStatus.set(R.drawable.button_red);
                break;
            case Pending:
                mTextStatus.set(R.drawable.button_blue);
                break;
            case Accepted:
                mTextStatus.set(R.drawable.button_orange);
                break;
            default:
                break;
        }
    }

    public String getStatus() {
        if (mOrderDetail != null) {
            return String.valueOf(mOrderDetail.getStatusOrder());
    }
        return "";
    }

    public String getQuantity() {
        if (mOrderDetail != null) {
            return String.format(mContext.getString(R.string.quantity), mOrderDetail.getQuantity());
        }
        return "";
    }

    public String getPrice() {
        if (mOrderDetail != null) {
            return String.format(mContext.getString(R.string.prices),
                    mOrderDetail.getTotalPriceFormat());
        }
        return "";
    }

    public String getNotes() {
        if (mOrderDetail != null) {
            return mOrderDetail.getNotes();
        }
        return "";
    }

    public String getImageProduct() {
        if (mOrderDetail.getImageProduct() != null) {
            return mOrderDetail.getImageProduct().getUrl();
        }
        return "";
    }

    public String getNameProduct() {
        if (mOrderDetail != null) {
            return mOrderDetail.getProductName();
        }
        return "";
    }

    public void onClickAcceptProduct() {
        //TODO Accept Product
    }

    public void onClickRejectProduct() {
        //TODO Reject Product
    }

    public ObservableInt getTextStatus() {
        return mTextStatus;
    }

    public void setTextStatus(ObservableInt textStatus) {
        mTextStatus = textStatus;
    }
}
