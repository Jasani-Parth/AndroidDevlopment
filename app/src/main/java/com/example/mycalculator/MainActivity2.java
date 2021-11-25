package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView = findViewById(R.id.textView2) ;
        Intent intent = getIntent() ;
        String s = intent.getStringExtra(MainActivity.EXTRA_NAME) ;


        int x = Integer.parseInt(s) ;

        TextView textView1 = findViewById(R.id.textView3) ;
        if ( x == 0 )
        {
            textView.setText("Press Count Button!!");
            textView1.setText(Integer.toString(x));
        }
        else
        {
            textView.setText("Random Number Between 0 And " + s + " Is..  ");
            Random random = new Random() ;
            int t = random.nextInt(x) ;
            textView1.setText(Integer.toString(t));
        }
    }

}