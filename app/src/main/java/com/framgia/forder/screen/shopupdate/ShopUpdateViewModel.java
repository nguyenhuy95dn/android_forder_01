package com.framgia.forder.screen.shopupdate;

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
import com.framgia.forder.data.model.CollectionAvatar;
import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.RegisterShopInfo;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.io.FileNotFoundException;

import static com.framgia.forder.screen.shopupdate.ShopUpdateFragment.UPDATE_SELECT_AVATAR;
import static com.framgia.forder.screen.shopupdate.ShopUpdateFragment.UPDATE_SELECT_COVER_IMAGE;

/**
 * Exposes the data to be used in the ShopUpdate screen.
 */

public class ShopUpdateViewModel extends BaseObservable
        implements ShopUpdateContract.ViewModel, TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "ShopUpdateViewModel";
    private static final int FLAG_OPEN_TIME = 1;
    private static final int FLAG_REJECT_TIME = 2;

    private final Context mContext;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private ShopUpdateContract.Presenter mPresenter;
    private String mName;
    private String mDescription;
    private String mOpenTime;
    private String mTimeAutoReject;
    private boolean mOpenForever;
    private String mNameError;
    private String mDescriptionError;
    private int mFlag;
    private final Shop mShop;
    private String mImageCover;
    private String mImageAvatar;
    private boolean mIsClickImageAvatar;
    private boolean mIsClickImageCover;
    private final Navigator mNavigatorForStartGallery;

    ShopUpdateViewModel(Context context, DialogManager dialogManager, Navigator navigator,
            ShopManagement shopManagement, Navigator navigatorForStartGallery) {
        this.mContext = context;
        mNavigator = navigator;
        mDialogManager = dialogManager.dialogTimePicker(this);
        mShop = shopManagement.getShop();
        mName = mShop.getName();
        mDescription = mShop.getDescription();
        mOpenTime = mShop.getTimeOpenShop();
        mOpenForever = mShop.isOpenForever();
        mTimeAutoReject = mShop.getTimeAutoReject();
        mNavigatorForStartGallery = navigatorForStartGallery;
        mImageAvatar = mShop.getCollectionAvatar().getImage().getUrl();
        mImageCover = mShop.getCoverImage().getImage().getUrl();
        mIsClickImageAvatar = true;
        mIsClickImageCover = true;
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
    public void setPresenter(ShopUpdateContract.Presenter presenter) {
        mPresenter = presenter;
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
            mTimeAutoReject = hourString + mContext.getString(R.string.two_dot) + minuteString;
            notifyPropertyChanged(BR.timeAutoReject);
        }
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
    public void onUpdateShopSuccess() {
        mNavigator.showToast(R.string.update_success);
        mNavigator.goBackChildFragment();
    }

    @Override
    public void onUpdateShopError(BaseException error) {
        mNavigator.showToast(error.getMessage());
    }

    @Override
    public void setImageCover(String imageCover) {
        mImageCover = imageCover;
        notifyPropertyChanged(BR.imageCover);
        mIsClickImageCover = false;
    }

    @Override
    public void setImageAvatar(String imageAvatar) {
        mImageAvatar = imageAvatar;
        notifyPropertyChanged(BR.imageAvatar);
        mIsClickImageAvatar = false;
    }

    @Bindable
    public String getNameError() {
        return mNameError;
    }

    @Bindable
    public String getDescriptionError() {
        return mDescriptionError;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    @Bindable
    public String getOpenTime() {
        return mOpenTime;
    }

    @Bindable
    public boolean isOpenForever() {
        return mOpenForever;
    }

    @Bindable
    public String getTimeAutoReject() {
        return mTimeAutoReject;
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    @Bindable
    public String getImageCover() {
        return mImageCover;
    }

    @Bindable
    public String getImageAvatar() {
        return mImageAvatar;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    public void setDescription(String description) {
        mDescription = description;
        notifyPropertyChanged(BR.description);
    }

    public void onClickChooseAutoRejectTime() {
        mFlag = FLAG_REJECT_TIME;
        mDialogManager.showTimePickerDialog();
    }

    public void onClickChooseOpenTime() {
        mFlag = FLAG_OPEN_TIME;
        mDialogManager.showTimePickerDialog();
    }

    public void checked() {
        mOpenForever = !mOpenForever;
    }

    public void onClickChooseAvatar() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mNavigatorForStartGallery.startActivityForResultFromFragment(intent, UPDATE_SELECT_AVATAR);
    }

    public void onClickChooseCoverImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mNavigatorForStartGallery.startActivityForResultFromFragment(intent,
                UPDATE_SELECT_COVER_IMAGE);
    }

    public void onClickUpdateShop() {
        if (!mPresenter.validateDataInput(mName, mDescription)) {
            return;
        }
        try {
            UpdateShopRequest updateShopRequest = new UpdateShopRequest();
            RegisterShopInfo updateShop = new RegisterShopInfo();
            if (mIsClickImageAvatar) {
                updateShop.setCollectionAvatar(new CollectionAvatar(new Image(mImageAvatar)));
            } else {
                updateShopRequest.setImageAvatar(
                        mContext.getContentResolver().openInputStream(Uri.parse(mImageAvatar)));
            }
            if (mIsClickImageCover) {
                updateShop.setCoverImage(new CollectionImage(new Image(mImageCover)));
            } else {
                updateShopRequest.setImageCover(
                        mContext.getContentResolver().openInputStream(Uri.parse(mImageCover)));
            }
            updateShopRequest.setShopId(mShop.getId());

            updateShop.setName(mName);
            updateShop.setDescription(mDescription);
            updateShop.setTimeAutoClose(mOpenTime);
            updateShop.setOpenForever(mOpenForever);
            updateShop.setTimeAutoReject(mTimeAutoReject);
            updateShopRequest.setShop(updateShop);
            mPresenter.onUpdateShop(updateShopRequest);
            mIsClickImageAvatar = true;
            mIsClickImageCover = true;
        } catch (FileNotFoundException e) {
            Log.e(TAG, "", e);
        }
    }
}
