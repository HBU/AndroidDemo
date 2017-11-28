package com.example.sqlitestudent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/** * RecyclerView Adapter .  Created by David on 2017/6/20. */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private ArrayList<Student> mData;
    public MyAdapter(ArrayList<Student> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<Student> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_stu, parent, false);// 实例化展示的view
        ViewHolder viewHolder = new ViewHolder(v);// 实例化viewholder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.mTv.setText(mData.get(position));// 绑定数据
        Student student = mData.get(position);
        holder.tvAge.setText(student.age+"");//注意数据类型
        holder.tvName.setText(student.name);
        holder.tvID.setText(student.student_ID+"");
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvID,tvName,tvAge;
        public ViewHolder(View itemView) {
            super(itemView);
            tvID = (TextView) itemView.findViewById(R.id.item_stuID);
            tvName = (TextView) itemView.findViewById(R.id.item_stuName);
            tvAge = (TextView) itemView.findViewById(R.id.item_stuAge);
        }
    }
}