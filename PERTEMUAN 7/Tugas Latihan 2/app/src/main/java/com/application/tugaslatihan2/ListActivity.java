package com.application.tugaslatihan2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {
    Button detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.listToolBar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        detail = findViewById(R.id.btn_detail);
        detail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ListActivity.this, DetailActivity.class);
        startActivity(intent);
    }
}