package com.example.zh.clms.fragment.Fragment_Teacher;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zh.clms.R;
import com.example.zh.clms.adapter.ListViewAdapter_Teacher;
import com.example.zh.clms.database.LitePal_Student_Apply.Student_Apply;
import com.example.zh.clms.utils.sp;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Teacher_Two extends Fragment implements AdapterView.OnItemClickListener {

    private View view;
    ArrayList<String> list_name = new ArrayList<>();
    ArrayList<String> list_phone = new ArrayList<>();
    ArrayList<String> list_roomNumber = new ArrayList<>();
    ArrayList<String> list_startTime = new ArrayList<>();
    ArrayList<String> list_endTime = new ArrayList<>();
    ArrayList<Integer> list_state = new ArrayList<>();
    private ListView listView;
    private ListViewAdapter_Teacher adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_2, container, false);

        init();

        return view;
    }

    private void init() {
        listView = view.findViewById(R.id.fragment_teacher_2_listView);

        listView.setOnItemClickListener(this);

        addDataIn_Listview();
        adapter = new ListViewAdapter_Teacher(getContext(), list_name, list_phone,
                list_roomNumber, list_startTime, list_endTime, list_state);
        listView.setAdapter(adapter);
//        Toast.makeText(getContext(), "所有申请查找成功！", Toast.LENGTH_SHORT).show();
    }

    private void addDataIn_Listview() {
        sp.getData(getContext());

        list_name.clear();
        list_phone.clear();
        list_roomNumber.clear();
        list_startTime.clear();
        list_endTime.clear();
        list_state.clear();
//        Toast.makeText(getContext(), (sp.sharedPreferences.getString("user", "").equals("zh01"))
//                + "", Toast.LENGTH_SHORT).show();
        if (sp.sharedPreferences.getString("user", "").equals("zh01")) {
            List<Student_Apply> student_applies = LitePal.where("roomNumber = ?", "D501").find
                    (Student_Apply.class);
            ForDataAdd(student_applies);
        } else if (sp.sharedPreferences.getString("user", "").equals("zh02")) {
            List<Student_Apply> student_applies = LitePal.where("roomNumber = ?", "D502").find
                    (Student_Apply.class);
            ForDataAdd(student_applies);
        } else if (sp.sharedPreferences.getString("user", "").equals("zh03")) {
            List<Student_Apply> student_applies = LitePal.where("roomNumber = ?", "D503").find
                    (Student_Apply.class);
            ForDataAdd(student_applies);
        } else if (sp.sharedPreferences.getString("user", "").equals("zh04")) {
            List<Student_Apply> student_applies = LitePal.where("roomNumber = ?", "D504").find
                    (Student_Apply.class);
            ForDataAdd(student_applies);
        } else if (sp.sharedPreferences.getString("user", "").equals("zh05")) {
            List<Student_Apply> student_applies = LitePal.where("roomNumber = ?", "D505").find
                    (Student_Apply.class);
            ForDataAdd(student_applies);
        } else {
            List<Student_Apply> student_applies = LitePal.findAll(Student_Apply.class);
            ForDataAdd(student_applies);
        }

    }

    private void ForDataAdd(List<Student_Apply> student_applies) {
        for (Student_Apply student_apply : student_applies) {
            list_name.add("申请人姓名：" + student_apply.getName());
            list_phone.add("申请人电话：" + student_apply.getPhone());
            list_roomNumber.add("实验室门牌号：" + student_apply.getRoomNumber());
            list_startTime.add("开始使用时间：" + student_apply.getStartTime());
            list_endTime.add("截止使用时间：" + student_apply.getEndTime());
            list_state.add(student_apply.getTagg());
        }
    }

    //listView单击事件
    @Override
    public void onItemClick(AdapterView<?> parent, final View view, final int position, final
    long id) {

        //listview单击item后的弹窗
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("对此申请是否同意？");
        builder.setPositiveButton("同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Student_Apply student_apply_update = new Student_Apply();
                student_apply_update.setTagg(2);
                student_apply_update.updateAll("roomNumber = ? and startTime = ? ",
                        list_roomNumber.get(position).substring(list_roomNumber.get(position)
                                .length() - 4), list_startTime.get(position).substring(7,
                                list_startTime.get(position).length()));

                addDataIn_Listview();
                adapter = new ListViewAdapter_Teacher(getContext(), list_name, list_phone,
                        list_roomNumber, list_startTime, list_endTime, list_state);
                listView.setAdapter(adapter);

            }
        });
        builder.setNegativeButton("不同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Student_Apply student_apply_update = new Student_Apply();
                student_apply_update.setTagg(1);
                student_apply_update.updateAll("roomNumber = ? and startTime = ? ",
                        list_roomNumber.get(position).substring(list_roomNumber.get(position)
                                .length() - 4), list_startTime.get(position).substring(7,
                                list_startTime.get(position).length()));

                addDataIn_Listview();
                adapter = new ListViewAdapter_Teacher(getContext(), list_name, list_phone,
                        list_roomNumber, list_startTime, list_endTime, list_state);
                listView.setAdapter(adapter);
//                Toast.makeText(getContext(), "所有申请查找成功！", Toast.LENGTH_SHORT).show();

//                LitePal.deleteAll(Student_Apply.class, "roomNumber = ? and startTime = ? ",
//                        list_roomNumber.get(position).substring(list_roomNumber.get(position)
//                                .length() - 4), list_startTime.get(position).substring(7,
//                                list_startTime.get(position).length()));
//
//                list_name.remove(position);
//                list_phone.remove(position);
//                list_roomNumber.remove(position);
//                list_startTime.remove(position);
//                list_endTime.remove(position);
//                Toast.makeText(getContext(), "数据删除成功", Toast.LENGTH_SHORT).show();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = 0.85f;
        window.setAttributes(layoutParams);
    }

}

