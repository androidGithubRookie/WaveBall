package com.lancer.waveball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lancer on 17-2-22.
 */
public class WaveBallView extends View {

    int width = 0, height = 0;
    int deepDeltaX, lightDeltaX;
    double waterPercent = 1.0d;
    int waveHight = 0;//波高度
    int speed = 3;//wave1波速
    double speedRate = 1;//两波速度比

    //球壳
    Paint shellPen;
    int shellColor = Color.WHITE;
    int shellWidth = 2;

    //玻璃厚度
    Paint glassPen;
    int glassThickSize = 15;
    int glassColor = 0xffff800d;

    //波浪颜色
    Paint wavePen;
    int waveColor = 0xfff19f53;
    int waveLightColor = 0xfff2af6f;
    Path deepWavePath;
    Path lightWavePath;

    public WaveBallView(Context context) {
        super(context);
        init();
    }

    public WaveBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        shellPen = new Paint();
        shellPen.setColor(shellColor);
        shellPen.setStrokeWidth(shellWidth);
        shellPen.setAntiAlias(true);
        shellPen.setStyle(Paint.Style.STROKE);

        glassPen = new Paint();
        glassPen.setAntiAlias(true);
        glassPen.setColor(glassColor);
        glassPen.setStyle(Paint.Style.FILL);

        wavePen = new Paint();
        wavePen.setColor(waveColor);
        wavePen.setStrokeWidth(1);
        wavePen.setAntiAlias(true);
        wavePen.setStyle(Paint.Style.FILL);


        deepWavePath = new Path();
        lightWavePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        deepWavePath.reset();
        lightWavePath.reset();

        drawShell(canvas);

        deepDeltaX += speed;
        if (deepDeltaX >= width) {
            deepDeltaX = deepDeltaX - width;
        }
        deepWavePath.moveTo(-width + deepDeltaX, getWaterHeight());

        deepWavePath.quadTo(-width + width / 4 + deepDeltaX, getWaterHeight() - waveHight, -width + width / 2 + deepDeltaX, getWaterHeight());
        deepWavePath.quadTo(-width + width * 3 / 4 + deepDeltaX, getWaterHeight() + waveHight, -width + width + deepDeltaX, getWaterHeight());

        deepWavePath.quadTo(width / 4 + deepDeltaX, getWaterHeight() - waveHight, width / 2 + deepDeltaX, getWaterHeight());
        deepWavePath.quadTo(width * 3 / 4 + deepDeltaX, getWaterHeight() + waveHight, width + deepDeltaX, getWaterHeight());

        deepWavePath.lineTo(width, height);
        deepWavePath.lineTo(0, height);
        deepWavePath.close();

        lightDeltaX += speed * speedRate;
        if (lightDeltaX >= width) {
            lightDeltaX = lightDeltaX - width;
        }

        lightWavePath.moveTo(-width + lightDeltaX, getWaterHeight());

        lightWavePath.quadTo(-width + width / 4 + lightDeltaX, getWaterHeight() + waveHight, -width + width / 2 + lightDeltaX, getWaterHeight());
        lightWavePath.quadTo(-width + width * 3 / 4 + lightDeltaX, getWaterHeight() - waveHight, -width + width + lightDeltaX, getWaterHeight());

        lightWavePath.quadTo(width / 4 + lightDeltaX, getWaterHeight() + waveHight, width / 2 + lightDeltaX, getWaterHeight());
        lightWavePath.quadTo(width * 3 / 4 + lightDeltaX, getWaterHeight() - waveHight, width + lightDeltaX, getWaterHeight());

        lightWavePath.lineTo(width, height);
        lightWavePath.lineTo(0, height);
        lightWavePath.close();

        int sc = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);


        drawGlassBody(canvas);
        wavePen.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        wavePen.setColor(waveColor);
        canvas.drawPath(deepWavePath, wavePen);
        wavePen.setColor(waveLightColor);
        canvas.drawPath(lightWavePath, wavePen);


        glassPen.setXfermode(null);
        wavePen.setXfermode(null);
        canvas.restoreToCount(sc);
        invalidate();
    }


    private void drawShell(Canvas canvas) {
        canvas.drawCircle(width / 2, height / 2, width / 2, shellPen);
    }

    private void drawGlassBody(Canvas canvas) {
        canvas.drawCircle(width / 2, height / 2, (width - shellWidth * 2 - glassThickSize * 2) / 2, glassPen);
    }

    private int getWaterHeight() {
        return (int) (height * waterPercent);
    }

    public void setPercent(double percent) {
        this.waterPercent = 1 - percent;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSpeedRate(double speedRate) {
        this.speedRate = speedRate;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        waveHight = (int) (h * 0.05);
    }

    public int getmWidth() {
        return width;
    }

    public int getmHeight() {
        return height;
    }

    public void setmWidth(int width) {
        this.width = width;
    }

    public void setmHeight(int height) {
        this.height = height;
    }
}
