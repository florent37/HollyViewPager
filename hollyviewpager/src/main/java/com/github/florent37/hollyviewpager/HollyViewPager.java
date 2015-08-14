package com.github.florent37.hollyviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 07/08/15.
 */
public class HollyViewPager extends FrameLayout {

    protected HollyViewPagerAnimator animator;
    protected HollyViewPagerSettings settings = new HollyViewPagerSettings();

    protected HorizontalScrollView headerScroll;
    protected ViewGroup headerLayout;
    protected ViewPager viewPager;

    protected HollyViewPagerConfigurator configurator;

    protected List<HeaderHolder> headerHolders = new ArrayList<>();

    public HollyViewPager(Context context) {
        super(context);
    }

    public HollyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        settings.handleAttributes(context, attrs);
    }

    public HollyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        settings.handleAttributes(context, attrs);
    }

    public HollyViewPagerConfigurator getConfigurator() {
        return configurator;
    }

    public void setConfigurator(HollyViewPagerConfigurator configurator) {
        this.configurator = configurator;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        HollyViewPagerBus.register(getContext(), this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        HollyViewPagerBus.unregister(getContext());
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addView(LayoutInflater.from(getContext()).inflate(R.layout.holly_view_pager, this, false));

        viewPager = (ViewPager) findViewById(R.id.bfp_viewPager);
        headerScroll = (HorizontalScrollView) findViewById(R.id.bfp_headerScroll);
        headerLayout = (ViewGroup) findViewById(R.id.bfp_headerLayout);

        {
            ViewGroup.LayoutParams layoutParams = headerLayout.getLayoutParams();
            layoutParams.height = this.settings.headerHeightPx;
            headerLayout.setLayoutParams(layoutParams);
        }

        animator = new HollyViewPagerAnimator(this);
    }

    public void setAdapter(PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
        animator.fillHeader(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount()); //TODO remove
    }

    public void registerRecyclerView(RecyclerView recyclerView) {
        animator.registerRecyclerView(recyclerView);
    }

    public void registerScrollView(ObservableScrollView scrollView) {
        animator.registerScrollView(scrollView);
    }
}