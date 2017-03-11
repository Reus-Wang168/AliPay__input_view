package com.travis.demopro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.travis.demopro.view.BottomDialog;
import com.travis.demopro.view.LoveLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.btn_select)
    Button btnSelect;
    @BindView(R.id.btn_password)
    Button btnPassword;
    @BindView(R.id.activity_main3)
    RelativeLayout activityMain3;
    @BindView(R.id.btn_love)
    Button btnLove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_password,R.id.btn_love})
    void click(View view) {
        switch (view.getId()){
            case  R.id.btn_password:

            BottomDialog.showDialog(R.layout.pop_password,this);
                break;
            case R.id.btn_love:
                startActivity(new Intent(this,Main4Activity.class));
                break;
        }

    }


}
