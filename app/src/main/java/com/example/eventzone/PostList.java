package com.example.eventzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Upload> uploadList;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        recyclerView=findViewById(R.id.postListRecycleId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Upload");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Upload upload=dataSnapshot.getValue(Upload.class);
                    uploadList.add(upload);
                }
                myAdapter=new MyAdapter(PostList.this,uploadList);
                recyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error :"+error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}