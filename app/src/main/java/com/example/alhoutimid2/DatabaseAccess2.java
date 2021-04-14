package com.example.alhoutimid2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseAccess2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper dbHelper=new DBHelper(this);
        setContentView(R.layout.activity_database_access2);
        setTitle("Database Access 2 Activity");

        Button go1=findViewById(R.id.button1_act3);
        Button go2=findViewById(R.id.button1_act2);

        Button delete=findViewById(R.id.btn_delete);
        Button select=findViewById(R.id.btn_select);
        Button add=findViewById(R.id.buttonAdd);

        EditText inp_ssn=findViewById(R.id.inp_ssn);
        EditText inp_name=findViewById(R.id.inp_name);
        EditText inp_email=findViewById(R.id.inp_email);
        EditText inp_phone=findViewById(R.id.inp_phone);
        EditText inp_sid=findViewById(R.id.inp_sid);


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=dbHelper.getResult(inp_name.getText()+"");

                if (c==null){
                    Toast.makeText(DatabaseAccess2.this,"There is no such record.",Toast.LENGTH_SHORT).show();
                    return;
                }
                String message= c.getInt(0)+" "+
                        c.getString(1)+" "+
                        c.getString(2)+" "+
                        c.getString(3)+" "+
                        c.getString(4)+" ";

                Toast.makeText(DatabaseAccess2.this,"Record found: "+message,Toast.LENGTH_LONG).show();



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.delete(inp_sid.getText()+"");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insert(inp_ssn.getText().toString(),inp_name.getText().toString(),inp_email.getText().toString(),inp_phone.getText().toString(),inp_sid.getText().toString());
            }
        });

        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseAccess2.this,DatabaseAccess1.class));
            }
        });
        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseAccess2.this,DatabaseAccess3.class));
            }
        });


    }
}