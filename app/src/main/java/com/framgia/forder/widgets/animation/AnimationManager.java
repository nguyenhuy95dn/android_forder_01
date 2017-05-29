package com.framgia.forder.widgets.animation;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.framgia.forder.R;

/**
 * Created by levutantuan on 5/23/17.
 */

public class AnimationManager {
    private final Context mContext;
    private int lastPosition = -1;

    public AnimationManager(Context context) {
        mContext = context;
    }

    public AnimationManager animationSlideInLeft(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_left_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
        return this;
    }

    public AnimationManager animationSlideTopIn(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_top_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
        return this;
    }
}
