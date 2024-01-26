package com.example.eventzone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class VenueCategoryCreate extends AppCompatActivity implements View.OnClickListener {
    DatePickerDialog.OnDateSetListener setListener;
    EditText tittle, description, date, price, phone;
    Spinner category, location;
    String[] category_names,district_names;
    Button addEventBtn;
    ImageButton uploadImgbtn;
    ImageView imgview;
    ArrayAdapter adapter;
    int imagereq = 1;

    Uri imageUri;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_category_create);

        databaseReference = FirebaseDatabase.getInstance().getReference("VenuePlace");
        storageReference = FirebaseStorage.getInstance().getReference("VenuePlace");
        tittle = findViewById(R.id.tittleEditId);
        category = findViewById(R.id.categorySpinId);
        category_names = getResources().getStringArray(R.array.category_names_place);
        district_names=getResources().getStringArray(R.array.district_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.categorynamelist, R.id.categoryTextId,category_names);
        ArrayAdapter<String> adapters = new ArrayAdapter<>(this, R.layout.categorynamelist, R.id.categoryTextId,district_names);
        category.setAdapter(adapter);
        description = findViewById(R.id.descriptionEditId);
        date = findViewById(R.id.dateEditId);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String dates= day+"/"+month+"/"+year;
                date.setText(dates);
            }
        };
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(VenueCategoryCreate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String dates = day + "/" + month + "/" + year;
                        date.setText(dates);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        price = findViewById(R.id.priceEditId);
        location = findViewById(R.id.locationSpinId);
        location.setAdapter(adapters);
        phone = findViewById(R.id.phoneEditId);
        uploadImgbtn = findViewById(R.id.uploadImgBtn);
        uploadImgbtn.setOnClickListener(this);
        imgview = findViewById(R.id.uploadImgShows);
        addEventBtn = (Button) findViewById(R.id.addEventId);
        addEventBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.uploadImgBtn) {
            openFileChoosers();
        }
        if (v.getId() == R.id.addEventId) {
            eventAdd();
        }
    }

    void openFileChoosers() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, imagereq);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == imagereq && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(imgview);
        }
    }

    //get in the extention of image
    String getFileExtention(Uri imageUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));

    }


    private void eventAdd() {

        String TITTLE = tittle.getText().toString().trim();
        String CATEGORY = category.getSelectedItem().toString().trim();
        String DESCRIPTION = description.getText().toString().trim();
        String LOCATION = location.getSelectedItem().toString().trim();
        String DATE = date.getText().toString().trim();
        String PRICE = price.getText().toString().trim();
        String PHONE = phone.getText().toString().trim();
        if (TITTLE.isEmpty()) {
            tittle.setError("Enter event tittle: ");
            tittle.requestFocus();
            return;


        }
        if (CATEGORY.isEmpty()) {
            tittle.setError("Enter category : ");
            tittle.requestFocus();
            return;


        }
        if (DESCRIPTION.isEmpty()) {
            description.setError("Enter event description: ");
            description.requestFocus();
            return;


        }
        if (LOCATION.isEmpty()) {
            tittle.setError("Enter event location: ");
            tittle.requestFocus();
            return;


        }
        if (DATE.isEmpty()) {
            date.setError("Enter event date: ");
            date.requestFocus();
            return;


        }
        if (PRICE.isEmpty()) {
            price.setError("Enter event price: ");
            price.requestFocus();
            return;


        }
        if (PHONE.isEmpty()) {
            phone.setError("Enter phone no : ");
            phone.requestFocus();
            return;


        }
        StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getFileExtention(imageUri));
        ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful()) ;
                Uri downuri = uriTask.getResult();
                Toast.makeText(getApplicationContext(), "Venue and place Event create  Succesfully Done", Toast.LENGTH_LONG).show();

                Category cat = new Category(TITTLE,CATEGORY,DESCRIPTION,LOCATION,DATE,PRICE,PHONE,downuri.toString());
                String uploadId = databaseReference.push().getKey();
                databaseReference.child(uploadId).setValue(cat);
                Intent intent=new Intent(VenueCategoryCreate.this,MainActivity.class);
                startActivity(intent);



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Image not stored Succesfully", Toast.LENGTH_SHORT).show();

            }
        });


    }
    }
