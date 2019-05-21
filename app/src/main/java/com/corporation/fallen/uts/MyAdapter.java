/*
Tanggal Pengerjaan : 21-05-2019
NIM                : 10116115
Nama               : Aditya Komara Ramadhan
Kelas              : IF-3
 */
package com.corporation.fallen.uts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhaumik on 8/29/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    List<UserData> list = new ArrayList<>();
    ItemClickListener itemClickListener;

    public MyAdapter(List<UserData> list) {
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        final UserData userData = list.get(position);

        holder.tv_name.setText(userData.getName());
        holder.tv_email.setText(userData.getEmail());
        holder.tv_nim.setText(userData.getNim());
        holder.tv_kelas.setText(userData.getKelas());
        holder.tv_telepon.setText(userData.getTelepon());
        holder.tv_sosmed.setText(userData.getSosmed());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.OnItemClick(position,userData);
            }
        });

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView tv_name,tv_email,tv_nim,tv_kelas,tv_telepon,tv_sosmed,tv_delete;

        public MyHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name_item);
            tv_email = itemView.findViewById(R.id.tv_email_item);
            tv_nim = itemView.findViewById(R.id.tv_nim_item);
            tv_kelas = itemView.findViewById(R.id.tv_kelas_item);
            tv_telepon = itemView.findViewById(R.id.tv_telepon_item);
            tv_sosmed = itemView.findViewById(R.id.tv_sosmed_item);
            tv_delete = itemView.findViewById(R.id.tv_delete_item);
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void UpdateData(int position,UserData userData){

        list.remove(position);
        list.add(userData);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }
}
