package com.github.florent37.hollyviewpager;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by florentchampigny on 07/08/15.
 */
public class HeaderHolder {
    public View view;
    public TextView textView;
    public View card;

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

    public void setTitle(CharSequence title){
        this.textView.setText(title);
    }

    public void setHeightPercent(float percent){
        ViewGroup.LayoutParams layoutParams = card.getLayoutParams();
        layoutParams.height = (int) (view.getLayoutParams().height * percent);
        card.setLayoutParams(layoutParams);
    }

    public void enable(){
        setEnabled(true);
    }

    public void disable(){
        setEnabled(false);
    }

    public void setEnabled(boolean enabled){
        if(enabled)
            view.setAlpha(1f);
        else
            view.setAlpha(0.5f);
    }

    public void animateEnabled(boolean enabled){
        view.animate().cancel();
        if(enabled)
            view.animate().setDuration(300).alpha(1f);
        else
            view.animate().setDuration(300).alpha(0.5f);
    }

    public boolean isEnabled(){
        return view.getAlpha() != 0.5f;
    }

    public boolean isVisible(){
        Rect rect = new Rect();
        return view.getGlobalVisibleRect(rect);
    }


}
