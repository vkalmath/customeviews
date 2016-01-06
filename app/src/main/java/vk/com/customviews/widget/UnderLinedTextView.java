package vk.com.customviews.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import vk.com.customviews.R;

/**
 * Created by kalmath_v on 1/5/16.
 */
public class UnderLinedTextView extends TextView {
    private int mUndelineHeight;
    private final Paint mPaint = new Paint();

    public void setUnderLineHeight(int height) {
        if(height < 0){
            mUndelineHeight = 0;
        }
        if(height != mUndelineHeight) {
            mUndelineHeight = height;
            setPadding(getPaddingLeft(), getPaddingRight(), getPaddingTop(),
                    getPaddingBottom() + mUndelineHeight);
        }
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom + mUndelineHeight);
    }

    public void setUnderLineColor(int color) {
        if(mPaint.getColor() != color) {
            mPaint.setColor(color);
            invalidate();
        }
    }

    public UnderLinedTextView(Context context) {
        super(context);
        initialize(context, null, 0, 0);
    }

    public UnderLinedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0, 0);
    }

    public UnderLinedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr, 0);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray array = null;
        try {
            array = context.obtainStyledAttributes(attrs, R.styleable.UnderLinedTextView, defStyleAttr, defStyleRes);
            setUnderLineColor(array.getColor(R.styleable.UnderLinedTextView_underlineColor, Color.TRANSPARENT));
            setUnderLineHeight(array.getDimensionPixelSize(R.styleable.UnderLinedTextView_underlineHeight, 0));
        } finally {
            if(array != null){
                array.recycle();
            }
        }
    }

    public UnderLinedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mPaint.getColor() != Color.TRANSPARENT && mUndelineHeight > 0 ) {
            canvas.drawRect(0, getHeight() - mUndelineHeight, getWidth(), getHeight(), mPaint);
        }
    }
}
