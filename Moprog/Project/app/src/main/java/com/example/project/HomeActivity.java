package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private CardView f_apkCardView, jenisCardView, riwayatCardView;
    private DatabaseReference reference;
    private String currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize CardViews
        f_apkCardView = findViewById(R.id.f_apk);
        jenisCardView = findViewById(R.id.jenis);
        riwayatCardView = findViewById(R.id.riwayat);


        // Initialize Firebase Database
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
                Intent intent = new Intent(HomeActivity.this, ItemRiwayatActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for jenis CardView
        jenisCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ArtikelActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for riwayat CardView
        riwayatCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RiwayatActivity.class);
                startActivity(intent);
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
                        Toast.makeText(HomeActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(HomeActivity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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