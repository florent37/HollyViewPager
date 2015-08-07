package com.github.florent37.hollyviewpager;

import android.view.View;
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
}
