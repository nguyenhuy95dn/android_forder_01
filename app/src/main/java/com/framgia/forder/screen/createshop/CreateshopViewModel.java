package com.framgia.forder.screen.createshop;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.util.Log;
import android.widget.TimePicker;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.RegisterShopInfo;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.io.FileNotFoundException;

import static com.framgia.forder.screen.createshop.CreateshopFragment.REQUEST_SELECT_AVATAR;
import static com.framgia.forder.screen.createshop.CreateshopFragment.REQUEST_SELECT_COVER_IMAGE;

/**
 * Exposes the data to be used in the Createshop screen.
 */

public class CreateshopViewModel extends BaseObservable
        implements CreateshopContract.ViewModel, TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "CreateshopViewModel";
    private static final int FLAG_OPEN_TIME = 1;
    private static final int FLAG_REJECT_TIME = 2;
    private final Context mContext;
    private final DialogManager mDialogManager;
    private final Navigator mNavigatorForStartGallery;
    private final Navigator mNavigator;
    private CreateshopContract.Presenter mPresenter;
    private boolean isChecked;
    private String mNameError;
    private String mDescriptionError;
    private String mName;
    private String mDescription;
    private String mOpenTime;
    private String mAutoRejectTime;
    private boolean isOpenDaily;
    private int mFlag;
    private String mImageCover;
    private String mImageAvatar;

    CreateshopViewModel(Context context, DialogManager dialogManager,
            Navigator navigatorForStartGallery, Navigator navigator) {
        mContext = context;
        mDialogManager = dialogManager;
        mDialogManager.dialogTimePicker(this);
        mNavigatorForStartGallery = navigatorForStartGallery;
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
        mNavigator.showToastCustomActivity(R.string.create_shop_successful);
        mNavigator.goBackChildFragment();
    }

    @Override
    public void onRequestRegisterShopError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void setImageCover(String imageCover) {
        mImageCover = imageCover;
        notifyPropertyChanged(BR.imageCover);
    }

    @Override
    public void setImageAvatar(String imageAvatar) {
        mImageAvatar = imageAvatar;
        notifyPropertyChanged(BR.imageAvatar);
    }

    @Override
    public void onShowProgressDialog() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressDialog() {
        mDialogManager.dismissProgressDialog();
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

    public void onClickChooseOpenTime() {
        mFlag = FLAG_OPEN_TIME;
        mDialogManager.showTimePickerDialog();
    }

    public void onClickChooseAutoRejectTime() {
        mFlag = FLAG_REJECT_TIME;
        mDialogManager.showTimePickerDialog();
    }

    public void onChecked() {
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
        mNavigatorForStartGallery.startActivityForResultFromFragment(i, REQUEST_SELECT_AVATAR);
    }

    public void onClickChooseCoverImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mNavigatorForStartGallery.startActivityForResultFromFragment(intent,
                REQUEST_SELECT_COVER_IMAGE);
    }

    public void onClickCreateShop() {
        if (!mPresenter.validateDataInput(mName, mDescription)) {
            return;
        }
        try {
            if (mImageCover == null || mImageAvatar == null) {
                mNavigator.showToastCustomActivity(R.string.you_must_choose_image);
                return;
            }
            RegisterShopRequest request = new RegisterShopRequest();
            request.setImageCover(
                    mContext.getContentResolver().openInputStream(Uri.parse(mImageCover)));
            request.setImageAvatar(
                    mContext.getContentResolver().openInputStream(Uri.parse(mImageAvatar)));
            RegisterShopInfo registerShopInfo = new RegisterShopInfo();
            registerShopInfo.setName(mName);
            registerShopInfo.setDescription(mDescription);
            registerShopInfo.setTimeAutoClose(mOpenTime);
            registerShopInfo.setOpenForever(isOpenDaily);
            registerShopInfo.setTimeAutoReject(mAutoRejectTime);
            request.setShop(registerShopInfo);
            mPresenter.onRequestRegisterShop(request);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "", e);
        }
    }

    @Bindable
    public String getName() {
        return mDescription;
    }

    public void setName(String name) {
        mName = name;
    }

    @Bindable
    public String getDescription() {
        return mName;
    }

    public void setDescription(String description) {
        mDescription = description;
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

    @Bindable
    public String getImageCover() {
        return mImageCover;
    }

    @Bindable
    public String getImageAvatar() {
        return mImageAvatar;
    }
}
