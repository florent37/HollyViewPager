package com.github.florent37.beautifulviewpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by florentchampigny on 07/08/15.
 */
public class BeautifulViewPagerBus {

    public static Map<Context, BeautifulViewPager> map = new HashMap<>();

    public static void register(Context context, BeautifulViewPager beautifulViewPager) {
        map.put(context, beautifulViewPager);
    }

    public static void unregister(Context context){
        map.remove(context);
    }

    public static void registerScrollView(Context context, ObservableScrollView scrollView) {
        BeautifulViewPager beautifulViewPager = map.get(context);
        if(beautifulViewPager != null)
            beautifulViewPager.registerScrollView(scrollView);
    }

    public static void registerRecyclerView(Context context, RecyclerView recyclerView) {
        BeautifulViewPager beautifulViewPager = map.get(context);
        if(beautifulViewPager != null)
            beautifulViewPager.registerRecyclerView(recyclerView);
    }
}
