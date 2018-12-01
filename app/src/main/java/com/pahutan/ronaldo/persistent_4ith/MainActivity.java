package com.pahutan.ronaldo.persistent_4ith;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {
    EditText etExam1, etExam2, etFirstName, etLastName, etAverage;
    Button  btnAverage, btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etExam1 = findViewById(R.id.etExam1);
        etExam2 = findViewById(R.id.etExam2);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAverage = findViewById(R.id.etAverage);
        btnAverage = findViewById(R.id.btnAverage);
        btnLoad = findViewById(R.id.btnLoad);

    }
    public void next(View v){
        Intent i = new Intent(this, activity_2.class);
        startActivity(i);
    }

    public void Average(View v){
        String exam1 = etExam1.getText().toString();
        String exam2 = etExam2.getText().toString();
        String firstname = etFirstName.getText().toString();
        String lastname = etLastName.getText().toString();
        String average = etAverage.getText().toString();
        FileOutputStream fos = null;

        int num1 = Integer.parseInt(exam1);
        int num2 = Integer.parseInt(exam2);
        int calculate = (num1 + num2)/2;
        int result = calculate;

        try {
            fos = openFileOutput("Average1", MODE_PRIVATE);
            fos.write(firstname.getBytes());
            fos.write(lastname.getBytes());
            fos.write(exam1.getBytes());
            fos.write(exam2.getBytes());
            fos.write(average.getBytes());

            etExam1.getText().clear();
            etExam2.getText().clear();
            etFirstName.getText().clear();
            etLastName.getText().clear();
            etAverage.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "Average1",
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //SharedPreferences sp = getSharedPreferences("Data1", MODE_PRIVATE);
        //SharedPreferences.Editor writer = sp.edit();
        //writer.putInt("Average", result);
        //writer.commit();
        Toast.makeText(this, "The average is " + result, Toast.LENGTH_LONG).show();
    }

    public void load(View v) {
        FileInputStream fis = null;

        try {
            fis = openFileInput("Average1");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String firstname;

            while ((firstname = br.readLine()) != null) {
                sb.append(firstname).append("\n");
            }

            etFirstName.setText(sb.toString());
            etLastName.setText(sb.toString());
            etExam1.setText(sb.toString());
            etExam2.setText(sb.toString());
            etAverage.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
