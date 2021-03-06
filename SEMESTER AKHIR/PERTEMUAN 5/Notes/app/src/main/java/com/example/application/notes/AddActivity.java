package com.example.application.notes;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper helper;
    EditText TxJudul, TxDeskripsi;
    long id;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        TxJudul = (EditText)findViewById(R.id.txJudul_Add);
        TxDeskripsi = (EditText)findViewById(R.id.txDeskripsi_Add);

        btnSave=(Button)findViewById(R.id.save_add);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_add:
                String judul = TxJudul.getText().toString().trim();
                String deskripsi = TxDeskripsi.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DBHelper.row_judul, judul);
                values.put(DBHelper.row_deskripsi, deskripsi);

                if ( judul.equals("") || deskripsi.equals("")){
                    Toast.makeText(AddActivity.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    helper.insertData(values);
                    Toast.makeText(AddActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
}
