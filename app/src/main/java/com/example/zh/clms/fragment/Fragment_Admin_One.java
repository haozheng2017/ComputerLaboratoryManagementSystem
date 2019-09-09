package com.example.zh.clms.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zh.clms.R;
import com.example.zh.clms.activity.LoginActivity;
import com.example.zh.clms.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Admin_One extends Fragment implements View.OnClickListener {

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    private EditText editText1, editText2;
    private Button button_insert, button_delete, button_update, button_select;
    private ListView listView;
    private ListViewAdapter adapter;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_1, container, false);

        init();

        return view;
    }

    private void init() {

        editText1 = view.findViewById(R.id.fragmentAdmin1_editText1);
        editText2 = view.findViewById(R.id.fragmentAdmin1_editText2);

        button_insert = view.findViewById(R.id.fragmentAdmin1_button_insert);
        button_delete = view.findViewById(R.id.fragmentAdmin1_button_delete);
        button_update = view.findViewById(R.id.fragmentAdmin1_button_update);
        button_select = view.findViewById(R.id.fragmentAdmin1_button_select);

        listView = view.findViewById(R.id.fragmentAdmin1_listView);


        button_insert.setOnClickListener(this);
        button_delete.setOnClickListener(this);
        button_update.setOnClickListener(this);
        button_select.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String str1 = editText1.getText().toString();
        String str2 = editText2.getText().toString();
        switch (v.getId()) {
            case R.id.fragmentAdmin1_button_insert:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    LoginActivity.setMap_admin(str1, str2, 1);
                    Toast.makeText(getContext(), "数据添加成功", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.fragmentAdmin1_button_update:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    LoginActivity.setMap_admin(str1, str2, 2);
                    Toast.makeText(getContext(), "数据修改成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.fragmentAdmin1_button_delete:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    LoginActivity.setMap_admin(str1, str2, 3);
                    Toast.makeText(getContext(), "数据删除成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.fragmentAdmin1_button_select:
                list1.clear();
                list2.clear();
                adapter = new ListViewAdapter(getContext(), list1, list2);
                listView.setAdapter(adapter);
                for (Map.Entry<String, String> vo : LoginActivity.map_admin.entrySet()) {
                    list1.add(vo.getKey());
                    list2.add(vo.getValue());
                }
                break;
        }
    }
}
