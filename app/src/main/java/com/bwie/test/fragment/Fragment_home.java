package com.bwie.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.test.adapter.XRAdapter;
import com.bwie.test.bean.SYBean;
import com.bwie.test.classes.XiaoxiActivity;
import com.bwie.test.classes.ZxingActivity;
import com.bwie.test.twodemo.R;
import com.bwie.test.utils.API;
import com.bwie.test.utils.GsonObjectCallback;
import com.bwie.test.utils.OkHttp3Utils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by ASUS on 2017/10/10.
 */

public class Fragment_home extends Fragment {
    ImageView Zxing;
    ImageView xiaoxi;
    EditText sousuo;
//    CountdownView countdownView;

    private XRecyclerView xr;
    private List<String > list=new ArrayList<>();
    //获取数据的开始
    private int curr;
    private XRAdapter adapter;

    String urlPATH = "http://result.eolinker.com/umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage";
//    ArrayList<String>mList;
//    ArrayList<MyNews>mList2;
//    ArrayList<String>mList3;
//    ArrayList<Qian>mList_grid;
//    Banner mbanner,mybannerTwo;
//    private RecyclerView mRecyclerView,grid;
//    private HomeAdapter mAdapter;
//    private QiandaoAdapter qianAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        //初始化xr
        xr= (XRecyclerView)view.findViewById(R.id.xre_xrv);
        //加布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xr.setLayoutManager(layoutManager);
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curr=0;
                list.clear();
                getData(API.TYPE_HOME,curr);
                xr.refreshComplete();
            }

            @Override
            public void onLoadMore() {
             /*   curr++;
                getData(API.TYPE_HOME,curr);
                xr.refreshComplete();*/
            }
        });
        getData(API.TYPE_HOME,1);

//        //Banner和RecyclerView
//        mbanner=(Banner)view.findViewById(R.id.mybanner);
//        mybannerTwo = (Banner) view.findViewById(R.id.mybannertwo);
//        mList=new ArrayList<String>();
//        mList2=new ArrayList<MyNews>();
//        mList3=new ArrayList<String>();
//        mList_grid=new ArrayList<Qian>();
//        countdownView=(CountdownView)view.findViewById(R.id.countdownView);
//        countdownView.start(88888888);
//
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
//        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
//        // Grid-RecyclerView
//        grid = (RecyclerView) view.findViewById(R.id.grid);
//        grid.setLayoutManager(new GridLayoutManager(getActivity(),4));
//
//        GetData();
//        initData();
//        initData_grid();

        Zxing = (ImageView) view.findViewById(R.id.Zxing);
        xiaoxi = (ImageView) view.findViewById(R.id.xiaoxi);
        sousuo = (EditText) view.findViewById(R.id.sousuo);
        Zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "扫描二维码", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ZxingActivity.class);
                startActivity(intent);

            }
        });

        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
                Intent intent_talk = new Intent(getActivity(), XiaoxiActivity.class);
                startActivity(intent_talk);
            }
        });
        return view;

    }

    //初始化数据
    private void getData(String url,int curr){
        OkHttp3Utils.getInstance().doGet(url, new GsonObjectCallback<SYBean>() {

            @Override
            public void onUi(SYBean syBean) {
                syBean.getMsg();
                XRAdapter  mxradapter=new XRAdapter(getActivity(),syBean.getData());
                xr.setAdapter(mxradapter);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }


//    private void initData_grid() {
//        OkHttp3Utils.getInstance().doGet(urlPATH, new GsonObjectCallback<HomeBean>() {
//
//            @Override
//            public void onUi(HomeBean homeBean) {
//                List<HomeBean.DataBean.Ad5Bean> ad5 =homeBean.getData().getAd5();
//                grid.setAdapter(qianAdapter = new QiandaoAdapter(ad5,getActivity()));
//                for (HomeBean.DataBean.Ad5Bean ad: ad5) {
//                    mList_grid.add(new Qian(ad.getImage(),ad.getTitle()));
//                }
//            }
//
//            @Override
//            public void onFailed(Call call, IOException e) {
//
//        }
//        });
//    }

//    // 请求轮播图图片
//    private void GetData() {
//        OkHttp3Utils.getInstance().doGet(urlPATH, new GsonObjectCallback<HomeBean>() {
//            @Override
//            public void onUi(HomeBean homeBean) {
//
//                for (int i=0;i<homeBean.getData().getAd1().size();i++){
//                    String ad1s = homeBean.getData().getAd1().get(i).getImage();
//                    mList.add(ad1s);
//                }
//                //设置图片加载器
//                mbanner.setImageLoader(new GlideImageLoader());
//                mbanner.setImages(mList);
//                mbanner.start();
//            }
//
//            @Override
//            public void onFailed(Call call, IOException e) {
//
//            }
//        });
//
//        // 第二个轮播图
//        OkHttp3Utils.getInstance().doGet(urlPATH, new GsonObjectCallback<HomeBean>() {
//            @Override
//            public void onUi(HomeBean homeBean) {
//
//                for (int i=0;i<homeBean.getData().getSubjects().size();i++){
//                    String descImage = homeBean.getData().getSubjects().get(i).getDescImage();
//                    mList3.add(descImage);
//                }
//                //设置图片加载器
//                mybannerTwo.setImageLoader(new GlideImageLoader());
//                mybannerTwo.setImages(mList3);
//                mybannerTwo.start();
//            }
//
//            @Override
//            public void onFailed(Call call, IOException e) {
//
//            }
//        });
//
//    }

//    private void initData() {
//        OkHttp3Utils.getInstance().doGet(urlPATH, new GsonObjectCallback<HomeBean>() {
//
//            @Override
//            public void onUi(HomeBean homeBean) {
//                List<HomeBean.DataBean.DefaultGoodsListBean>defaultGoodsList=homeBean.getData().getDefaultGoodsList();
//                for (HomeBean.DataBean.DefaultGoodsListBean goodsList:defaultGoodsList){
//                    mList2.add(new MyNews(goodsList.getGoods_img(),goodsList.getGoods_name()));
//                }
//            }
//
//            @Override
//            public void onFailed(Call call, IOException e) {
//
//            }
//        });
//    }
//
//    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
//                    getActivity()).inflate(R.layout.item_home, parent,
//                    false));
//            return holder;
//        }
//
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int position) {
//            holder.tv.setText(mList2.get(position).getTitle());
//            String imgURL = mList2.get(position).getPic_url();
//            ImageLoader instance = ImageLoader.getInstance();
//            instance.displayImage(imgURL,holder.img);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mList2.size();
//        }
//
//        class MyViewHolder extends RecyclerView.ViewHolder {
//
//            TextView tv;
//            ImageView img;
//
//            public MyViewHolder(View view) {
//                super(view);
//                tv = (TextView) view.findViewById(R.id.id_num);
//                img = (ImageView) view.findViewById(R.id.img);
//            }
//        }
//    }

}
