package com.bwie.test.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.test.bean.GoodsInfo;
import com.bwie.test.bean.StoreInfo;
import com.bwie.test.twodemo.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/10/24.
 */

public class ShopcartAdapter extends BaseExpandableListAdapter {
    private List<StoreInfo> groups;
    private Map<String, List<GoodsInfo>> children;
    private Context context;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private int flag = 0;
    private GroupEdtorListener mListener;

    public GroupEdtorListener getmListener() {
        return mListener;
    }

    public void setmListener(GroupEdtorListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 构造函数
     *
     * @paramgroups   组元素列表
     * @paramchildren 子元素列表
     * @paramcontext
     */
    public ShopcartAdapter(List<StoreInfo> groups, Map<String, List<GoodsInfo>> children, Context context) {
        this.groups = groups;
        this.children = children;
        this.context = context;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }


    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int i) {
        String groupId = groups.get(i).getId();
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int i) {
        return groups.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        List<GoodsInfo> childs = children.get(groups.get(i).getId());
        return childs.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        final GroupViewHolder gholder;
        if (view == null) {
            gholder = new GroupViewHolder();
            view = View.inflate(context, R.layout.item_shopcart_group, null);
            gholder.cb_check = (CheckBox) view.findViewById(R.id.determine_chekbox);
            gholder.tv_group_name = (TextView) view.findViewById(R.id.tv_source_name);
            gholder.store_edtor = (Button) view.findViewById(R.id.tv_store_edtor);
            view.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) view.getTag();
        }
        final StoreInfo group = (StoreInfo) getGroup(i);
        if (group != null) {
            gholder.tv_group_name.setText(group.getName());
            gholder.cb_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)

                {
                    group.setChoosed(((CheckBox) v).isChecked());
                    checkInterface.checkGroup(i, ((CheckBox) v).isChecked());// 暴露组选接口
                }
            });
            gholder.cb_check.setChecked(group.isChoosed());
            gholder.store_edtor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.groupEdit(i);
                    if (flag == 0) {
                        group.setIsEdtor(true);
                        gholder.store_edtor.setText("完成");
                    } else if (flag == 1) {
                        group.setIsEdtor(false);
                        gholder.store_edtor.setText("编辑");
                    }
                    flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
                }
            });
        } else {
            groups.remove(i);
        }

        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final ChildViewHolder cholder;
        if (view == null) {
            cholder = new ChildViewHolder();
            view = View.inflate(context, R.layout.item_shopcart_product, null);
            cholder.cb_check = (CheckBox) view.findViewById(R.id.check_box);
            cholder.tv_product_desc = (TextView) view.findViewById(R.id.tv_intro);
            cholder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            cholder.iv_increase = (TextView) view.findViewById(R.id.tv_add);
            cholder.iv_decrease = (TextView) view.findViewById(R.id.tv_reduce);
            cholder.tv_count = (TextView) view.findViewById(R.id.tv_num);
            cholder.rl_no_edtor = (RelativeLayout) view.findViewById(R.id.rl_no_edtor);
            cholder.tv_color_size = (TextView) view.findViewById(R.id.tv_color_size);
            cholder.tv_discount_price = (TextView) view.findViewById(R.id.tv_discount_price);
            cholder.tv_buy_num = (TextView) view.findViewById(R.id.tv_buy_num);
            cholder.ll_edtor = (LinearLayout) view.findViewById(R.id.ll_edtor);
            cholder.tv_colorsize = (TextView) view.findViewById(R.id.tv_colorsize);
            cholder.tv_goods_delete = (TextView) view.findViewById(R.id.tv_goods_delete);
            cholder.iv_adapter_list_pic= (ImageView) view.findViewById(R.id.iv_adapter_list_pic);
            view.setTag(cholder);
        } else {
            cholder = (ChildViewHolder) view.getTag();
        }
        if (groups.get(i).isEdtor() == true) {
            cholder.ll_edtor.setVisibility(View.VISIBLE);
            cholder.rl_no_edtor.setVisibility(View.GONE);
        } else {
            cholder.ll_edtor.setVisibility(View.GONE);
            cholder.rl_no_edtor.setVisibility(View.VISIBLE);
        }
        final GoodsInfo goodsInfo = (GoodsInfo) getChild(i, i1);
        if (goodsInfo != null) {
            cholder.tv_product_desc.setText(goodsInfo.getDesc());
            cholder.tv_price.setText("￥" + goodsInfo.getPrice() + "");
            cholder.tv_count.setText(goodsInfo.getCount() + "");
            cholder.iv_adapter_list_pic.setImageResource(goodsInfo.getGoodsImg());
            cholder.tv_color_size.setText("颜色：" + goodsInfo.getColor() + "," + "尺码：" + goodsInfo.getSize() + "瓶/斤");
            SpannableString spanString = new SpannableString("￥"+String.valueOf(goodsInfo.getDiscountPrice()));
            StrikethroughSpan span = new StrikethroughSpan();
            spanString.setSpan(span, 0, String.valueOf(goodsInfo.getDiscountPrice()).length()+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(cholder.tv_discount_price.getText().toString().length()>0){
                cholder.tv_discount_price.setText("");
            }
            cholder.tv_discount_price.append(spanString);
            cholder.tv_buy_num.setText("x" + goodsInfo.getCount());
            cholder.cb_check.setChecked(goodsInfo.isChoosed());
            cholder.cb_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goodsInfo.setChoosed(((CheckBox) v).isChecked());
                    cholder.cb_check.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(i, i1, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });
            cholder.iv_increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(i, i1, cholder.tv_count, cholder.cb_check.isChecked());// 暴露增加接口
                }
            });
            cholder.iv_decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(i, i1, cholder.tv_count, cholder.cb_check.isChecked());// 暴露删减接口
                }
            });
            //删除 购物车
            cholder.tv_goods_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alert = new AlertDialog.Builder(context).create();
                    alert.setTitle("操作提示");
                    alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });
                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    modifyCountInterface.childDelete(i, i1);

                                }
                            });
                    alert.show();

                }
            });
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 组元素绑定器
     */
    private class GroupViewHolder {
        CheckBox cb_check;
        TextView tv_group_name;
        Button store_edtor;
    }

    /**
     * 子元素绑定器
     */
    private class ChildViewHolder {
        CheckBox cb_check;
        ImageView iv_adapter_list_pic;
        TextView tv_product_name;
        TextView tv_product_desc;
        TextView tv_price;
        TextView iv_increase;
        TextView tv_count;
        TextView iv_decrease;
        RelativeLayout rl_no_edtor;
        TextView tv_color_size;
        TextView tv_discount_price;
        TextView tv_buy_num;
        LinearLayout ll_edtor;
        TextView tv_colorsize;
        TextView tv_goods_delete;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        public void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        public void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删除子item
         * @param groupPosition
         * @param childPosition
         */
        public void childDelete(int groupPosition, int childPosition);
    }

    /**
     * 监听编辑状态
     */
    public interface GroupEdtorListener{
        public void groupEdit(int groupPosition);
    }
}
