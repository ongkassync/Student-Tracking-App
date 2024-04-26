package com.example.studenttrackingapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class CreateAccount extends AppCompatActivity {
    TextView Alreadyhave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        EditText txt_Name = findViewById(R.id.Name);
        EditText txt_Lastname = findViewById(R.id.Lastname);
        EditText txt_Email = findViewById(R.id.emailEditText);
        EditText txt_Gender = findViewById(R.id.Gender);
        EditText txt_Birthdate = findViewById(R.id.Birthdate);
        EditText txt_YearLevel = findViewById(R.id.YearLevel);
        EditText txt_Strand = findViewById(R.id.Strand);
        EditText txt_PhoneNum = findViewById(R.id.Phone);
        EditText txt_Password = findViewById(R.id.passwordEditText);
        Button Signup = findViewById(R.id.SignupButton);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = txt_Name.getText().toString();
                String Lastname = txt_Lastname.getText().toString();
                String Email = txt_Email.getText().toString();
                String Gender = txt_Gender.getText().toString();
                String Birthdate = txt_Birthdate.getText().toString();
                String YearLevel = txt_YearLevel.getText().toString();
                String Strand = txt_Strand.getText().toString();
                String PhoneNum = txt_PhoneNum.getText().toString();
                String Password = txt_Password.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://192.168.137.121/StudentTrackingApp/create.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")) {
                            Toast.makeText(CreateAccount.this, "Data Added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreateAccount.this, "Data Failed to Add to Database", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("Name", Name);
                        paramV.put("Lastname", Lastname);
                        paramV.put("Email", Email);
                        paramV.put("Gender", Gender);
                        paramV.put("Birthdate", Birthdate);
                        paramV.put("YearLevel", YearLevel);
                        paramV.put("Strand", Strand);
                        paramV.put("PhoneNum", PhoneNum);
                        paramV.put("Password", Password);

                        return paramV;
                    }
                };
                queue.add(stringRequest);

            }
        });

        Alreadyhave = findViewById(R.id.Alreadyhave);

        // Set OnClickListener for "Create Account" TextView
        Alreadyhave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the account creation activity
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
