package com.framgia.forder.screen.shopmanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableInt;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.ShopInfo;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.LeaveShopToDomainRequest;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Created by levutantuan on 5/8/17.
 */

public class ItemShopInfoViewModel {

    //TODO Edit Later File
    private final Context mContext;
    private final ShopInfo mShopInfo;
    private final Domain mDomain;
    private final Shop mShop;
    private ObservableInt mButtonJoinDomain;
    private ObservableInt mTextButton;
    private ObservableInt mTextStatus;
    private final DialogManager mDialogManager;

    ItemShopInfoViewModel(Context context, ShopManagement shopManagement, int position,
            DialogManager dialogManager) {
        this.mContext = context;
        mShopInfo = shopManagement.getShopInfos().get(position);
        mDomain = shopManagement.getShopDomains().get(position);
        mShop = shopManagement.getShop();
        mDialogManager = dialogManager;
        initValueStatus();
    }

    private void initValueStatus() {
        mButtonJoinDomain = new ObservableInt();
        mTextButton = new ObservableInt();
        mTextStatus = new ObservableInt();
        switch (mDomain.getStatus()) {
            case NONE:
                mButtonJoinDomain.set(R.drawable.button_blue);
                mTextButton.set(R.string.request);
                mTextStatus.set(R.color.transparent);
                break;
            case PENDING:
                mButtonJoinDomain.set(R.drawable.button_red);
                mTextButton.set(R.string.cancel);
                mTextStatus.set(R.drawable.border_pedding);
                break;
            case APPROVED:
                mButtonJoinDomain.set(R.drawable.button_red);
                mTextButton.set(R.string.cancel);
                mTextStatus.set(R.drawable.border_status);
                break;
            default:
                break;
        }
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
            return String.valueOf(mDomain.getStatus());
        }
        return "";
    }

    public void onRequestCancelJoinDomainClick() {

        if (mDomain.getStatus() == Domain.Status.NONE) {
            onRequestShopInDomain();
        } else if (mDomain.getStatus() == Domain.Status.APPROVED
                || mDomain.getStatus() == Domain.Status.PENDING) {
            mDialogManager.dialogwithNoTitleTwoButton(R.string.are_you_sure_you_want_to_cancel,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onCancelShopInDomain();
                        }
                    });
            mDialogManager.show();
        }
    }

    private void onRequestShopInDomain() {
        ApplyShopToDomainRequest applyShopToDomainRequest = new ApplyShopToDomainRequest();
        applyShopToDomainRequest.setDomainId(mShopInfo.getDomainId());
        applyShopToDomainRequest.setShopName(mShop.getName());
        applyShopToDomainRequest.setNumberProduct(mShopInfo.getNumberProduct());
        applyShopToDomainRequest.setDomainName(mShopInfo.getDomainName());
        //        mShopDomainManagementListener.onRequestJoinDomain(applyShopToDomainRequest);
    }

    private void onCancelShopInDomain() {
        LeaveShopToDomainRequest leaveShopToDomainRequest = new LeaveShopToDomainRequest();
        leaveShopToDomainRequest.setDomainId(mShopInfo.getDomainId());
        leaveShopToDomainRequest.setShopId(mShop.getId());
        //        mShopDomainManagementListener.onCancleJoinDomain(leaveShopToDomainRequest);
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
