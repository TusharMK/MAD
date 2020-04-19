package com.example.sharedpreferences;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String My_FILE = "com.example.sharedpreferences.Names" ;
    TextView tvdisplay ;
    EditText etname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvdisplay = findViewById(R.id.tvdisplay);
        etname = findViewById(R.id.etname);
        SharedPreferences pref = getSharedPreferences(My_FILE , MODE_PRIVATE);
        String s = pref.getString("user","");
        tvdisplay.setText("Welcome "+ s);

    }

    public void setName(View view) {
        String txtname = etname.getText().toString().trim();
        if(TextUtils.isEmpty(txtname)) {
            Toast.makeText(this, "Enter Name !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences.Editor editor = getSharedPreferences(My_FILE,MODE_PRIVATE).edit();
        editor.putString("user",txtname);
        editor.commit();
        tvdisplay.setText("Welcome "+ txtname);
    }
}
