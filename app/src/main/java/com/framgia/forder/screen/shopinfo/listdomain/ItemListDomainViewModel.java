package com.framgia.forder.screen.shopinfo.listdomain;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import com.framgia.forder.R;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.response.DomainToRequestShopResponse;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Created by levutantuan on 6/23/17.
 */

public class ItemListDomainViewModel {

    private final DomainToRequestShopResponse.DomainToRequest mDomain;
    private int mButtonJoinDomain;
    private int mTextButton;
    private int mTextStatusColor;
    private final DialogManager mDialogManager;
    private final ListDomainAdapter.ShopDomainManagementListener mDomainManagementListener;

    ItemListDomainViewModel(@NonNull Context context,
            DomainToRequestShopResponse.DomainToRequest domain,
            ListDomainAdapter.ShopDomainManagementListener domainManagementListener) {
        mDomain = domain;
        mDialogManager = new DialogManager(context);
        mDomainManagementListener = domainManagementListener;
        initValueStatus();
    }

    public String getDomainName() {
        return mDomain.getDomainName();
    }

    public String getNumberUser() {
        return String.valueOf(mDomain.getNumberOfUsers());
    }

    public String getNumberShop() {
        return String.valueOf(mDomain.getNumberOfShops());
    }

    public String getNumberProduct() {
        return String.valueOf(mDomain.getNumberOfProducts());
    }

    public String getStatus() {
        return String.valueOf(mDomain.getStatus());
    }

    private void initValueStatus() {
        switch (mDomain.getStatus()) {
            case NONE:
                setButtonJoinDomain(R.drawable.button_blue);
                setTextButton(R.string.request);
                setTextStatusColor(Color.GRAY);
                break;
            case PENDING:
                setButtonJoinDomain(R.drawable.button_red);
                setTextButton(R.string.cancel);
                setTextStatusColor(Color.BLUE);
                break;
            case APPROVED:
                setButtonJoinDomain(R.drawable.button_red);
                setTextButton(R.string.cancel);
                setTextStatusColor(Color.RED);
                break;
            default:
                break;
        }
    }

    public int getButtonJoinDomain() {
        return mButtonJoinDomain;
    }

    private void setButtonJoinDomain(int buttonJoinDomain) {
        mButtonJoinDomain = buttonJoinDomain;
    }

    public int getTextButton() {
        return mTextButton;
    }

    private void setTextButton(int textButton) {
        mTextButton = textButton;
    }

    public int getTextStatusColor() {
        return mTextStatusColor;
    }

    private void setTextStatusColor(int textStatusColor) {
        mTextStatusColor = textStatusColor;
    }

    public void onClickRequestOrCancelDomain() {
        if (mDomain.getStatus() == DomainToRequestShopResponse.DomainToRequest.Status.NONE) {
            onApplyToDomain();
        } else if (mDomain.getStatus()
                == DomainToRequestShopResponse.DomainToRequest.Status.APPROVED
                || mDomain.getStatus()
                == DomainToRequestShopResponse.DomainToRequest.Status.PENDING) {
            mDialogManager.dialogwithNoTitleTwoButton(R.string.are_you_sure_you_want_to_cancel,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onLeaveToDomain();
                        }
                    });
            mDialogManager.show();
        }
    }

    private void onApplyToDomain() {
        ApplyShopToDomainRequest applyShopToDomainRequest = new ApplyShopToDomainRequest();
        applyShopToDomainRequest.setDomainId(mDomain.getDomainId());
        mDomainManagementListener.onApplyToDomain(applyShopToDomainRequest);
    }

    private void onLeaveToDomain() {
        mDomainManagementListener.onLeaveToDomain(mDomain.getDomainId());
    }
}
