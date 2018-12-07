package com.smallsnailtech.smallsnail.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class SuspendedWindowLayout extends RelativeLayout {
    public SuspendedWindowLayout(Context context) {
        super(context);
    }

    public SuspendedWindowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuspendedWindowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SuspendedWindowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return onTouchEvent(event);
    }

}
