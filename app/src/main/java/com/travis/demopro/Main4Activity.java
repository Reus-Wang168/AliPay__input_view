package com.travis.demopro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.travis.demopro.view.LoveLayout;

public class Main4Activity extends AppCompatActivity {

    private LoveLayout loveLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.love);
         loveLayout= (LoveLayout) findViewById(R.id.container);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("","ssss");
                loveLayout.addLove();

            }
        });
    }
}
