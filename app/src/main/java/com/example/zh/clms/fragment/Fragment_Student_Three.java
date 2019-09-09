package com.example.zh.clms.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zh.clms.R;

public class Fragment_Student_Three extends Fragment implements View.OnClickListener {
    private TextView textView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_student_three, container, false);
        init();
        return view;
    }

    private void init() {
        textView = view.findViewById(R.id.fragment_three_textview);

        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_three_textview:
                getActivity().finish();
                break;

        }
    }
}
