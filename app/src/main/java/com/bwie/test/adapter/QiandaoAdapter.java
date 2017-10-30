package com.bwie.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.bean.GridListBean;
import com.bwie.test.fragment.Fragment_my;
import com.bwie.test.homes.HomeBean;
import com.bwie.test.homes.MyNews;
import com.bwie.test.homes.Qian;
import com.bwie.test.twodemo.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/10/20.
 */

public class QiandaoAdapter extends RecyclerView.Adapter<QiandaoAdapter.MyViewHolder> {

    List<GridListBean> mList_grid;
    Context context;

    public QiandaoAdapter(List<GridListBean> mList_grid, Context context) {
        this.mList_grid = mList_grid;
        this.context = context;
    }

    @Override
    public QiandaoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from
                (context).inflate(R.layout.item_qiandao,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(QiandaoAdapter.MyViewHolder holder, int position) {
        holder.qian_text.setText(mList_grid.get(position).getText().toString());
        String imgUrl=mList_grid.get(position).getImg().toString();
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage(imgUrl, holder.qian_img);
    }

    @Override
    public int getItemCount() {
        return mList_grid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView qian_img;
        TextView qian_text;

        public MyViewHolder(View view){
            super(view);
            qian_text=(TextView)view.findViewById(R.id.qian_num);
            qian_img=(ImageView) view.findViewById(R.id.qian_img);
        }

    }
}
