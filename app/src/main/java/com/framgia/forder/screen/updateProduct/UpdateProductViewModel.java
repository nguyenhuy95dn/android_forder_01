package com.framgia.forder.screen.updateProduct;

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
import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.RegisterProductInfo;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.UpdateProductRequest;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.forder.screen.createProduct.CreateProductFragment.REQUEST_SELECT_IMAGE;
import static com.framgia.forder.utils.Constant.FLAG_END_HOUR;
import static com.framgia.forder.utils.Constant.FLAG_OPEN_HOUR;

/**
 * Exposes the data to be used in the UpdateProduct screen.
 */

public class UpdateProductViewModel extends BaseObservable
        implements UpdateProductContract.ViewModel, TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "UpdateProductViewModel";

    private UpdateProductContract.Presenter mPresenter;
    private final Context mContext;
    private final Product mProduct;
    private final ArrayAdapter<String> mAdapter;
    private final Navigator mNavigatorForStartGallery;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private final List<Category> mCategories;
    private String mImage;
    private String mName;
    private String mDescription;
    private String mPrice;
    private String mStartHour;
    private String mEndHour;
    private String mStatus;
    private int mShopId;
    private String mNameError;
    private String mDescriptionError;
    private boolean isClickChooseImage;
    private boolean isSwitched;
    private int mSelectedTypePosition;
    private int mFlag;
    private int mProductId;

    UpdateProductViewModel(Context context, Product product, DialogManager dialogManager,
            ArrayAdapter<String> adapter, Navigator navigatorForStartGallery, Navigator navigator) {
        mContext = context;
        mProduct = product;
        mDialogManager = dialogManager.dialogTimePicker(this);
        mAdapter = adapter;
        mNavigatorForStartGallery = navigatorForStartGallery;
        mNavigator = navigator;
        getDetailProduct(product);
        mCategories = new ArrayList<>();
        isClickChooseImage = true;
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
    public void setPresenter(UpdateProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    @Bindable
    public String getPrice() {
        return mPrice;
    }

    @Bindable
    public String getStartHour() {
        return mStartHour;
    }

    @Bindable
    public String getEndHour() {
        return mEndHour;
    }

    @Bindable
    public String getImage() {
        return mImage == null ? "" : mImage;
    }

    @Override
    public void onUpdateProductSuccess() {
        mNavigator.showToast(R.string.update_successful);
        mNavigator.goBackChildFragment();
    }

    @Override
    public void onUpdateProductError(BaseException error) {
        mNavigator.showToast(error.getMessage());
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

    public void setImage(String image) {
        mImage = image;
        notifyPropertyChanged(BR.image);
        isClickChooseImage = false;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public void setStartHour(String startHour) {
        mStartHour = startHour;
    }

    public void setEndHour(String endHour) {
        mEndHour = endHour;
    }

    public Boolean changeStatus() {
        return isClickChooseImage;
    }

    public void onSwitchChange() {
        if (isSwitched) {
            isSwitched = false;
            mStatus = mContext.getString(R.string.active);
            mNavigator.showToast(R.string.active);
        } else {
            isSwitched = true;
            mStatus = mContext.getString(R.string.inactive);
            mNavigator.showToast(R.string.inactive);
        }
    }

    public ArrayAdapter<String> getAdapterCategory() {
        return mAdapter;
    }

    @Override
    public void onGetCategoriesSuccess(List<Category> categories) {
        if (categories == null) {
            return;
        }
        mCategories.clear();
        mCategories.addAll(categories);
        for (Category category : categories) {
            mAdapter.add(category.getName());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetCategoriesError(BaseException error) {
        //Todo dev later
    }

    public int getSelectedTypePosition() {
        return mSelectedTypePosition;
    }

    public void setSelectedTypePosition(int selectedTypePosition) {
        mSelectedTypePosition = selectedTypePosition;
    }

    public void onClickChooseStartHour() {
        mFlag = FLAG_OPEN_HOUR;
        mDialogManager.showTimePickerDialog();
    }

    public void onClickChooseEndHour() {
        mFlag = FLAG_END_HOUR;
        mDialogManager.showTimePickerDialog();
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

    public void onClickChooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mNavigatorForStartGallery.startActivityForResultFromFragment(intent, REQUEST_SELECT_IMAGE);
    }

    public void onClickUpdateProduct() {
        if (!mPresenter.validateDataInput(mName, mDescription)) {
            return;
        }
        try {
            if (mImage == null) {
                mNavigator.showToast(R.string.you_must_choose_image);
                return;
            }
            UpdateProductRequest request = new UpdateProductRequest();
            RegisterProductInfo registerProductInfo = new RegisterProductInfo();
            if (isClickChooseImage) {
                registerProductInfo.setImage(new CollectionImage(new Image(mImage)));
            } else {
                request.setImage(mContext.getContentResolver().openInputStream(Uri.parse(mImage)));
            }
            registerProductInfo.setName(mName);
            registerProductInfo.setDescription(mDescription);
            registerProductInfo.setPrice(Double.parseDouble(mPrice));
            registerProductInfo.setStatus(mStatus);
            registerProductInfo.setStartHour(mStartHour);
            registerProductInfo.setEndHour(mEndHour);
            registerProductInfo.setShopId(String.valueOf(mShopId));
            registerProductInfo.setCategoryId(String.valueOf(getCategory().getId()));
            request.setProduct(registerProductInfo);
            request.setProductId(mProductId);
            mPresenter.updateProduct(request);
            isClickChooseImage = true;
        } catch (FileNotFoundException e) {
            Log.e(TAG, "", e);
        }
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

    private void getDetailProduct(Product product) {
        if (product.getCollectionImage() != null
                && product.getCollectionImage().getImage() != null
                && product.getCollectionImage().getImage().getUrl() != null) {
            mImage = product.getCollectionImage().getImage().getUrl();
        }
        mName = product.getName();
        mDescription = product.getDescription();
        mPrice = String.valueOf(product.getPrice());
        mStartHour = product.getFormatStartHour();
        mEndHour = product.getFormatEndHour();
        mStatus = product.getStatus();
        mShopId = product.getShopId();
        mProductId = product.getId();
    }

    private Category getCategory() {
        return mCategories.get(mSelectedTypePosition);
    }
}
