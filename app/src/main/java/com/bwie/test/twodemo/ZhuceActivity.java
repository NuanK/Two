package com.bwie.test.twodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.test.bean.ZhuceBean;
import com.bwie.test.utils.GsonObjectCallback;
import com.bwie.test.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public class ZhuceActivity extends AppCompatActivity {

    EditText username,password,password2,email;
    Button btn_zhuce;
    HashMap mmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        initView();
        initData();

    }

    private void initView() {
        username=(EditText)findViewById(R.id.newuser_username);
        password=(EditText)findViewById(R.id.newuser_password);
        password2=(EditText)findViewById(R.id.newuser_password_again);
        btn_zhuce=(Button)findViewById(R.id.newuser_btn_zhuce);

    }

    private void initData() {

        btn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String pass=password.getText().toString();
                String pass2=password2.getText().toString();

                mmap=new HashMap();
                mmap.put("mobile",name);
                mmap.put("password",pass);
                OkHttp3Utils.getInstance().doPost("http://120.27.23.105/user/reg", mmap, new GsonObjectCallback<ZhuceBean>() {
                    @Override
                    public void onUi(ZhuceBean zhuceBean) {
                        String msg=zhuceBean.getMsg();
                        Toast.makeText(ZhuceActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });

            }
        });

    }
}
