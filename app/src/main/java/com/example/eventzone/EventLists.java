package com.example.eventzone;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventLists extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    private List<Event> eventList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_lists);
        databaseReference = FirebaseDatabase.getInstance().getReference("event");
        eventList = new ArrayList<>();
        customAdapter = new CustomAdapter(EventLists.this, eventList);
        listView = findViewById(R.id.stageListId);


    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventList.clear();
                for(DataSnapshot dataSnapshot1:snapshot.getChildren())
                {
                    Event event=dataSnapshot1.getValue(Event.class);
                    eventList.add(event);
                }
                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}