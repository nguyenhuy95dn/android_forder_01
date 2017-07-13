package com.framgia.forder.screen.createProduct;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.model.RegisterProductInfo;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.RegisterProductRequest;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.forder.screen.createProduct.CreateProductFragment.REQUEST_SELECT_IMAGE;

/**
 * Exposes the data to be used in the Createproduct screen.
 */

public class CreateProductViewModel extends BaseObservable
        implements CreateProductContract.ViewModel, TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "CreateProductViewModel";
    private static final int FLAG_OPEN_HOUR = 1;
    private static final int FLAG_END_HOUR = 2;

    private final Context mContext;
    private final DialogManager mDialogManager;
    private final ArrayAdapter<String> mAdapter;
    private final Navigator mNavigator;
    private final List<Category> mCategories;
    private final Navigator mNavigatorForStartGallery;
    private final ShopManagement mShopManagement;
    private CreateProductContract.Presenter mPresenter;
    private String mName;
    private String mDescription;
    private String mPrice;
    private String mNameError;
    private String mDescriptionError;
    private String mStatus;
    private String mStartHour;
    private String mEndHour;
    private boolean isSwitched;
    private int mFlag;
    private int mSelectedTypePosition;
    private String mImage;

    CreateProductViewModel(Context context, DialogManager dialogManager,
            ArrayAdapter<String> adapter, Navigator navigatorForStartGallery, Navigator navigator,
            ShopManagement shopManagement) {
        mContext = context;
        mDialogManager = dialogManager;
        mDialogManager.dialogTimePicker(this);
        mAdapter = adapter;
        mNavigatorForStartGallery = navigatorForStartGallery;
        mNavigator = navigator;
        mShopManagement = shopManagement;
        mCategories = new ArrayList<>();
        isSwitched = true;
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
    public void setPresenter(CreateProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ArrayAdapter<String> getAdapterCategory() {
        return mAdapter;
    }

    @Override
    public void onGetCategoriesSuccess(List<Category> categories) {
        if (categories == null) {
            return;
        }
        mAdapter.clear();
        mCategories.clear();
        mCategories.addAll(categories);
        for (Category category : categories) {
            mAdapter.add(category.getName());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetCategoriesError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hourString =
                hourOfDay < 10 ? mContext.getString(R.string.zero) + hourOfDay : "" + hourOfDay;
        String minuteString =
                minute < 10 ? mContext.getString(R.string.zero) + minute : "" + minute;
        if (mFlag == FLAG_OPEN_HOUR) {
            mStartHour = hourString + mContext.getString(R.string.two_dot) + minuteString;
            notifyPropertyChanged(BR.startHour);
        } else {
            mEndHour = hourString + mContext.getString(R.string.two_dot) + minuteString;
            notifyPropertyChanged(BR.endHour);
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
    public void setImage(String image) {
        mImage = image;
        notifyPropertyChanged(BR.image);
    }

    @Override
    public void onRegisterProductSuccess() {
        mNavigator.showToastCustomActivity(R.string.create_product_successful);
        mNavigator.goBackChildFragment();
    }

    @Override
    public void onRegisterProductError(BaseException error) {
        mNavigator.showToast(error.getMessage());
    }

    @Override
    public void onShowProgressDialog() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressDialog() {
        mDialogManager.dismissProgressDialog();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
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
    public String getStartHour() {
        return mStartHour;
    }

    public void setStartHour(String startHour) {
        mStartHour = startHour;
    }

    @Bindable
    public String getEndHour() {
        return mEndHour;
    }

    public void setEndHour(String endHour) {
        mEndHour = endHour;
    }

    public void onSwitchChange() {
        if (isSwitched) {
            isSwitched = false;
            mStatus = mContext.getString(R.string.active);
        } else {
            isSwitched = true;
            mStatus = mContext.getString(R.string.inactive);
        }
    }

    public void onClickCreateProduct() {
        if (!mPresenter.validateDataInput(mName, mDescription, mPrice)) {
            return;
        }
        try {
            if (mImage == null) {
                mNavigator.showToastCustomActivity(R.string.you_must_choose_image);
                return;
            }
            RegisterProductRequest request = new RegisterProductRequest();
            request.setImage(mContext.getContentResolver().openInputStream(Uri.parse(mImage)));
            RegisterProductInfo registerProductInfo = new RegisterProductInfo();
            registerProductInfo.setName(mName);
            registerProductInfo.setDescription(mDescription);
            registerProductInfo.setPrice(Double.parseDouble(mPrice));
            registerProductInfo.setStatus(mStatus);
            registerProductInfo.setStartHour(mStartHour);
            registerProductInfo.setEndHour(mEndHour);
            registerProductInfo.setCategoryId(String.valueOf(getCategory().getId()));
            registerProductInfo.setShopId(String.valueOf(mShopManagement.getShop().getId()));
            request.setProduct(registerProductInfo);
            mPresenter.registerProduct(request);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "", e);
        }
    }

    public void onClickChooseStartHour() {
        mFlag = FLAG_OPEN_HOUR;
        mDialogManager.showTimePickerDialog();
    }

    public void onClickChooseEndHour() {
        mFlag = FLAG_END_HOUR;
        mDialogManager.showTimePickerDialog();
    }

    public void onClickBack() {
        mNavigator.goBackChildFragment();
    }

    @Bindable
    public String getImage() {
        return mImage;
    }

    public void onClickChooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mNavigatorForStartGallery.startActivityForResultFromFragment(intent, REQUEST_SELECT_IMAGE);
    }

    public Category getCategory() {
        return mCategories.get(mSelectedTypePosition);
    }

    public int getSelectedTypePosition() {
        return mSelectedTypePosition;
    }

    public void setSelectedTypePosition(int selectedTypePosition) {
        mSelectedTypePosition = selectedTypePosition;
    }
}
