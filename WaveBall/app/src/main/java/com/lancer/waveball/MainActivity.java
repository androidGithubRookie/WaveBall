package com.lancer.waveball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private WaveChart wave_ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wave_ball = (WaveChart) findViewById(R.id.wave_ball);

        wave_ball.setPercent(0.5d);//wave高度
        wave_ball.setTextViewText(1, "文字1");
        wave_ball.setTextViewText(2, "+文字2");
        wave_ball.setButtonText(1, "btn1");
        wave_ball.setButtonText(2, "btn2");
    }
}
