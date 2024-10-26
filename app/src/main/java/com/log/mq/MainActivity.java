package com.log.mq;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Sp.SP_init(this);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new SettingFragement()).commit();
        }catch (Exception e){
            new MaterialAlertDialogBuilder(this).setTitle("模块初始化失败").setMessage("请先激活模块后在进入模块").setCancelable(false).show();
        }
    }
}