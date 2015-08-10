package com.github.florent37.hollyviewpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by florentchampigny on 07/08/15.
 */
public class HollyViewPagerBus {

    public static Map<Context, HollyViewPager> map = new HashMap<>();

    public static void register(Context context, HollyViewPager hollyViewPager) {
        map.put(context, hollyViewPager);
    }

    public static void unregister(Context context){
        map.remove(context);
    }

    public static void registerScrollView(Context context, ObservableScrollView scrollView) {
        HollyViewPager hollyViewPager = map.get(context);
        if(hollyViewPager != null)
            hollyViewPager.registerScrollView(scrollView);
    }

    public static void registerRecyclerView(Context context, RecyclerView recyclerView) {
        HollyViewPager hollyViewPager = map.get(context);
        if(hollyViewPager != null)
            hollyViewPager.registerRecyclerView(recyclerView);
    }

    public static HollyViewPager get(Context context){
        return map.get(context);
    }
}
