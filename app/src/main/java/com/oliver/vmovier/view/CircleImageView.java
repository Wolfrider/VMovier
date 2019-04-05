package com.oliver.vmovier.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CircleImageView extends AppCompatImageView {

    private Bitmap mBitmap;
    private Paint mPaint;
    private RectF mRectF;


    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        init();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        init();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null == mBitmap) {
            return;
        }
        canvas.drawCircle(mRectF.centerX(), mRectF.centerY(), mRectF.width() / 2, mPaint);
    }

    private void init() {
        mBitmap = getBitmap();
        if (null != mBitmap) {
            BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setShader(shader);

            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.DKGRAY);
            mPaint.setStrokeWidth(2);
            mRectF = calculateBounds();
        }
    }

    private Bitmap getBitmap() {
        Drawable drawable = getDrawable();
        if (null == drawable) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        } else if (drawable instanceof ColorDrawable) {
            return Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
        } else {
            return Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
    }

    private RectF calculateBounds() {
        int availableWidth  = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        int sideLength = Math.min(availableWidth, availableHeight);

        float left = getPaddingLeft() + (availableWidth - sideLength) / 2f;
        float top = getPaddingTop() + (availableHeight - sideLength) / 2f;

        return new RectF(left, top, left + sideLength, top + sideLength);
    }
}
