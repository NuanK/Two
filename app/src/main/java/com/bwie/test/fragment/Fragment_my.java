package com.bwie.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.bwie.test.twodemo.LoginActivity;
import com.bwie.test.twodemo.R;

/**
 * Created by ASUS on 2017/10/10.
 */

public class Fragment_my extends Fragment {
    RadioButton btn_login;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_user,null);
        initView();
        initData();

        return view;
    }

    private void initView() {
        btn_login=(RadioButton)view.findViewById(R.id.two_head);
    }

    private void initData() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_login=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent_login);
            }
        });

    }

}
