package com.github.florent37.hollyviewpager;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by florentchampigny on 07/08/15.
 */
public class HeaderHolder {
    public View view;
    public TextView textView;
    public View card;

    ObjectAnimator animator = null;

    public HeaderHolder(View view, TextView textView, View card) {
        this.view = view;
        this.textView = textView;
        this.card = card;
    }

    public HeaderHolder(View view) {
        this.view = view;
        this.textView = (TextView) view.findViewById(R.id.title);
        this.card = view.findViewById(R.id.card);
    }

    public void setTitle(CharSequence title) {
        this.textView.setText(title);
    }

    public void setHeightPercent(float percent) {
        ViewGroup.LayoutParams layoutParams = card.getLayoutParams();
        layoutParams.height = (int) (view.getLayoutParams().height * percent);
        card.setLayoutParams(layoutParams);
    }

    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(false);
    }

    public void setEnabled(boolean enabled) {
        if (enabled)
            ViewHelper.setAlpha(view, 1f);
        else
            ViewHelper.setAlpha(view, 0.5f);
    }

    public void animateEnabled(boolean enabled) {
        if(animator != null) {
            animator.cancel();
            animator = null;
        }

        if (enabled) {
            animator = ObjectAnimator.ofFloat(view, "alpha", 1f);
        } else
            animator = ObjectAnimator.ofFloat(view, "alpha", 0.5f);

        animator.setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animator = null;
            }
        });
        animator.start();
    }

    public boolean isEnabled() {
        return ViewHelper.getAlpha(view) != 0.5f;
    }

    public boolean isVisible() {
        Rect rect = new Rect();
        return view.getGlobalVisibleRect(rect);
    }


}
