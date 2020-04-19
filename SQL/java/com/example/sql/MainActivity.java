package com.example.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText1, editText2;
    Button button1, button2, button3, button4;
    SQLiteDatabase sqLiteDatabase;
    String name, roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        sqLiteDatabase = openOrCreateDatabase("MY_DATABASE", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Emp(id INTEGER PRIMARY KEY AUTOINCREMENT ,name VARCHAR(250),roll VARCHAR(20));");

    }


    public void add(View view) {
        name = editText1.getText().toString().trim();
        roll = editText2.getText().toString().trim();

        sqLiteDatabase.execSQL("INSERT INTO Emp(name , roll)VALUES('" + name + "','" + roll + "');");
        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();

    }

    public void display(View view) {
        String text = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Emp", null);
        if (c.getCount() == 0) {
            Toast.makeText(this, "No Data Exists", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(c.moveToNext())
            {
                text += "Emp name : " + c.getString(1)+"\tEmp roll : "+c.getString(2)+"\n";
            }
            textView.setTypeface(Typeface.SERIF);
            textView.setTextSize(15);
            textView.setText(text);
            textView.setMovementMethod(new ScrollingMovementMethod());

        }
    }

    public void update(View view) {
        String old = "Tushar";
        String new1 = "TUSHAR";
        sqLiteDatabase.execSQL("UPDATE Emp SET name = '" + new1 +"' WHERE name = '" + old + "';");
    }

    public void delete(View view) {

       sqLiteDatabase.execSQL("DELETE FROM Emp where name = 'Viraj';");
    }
}
