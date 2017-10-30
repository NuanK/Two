package com.bwie.test.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.bean.GridListBean;
import com.bwie.test.bean.SYBean;
import com.bwie.test.twodemo.R;
import com.bwie.test.utils.GlideImaGlideImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * Created by ASUS on 2017/10/24.
 */

public class XRAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    SYBean.DataBean list;
    Context mcontext;
    ArrayList mlist;
    ArrayList<GridListBean> SYlist;

    //枚举类型
    private enum Item_Type {

        Typeone, Typetwo, Typethree, Typefour

    }
    public XRAdapter(Context context, SYBean.DataBean data) {
        this.mcontext = context;
        this.list = data;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Item_Type.Typeone.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_a, null);
            ViewHolderA viewHolder = new ViewHolderA(mView);
            return viewHolder;

        } else if (viewType == Item_Type.Typetwo.ordinal()) {

            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_b, null);
            ViewHolderB viewHolder = new ViewHolderB(mView);
            return viewHolder;
        } else if (viewType == Item_Type.Typethree.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_c, null);
            ViewHolderC viewHolder = new ViewHolderC(mView);
            return viewHolder;
        } else if (viewType == Item_Type.Typefour.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_d, null);
            ViewHolderD viewHolder = new ViewHolderD(mView);
            return viewHolder;
        }
        return null;
    }


    /**
     * 绑定数据：可以直接拿到已经绑定控件的Viewholder对象
     *
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderA) {
            mlist = new ArrayList();
            for (int i = 0; i < list.getAd1().size(); i++) {
                mlist.add(list.getAd1().get(i).getImage());
            }
            //设置图片加载器
            ((ViewHolderA) holder).mbanner.setImageLoader(new GlideImaGlideImageLoader());
            ((ViewHolderA) holder).mbanner.setImages(mlist);
            ((ViewHolderA) holder).mbanner.start();

        } else if (holder instanceof ViewHolderB) {
            SYlist = new ArrayList<GridListBean>();
            for (int i = 0; i < list.getAd5().size(); i++) {
                SYlist.add(new GridListBean(list.getAd5().get(i).getImage(),list.getAd5().get(i).getTitle()));
            }
            ((ViewHolderB) holder).recyView_one.setAdapter(new QiandaoAdapter(SYlist,mcontext));
            ((ViewHolderB) holder).recyView_one.setLayoutManager(new GridLayoutManager(mcontext,4));

//          ((ViewHolderB) holder).text.setText(list.getDefaultGoodsList().get(position).getGoods_name() + "------样式二");
        } else if (holder instanceof ViewHolderC) {
            mlist = new ArrayList();
            for (int i = 0; i < list.getSubjects().size(); i++) {
                mlist.add(list.getSubjects().get(i).getDescImage());

            }
            //设置图片加载器
            ((ViewHolderC) holder).mbannertwo.setImageLoader(new GlideImaGlideImageLoader());
            ((ViewHolderC) holder).mbannertwo.setImages(mlist);
            ((ViewHolderC) holder).mbannertwo.start();

//            ((ViewHolderC) holder).text.setText(list.getDefaultGoodsList().get(position).getGoods_name() + "------样式三");
        } else if (holder instanceof ViewHolderD) {
            SYlist = new ArrayList<GridListBean>();
            for (int i = 0; i < list.getDefaultGoodsList().size(); i++) {
                SYlist.add(new GridListBean(list.getDefaultGoodsList().get(i).getGoods_img(),list.getDefaultGoodsList().get(i).getGoods_name()));
            }
            ((ViewHolderD) holder).recyView_two.setAdapter(new QiandaoAdapter(SYlist,mcontext));
            ((ViewHolderD) holder).recyView_two.setLayoutManager(new GridLayoutManager(mcontext,2));

//            ((ViewHolderD) holder).text.setText(list.getDefaultGoodsList().get(position).getGoods_name() + "------样式4");
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
    //返回值赋值给onCreateViewHolder的参数 viewType


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Item_Type.Typeone.ordinal();
        } else if (position == 1) {
            return Item_Type.Typetwo.ordinal();
        } else if (position == 2) {
            return Item_Type.Typethree.ordinal();
        } else if (position == 3) {
            return Item_Type.Typefour.ordinal();
        }
        return -1;
    }

    class ViewHolderA extends RecyclerView.ViewHolder {
        public Banner mbanner;

        public ViewHolderA(View itemView) {
            super(itemView);

            mbanner = (Banner) itemView.findViewById(R.id.mybanner);
        }
    }


    class ViewHolderB extends RecyclerView.ViewHolder {
        public RecyclerView recyView_one;

        public ViewHolderB(View view){
            super(view);
            recyView_one = (RecyclerView) itemView.findViewById(R.id.recyView_one);
        }
    }

    class ViewHolderC extends RecyclerView.ViewHolder {

        public Banner mbannertwo;

        public ViewHolderC(View itemView) {
            super(itemView);

            mbannertwo = (Banner) itemView.findViewById(R.id.mybannertwo);
        }
    }

    class ViewHolderD extends RecyclerView.ViewHolder {

        public RecyclerView recyView_two;

        public ViewHolderD(View view){
            super(view);
            recyView_two = (RecyclerView) itemView.findViewById(R.id.recyView_two);
        }
    }


}
