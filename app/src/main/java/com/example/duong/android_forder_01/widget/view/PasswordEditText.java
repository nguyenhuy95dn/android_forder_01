package com.example.duong.android_forder_01.widget.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.duong.android_forder_01.R;

public class PasswordEditText extends EditText {
    private final int ALPHA = (int) (255 * .70f);
    private Drawable mEye, mEyeStrike;
    private boolean mVisible;
    private boolean mUseStrike;
    private Drawable mDrawable;

    public PasswordEditText(Context context) {
        super(context);
        start(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        start(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        start(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr,
                            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        start(attrs);
    }

    private void start(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().getTheme()
                .obtainStyledAttributes(attrs, R.styleable.PasswordEditText, 0, 0);
            this.mUseStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike, false);
        }
        mEye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black).mutate();
        mEyeStrike =
            ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black).mutate();
        config();
    }

    private void config() {
        setInputType(InputType.TYPE_CLASS_TEXT |
            (mVisible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        mDrawable = mUseStrike && !mVisible ? mEyeStrike : mEye;
        mDrawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], mDrawable,
            drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP &&
            event.getX() >= (getRight() - mDrawable.getBounds().width())) {
            mVisible = !mVisible;
            config();
        }
        return super.onTouchEvent(event);
    }
}
