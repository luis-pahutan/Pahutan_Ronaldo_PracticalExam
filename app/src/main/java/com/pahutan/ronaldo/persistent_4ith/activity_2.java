package com.pahutan.ronaldo.persistent_4ith;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class activity_2 extends AppCompatActivity {
    TextView tvFirstName, tvLastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
    }
    public void previous(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void loadData(View v){
        SharedPreferences pref = getSharedPreferences("Data1", MODE_PRIVATE);
        String uname = pref.getString("user", null);
        String password = pref.getString("pass", null);
        tvFirstName.setText(uname);
        tvLastName.setText(password);

    }
}
