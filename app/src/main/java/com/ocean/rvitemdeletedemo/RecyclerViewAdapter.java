package com.ocean.rvitemdeletedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ocean.rvitemdeletedemo.databinding.CardLayoutBinding;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<RecyclerData> courseDataArrayList;
    private Context mcontext;
    CardLayoutBinding binding;

    public RecyclerViewAdapter(ArrayList<RecyclerData> courseDataArrayList, Context mcontext) {
        this.courseDataArrayList = courseDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = CardLayoutBinding.inflate(LayoutInflater.from(mcontext), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        RecyclerData recyclerData = courseDataArrayList.get(position);
        holder.binding.idTVCourseName.setText(recyclerData.getTitle());
        holder.binding.idTVCourseDesc.setText(recyclerData.getDescription());
    }

    @Override
    public int getItemCount() {
        return courseDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CardLayoutBinding binding;

        public MyViewHolder(CardLayoutBinding itemview) {
            super(itemview.getRoot());
            binding = itemview;
        }
    }
}
