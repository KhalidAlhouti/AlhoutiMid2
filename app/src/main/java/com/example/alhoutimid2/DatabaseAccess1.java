package com.example.alhoutimid2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseAccess1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_access1);

        setTitle("Database Access 1 Activity");

        Button go1=findViewById(R.id.Button2_act1);
        Button go3=findViewById(R.id.Button2_act3);

        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseAccess1.this,DatabaseAccess2.class));
            }
        });
        go3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseAccess1.this,DatabaseAccess3.class));
            }
        });


        Button select_1=findViewById(R.id.btn_read_first_row);
        Button del_1=findViewById(R.id.btn_del_first_row);
        TextView out_row_1=findViewById(R.id.out_first_row);

        DBHelper dbHelper=new DBHelper(this);

        select_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor Mycursor=dbHelper.getFirstRow();
                if (Mycursor==null){
                    Toast.makeText(DatabaseAccess1.this,"No data to read.",Toast.LENGTH_SHORT).show();
                    return;
                }
                out_row_1.setText(Mycursor.getInt(0)+" "+ Mycursor.getString(1)+" "+ Mycursor.getString(2)+" "+ Mycursor.getString(3)+" "+ Mycursor.getString(4)+" ");

            }
        });
        del_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteFirstRow();
                Toast.makeText(DatabaseAccess1.this,"Successfully deleted first row",Toast.LENGTH_SHORT).show();
            }
        });

    }
}