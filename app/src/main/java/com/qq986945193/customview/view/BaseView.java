package com.qq986945193.customview.view;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qq986945193.customview.R;

/**
 * 自定义View实现方式
 * 1. 写一个类继承RelativeLayout(ViewGroup)
 * 2. 写布局文件
 * 3. 将布局添加到RelativeLayout中(initView方法)
 * 4. 自定义属性(1. values/attrs.xml, 2. 声明命名空间 , 3.在自定义view中配置属性, 4. 在自定义view中加载属性值 )
 */
public class BaseView extends RelativeLayout {
    private static final String NAME_SPAGE = "http://schemas.android.com/apk/res-auto";
    private TextView tvCenterTitle;
    private TextView tvRightTitle;
    private ImageView ivGoBack;

    public BaseView(Context context) {
        super(context);
        initView();
    }


    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

        int count = attrs.getAttributeCount();
        Log.e("david", count + "");

        for (int i = 0; i < count; i++) {
            Log.e("david", attrs.getAttributeName(i) + " " + attrs.getAttributeValue(i));
        }

        String centerTitle = attrs.getAttributeValue(NAME_SPAGE, "center_title");
        String rightTitle = attrs.getAttributeValue(NAME_SPAGE, "right_title");

        setTvCenterTitle(centerTitle);
        setTvRightTitle(rightTitle);

    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();


    }

    /**
     * 初始化布局 将布局添加到Relativelayout
     */
    private void initView() {
        View childView = View.inflate(getContext(), R.layout.view_base, null);
        tvCenterTitle = (TextView) childView.findViewById(R.id.tv_center_title);
        tvRightTitle = (TextView) childView.findViewById(R.id.tv_right_title);
        ivGoBack = (ImageView) childView.findViewById(R.id.iv_go_back);
        /*将布局添加给当前的对象Relativelayout*/
        this.addView(childView);

    }


    /**
     * 设置中间标题
     */
    public void setTvCenterTitle(String title) {
        if (tvCenterTitle != null) {
            tvCenterTitle.setText(title);
        }
    }

    /**
     * 设置最右边标题
     */
    public void setTvRightTitle(String rightTitle) {
        if (tvRightTitle != null) {
            tvRightTitle.setText(rightTitle);
        }
    }


}
