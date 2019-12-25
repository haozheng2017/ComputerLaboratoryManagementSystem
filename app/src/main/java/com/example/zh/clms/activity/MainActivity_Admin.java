package com.example.zh.clms.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zh.clms.R;
import com.example.zh.clms.adapter.ViewPagerAdapter;
import com.example.zh.clms.fragment.Fragment_Admin_One;
import com.example.zh.clms.fragment.Fragment_Admin_Three;
import com.example.zh.clms.fragment.Fragment_Admin_Two;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Admin extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_title, textView_lab1, textView_lab2, textView_lab3;
    private ImageView imageView_finish;
    private ViewPager viewPager;

    private Fragment_Admin_One fragment_admin_one;
    private Fragment_Admin_Two fragment_admin_two;
    private Fragment_Admin_Three fragment_admin_three;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private ViewPagerAdapter viewPagerAdapter;

    String[] titles = new String[]{"管理员", "教 师", "学 生"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main_admin);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        init();

        viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), fragmentList);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);

        textView_lab1.setTextColor(getResources().getColor(R.color.light_green));
        viewpaerOnclick();

    }

    private void init() {
        textView_title = findViewById(R.id.mainAdmin_TextView_title);
        textView_lab1 = findViewById(R.id.mainAdmin_TextView_lab1);
        textView_lab2 = findViewById(R.id.mainAdmin_TextView_lab2);
        textView_lab3 = findViewById(R.id.mainAdmin_TextView_lab3);
        imageView_finish = findViewById(R.id.mainAdmin_imageView_finish);


        imageView_finish.setOnClickListener(this);
        textView_lab1.setOnClickListener(this);
        textView_lab2.setOnClickListener(this);
        textView_lab3.setOnClickListener(this);


        viewPager = findViewById(R.id.mainAdmin_viewpager);

        fragment_admin_one = new Fragment_Admin_One();
        fragment_admin_two = new Fragment_Admin_Two();
        fragment_admin_three = new Fragment_Admin_Three();

        fragmentList.add(fragment_admin_one);
        fragmentList.add(fragment_admin_two);
        fragmentList.add(fragment_admin_three);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainAdmin_imageView_finish:
                showFinishDialog();
                break;
            case R.id.mainAdmin_TextView_lab1:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.mainAdmin_TextView_lab2:
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.mainAdmin_TextView_lab3:
                viewPager.setCurrentItem(2, true);
                break;
        }

    }

    private void showFinishDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("您确认要退出登录？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("取消", null);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = 0.85f;
        window.setAttributes(layoutParams);
    }

    private void viewpaerOnclick() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                textView_title.setText(titles[i]);
                changeTextViewColot(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void changeTextViewColot(int postion) {
        switch (postion) {
            case 0:
                textView_lab1.setTextColor(getResources().getColor(R.color.light_green));
                textView_lab2.setTextColor(getResources().getColor(R.color.black));
                textView_lab3.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:

                textView_lab1.setTextColor(getResources().getColor(R.color.black));
                textView_lab2.setTextColor(getResources().getColor(R.color.light_green));
                textView_lab3.setTextColor(getResources().getColor(R.color.black));
                break;
            case 2:
                textView_lab1.setTextColor(getResources().getColor(R.color.black));
                textView_lab2.setTextColor(getResources().getColor(R.color.black));
                textView_lab3.setTextColor(getResources().getColor(R.color.light_green));
                break;
        }

    }
}
