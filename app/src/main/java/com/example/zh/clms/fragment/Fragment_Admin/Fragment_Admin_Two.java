package com.example.zh.clms.fragment.Fragment_Admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zh.clms.R;
import com.example.zh.clms.adapter.ListViewAdapter;
import com.example.zh.clms.adapter.ListViewAdapter_admin2;
import com.example.zh.clms.database.Teacher.Teacher;
import com.example.zh.clms.database.Teacher.TeacherDao;
import com.example.zh.clms.database.Teacher.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fragment_Admin_Two extends Fragment implements View.OnClickListener {

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> list3 = new ArrayList<>();
    ArrayList<String> list4 = new ArrayList<>();
    ArrayList<String> list5 = new ArrayList<>();
    private EditText editText1, editText2;
    private Button button_delete, button_update, button_select, button_select_all;
    private ListView listView;
    private ListViewAdapter_admin2 adapter;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_2, container, false);

        init();
        return view;
    }

    private void init() {
        editText1 = view.findViewById(R.id.fragmentAdmin2_editText1);
        editText2 = view.findViewById(R.id.fragmentAdmin2_editText2);


//        button_insert = view.findViewById(R.id.fragmentAdmin2_button_insert);
        button_delete = view.findViewById(R.id.fragmentAdmin2_button_delete);
        button_update = view.findViewById(R.id.fragmentAdmin2_button_update);
        button_select = view.findViewById(R.id.fragmentAdmin2_button_select);
        button_select_all = view.findViewById(R.id.fragmentAdmin2_button_select_all);

        listView = view.findViewById(R.id.fragmentAdmin2_listView);


//        button_insert.setOnClickListener(this);
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
//            case R.id.fragmentAdmin2_button_insert:
//                if ("".equals(str1)) {
//                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
//                } else if ("".equals(str2)) {
//                    Toast.makeText(getContext(), "密码的输入不可为空", Toast.LENGTH_SHORT).show();
//                } else if ("".equals(string_spinnerValue)) {
//                    Toast.makeText(getContext(), "请选择实验室门牌号", Toast.LENGTH_SHORT).show();
//                }else if ("请选择实验室门牌号".equals(string_spinnerValue)) {
//                    Toast.makeText(getContext(), "请选择实验室门牌号", Toast.LENGTH_SHORT).show();
//                } else {
//                    Teacher teacher = new Teacher();
//                    teacher.setUserName(str1);
//                    TeacherService service = new TeacherDao(getContext());
//                    Map<String, String> map = service.viewTeacher(teacher);
//                    if (str1.trim().equals(map.get("userName"))) {
//                        Toast.makeText(getContext(), "该用户名已被使用", Toast.LENGTH_SHORT).show();
//                    } else {
//                        teacher.setUserName(str1);
//                        teacher.setPassword(str2);
//                        teacher.setRoomNum(string_spinnerValue);
//                        Object[] params = {teacher.getUserName(), teacher.getPassword(), teacher
//                                .getRoomNum()};
//                        boolean flag = new TeacherDao(getContext()).addTeacher(teacher);
//                        if (flag) {
//                            editText1.setText("");
//                            editText2.setText("");
//                            Toast.makeText(getContext(), "数据添加成功", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getContext(), "数据添加失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                break;

            case R.id.fragmentAdmin2_button_update:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else if ("".equals(str2)) {
                    Toast.makeText(getContext(), "密码的输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    Teacher teacher = new Teacher();
                    teacher.setUserName(str1);
                    teacher.setPassword(str2);
                    TeacherService service = new TeacherDao(getContext());
                    Map<String, String> map = service.viewTeacher(teacher);
                    if (str1.trim().equals(map.get("userName"))) {
                        Object[] params = {teacher.getPassword(), teacher.getUserName()};
                        boolean flag1 = new TeacherDao(getContext()).updateTeacher(teacher, params);
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
            case R.id.fragmentAdmin2_button_delete:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    Teacher teacher = new Teacher();
                    teacher.setUserName(str1);
                    TeacherService service = new TeacherDao(getContext());
                    Map<String, String> map = service.viewTeacher(teacher);
                    if (str1.trim().equals(map.get("userName"))) {
                        boolean flag = new TeacherDao(getContext()).deleteTeacher(teacher);
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
            case R.id.fragmentAdmin2_button_select:
                if ("".equals(str1)) {
                    Toast.makeText(getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    list1.clear();
                    list2.clear();
                    list3.clear();
                    list4.clear();
                    list5.clear();
                    Teacher teacher = new Teacher();
                    teacher.setUserName(str1);
                    TeacherService service = new TeacherDao(getContext());
                    Map<String, String> map = service.viewTeacher(teacher);

                    if (str1.trim().equals(map.get("userName"))) {
                        list1.add(map.get("userName"));
                        list2.add(map.get("password"));
                        list3.add(map.get("realName"));
                        list4.add(map.get("phoneNumber"));
                        list5.add(map.get("roomNum"));
                        adapter = new ListViewAdapter_admin2(getContext(), list1, list2, list3,
                                list4, list5);
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
            case R.id.fragmentAdmin2_button_select_all:
                editText1.setText("");
                editText2.setText("");
                list1.clear();
                list2.clear();
                list3.clear();
                list4.clear();
                list5.clear();
                TeacherService service = new TeacherDao(getContext());
                List<Map<String, String>> list = service.listTeacherMaps();

                String key = null;//去重键
                for (Map<String, String> m : list) {
                    for (Map.Entry<String, String> vo : m.entrySet()) {
                        if (key != m.get("userName")) {
                            list1.add(m.get("userName"));
                            list2.add(m.get("password"));
                            list3.add(m.get("realName"));
                            list4.add(m.get("phoneNumber"));
                            list5.add(m.get("roomNum"));
                            key = m.get("userName");
                        }
                    }
                }
                adapter = new ListViewAdapter_admin2(getContext(), list1, list2, list3, list4,
                        list5);
                listView.setAdapter(adapter);
                editText1.setText("");
                editText2.setText("");
                Toast.makeText(getContext(), "所有用户查找成功！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
