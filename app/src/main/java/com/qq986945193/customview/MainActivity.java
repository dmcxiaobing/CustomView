package com.qq986945193.customview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
