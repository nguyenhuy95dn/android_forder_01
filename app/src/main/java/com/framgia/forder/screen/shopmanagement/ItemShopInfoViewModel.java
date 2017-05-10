package com.framgia.forder.screen.shopmanagement;

import android.databinding.ObservableInt;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.ShopInfo;
import com.framgia.forder.data.model.ShopManagement;

/**
 * Created by levutantuan on 5/8/17.
 */

public class ItemShopInfoViewModel {
    private final ShopInfo mShopInfo;
    private final Domain mDomain;
    private final ObservableInt mButtonJoinDomain;
    private final ObservableInt mTextButton;
    private final ObservableInt mTextStatus;

    ItemShopInfoViewModel(ShopManagement shopManagement, int position) {
        mShopInfo = shopManagement.getShopInfos().get(position);
        mDomain = shopManagement.getShopDomains().get(position);
        mButtonJoinDomain = new ObservableInt();
        mTextButton = new ObservableInt();
        mTextStatus = new ObservableInt();
    }

    public String getDomainId() {
        if (mShopInfo != null) {
            return String.valueOf(mShopInfo.getDomainId());
        }
        return "";
    }

    public String getDomainName() {
        if (mShopInfo != null) {
            return mShopInfo.getDomainName();
        }
        return "";
    }

    public String getNumberUser() {
        if (mShopInfo != null) {
            return String.valueOf(mShopInfo.getNumberUser());
        }
        return String.valueOf(0);
    }

    public String getNumberShop() {
        if (mShopInfo != null) {
            return String.valueOf(mShopInfo.getNumberShop());
        }
        return String.valueOf(0);
    }

    public String getNumberProduct() {
        if (mShopInfo != null) {
            return String.valueOf(mShopInfo.getNumberProduct());
        }
        return String.valueOf(0);
    }

    public String getStatus() {
        if (mDomain != null) {

            switch (mDomain.getStatus()) {
                case NONE:
                    onRequest();
                    mTextStatus.set(R.color.transparent);
                    break;
                case PENDING:
                    onCancel();
                    mTextStatus.set(R.drawable.border_pedding);
                    break;
                case APPROVED:
                    onCancel();
                    mTextStatus.set(R.drawable.border_status);
                    break;
                default:
                    break;
            }
            return String.valueOf(mDomain.getStatus());
        }
        return "";
    }

    public void onRequestCancelJoinDomainClick() {
        if (mButtonJoinDomain.get() == R.drawable.button_blue) {
            onCancel();
        } else {
            onRequest();
        }
    }

    private void onRequest() {
        mButtonJoinDomain.set(R.drawable.button_blue);
        mTextButton.set(R.string.request);
    }

    private void onCancel() {
        mButtonJoinDomain.set(R.drawable.button_red);
        mTextButton.set(R.string.cancel);
    }

    public ObservableInt getButtonJoinDomain() {
        return mButtonJoinDomain;
    }

    public ObservableInt getTextButton() {
        return mTextButton;
    }

    public ObservableInt getTextStatus() {
        return mTextStatus;
    }
}
