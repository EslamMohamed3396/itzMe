package com.itzme.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.itzme.R;
import com.itzme.utilits.Constant;
import com.itzme.utilits.PreferencesUtils;

public class SwitchTrackTextDrawable extends Drawable {

    private final Context mContext;

    private final String mLeftText;

    private final String mRightText;

    private final Paint mTextPaint;

    public SwitchTrackTextDrawable(@NonNull Context context,
                                   @StringRes int leftTextId,
                                   @StringRes int rightTextId) {
        mContext = context;

        // Left text
        mLeftText = context.getString(leftTextId);
        mTextPaint = createTextPaint();

        // Right text
        mRightText = context.getString(rightTextId);
    }

    private Paint createTextPaint() {
        Paint textPaint = new Paint();
        //noinspection deprecation
        textPaint.setColor(mContext.getResources().getColor(R.color.light_gray));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(35f);

        textPaint.setTextAlign(Paint.Align.CENTER);
        // Set textSize, typeface, etc, as you wish
        return textPaint;
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect textBounds = new Rect();
        mTextPaint.getTextBounds(mRightText, 0, mRightText.length(), textBounds);

        // The baseline for the text: centered, including the height of the text itself
        final int heightBaseline = canvas.getClipBounds().height() / 2 + textBounds.height() / 2;

        // This is one quarter of the full width, to measure the centers of the texts
        final int widthQuarter = canvas.getClipBounds().width() / 4;


        if (checkArabic()) {
            canvas.drawText(mRightText, 0, mRightText.length(),
                    widthQuarter, heightBaseline,
                    mTextPaint);
            canvas.drawText(mLeftText, 0, mLeftText.length(),
                    widthQuarter * 3, heightBaseline,
                    mTextPaint);
        } else {
            canvas.drawText(mLeftText, 0, mLeftText.length(),
                    widthQuarter, heightBaseline,
                    mTextPaint);
            canvas.drawText(mRightText, 0, mRightText.length(),
                    widthQuarter * 3, heightBaseline,
                    mTextPaint);
        }

    }

    private Boolean checkArabic() {
        PreferencesUtils preferencesUtils = new PreferencesUtils(mContext);
        return preferencesUtils.getString(Constant.LANGUAGE_KEY).equals(Constant.ARABIC_LANGUAGE);
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}