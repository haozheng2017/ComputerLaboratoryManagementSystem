package com.example.zh.clms.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zh.clms.R;
import com.example.zh.clms.database.Admin.Admin;
import com.example.zh.clms.database.Admin.AdminDao;
import com.example.zh.clms.database.Admin.AdminService;
import com.example.zh.clms.database.DatabaseOpenHelper;
import com.example.zh.clms.database.Student.Student;
import com.example.zh.clms.database.Student.StudentDao;
import com.example.zh.clms.database.Student.StudentService;
import com.example.zh.clms.database.Teacher.Teacher;
import com.example.zh.clms.database.Teacher.TeacherDao;
import com.example.zh.clms.database.Teacher.TeacherService;
import com.example.zh.clms.utils.sp;

import org.litepal.LitePal;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    private EditText editText_user, editText_password;
    private CheckBox checkBox_remember;
    private Button button_Sign;
    private TextView textView_ip, textView_register, textView_about;

    private String[] String_spinner;
    private String adressIP = null;
    private String string_user = null, string_password = null;
    private String string_register_user = null, string_register_password = null;

    private ArrayAdapter<String> arrayAdapter;

    private static int POSTION = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        createdb();
        LitePal.getDatabase();

        is_First_Run();
        init();
        setSpinner_OnClick();
        setCheckBox_OnClick();
    }

    /**
     * 初始化控件
     */
    private void init() {
        getApplicationContext();
        spinner = findViewById(R.id.login_spinner);
        editText_user = findViewById(R.id.login_editText_user);
        editText_password = findViewById(R.id.login_editText_password);
        checkBox_remember = findViewById(R.id.login_checkbox2);
        button_Sign = findViewById(R.id.login_button);
        textView_ip = findViewById(R.id.login_textview_ip);
        textView_register = findViewById(R.id.login_textview_register);
        textView_about = findViewById(R.id.login_textview_about);

        button_Sign.setOnClickListener(this);
        textView_ip.setOnClickListener(this);
        textView_register.setOnClickListener(this);
        textView_about.setOnClickListener(this);

        sp.getData(this);
        editText_user.setText(sp.sharedPreferences.getString("user", ""));
        editText_password.setText(sp.sharedPreferences.getString("password", ""));
    }

    //Button、TextView_Ip、TextView_register监听事件
    @Override
    public void onClick(View v) {
        string_user = editText_user.getText().toString();
        string_password = editText_password.getText().toString();
        adressIP = getIP();

        switch (v.getId()) {

            case R.id.login_button:
                if (adressIP == null) {
                    Toast.makeText(LoginActivity
                            .this, "请检查网络，没有网络连接", Toast.LENGTH_SHORT).show();
                } else if (POSTION == 1) {
                    Admin admin = new Admin();
                    admin.setUserName(string_user);
                    AdminService service = new AdminDao(this);
                    Map<String, String> map = new HashMap<String, String>();
                    map = service.viewAdmin(admin);
                    if ((string_password).equals(map.get("password"))) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity_Admin.class);
                        startActivity(intent);
                        finish();
                        if (checkBox_remember.isChecked() == true) {
                            sp.saveData(this, string_user, string_password);
                        }
                    } else {
                        Toast.makeText(LoginActivity
                                .this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else if (POSTION == 2) {
                    Teacher teacher = new Teacher();
                    teacher.setUserName(string_user);
                    TeacherService service = new TeacherDao(this);
                    Map<String, String> map = new HashMap<String, String>();
                    map = service.viewTeacher(teacher);
                    if ((string_password).equals(map.get("password"))) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity_Teacher.class);
                        startActivity(intent);
                        finish();
                        if (checkBox_remember.isChecked() == true) {
                            sp.saveData(this, string_user, string_password);
                        }
                    } else {
                        Toast.makeText(LoginActivity
                                .this, "用户名 或 密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else if (POSTION == 3) {
                    Student student = new Student();
                    student.setUserName(string_user);
                    StudentService service = new StudentDao(this);
                    Map<String, String> map = new HashMap<String, String>();
                    map = service.viewStudent(student);
                    if ((string_password).equals(map.get("password"))) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity_Student.class);
                        startActivity(intent);
                        finish();
                        if (checkBox_remember.isChecked() == true) {
                            sp.saveData(this, string_user, string_password);
                        }
                    } else {
                        Toast.makeText(LoginActivity
                                .this, "用户名 或 密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity
                            .this, "请选择登录者身份", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.login_textview_ip:
                show_IPadress_AlertDialog();
                break;

            case R.id.login_textview_register:
                show_register_AlertDialog();
                break;

            case R.id.login_textview_about:
                show_About_AlertDialog();
                break;
        }

    }


    //spinner监听事件
    private void setSpinner_OnClick() {
        String_spinner = getResources().getStringArray(R.array.User_identity);
        arrayAdapter = new ArrayAdapter<String>(LoginActivity.this, R.layout
                .support_simple_spinner_dropdown_item, String_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(LoginActivity
                            .this, "请选择登录者身份", Toast.LENGTH_LONG).show();
                    POSTION = 0;
                }
                if (position == 1) {
                    Toast.makeText(LoginActivity
                            .this, "您将以管理员身份进入本系统", Toast.LENGTH_SHORT).show();
                    POSTION = 1;
                }
                if (position == 2) {
                    Toast.makeText(LoginActivity
                            .this, "您将以教师身份进入本系统", Toast.LENGTH_SHORT).show();
                    POSTION = 2;
                }
                if (position == 3) {
                    Toast.makeText(LoginActivity
                            .this, "您将以学生身份进入本系统", Toast.LENGTH_SHORT).show();
                    POSTION = 3;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(LoginActivity
                        .this, "请选择登陆者身份", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //CheckBox监听事件
    private void setCheckBox_OnClick() {
        checkBox_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    sp.getData(LoginActivity.this);
                    editText_password.setText(sp.sharedPreferences.getString("password", ""));
                } else {
                    editText_password.setText("");
                }
            }
        });
    }


    //显示IP地址半透明弹窗
    private void show_IPadress_AlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("本机IP地址：");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (adressIP != null) {
                    Toast.makeText(LoginActivity
                            .this, "本机器网络通畅", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity
                            .this, "请检查网络，没有网络连接", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(adressIP, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = 0.85f;
        window.setAttributes(layoutParams);
    }

    //显示 关于 半透明弹窗
    private void show_About_AlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("计算机实验室管理系统");
        builder.setItems(new String[]{"项目制作人：郑昊", "电话：17705607189", "学校：安徽三联学院", "学院：计算机工程学院",
                "年级：18级", "系班：计科专升本一班"}, null);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LoginActivity.this, "毕业设计", Toast.LENGTH_SHORT).show();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = 0.85f;
        window.setAttributes(layoutParams);
    }

    //获取本机IP地址
    public static String getIP() {
        try {
            for (Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces(); enu
                    .hasMoreElements(); ) {
                NetworkInterface inf = enu.nextElement();
                for (Enumeration<InetAddress> enumeration = inf.getInetAddresses(); enumeration
                        .hasMoreElements(); ) {
                    InetAddress inetAddress = enumeration.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    //注册界面
    private void show_register_AlertDialog() {
        Toast.makeText(this, "仅有学生用户可以注册，其余用户请联系管理员", Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View view = View.inflate(LoginActivity.this, R.layout
                .activity_login_textview_register_alertdialog, null);

        final ImageView register_imageview_close = view.findViewById(R.id.register_imageview_Close);
        final EditText register_edittext_user = view.findViewById(R.id.register_editText_user);
        final EditText register_edittext_password = view.findViewById(R.id
                .register_editText_password);
        final Button register_button = view.findViewById(R.id.register_button);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        register_imageview_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string_register_user = register_edittext_user.getText().toString().trim();
                string_register_password = register_edittext_password.getText().toString().trim();

                Student student = new Student();
                if (("".equals(string_register_user)) && ("".equals(string_register_password))) {
                    Toast.makeText(LoginActivity.this, "用户名 和 密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    student.setUserName(string_register_user);
                    StudentService service = new StudentDao(LoginActivity.this);
                    Map<String, String> map = service.viewStudent(student);
                    if (string_register_user.equals(map.get("userName"))) {
                        Toast.makeText(LoginActivity.this, "该用户名已被使用", Toast.LENGTH_SHORT).show();
                    } else {
                        student.setUserName(string_register_user);
                        student.setPassword(string_register_password);
                        Object[] params = {student.getUserName(), student.getPassword()};
                        boolean flag = new StudentDao(LoginActivity.this).addStudent(student);
                        if (flag) {
                            Toast.makeText(LoginActivity.this, "数据添加成功，注册成功", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast.makeText(LoginActivity.this, "数据添加失败", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    //创建数据库
    private void createdb() {
        DatabaseOpenHelper dbOpenHelper = new DatabaseOpenHelper(getBaseContext());
        dbOpenHelper.getWritableDatabase();

    }

    //测试超级账户（学生）
    private void product_account_student(String str_name, String str_password) {

        Student student = new Student();
        student.setUserName(str_name);
        student.setPassword(str_password);
        StudentService service = new StudentDao(getApplicationContext());
        Map<String, String> map = service.viewStudent(student);
        Object[] params = {student.getUserName(), student.getPassword()};
        new StudentDao(getApplicationContext()).addStudent(student);
    }

    //测试超级账户（管理员）
    private void product_account_admin() {
        Admin admin = new Admin();
        admin.setUserName("zh");
        admin.setPassword("123");
        AdminService service = new AdminDao(getApplicationContext());
        Map<String, String> map = service.viewAdmin(admin);
        Object[] params = {admin.getUserName(), admin.getPassword()};
        new AdminDao(getApplicationContext()).addAdmin(admin);
    }

    //测试超级账户（管理员）
    private void product_account_teacher() {
        Teacher teacher = new Teacher();
        teacher.setUserName("zh");
        teacher.setPassword("123");
        TeacherService service = new TeacherDao(getApplicationContext());
        Map<String, String> map = service.viewTeacher(teacher);
        Object[] params = {teacher.getUserName(), teacher.getPassword()};
        new TeacherDao(getApplicationContext()).addTeacher(teacher);
    }

    private void is_First_Run() {
        SharedPreferences setting = getSharedPreferences("isFirstRun", 0);
        Boolean user_first = setting.getBoolean("FIRST", true);
        if (user_first) {//第一次
            setting.edit().putBoolean("FIRST", false).commit();
            product_account_admin();
            product_account_teacher();
            product_account_student("zh", "123");
            product_account_student("stu", "123");
            //Toast.makeText(LoginActivity.this, "第一次", Toast.LENGTH_LONG).show();
        } else {
            // Toast.makeText(LoginActivity.this, "不是第一次", Toast.LENGTH_LONG).show();
        }
    }

}
