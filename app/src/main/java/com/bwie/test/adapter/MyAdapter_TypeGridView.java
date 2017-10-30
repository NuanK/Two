package com.bwie.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.test.bean.DateGridBean;
import com.bwie.test.twodemo.R;

import java.util.List;

/**
 * Created by ASUS on 2017/10/20.
 */

public class MyAdapter_TypeGridView extends BaseAdapter {
    private Context context;
    private List<DateGridBean.DatasBean.ClassListBean> list;

    public MyAdapter_TypeGridView(Context context, List<DateGridBean.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null){
            view = View.inflate(context, R.layout.class_type_grid_item,null);
            holder = new ViewHolder();
            holder.tv = (TextView) view.findViewById(R.id.tv_gv_type);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv.setText(list.get(i).getGc_name());
        return view;
    }
    class ViewHolder{
        TextView tv;
    }
}
