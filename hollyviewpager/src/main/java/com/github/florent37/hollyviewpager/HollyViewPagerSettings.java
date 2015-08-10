package com.github.florent37.hollyviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * Created by florentchampigny on 10/08/15.
 */
public class HollyViewPagerSettings {

    protected int headerHeightPx;
    protected int headerHeight;

    protected void handleAttributes(Context context, AttributeSet attrs) {
        try {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.HollyViewPager);

            {
                headerHeightPx = styledAttrs.getDimensionPixelOffset(R.styleable.HollyViewPager_hvp_headerHeight, -1);
                if(headerHeightPx == -1){
                    headerHeightPx = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
                }
                headerHeight = Math.round(pxToDp(headerHeightPx, context)); //convert to dp
            }

            styledAttrs.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static float pxToDp(float px, Context context) {
        return px / context.getResources().getDisplayMetrics().density;
    }
}
