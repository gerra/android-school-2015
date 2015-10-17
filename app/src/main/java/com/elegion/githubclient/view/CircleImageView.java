package com.elegion.githubclient.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.elegion.githubclient.R;

/**
 * Created on 17.10.15.
 *
 * @author German Berezhko, gerralizza@gmail.com
 */
public class CircleImageView extends ImageView {

    private enum ShapeType {
        SQUARE,
        CIRCLE
    }

    private ShapeType shapeType;
    private int size;

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getResources().obtainAttributes(attrs, R.styleable.CircleImageView);
        shapeType = ShapeType.values()[a.getInt(R.styleable.CircleImageView_shapeType, 0)];
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        size = Math.min(width, height);
        setMeasuredDimension(size, size);
    }

    private Bitmap getCircleBitmap(Bitmap squareBitmap) {
        int size = squareBitmap.getWidth();
        Bitmap circleBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circleBitmap);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, size, size);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(size / 2f, size / 2f, size / 2f + 1, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(squareBitmap, rect, rect, paint);
        return  circleBitmap;
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap imageBitmap = bitmapDrawable.getBitmap();
            if (shapeType == ShapeType.CIRCLE) {
                imageBitmap = getCircleBitmap(imageBitmap);
            }
            super.setImageDrawable(new BitmapDrawable(getResources(), imageBitmap));
        }
    }
}
