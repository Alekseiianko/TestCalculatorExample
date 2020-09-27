package com.example.testcalculatorexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecretActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);
    }

    public void backToMainActivity(View view) {
        Intent intent = new Intent(SecretActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
