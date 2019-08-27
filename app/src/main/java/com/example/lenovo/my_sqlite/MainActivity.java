package com.example.lenovo.my_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDatabaseHelper(this,MyDatabaseHelper.CREATE_BOOK,null,1);
        Button createDatabase=findViewById(R.id.button);
        Button addData=findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name","the Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();
                values.put("name","the Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",488);
                values.put("price",19.95);
                db.insert("Book",null,values);
            }
        });
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        Button queryButton=findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor=db.query("Book",null,null,null,
                null,null,null);
                if(cursor.moveToFirst()){
                    do{
                       String name=cursor.getString(cursor.getColumnIndex("name"));
                       String author=cursor.getString(cursor.getColumnIndex("author"));
                       int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                       double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity","book name is"+name);
                    }while (cursor.moveToNext());
                }
            }
        });
    }
}
