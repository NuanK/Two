package com.bwie.test.twodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.test.bean.LoginBean;
import com.bwie.test.fragment.Fragment_my;
import com.bwie.test.utils.GsonObjectCallback;
import com.bwie.test.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_Login;
    TextView username,password;
    HashMap mmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        InitData();

    }
    private void initView() {
        btn_Login=(Button)findViewById(R.id.user_btn_login);
        username=(TextView)findViewById(R.id.user_login_username);
        password=(TextView)findViewById(R.id.user_login_password);
    }

    private void InitData() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String pass=password.getText().toString();
                mmap=new HashMap();
                mmap.put("mobile",name);
                mmap.put("password",pass);
                OkHttp3Utils.getInstance().doPost("http://120.27.23.105/user/login", mmap, new GsonObjectCallback<LoginBean>() {

                    @Override
                    public void onUi(LoginBean loginBean) {
                        String msg=loginBean.getMsg();
                        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_forgetpass:

                break;

            case R.id.user_new_zhuce:
                Intent intent_zhuce=new Intent(LoginActivity.this,ZhuceActivity.class);
                startActivity(intent_zhuce);
        }
    }
}
