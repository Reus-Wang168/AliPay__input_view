package com.travis.demopro;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.travis.demopro.view.BottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Main2Activity extends AppCompatActivity  {


    @BindView(R.id.bn)
    Button bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bn= (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog sheetDialog=new BottomSheetDialog(Main2Activity.this);
                sheetDialog.setCancelable(true);
                sheetDialog.setContentView(R.layout.fragment_bottom);
                sheetDialog.show();


        }
        });

    }


}
