# CustomView
作者：程序员小冰 （转载请说明出处）博客地址：http://blog.csdn.net/qq_21376985

首先大家都知道，我们在程序中有好多功能有时候需要封装，以及自定义View。今天

我就教大家一步一步的实现自定义View的功能。这里只是说明自定义View的功能实现。当然

引用style样式也能实现，这里暂时不讨论。首先先看一下今天的效果图：

![这里写图片描述](http://img.blog.csdn.net/20161122144831538)

这个是我们今天的代码，黑色的字体是我为了区分来用的。就是简单的TextView，红色背景是要实现的功

能。比如我们常用这个红色背景以及代码，我们就可以封装起来。

如：上面是在java代码中按实际需求。java代码中设置不同的内容。首先肯定写一个自定义View（自定义View的布局我这里就不再写了。比较简单。）：

```
package com.qq986945193.customview.view;
/* @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.Context;
import android.util.AttributeSet;
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
 * 4.写一个主类进行调用即可。然后设置内容。
 */

/**
 * 简单设置自定义View，没有添加命名空间
 */
public class BaseTitleView extends RelativeLayout {
    private TextView tvCenterTitle;
    private TextView tvRightTitle;
    private ImageView ivGoBack;

    public BaseTitleView(Context context) {
        super(context);
        initView();
    }


    public BaseTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BaseTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化布局 将布局添加到Relativelayout
     */
    private void initView() {
        View childView = View.inflate(getContext(), R.layout.view_base_title, null);
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


    /**
     * 设置隐藏返回图标
     */

    public void setIvGoBack() {
        if (ivGoBack != null) {
            ivGoBack.setVisibility(INVISIBLE);
        }
    }

}

```

然后我们再在我们的一个类中进行设置不同的内容：

```
package com.qq986945193.customview;

import android.app.Activity;
import android.os.Bundle;

import com.qq986945193.customview.view.BaseTitleView;

/* @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class MainActivity extends Activity {
    private BaseTitleView main_base_title_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        main_base_title_view.setTvCenterTitle("Java代码中实现");
        main_base_title_view.setTvRightTitle("Java代码");
        main_base_title_view.setIvGoBack();

    }

    /**
     * 初始化布局
     */
    private void initView() {
        main_base_title_view = (BaseTitleView) findViewById(R.id.main_base_title_view);
    }
}

```

然后我们再看如何在XML直接进行更改内容，省的再去java代码中进行设置。首先看一下自定义View代码：

```
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

```

当然大家看到了上面代码我写的注释，第四条是：
自定义属性(1. values/attrs.xml, 2. 声明命名空间 , 3.在自定义view中配置属性, 4. 在自定义view中加载属性值 )
所以，我们这里需要在values中新建attrs.xml文件，声明我们自定义的属性，下面是代码：

```
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <declare-styleable name="BaseView">
        <attr name="center_title" format="string" />
        <attr name="right_title" format="string" />
    </declare-styleable>
</resources>
```
然后我们就可以引用了。我这里的两种实现方式，均是在一个xml文件中，xml对应的也就是上面的MainActivity类。好了，给大家看一下XML的代码吧：

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:david="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="这是在java代码中设置问题"
        android:textSize="16sp" />

    <com.qq986945193.customview.view.BaseTitleView
        android:id="@+id/main_base_title_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="这是在布局XML中设置内容"
        android:textSize="16sp" />

    <com.qq986945193.customview.view.BaseView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        david:center_title="XML中设置center"
        david:right_title="XML中设置right" />

</LinearLayout>

```

好了，这两种方式算是讲述完了。相信你应该学会了吧？说明，本人用的是AndroidStudio写的代码，当然你如果是Eclipse需要将
“”http://schemas.android.com/apk/res-auto“”
这个换位你对应的报名如：
“”http://schemas.android.com/apk/res/com.qq986945193.customview“”。
好了，源代码我分享到了github上面。需要的请自己下载查看。、。。

源代码下载地址:`https://github.com/QQ986945193/CustomView`

