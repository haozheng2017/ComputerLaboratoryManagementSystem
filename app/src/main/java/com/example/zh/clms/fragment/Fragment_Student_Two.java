package com.example.zh.clms.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.zh.clms.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment_Student_Two extends Fragment implements View.OnClickListener {

    private View view;
    private EditText editText_name, editText_phone;
    private Spinner spinner;
    private Button button_start1, button_start2, button_end1, button_end2, button_commit;

    private String string_name, string_phone, string_spinnerValue;
    private String[] strings_spinner;
    private ArrayAdapter<String> arrayAdapter;
    private int m_year = 2019, m_month = 12, m_day = 1, m_hour = 0, m_minute = 0;
    private int m_year2 = 2019, m_month2 = 12, m_day2 = 1, m_hour2 = 0, m_minute2 = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_two, container, false);
        init();
        spinner_OnClick();
        return view;
    }


    private void init() {
        editText_name = view.findViewById(R.id.fragment_student_two_editText_name);
        editText_phone = view.findViewById(R.id.fragment_student_two_editText_phone);
        spinner = view.findViewById(R.id.fragment_student_two_spinner_roomNumber);
        button_start1 = view.findViewById(R.id.fragment_student_two_button_start_1);
        button_start2 = view.findViewById(R.id.fragment_student_two_button_start_2);
        button_end1 = view.findViewById(R.id.fragment_student_two_button_end_1);
        button_end2 = view.findViewById(R.id.fragment_student_two_button_end_2);
        button_commit = view.findViewById(R.id.fragment_student_two_button_commit);

        button_start1.setOnClickListener(this);
        button_start2.setOnClickListener(this);
        button_end1.setOnClickListener(this);
        button_end2.setOnClickListener(this);
        button_commit.setOnClickListener(this);

    }

    private void spinner_OnClick() {
        strings_spinner = getResources().getStringArray(R.array.room_Number);
        arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout
                .support_simple_spinner_dropdown_item, strings_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string_spinnerValue = (String) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        string_name = editText_name.getText().toString().trim();
        string_phone = editText_phone.getText().toString().trim();
        switch (v.getId()) {
            case R.id.fragment_student_two_button_start_1:
                showYearMonthDay_choose();
                break;
            case R.id.fragment_student_two_button_start_2:
                showHourMinute_choose();
                break;
            case R.id.fragment_student_two_button_end_1:
                showEndYearMonthDay_choose();
                break;
            case R.id.fragment_student_two_button_end_2:
                showEndHourMinute_choose();
//                Toast.makeText(getContext(), getTimeCompareSize(m_year + "-" + m_month + "-" +
//                        m_day + " " + m_hour + ":" + m_minute, m_year2 + "-" + m_month2 + "-" +
//                        m_day2 + " " + m_hour2 + ":" + m_minute2) + "", Toast.LENGTH_LONG).show();
                break;
            case R.id.fragment_student_two_button_commit:
                if (string_name.equals("")) {
                    Toast.makeText(getContext(), "申请人姓名不能为空！", Toast.LENGTH_LONG).show();
                } else if (string_phone.equals("")) {
                    Toast.makeText(getContext(), "申请人电话不能为空！", Toast.LENGTH_LONG).show();
                } else if (string_spinnerValue.equals("请选择实验室门牌号")) {
                    Toast.makeText(getContext(), "请选择实验室门牌号！", Toast.LENGTH_LONG).show();
                } else if ((m_year + "-" + m_month + "-" + m_day).equals("2019-12-1")) {
                    Toast.makeText(getContext(), "请选择开始使用实验室的年月日！", Toast.LENGTH_LONG).show();
                } else if ((m_hour + ":" + m_minute).equals("0:0")) {
                    Toast.makeText(getContext(), "请选择开始使用实验室时分时间！", Toast.LENGTH_LONG).show();
                } else if ((m_year2 + "-" + m_month2 + "-" + m_day2).equals("2019-12-1")) {
                    Toast.makeText(getContext(), "请选择截止使用实验室的年月日！", Toast.LENGTH_LONG).show();
                } else if ((m_hour2 + ":" + m_minute2).equals("0:0")) {
                    Toast.makeText(getContext(), "请选择截止使用实验室时分时间！", Toast.LENGTH_LONG).show();
                } else if (getTimeCompareSize(m_year + "-" + m_month + "-" + m_day + " " + m_hour
                        + ":" + m_minute, m_year2 + "-" + m_month2 + "-" + m_day2 + " " + m_hour2
                        + ":" + m_minute2) == 1) {
                    Toast.makeText(getContext(), "开始时间比截止时间大，请重新选择", Toast.LENGTH_LONG).show();
                } else if (getTimeCompareSize(m_year + "-" + m_month + "-" + m_day + " " + m_hour
                        + ":" + m_minute, m_year2 + "-" + m_month2 + "-" + m_day2 + " " + m_hour2
                        + ":" + m_minute2) == 2) {
                    Toast.makeText(getContext(), "开始时间与截止时间相同，请重新选择", Toast.LENGTH_LONG).show();
                } else {
                    showButtonCommit();
                }
                break;
        }
    }

    //开始 年-月-日 选择
    private void showYearMonthDay_choose() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog
                .OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                m_year = year;
                m_month = month;
                m_day = dayOfMonth;
            }
        };
        DatePickerDialog date = new DatePickerDialog(getContext(), dateSetListener, m_year,
                m_month, m_day);

        date.show();
    }

    //开始 时-分 选择
    private void showHourMinute_choose() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog
                .OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                m_hour = hourOfDay;
                m_minute = minute;
            }
        };
        TimePickerDialog dialog = new TimePickerDialog(getContext(), timeSetListener, m_hour,
                m_minute, true);
        dialog.show();
    }

    //结束 年-月-日 选择
    private void showEndYearMonthDay_choose() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog
                .OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                m_year2 = year;
                m_month2 = month;
                m_day2 = dayOfMonth;
            }
        };
        DatePickerDialog date = new DatePickerDialog(getContext(), dateSetListener, m_year2,
                m_month2, m_day2);
        date.show();
    }

    //结束 时-分 选择
    private void showEndHourMinute_choose() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog
                .OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                m_hour2 = hourOfDay;
                m_minute2 = minute;
            }
        };
        TimePickerDialog dialog = new TimePickerDialog(getContext(), timeSetListener, m_hour2,
                m_minute2, true);
        dialog.show();
    }

    public static int getTimeCompareSize(String startTime, String endTime) {
        int i = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//年-月-日 时-分
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(endTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime() < date1.getTime()) {
                i = 1;
            } else if (date2.getTime() == date1.getTime()) {
                i = 2;
            } else if (date2.getTime() > date1.getTime()) {
                i = 3;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return i;
    }

    //提交申请
    private void showButtonCommit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("您确认要提交申请？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //提交申请
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
}
