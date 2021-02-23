package com.example.application.notes;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
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

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper helper;
    EditText TxJudul, TxDeskripsi;
    long id;
    Button btnSaveEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        TxJudul = (EditText)findViewById(R.id.txJudul_Edit);
        TxDeskripsi = (EditText)findViewById(R.id.txDeskripsi_Edit);

        btnSaveEdit=(Button)findViewById(R.id.save_edit);
        btnSaveEdit.setOnClickListener(this);

        getData();
    }

    private void getData(){
        Cursor cursor = helper.oneData(id);
        if(cursor.moveToFirst()){
            String judul = cursor.getString(cursor.getColumnIndex(DBHelper.row_judul));
            String deskripsi = cursor.getString(cursor.getColumnIndex(DBHelper.row_deskripsi));

            TxJudul.setText(judul);
            TxDeskripsi.setText(deskripsi);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_edit:
                String judul = TxJudul.getText().toString().trim();
                String deskripsi = TxDeskripsi.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DBHelper.row_judul, judul);
                values.put(DBHelper.row_deskripsi, deskripsi);

                if (judul.equals("") || deskripsi.equals("")){
                    Toast.makeText(EditActivity.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT);
                }else{
                    helper.updateData(values, id);
                    Toast.makeText(EditActivity.this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
}
