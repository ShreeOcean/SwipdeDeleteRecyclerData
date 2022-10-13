package com.ocean.rvitemdeletedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.ocean.rvitemdeletedemo.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ArrayList<RecyclerData> recyclerDataArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerDataArrayList = new ArrayList<>();

        // in below line we are adding data to our array list.
        recyclerDataArrayList.add(new RecyclerData("DSA Course", "DSA Self Paced Course"));
        recyclerDataArrayList.add(new RecyclerData("C++ Course", "C++ Self Paced Course"));
        recyclerDataArrayList.add(new RecyclerData("Java Course", "Java Self Paced Course"));
        recyclerDataArrayList.add(new RecyclerData("Python Course", "Python Self Paced Course"));
        recyclerDataArrayList.add(new RecyclerData("Fork CPP", "Fork CPP Self Paced Course"));
        recyclerDataArrayList.add(new RecyclerData("Amazon SDE", "Amazon SDE Test Questions"));

        // initializing our adapter class with our array list and context.
        recyclerViewAdapter = new RecyclerViewAdapter(recyclerDataArrayList, this);

        // below line is to set layout manager for our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);

        binding.idRVCourse.setLayoutManager(manager);
        binding.idRVCourse.setAdapter(recyclerViewAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                RecyclerData deletedCourse = recyclerDataArrayList.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                recyclerDataArrayList.remove(viewHolder.getAdapterPosition());
                recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                Snackbar.make(binding.idRVCourse , deletedCourse.getTitle(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // adding on click listener to our action of snack bar.
                        // below line is to add our item to array list with a position.
                        recyclerDataArrayList.add(position, deletedCourse);

                        // below line is to notify item is
                        // added to our adapter class.
                        recyclerViewAdapter.notifyItemInserted(position);
                    }
                }).show();
            }

        }).attachToRecyclerView(binding.idRVCourse);

    }
}