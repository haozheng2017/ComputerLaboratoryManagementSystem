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

public class ListViewAdapter extends BaseAdapter {

    List<String> list1 = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, List<String> list1, List<String> list2) {
        this.list1 = list1;
        this.list2 = list2;
        inflater = LayoutInflater.from(context);
    }

    private class viewHolder {
        TextView textView1;
        TextView textView2;
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
            convertView = inflater.inflate(R.layout.fragment_admin_1_listview_item, null);
            holder = new viewHolder();
            holder.textView1 = convertView.findViewById(R.id.fragmentAdmin1_list_item_textView1);
            holder.textView2 = convertView.findViewById(R.id.fragmentAdmin1_list_item_textView2);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.textView1.setText(list1.get(position));
        holder.textView1.setTextColor(Color.BLACK);
        holder.textView2.setText(list2.get(position));
        holder.textView2.setTextColor(Color.BLACK);
        return convertView;
    }
}
