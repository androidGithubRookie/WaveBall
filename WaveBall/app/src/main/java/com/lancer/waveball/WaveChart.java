package com.lancer.waveball;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lancer on 2017/2/27.
 */

public class WaveChart extends RelativeLayout {
    int width = 0, height = 0;
    WaveBallView wv;
    Button btn1, btn2;
    TextView tv1, tv2, tv3;

    public WaveChart(Context context) {
        super(context);
        initView();
    }

    public WaveChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WaveChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        wv = new WaveBallView(getContext());
        wv.setmWidth(width);
        wv.setmHeight(height);
        this.addView(wv);


        RelativeLayout rl = new RelativeLayout(getContext());
        int innerWidth = (int) (width / 2 * Math.sqrt(2));
        LayoutParams rllp = new LayoutParams(dip2px(getContext(), innerWidth), dip2px(getContext(), innerWidth));
        rl.setLayoutParams(rllp);
        rl.setGravity(Gravity.CENTER);
        rllp.addRule(RelativeLayout.CENTER_IN_PARENT);
        rl.setBackgroundColor(Color.TRANSPARENT);

        View textViews = LayoutInflater.from(getContext()).inflate(R.layout.wave_chart, this);

        btn1 = (Button) (textViews.findViewById(R.id.btn1));
        btn2 = (Button) textViews.findViewById(R.id.btn2);
        tv1 = (TextView) textViews.findViewById(R.id.tv1);
        tv2 = (TextView) textViews.findViewById(R.id.tv2);
        tv3 = (TextView) textViews.findViewById(R.id.tv3);
        this.addView(rl);
    }

    public void setPercent(double percent) {
        wv.setPercent(percent);
    }

    public void setSpeed(int speed) {
        wv.setSpeed(speed);
    }

    public void setSpeedRate(double speedRate) {
        wv.setSpeedRate(speedRate);
    }

    /**
     * 自定义字体大小
     */
    public void setTextViewText(int index, int size, String text) {
        TextView tv = null;
        switch (index) {
            case 1:
                tv = tv1;
                break;
            case 2:
                tv = tv2;
                break;
            case 3:
                tv = tv3;
                break;
            default:
                tv = tv1;
                break;
        }
//        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        tv.setText(text);
    }

    /**
     * 指定字体大小
     */
    public void setTextViewText(int index, String text) {
        setTextViewText(index, 30, text);
    }

    public void setButtonText(int index, int size, String text) {
        Button btn = null;
        switch (index) {
            case 1:
                btn = btn1;
                break;
            case 2:
                btn = btn2;
                break;
            default:
                btn = btn1;
                break;
        }
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        btn.setText(text);
    }

    public void setButtonText(int index, String text) {
        setButtonText(index, 10, text);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
