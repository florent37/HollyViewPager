package com.github.florent37.hollyviewpager;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Created by florentchampigny on 10/08/15.
 */
public class HollyViewPagerPlaceholder extends View {


    public HollyViewPagerPlaceholder(Context context) {
        super(context);
    }

    public HollyViewPagerPlaceholder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HollyViewPagerPlaceholder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HollyViewPagerPlaceholder(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void setHeaderHeight() {
        //get the HollyViewPagerAnimator attached to this activity
        //to retrieve the declared header height
        //and set it as current view height (+10dp margin)

        HollyViewPager pager = HollyViewPagerBus.get(getContext());
        if (pager != null) {
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = pager.settings.headerHeightPx;
            super.setLayoutParams(params);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    setHeaderHeight();
                    getViewTreeObserver().removeOnPreDrawListener(this);
                    return false;
                }
            });
        }
    }

}
