package com.bwie.test.twodemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwie.test.classes.XiaoxiActivity;
import com.bwie.test.classes.ZxingActivity;
import com.bwie.test.fragment.Fragment_cart;
import com.bwie.test.fragment.Fragment_class;
import com.bwie.test.fragment.Fragment_home;
import com.bwie.test.fragment.Fragment_my;

public class MainActivity extends AppCompatActivity{
    RadioGroup radio;
    Fragment_home fhome;
    Fragment_class fclass;
    Fragment_cart fcart;
    Fragment_my fmy;
    RadioButton two_home,two_class,two_cart,two_user;
    FragmentManager fm;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        InData();
    }

    private void init() {
        two_home=(RadioButton)findViewById(R.id.two_home);
        two_class=(RadioButton)findViewById(R.id.two_class);
        two_cart=(RadioButton)findViewById(R.id.two_cart);
        two_user=(RadioButton)findViewById(R.id.two_user);
        radio=(RadioGroup)findViewById(R.id.group);
        fhome=new Fragment_home();
        fclass=new Fragment_class();
        fcart=new Fragment_cart();
        fmy=new Fragment_my();
    }

    private void InData() {
        two_home.setChecked(true);
        final FragmentManager fm1=getSupportFragmentManager();
        FragmentTransaction ftransaction=fm1.beginTransaction();
        ftransaction.replace(R.id.fra_layout,fhome);
        ftransaction.commit();
        two_home.setTextColor(Color.RED);
        two_class.setTextColor(Color.GRAY);
        two_cart.setTextColor(Color.GRAY);
        two_user.setTextColor(Color.GRAY);

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                fm=getSupportFragmentManager();
                transaction=fm.beginTransaction();
                switch (i){
                    case R.id.two_home:
                        transaction.replace(R.id.fra_layout,fhome);
                        two_home.setTextColor(Color.RED);
                        two_class.setTextColor(Color.GRAY);
                        two_cart.setTextColor(Color.GRAY);
                        two_user.setTextColor(Color.GRAY);
                        break;

                    case R.id.two_class:
                        transaction.replace(R.id.fra_layout,fclass);
                        two_home.setTextColor(Color.GRAY);
                        two_class.setTextColor(Color.RED);
                        two_cart.setTextColor(Color.GRAY);
                        two_user.setTextColor(Color.GRAY);
                        break;

                    case R.id.two_cart:
                        transaction.replace(R.id.fra_layout,fcart);
                        two_home.setTextColor(Color.GRAY);
                        two_class.setTextColor(Color.GRAY);
                        two_cart.setTextColor(Color.RED);
                        two_user.setTextColor(Color.GRAY);
                        break;

                    case R.id.two_user:
                        transaction.replace(R.id.fra_layout,fmy);
                        two_home.setTextColor(Color.GRAY);
                        two_class.setTextColor(Color.GRAY);
                        two_cart.setTextColor(Color.GRAY);
                        two_user.setTextColor(Color.RED);
                        break;
                }
                transaction.commit();
            }
        });
    }

}
