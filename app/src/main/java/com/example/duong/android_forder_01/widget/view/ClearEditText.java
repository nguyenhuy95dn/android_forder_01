package com.example.duong.android_forder_01.widget.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.duong.android_forder_01.R;

public class ClearEditText extends EditText {
    private Drawable mCrossX;
    private Drawable mNoneCrossX;
    private Drawable mDrawable;
    private boolean mVisible;

    public ClearEditText(Context context) {
        super(context);
        start();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        start();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        start();
    }

    private void start() {
        mCrossX = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black).mutate();
        mNoneCrossX = ContextCompat
            .getDrawable(getContext(), android.R.drawable.screen_background_light_transparent)
            .mutate();
        config();
    }

    private void config() {
        Drawable[] drawables = getCompoundDrawables();
        mDrawable = mVisible ? mCrossX : mNoneCrossX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], mDrawable,
            drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction() &&
            event.getX() >= (getRight() - mDrawable.getBounds().width())) {
            setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter == 0 && start == 0) mVisible = false;
        else mVisible = true;
        config();
    }
}
