package com.example.zh.clms.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zh.clms.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter_Teacher extends BaseAdapter {

    List<String> list_name = new ArrayList<>();
    List<String> list_phone = new ArrayList<>();
    List<String> list_roomNumber = new ArrayList<>();
    List<String> list_startTime = new ArrayList<>();
    List<String> list_endTime = new ArrayList<>();
    List<Integer> list_state = new ArrayList<>();
    private LayoutInflater inflater;
    viewHolder holder;

    public ListViewAdapter_Teacher(Context context, List<String> list_name, List<String>
            list_phone, List<String> list_roomNumber, List<String> list_startTime, List<String>
            list_endTime, List<Integer> list_state) {
        this.list_name = list_name;
        this.list_phone = list_phone;
        this.list_roomNumber = list_roomNumber;
        this.list_startTime = list_startTime;
        this.list_endTime = list_endTime;
        this.list_state = list_state;
        inflater = LayoutInflater.from(context);
    }

    private class viewHolder {
        TextView textView_name;
        TextView textView_phone;
        TextView textView_roomNumber;
        TextView textView_startTime;
        TextView textView_endTime;
        TextView textView_state;
    }

    @Override
    public int getCount() {
        return list_name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = inflater.inflate(R.layout.fragment_teacher_2_listview_item, null);
            holder = new viewHolder();
            holder.textView_name = convertView.findViewById(R.id
                    .fragment_teacher_2_listView_item_textView_name);
            holder.textView_phone = convertView.findViewById(R.id
                    .fragment_teacher_2_listView_item_textView_phone);
            holder.textView_roomNumber = convertView.findViewById(R.id
                    .fragment_teacher_2_listView_item_textView_roomNumber);
            holder.textView_startTime = convertView.findViewById(R.id
                    .fragment_teacher_2_listView_item_textView_startTime);
            holder.textView_endTime = convertView.findViewById(R.id
                    .fragment_teacher_2_listView_item_textView_endTime);
            holder.textView_state = convertView.findViewById(R.id
                    .fragment_teacher_2_listView_item_textView_state);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.textView_name.setText(list_name.get(position));
        holder.textView_name.setTextColor(Color.BLACK);
        holder.textView_phone.setText(list_phone.get(position));
        holder.textView_phone.setTextColor(Color.BLACK);
        holder.textView_roomNumber.setText(list_roomNumber.get(position));
        holder.textView_roomNumber.setTextColor(Color.BLACK);
        holder.textView_startTime.setText(list_startTime.get(position));
        holder.textView_startTime.setTextColor(Color.BLACK);
        holder.textView_endTime.setText(list_endTime.get(position));
        holder.textView_endTime.setTextColor(Color.BLACK);
        if (list_state.get(position) == 1) {
            holder.textView_state.setText("不同意");
        } else if (list_state.get(position) == 2) {
            holder.textView_state.setText("同意");
        } else {
            holder.textView_state.setText("审核中");
        }
        holder.textView_state.setTextColor(Color.BLACK);

        return convertView;
    }
}
