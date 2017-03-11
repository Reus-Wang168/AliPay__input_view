package com.travis.demopro.view;

import android.app.Dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.travis.demopro.R;

import butterknife.ButterKnife;

/**
 * Created by Tenney on 2017/2/15.
 * QQ 1021534072
 * email wjky11111@163.com
 */

public class BottomDialog{

     public  static  void showDialog(int id, Context context){
         BottomSheetDialog dialog=new BottomSheetDialog(context);
         dialog.setContentView(id);
         dialog.setCancelable(true);
         dialog.setCanceledOnTouchOutside(true);
         dialog.show();
     }
}
