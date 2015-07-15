package com.hanry.material.widget;

import com.hanry.material.drawable.RippleDrawable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CheckedTextView extends android.widget.CheckedTextView {

	private RippleManager mRippleManager;

    public CheckedTextView(Context context) {
        super(context);

        init(context, null, 0, 0);
    }

    public CheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, 0, 0);
    }

	public CheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		init(context, attrs, defStyleAttr, 0);
	}

    public CheckedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr, defStyleRes);
    }
	
	private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
		applyStyle(context, attrs, defStyleAttr, defStyleRes);
	}

    public void applyStyle(int resId){
        applyStyle(getContext(), null, 0, resId);
    }

    private void applyStyle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        getRippleManager().onCreate(this, context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setBackgroundDrawable(Drawable drawable) {
        Drawable background = getBackground();
        if(background instanceof RippleDrawable && !(drawable instanceof RippleDrawable))
            ((RippleDrawable) background).setBackgroundDrawable(drawable);
        else
            super.setBackgroundDrawable(drawable);
    }

    protected RippleManager getRippleManager(){
        if(mRippleManager == null){
            synchronized (RippleManager.class){
                if(mRippleManager == null)
                    mRippleManager = new RippleManager();
            }
        }

        return mRippleManager;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        RippleManager rippleManager = getRippleManager();
        if (l == rippleManager)
            super.setOnClickListener(l);
        else {
            rippleManager.setOnClickListener(l);
            setOnClickListener(rippleManager);
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        return  getRippleManager().onTouchEvent(event) || result;
    }
}
