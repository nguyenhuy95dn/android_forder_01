package com.framgia.forder.screen.createshop;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.TimePicker;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Exposes the data to be used in the Createshop screen.
 */

public class CreateshopViewModel extends BaseObservable
        implements CreateshopContract.ViewModel, TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "CreateshopViewModel";
    private static final int FLAG_OPEN_TIME = 1;
    private static final int FLAG_REJECT_TIME = 2;

    private CreateshopContract.Presenter mPresenter;
    private final Context mContext;
    private final DialogManager mDialogManager;
    private boolean isChecked;
    private String mNameError;
    private String mDescriptionError;
    private String mName;
    private String mDescription;
    private String mOpenTime;
    private String mAutoRejectTime;
    private boolean isOpenDaily;
    private int mFlag;
    private final Navigator mNavigator;

    public CreateshopViewModel(Context context, DialogManager dialogManager, Navigator navigator) {
        mContext = context;
        mDialogManager = dialogManager.dialogTimePicker(this);
        mNavigator = navigator;
        isChecked = true;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(CreateshopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onClickChooseOpenTime() {
        mFlag = FLAG_OPEN_TIME;
        mDialogManager.showTimePickerDialog();
    }

    public void onClickChooseAutoRejectTime() {
        mFlag = FLAG_REJECT_TIME;
        mDialogManager.showTimePickerDialog();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hourString =
                hourOfDay < 10 ? mContext.getString(R.string.zero) + hourOfDay : "" + hourOfDay;
        String minuteString =
                minute < 10 ? mContext.getString(R.string.zero) + minute : "" + minute;
        if (mFlag == FLAG_OPEN_TIME) {
            mOpenTime = hourString + mContext.getString(R.string.two_dot) + minuteString;
            notifyPropertyChanged(BR.openTime);
        } else {
            mAutoRejectTime = hourString + mContext.getString(R.string.two_dot) + minuteString;
            notifyPropertyChanged(BR.autoRejectTime);
        }
    }

    public void checked() {
        if (isChecked) {
            isChecked = false;
            isOpenDaily = true;
        } else {
            isChecked = true;
            isOpenDaily = false;
        }
    }

    public void onClickChooseAvatar() {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mNavigator.startActivityForResult(Intent.createChooser(i, ""), 0);
    }

    public void onClickChooseCoverImage() {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mNavigator.startActivityForResult(Intent.createChooser(i, ""), 0);
    }

    public void onClickCreateShop() {
        if (!mPresenter.validateDataInput(mName, mDescription)) {
            return;
        }
        RegisterShopRequest request = new RegisterShopRequest();
        Shop shop = new Shop();
        shop.setName(mName);
        shop.setDescription(mDescription);
        shop.setTimeAutoClose(mOpenTime);
        shop.setOpenForever(isOpenDaily);
        shop.setTimeAutoReject(mAutoRejectTime);
        shop.setCoverImage(null);
        shop.setCollectionAvatar(null);
        request.setShop(shop);
        mPresenter.onRequestRegisterShop(request);
    }

    @Bindable
    public String getName() {
        return mDescription;
    }

    @Bindable
    public String getDescription() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    public void onInputNameError() {
        mNameError = mContext.getString(R.string.name_is_empty);
        notifyPropertyChanged(BR.nameError);
    }

    @Override
    public void onInputDescriptionError() {
        mDescriptionError = mContext.getString(R.string.description_is_empty);
        notifyPropertyChanged(BR.descriptionError);
    }

    @Override
    public void onRequestRegisterShopSuccess() {
        // Todo show dialog message
    }

    @Override
    public void onRequestRegisterShopError(BaseException error) {
        // Todo show dialog message
    }

    @Bindable
    public String getNameError() {
        return mNameError;
    }

    public void setNameError(String nameError) {
        mNameError = nameError;
    }

    @Bindable
    public String getDescriptionError() {
        return mDescriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        mDescriptionError = descriptionError;
    }

    @Bindable
    public String getOpenTime() {
        return mOpenTime;
    }

    @Bindable
    public String getAutoRejectTime() {
        return mAutoRejectTime;
    }
}
