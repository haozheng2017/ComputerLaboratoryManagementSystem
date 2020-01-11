package com.example.zh.clms.fragment.Fragment_Admin;

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
import com.example.zh.clms.adapter.ListViewAdapter;
import com.example.zh.clms.database.Admin.Admin;
import com.example.zh.clms.database.Admin.AdminDao;
import com.example.zh.clms.database.Admin.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fragment_Admin_One extends Fragment implements View.OnClickListener {

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    private EditText editText1, editText2;
    private Button button_insert, button_delete, button_update, button_select, button_select_all;
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
        button_select_all = view.findViewById(R.id.fragmentAdmin1_button_select_all);

        listView = view.findViewById(R.id.fragmentAdmin1_listView);


        button_insert.setOnClickListener(this);
        button_delete.setOnClickListener(this);
        button_update.setOnClickListener(this);
        button_select.setOnClickListener(this);
        button_select_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str1 = editText1.getText().toString();
        String str2 = editText2.getText().toString();
        switch (v.getId()) {
            case R.id.fragmentAdmin1_button_insert:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else if ("".equals(str2)) {
                    Toast.makeText(getContext(), "密码的输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    Admin admin = new Admin();
                    admin.setUserName(str1);
                    AdminService service = new AdminDao(getContext());
                    Map<String, String> map = service.viewAdmin(admin);
                    if (str1.trim().equals(map.get("userName"))) {
                        Toast.makeText(getContext(), "该用户名已被使用", Toast.LENGTH_SHORT).show();
                    } else {
                        admin.setUserName(str1);
                        admin.setPassword(str2);
                        Object[] params = {admin.getUserName(), admin.getPassword()};
                        boolean flag = new AdminDao(getContext()).addAdmin(admin);
                        if (flag) {
                            editText1.setText("");
                            editText2.setText("");
                            Toast.makeText(getContext(), "数据添加成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "数据添加失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;

            case R.id.fragmentAdmin1_button_update:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else if ("".equals(str2)) {
                    Toast.makeText(getContext(), "密码的输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    Admin admin = new Admin();
                    admin.setUserName(str1);
                    admin.setPassword(str2);
                    AdminService service = new AdminDao(getContext());
                    Map<String, String> map = service.viewAdmin(admin);
                    if (str1.trim().equals(map.get("userName"))) {
                        Object[] params = {admin.getPassword(), admin.getUserName()};
                        boolean flag1 = new AdminDao(getContext()).updateAdmin(admin, params);
                        if (flag1) {
                            editText1.setText("");
                            editText2.setText("");
                            Toast.makeText(getContext(), "数据修改成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "数据修改失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "该用户不存在", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.fragmentAdmin1_button_delete:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    Admin admin = new Admin();
                    admin.setUserName(str1);
                    AdminService service = new AdminDao(getContext());
                    Map<String, String> map = service.viewAdmin(admin);
                    if (str1.trim().equals(map.get("userName"))) {
                        boolean flag = new AdminDao(getContext()).deleteAdmin(admin);
                        if (flag) {
                            editText1.setText("");
                            editText2.setText("");
                            Toast.makeText(getContext(), "数据删除成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "数据删除失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "该用户不存在", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.fragmentAdmin1_button_select:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    list1.clear();
                    list2.clear();
                    Admin admin = new Admin();
                    admin.setUserName(str1);
                    AdminService service = new AdminDao(getContext());
                    Map<String, String> map = service.viewAdmin(admin);
                    if (str1.trim().equals(map.get("userName"))) {
                        list1.add(map.get("userName"));
                        list2.add(map.get("password"));
                        adapter = new ListViewAdapter(getContext(), list1, list2);
                        listView.setAdapter(adapter);
                        map.clear();
                        editText1.setText("");
                        editText2.setText("");
                        Toast.makeText(getContext(), "单个数据查找成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "该用户不存在", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.fragmentAdmin1_button_select_all:
                list1.clear();
                list2.clear();
                AdminService service = new AdminDao(getContext());
                List<Map<String, String>> list = service.listAdminMaps();

                String key = null;//去重键
                for (Map<String, String> m : list) {
                    for (Map.Entry<String, String> vo : m.entrySet()) {
                        if (key != m.get("userName")) {
                            list1.add(m.get("userName"));
                            list2.add(m.get("password"));
                            key = m.get("userName");
                        }
                    }
                }
                adapter = new ListViewAdapter(getContext(), list1, list2);
                listView.setAdapter(adapter);
                editText1.setText("");
                editText2.setText("");
                Toast.makeText(getContext(), "所有用户查找成功！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
