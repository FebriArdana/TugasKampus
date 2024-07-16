package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private CardView f_apkCardView, jenisCardView, riwayatCardView;
    private DatabaseReference reference;
    private String currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f_apkCardView = findViewById(R.id.f_apk);
        jenisCardView = findViewById(R.id.jenis);
        reference = FirebaseDatabase.getInstance("https://project-af67c-default-rtdb.firebaseio.com/").getReference("users");

        // Get the current user's email (assuming it was passed from the login activity)
        currentUserEmail = getIntent().getStringExtra("email");
        if (currentUserEmail != null) {
            currentUserEmail = currentUserEmail.replace(".", ",");
        }

        // Load user role from Firebase and set up UI accordingly
        loadUserRoleAndSetupUI();

        // Set onClickListener for f_apk CardView
        f_apkCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your action here, for example:
                Intent intent = new Intent(MainActivity.this, ItemRiwayatActivity.class);
                startActivity(intent);
            }
        });

        jenisCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.sphome);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.sphome){
                    return true;
                }
                if (id == R.id.splokasi){
                    startActivity(new Intent(getApplicationContext(), ArtikelActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;
                }
                if (id == R.id.sptambah){
                    startActivity(new Intent(getApplicationContext(), TambahActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;
                }
                return false;
            }
        });
    }

    private void loadUserRoleAndSetupUI() {
        if (currentUserEmail != null) {
            reference.child(currentUserEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String role = dataSnapshot.child("role").getValue(String.class);
                        setupUIBasedOnRole(role);
                    } else {
                        Toast.makeText(MainActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setupUIBasedOnRole(String role) {
        if ("admin".equals(role)) {
            // Set up UI for admin
            // Example: Change text or visibility of certain elements
            // You can customize this part based on your requirements
            findViewById(R.id.admin_specific_element).setVisibility(View.VISIBLE);
        } else {
            // Set up UI for regular user
            // Example: Hide certain elements or change text
            findViewById(R.id.admin_specific_element).setVisibility(View.GONE);
        }
    }
}