package com.framgia.forder.widgets.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.framgia.forder.R;

/**
 * Created by tri on 27/04/2017.
 */

public class DialogManager {
    private Context mContext;
    private AlertDialog mDialog;

    public DialogManager(Context context) {
        mContext = context;
    }

    public DialogManager dialogwithNoTitleTwoButton(int message,
            DialogInterface.OnClickListener positiveButtonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(message)
                .setPositiveButton(R.string.action_yes, positiveButtonListener)
                .setNegativeButton(R.string.action_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        mDialog = builder.create();
        return this;
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
        mDialog.show();
    }

    public void dismiss() {
        if (mDialog == null) {
            return;
        }
        mDialog.dismiss();
    }
}
