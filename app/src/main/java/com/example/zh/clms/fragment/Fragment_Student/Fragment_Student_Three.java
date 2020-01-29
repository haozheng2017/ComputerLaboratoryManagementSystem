package com.example.zh.clms.fragment.Fragment_Student;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zh.clms.R;
import com.example.zh.clms.activity.LoginActivity;
import com.example.zh.clms.adapter.ListViewAdapter_Student;
import com.example.zh.clms.database.Student.Student;
import com.example.zh.clms.database.Student.StudentDao;
import com.example.zh.clms.database.LitePal_Student_Apply.Student_Apply;
import com.example.zh.clms.database.Student.StudentService;
import com.example.zh.clms.utils.sp;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fragment_Student_Three extends Fragment implements View.OnClickListener, AdapterView
        .OnItemClickListener {
    private RelativeLayout re_personal_info, re_userPassword_update, re_exit_login, re_exit_app;
    private View view;
    ArrayList<String> list_name = new ArrayList<>();
    ArrayList<String> list_phone = new ArrayList<>();
    ArrayList<String> list_roomNumber = new ArrayList<>();
    ArrayList<String> list_startTime = new ArrayList<>();
    ArrayList<String> list_endTime = new ArrayList<>();
    ArrayList<Integer> list_state = new ArrayList<>();
    private ListView listView;
    private ListViewAdapter_Student adapter;

    private String string_getuser;
    private String string_getpassword;
    private String str_ed_first;
    private String str_ed_second;
    private static String t;

    private boolean disagree = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_three, container, false);
        init();

        return view;
    }

    private void init() {
        re_personal_info = view.findViewById(R.id.fragment_three_RelativeLayout_personal_info);
        re_userPassword_update = view.findViewById(R.id
                .fragment_three_RelativeLayout_userPassword_update);
        re_exit_login = view.findViewById(R.id.fragment_three_RelativeLayout_exit_login);
        re_exit_app = view.findViewById(R.id.fragment_three_RelativeLayout_exit_app);

        listView = view.findViewById(R.id.fragment_student_three_listView);
        listView.setOnItemClickListener(this);

        re_personal_info.setOnClickListener(this);
        re_userPassword_update.setOnClickListener(this);
        re_exit_login.setOnClickListener(this);
        re_exit_app.setOnClickListener(this);

        sp.getData(getContext());
        string_getuser = sp.sharedPreferences.getString("user", "");
        string_getpassword = sp.sharedPreferences.getString("password", "");
        t = string_getuser;

        addDataIn_Listview();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_three_RelativeLayout_personal_info:
                //用户信息
                showPersonalDialog();
                break;
            case R.id.fragment_three_RelativeLayout_userPassword_update:
                //用户名密码修改
                show_UserPassword_update_Dialog();
                break;
            case R.id.fragment_three_RelativeLayout_exit_login:
                //退出登录
                showExitLogin();
                break;
            case R.id.fragment_three_RelativeLayout_exit_app:
                //退出应用
                showExitApp();
                break;
        }
    }

    //用户信息
    private void showPersonalDialog() {

        sp.getData(getContext());

        string_getuser = sp.sharedPreferences.getString("user", "");
        string_getpassword = sp.sharedPreferences.getString("password", "");
        t = string_getuser;

        Student student = new Student();
        student.setUserName(string_getuser);
        StudentService service = new StudentDao(getContext());
        Map<String, String> map = service.singleInfo(student);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(view
                .getContext());
        builder.setTitle("用户信息");
        builder.setItems(new String[]{"用户名：" + string_getuser, "密码：" + string_getpassword,
                "真实姓名：" + map.get("realName"), "班级:" + map.get("gradeClass")}, null);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final android.app.AlertDialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = 0.8f;
        window.setAttributes(layoutParams);
    }

    //账户密码修改
    private void show_UserPassword_update_Dialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());

        final View view = View.inflate(getContext(), R.layout
                .fragment_student_three_password_update_alertdialog, null);
        final TextView password_update_textView_username = view.findViewById(R.id
                .fragment_student_three_password_update_TextView_username);
        final ImageView password_update_imageView_close = view.findViewById(R.id
                .fragment_student_three_password_update_imageView_Close);
        final EditText password_update_editText_first = view.findViewById(R.id
                .fragment_student_three_password_update_editText_first);
        final EditText password_update_editText_second = view.findViewById(R.id
                .fragment_student_three_password_update_editText_second);
        final Button password_update_button = view.findViewById(R.id
                .fragment_student_three_password_update_button);
        password_update_textView_username.setText(t);
        builder.setView(view);
        final android.app.AlertDialog dialog = builder.create();
        dialog.show();

        password_update_imageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        password_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_ed_first = password_update_editText_first.getText().toString().trim();
                str_ed_second = password_update_editText_second.getText().toString().trim();
                if ((str_ed_first).equals("")) {
                    Toast.makeText(getContext(), "输入不能为空", Toast.LENGTH_LONG).show();
                } else if ((str_ed_first).equals(str_ed_second)) {
                    sp.saveData(getContext(), t, str_ed_second);

                    Student student = new Student();
                    student.setUserName(t);
                    student.setPassword(str_ed_second);
                    Object[] params = {student.getPassword(), student.getUserName()};
                    boolean flag1 = new StudentDao(getContext()).updateStudent(student, params);
                    if (flag1) {
                        Toast.makeText(getContext(), "数据修改成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "数据修改失败", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "两次输入不相同，请您重新输入！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //退出登录
    private void showExitLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("您确认要退出登录？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (disagree == true) {
                    deleteDisagree();
                }
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
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

    //退出应用程序
    private void showExitApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("您确认要退出应用程序？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (disagree == true) {
                    deleteDisagree();
                }
                getActivity().finish();
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

    private void addDataIn_Listview() {
        list_name.clear();
        list_phone.clear();
        list_roomNumber.clear();
        list_startTime.clear();
        list_endTime.clear();
        list_state.clear();
        List<Student_Apply> User = LitePal.where("user = ? ", string_getuser).find(Student_Apply
                .class);
        for (Student_Apply student_apply : User) {
            list_name.add("申请人姓名：" + student_apply.getName());
            list_phone.add("申请人电话：" + student_apply.getPhone());
            list_roomNumber.add("实验室门牌号：" + student_apply.getRoomNumber());
            list_startTime.add("开始使用时间：" + student_apply.getStartTime());
            list_endTime.add("截止使用时间：" + student_apply.getEndTime());
            list_state.add(student_apply.getTagg());
        }

        adapter = new ListViewAdapter_Student(getContext(), list_name, list_phone,
                list_roomNumber, list_startTime, list_endTime, list_state);
        listView.setAdapter(adapter);
//        Toast.makeText(getContext(), "所有申请查找成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("您的申请结果已经告知，下次登录将不会显示已否决的申请！");
        builder.setPositiveButton("嗯，好的！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                disagree = true;
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteDisagree() {
        LitePal.deleteAll(Student_Apply.class, "user = ? and tagg = ? ", string_getuser, "1");
    }

//    private void deleteAgree() {
//        List<Student_Apply> User = LitePal.where("user = ?  ", string_getuser).find(Student_Apply
//                .class);
//        for (Student_Apply student_apply : User) {
//            if(Fragment_Student_Two.getTimeCompareSize(student_apply
//                    .getEndTime(), simpleDateFormat.format(date)) == 1){
//
//            }
////            Toast.makeText(getContext(), (Fragment_Student_Two.getTimeCompareSize(student_apply
////                    .getEndTime(), simpleDateFormat.format(date)) == 1) + "", Toast
// .LENGTH_SHORT)
////                    .show();
//        }
//
//    }
}
