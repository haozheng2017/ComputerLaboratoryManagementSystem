package com.example.zh.clms.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.zh.clms.R;
import com.example.zh.clms.adapter.ViewPagerAdapter;
import com.example.zh.clms.fragment.Fragment_Student_One;
import com.example.zh.clms.fragment.Fragment_Teacher_Three;
import com.example.zh.clms.fragment.Fragment_Teacher_Two;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Teacher extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_title, textView_lab1, textView_lab2, textView_lab3;
    private ViewPager viewPager;

    private Fragment_Student_One fragment_teacher_one;
    private Fragment_Teacher_Two fragment_teacher_two;
    private Fragment_Teacher_Three fragment_teacher_three;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private ViewPagerAdapter viewPagerAdapter;

    String[] titles = new String[]{"首 页", "功 能", "我 的"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_teacher);

        init();
        viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), fragmentList);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);

        textView_lab1.setTextColor(getResources().getColor(R.color.black));
        viewpaerOnclick();
    }


    private void init() {
        textView_title = findViewById(R.id.activity_main_teacher_textView_title);
        textView_lab1 = findViewById(R.id.activity_main_teacher_textView_lab1);
        textView_lab2 = findViewById(R.id.activity_main_teacher_textView_lab2);
        textView_lab3 = findViewById(R.id.activity_main_teacher_textView_lab3);

        viewPager = findViewById(R.id.activity_main_teacher_viewpager);

        textView_lab1.setOnClickListener(this);
        textView_lab2.setOnClickListener(this);
        textView_lab3.setOnClickListener(this);


        fragment_teacher_one = new Fragment_Student_One();
        fragment_teacher_two = new Fragment_Teacher_Two();
        fragment_teacher_three = new Fragment_Teacher_Three();

        fragmentList.add(fragment_teacher_one);
        fragmentList.add(fragment_teacher_two);
        fragmentList.add(fragment_teacher_three);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_teacher_textView_lab1:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.activity_main_teacher_textView_lab2:
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.activity_main_teacher_textView_lab3:
                viewPager.setCurrentItem(2, true);
                break;
        }
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
                textView_lab1.setBackgroundColor(getResources().getColor(R.color.Cyan));
                textView_lab2.setBackgroundColor(getResources().getColor(R.color.DeepSkyBlue));
                textView_lab3.setBackgroundColor(getResources().getColor(R.color.DeepSkyBlue));
                textView_lab1.setTextColor(getResources().getColor(R.color.black));
                textView_lab2.setTextColor(getResources().getColor(R.color.white));
                textView_lab3.setTextColor(getResources().getColor(R.color.white));
                break;
            case 1:
                textView_lab2.setBackgroundColor(getResources().getColor(R.color.Cyan));
                textView_lab1.setBackgroundColor(getResources().getColor(R.color.DeepSkyBlue));
                textView_lab3.setBackgroundColor(getResources().getColor(R.color.DeepSkyBlue));
                textView_lab1.setTextColor(getResources().getColor(R.color.white));
                textView_lab2.setTextColor(getResources().getColor(R.color.black));
                textView_lab3.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                textView_lab3.setBackgroundColor(getResources().getColor(R.color.Cyan));
                textView_lab1.setBackgroundColor(getResources().getColor(R.color.DeepSkyBlue));
                textView_lab2.setBackgroundColor(getResources().getColor(R.color.DeepSkyBlue));
                textView_lab1.setTextColor(getResources().getColor(R.color.white));
                textView_lab2.setTextColor(getResources().getColor(R.color.white));
                textView_lab3.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }
}
