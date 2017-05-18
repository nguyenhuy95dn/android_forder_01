package com.framgia.forder.screen.createshop;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;
import com.framgia.forder.R;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Exposes the data to be used in the Createshop screen.
 */

public class CreateshopViewModel
        implements CreateshopContract.ViewModel, TimePickerDialog.OnTimeSetListener {

    private CreateshopContract.Presenter mPresenter;
    private final Context mContext;
    private DialogManager mDialogManager;

    public CreateshopViewModel(Context context, DialogManager dialogManager) {
        mContext = context;
        mDialogManager = dialogManager.dialogTimePicker(this);
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
        mDialogManager.showTimePickerDialog();
    }

    public void onClickChooseAutoRejectTime() {
        mDialogManager.showTimePickerDialog();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hourString =
                hourOfDay < 10 ? mContext.getString(R.string.zero) + hourOfDay : "" + hourOfDay;
        String minuteString =
                minute < 10 ? mContext.getString(R.string.zero) + minute : "" + minute;
    }
}
