package com.example.zh.clms.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zh.clms.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter_admin2 extends BaseAdapter {

    List<String> list1 = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();
    List<String> list3 = new ArrayList<String>();
    List<String> list4 = new ArrayList<String>();
    List<String> list5 = new ArrayList<String>();
    private LayoutInflater inflater;

    public ListViewAdapter_admin2(Context context, List<String> list1, List<String> list2,
                                  List<String> list3, List<String> list4, List<String> list5) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.list5 = list5;
        inflater = LayoutInflater.from(context);
    }

    private class viewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
    }

    @Override
    public int getCount() {
        return list1.size();
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
        viewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.fragment_admin_listview_item2, null);
            holder = new viewHolder();
            holder.textView1 = convertView.findViewById(R.id.fragmentAdmin1_list_item2_textView1);
            holder.textView2 = convertView.findViewById(R.id.fragmentAdmin1_list_item2_textView2);
            holder.textView3 = convertView.findViewById(R.id.fragmentAdmin1_list_item2_textView3);
            holder.textView4 = convertView.findViewById(R.id.fragmentAdmin1_list_item2_textView4);
            holder.textView5 = convertView.findViewById(R.id.fragmentAdmin1_list_item2_textView5);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.textView1.setText("用  户  名："+list1.get(position));
        holder.textView1.setTextColor(Color.BLACK);
        holder.textView2.setText("密        码："+list2.get(position));
        holder.textView2.setTextColor(Color.BLACK);
        holder.textView3.setText("真实姓名："+list3.get(position));
        holder.textView3.setTextColor(Color.BLACK);
        holder.textView4.setText("联系电话："+list4.get(position));
        holder.textView4.setTextColor(Color.BLACK);
        holder.textView5.setText("管理的教室："+list5.get(position));
        holder.textView5.setTextColor(Color.BLACK);
        return convertView;
    }
}
