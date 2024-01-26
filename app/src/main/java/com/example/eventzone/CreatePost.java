package com.example.eventzone;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class CreatePost extends AppCompatActivity implements View.OnClickListener {
    EditText descriptionText;
    ImageButton uploadImage;
    Button createPostBtn;
    Uri imgUri;
    ImageView imageView,uploadimgshow;
    int IMAGE_REQUEST = 1;
    DatabaseReference databaseReference;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        //hide tittle bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//hide action bar
        getSupportActionBar().hide();
        descriptionText = findViewById(R.id.createDescriptionEditId);
        imageView=findViewById(R.id.uploadImgShow);
        uploadImage = findViewById(R.id.uploadImageBtn);
        uploadimgshow=findViewById(R.id.uploadImgShow);
        createPostBtn = findViewById(R.id.createPostBtn);
        databaseReference = FirebaseDatabase.getInstance().getReference("Upload");
        storageReference = FirebaseStorage.getInstance().getReference("Upload");
        uploadImage.setOnClickListener(this);
        createPostBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.uploadImageBtn) {
            openFileChooser();

        }
        if (v.getId() == R.id.createPostBtn) {
            createPost();
        }
    }


    void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();
            Picasso.get().load(imgUri).into(imageView);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void createPost() {
        String imageName= descriptionText.getText().toString().trim();
        if (imageName.isEmpty()) {
            descriptionText.setError("enter description" );
            descriptionText.requestFocus();
            return;

        }
        StorageReference ref=storageReference.child(System.currentTimeMillis()+"."+getFileExtention(imgUri));
        ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                Uri downuri=uriTask.getResult();

                Upload upload=new Upload(imageName,downuri.toString());
                String imgId=databaseReference.push().getKey();
                databaseReference.child(imgId).setValue(upload);
                Toast.makeText(getApplicationContext(),"Image stored Succesfully",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Image not stored Succesfully",Toast.LENGTH_SHORT).show();

            }
        });

    }
//get in the extention of image
    String getFileExtention(Uri imgUri)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imgUri));

    }

}

