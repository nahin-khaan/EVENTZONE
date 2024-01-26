package com.example.eventzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eventzone.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity{
    com.example.eventzone.databinding.ActivityMainBinding binding;

    ActionBarDrawerToggle drawerToggle;



    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayoutss);
        NavigationView navigationView = findViewById(R.id.navId);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.changePassword) {

                    Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Change Password Page", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (item.getItemId() == R.id.logouts) {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Log Out", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.abouts) {

                    Intent intent = new Intent(getApplicationContext(), About.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (item.getItemId() == R.id.createevents) {

                    Intent intent = new Intent(getApplicationContext(), CategorySelect.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Choose Category", Toast.LENGTH_SHORT).show();
                    return true;

                }

                if (item.getItemId() == R.id.payment) {

                    Intent intent = new Intent(getApplicationContext(), Payement.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Payment Screen Open", Toast.LENGTH_SHORT).show();
                    return true;

                }
                if (item.getItemId() == R.id.createposts) {
                    Intent intent = new Intent(getApplicationContext(), CreatePost.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Create Post", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (item.getItemId() == R.id.share) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT,"Downlad this fantastic EventZone App. One stop solution app for your upcoming event .  \n\nhttps://play.google.com/store/apps/details?id="+getPackageName());

                    startActivity(Intent.createChooser(intent,"Chosse one"));
                    Toast.makeText(MainActivity.this, "Share this app", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return true;
            }

        });


        binding.bottomNav.setOnItemSelectedListener(item ->
        {


            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());

            }

            if (item.getItemId() == R.id.account) {
                replaceFragment(new AccountFragment());

            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameId, fragment);
        fragmentTransaction.commit();


    }


}