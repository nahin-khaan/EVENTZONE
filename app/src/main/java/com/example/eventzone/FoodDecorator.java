package com.example.eventzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodDecorator extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private StageAdapter stageAdapter;
    private List<Category> categoryList;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_decorator);
        recyclerView = findViewById(R.id.foodEventListRecycleId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryList = new ArrayList<>();
        searchView = (SearchView) findViewById(R.id.serchViewId);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }


        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Food");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Category category = dataSnapshot.getValue(Category.class);
                    categoryList.add(category);
                }
                stageAdapter = new StageAdapter(FoodDecorator.this, categoryList);
                recyclerView.setAdapter(stageAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error : " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    private void filterList(String text) {
        List<Category> filteredList = new ArrayList<>();
        for (Category category : categoryList)
        {
            if(category.getLOCATION().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(category);
            }

        }
        if (filteredList.isEmpty())
        {
            Toast.makeText(this, "Data searching", Toast.LENGTH_SHORT).show();
        }
        else {
            stageAdapter.setFilteredList(filteredList);
        }

    }
}