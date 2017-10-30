package com.bwie.test.classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.test.twodemo.MainActivity;
import com.bwie.test.twodemo.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;


/**
 * Created by ASUS on 2017/10/12.
 */

public class ZxingActivity extends AppCompatActivity {
    int Result_Code=11;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(ZxingActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Result_Code);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == 0) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    Toast.makeText(ZxingActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    String result=bundle.getString("result");
                    Toast.makeText(ZxingActivity.this,result, Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
