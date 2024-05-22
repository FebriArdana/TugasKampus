package com.example.a2212500199_febri_uas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class mainscreen extends AppCompatActivity {

    ImageView imageView, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        imageView = findViewById(R.id.lok);
        img = findViewById(R.id.tambah);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainscreen.this, lokasi1.class));
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainscreen.this, TambahDataActivity.class));
            }
        });

    }
}