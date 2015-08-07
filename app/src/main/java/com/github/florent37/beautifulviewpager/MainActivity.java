package com.github.florent37.beautifulviewpager;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    int pageCount = 10;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.header)
    HorizontalScrollView header;

    @Bind(R.id.headerLayout)
    ViewGroup headerLayout;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    float initialHeaderHeight = -1;
    float finalHeaderHeight = -1;
    List<HeaderHolder> headerHolders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fillHeader();

        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //if(position%2==0)
                //    return new RecyclerViewFragment();
                //else
                return new ScrollViewFragment();
            }

            @Override
            public int getCount() {
                return pageCount;
            }
        });

        viewPager.setOffscreenPageLimit(pageCount);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int oldpage = -2;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position != oldpage) {
                    oldpage = position;
                    for (int i = 0, size = headerHolders.size(); i < size; ++i) {
                        headerHolders.get(i).view.animate().cancel();
                        if (i == position) {
                            headerHolders.get(i).view.animate().setDuration(300).alpha(1).start();

                            Rect rect = new Rect();
                            if(!headerHolders.get(i).view.getGlobalVisibleRect(rect)){
                                if(i-1>0)
                                    header.smoothScrollTo(headerHolders.get(i-1).view.getLeft(), 0);
                                else
                                    header.smoothScrollTo(headerHolders.get(i).view.getLeft(),0);
                            }

                        } else {
                            if (headerHolders.get(i).view.getAlpha() != 0.5)
                                headerHolders.get(i).view.animate().setDuration(300).alpha(0.5f);
                        }
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onScroll(Object source, int verticalOffset) {
        dispatchScroll(source, verticalOffset);

        header.setTranslationY(-verticalOffset);
        if (header.getTranslationY() > 0)
            header.setTranslationY(0);

        if (initialHeaderHeight <= 0) {
            initialHeaderHeight = header.getHeight();
            finalHeaderHeight = initialHeaderHeight / 2;
        }
        if (initialHeaderHeight > 0) {

            if (verticalOffset < finalHeaderHeight) {
                float percent = (initialHeaderHeight - verticalOffset) / initialHeaderHeight;

                headerLayout.setPivotX(0);
                //headerLayout.setPivotY(0);
                headerLayout.setScaleX(percent);
                headerLayout.setScaleY(percent);
                headerLayout.setTranslationY(verticalOffset / 2);

                headerLayout.setTranslationX(1.5f * verticalOffset);

                float alphaPercent = 1 - verticalOffset / finalHeaderHeight;
                for (int i=0, count=headerHolders.size();i<count;++i) {
                    HeaderHolder headerHolder = headerHolders.get(i);
                    headerHolder.textView.setAlpha(alphaPercent);

                    headerHolder.view.setTranslationX(-i*4*headerHolder.view.getPaddingLeft()*(1-percent));
                }
            }
        }
    }

    Random rand = new Random();
    public float random(){
        float minX = 0.4f;
        float maxX = 0.8f;
        return rand.nextFloat() * (maxX - minX) + minX;
    }

    protected void fillHeader() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (int i = 0; i < pageCount; ++i) {
            View view = layoutInflater.inflate(R.layout.header_card, headerLayout, false);
            headerLayout.addView(view);

            HeaderHolder headerHolder = new HeaderHolder(view, (TextView) view.findViewById(R.id.title), view.findViewById(R.id.card));
            headerHolders.add(headerHolder);

            ViewGroup.LayoutParams layoutParams = headerHolder.card.getLayoutParams();
            layoutParams.height = (int) (view.getLayoutParams().height * random());
            headerHolder.card.setLayoutParams(layoutParams);

            if(i==0){
                headerHolders.get(i).view.setAlpha(1f);
            }else{
                headerHolders.get(i).view.setAlpha(0.5f);
            }

            final int position = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onHeaderClick(position);
                }
            });
        }
    }


    public void onHeaderClick(int position) {
        viewPager.setCurrentItem(position, true);
    }

    List<Object> scrolls = new ArrayList<>();

    public void registerRecyclerView(RecyclerView recyclerView) {
        scrolls.add(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (calledScrolls.contains(recyclerView))
                    calledScrolls.remove(recyclerView);
                else {
                    onScroll(recyclerView, recyclerView.computeVerticalScrollOffset());
                }
            }
        });

    }

    public void registerScrollView(final ObservableScrollView scrollView) {
        scrolls.add(scrollView);

        if (scrollView.getParent() != null && scrollView.getParent().getParent() != null && scrollView.getParent().getParent() instanceof ViewGroup)
            scrollView.setTouchInterceptionViewGroup((ViewGroup) scrollView.getParent().getParent());

        scrollView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int i, boolean b, boolean b1) {
                if (calledScrolls.contains(scrollView))
                    calledScrolls.remove(scrollView);
                else {
                    onScroll(scrollView, i);
                }
            }

            @Override
            public void onDownMotionEvent() {

            }

            @Override
            public void onUpOrCancelMotionEvent(ScrollState scrollState) {

            }
        });
    }

    List<Object> calledScrolls = new ArrayList<>();

    public void dispatchScroll(Object source, int yOffset) {
        for (Object scroll : scrolls) {
            if (scroll != source) {
                calledScrolls.add(scroll);
                if (scroll instanceof RecyclerView) {
                    RecyclerView recyclerView = (RecyclerView) scroll;
                    if (recyclerView != null && recyclerView.getLayoutManager() != null && recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        linearLayoutManager.scrollToPositionWithOffset(0, -yOffset);
                    }
                } else if (scroll instanceof ScrollView) {
                    ((ScrollView) scroll).scrollTo(0, yOffset);
                }
            }
        }
    }
}
