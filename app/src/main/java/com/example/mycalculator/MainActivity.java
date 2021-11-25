package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random ;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.mycalculator.extra.NAME" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_toast = findViewById(R.id.toast_button);
        Button button_count = findViewById(R.id.count_button) ;
        Button button_random = findViewById(R.id.random_button) ;
        Button button_reset = findViewById(R.id.button) ;

        Toast.makeText(MainActivity.this,"Welcome User !!",Toast.LENGTH_SHORT).show();

        button_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hey This Is Parth , Please Press Any Button", Toast.LENGTH_SHORT).show();
            }
        }) ;

        button_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.textView) ;
                String s = text.getText().toString() ;
                Integer count = Integer.parseInt(s) ;
                count = count+1 ;
                String s1 = count.toString() ;
                text.setText(s1);
            }
        });

        button_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Generating Random Number..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this,MainActivity2.class) ;

                TextView text = findViewById(R.id.textView) ;
                String s = text.getText().toString() ;
                intent.putExtra(EXTRA_NAME,s) ;

                startActivity(intent);
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.textView) ;
                textView.setText("0");
            }
        });
    }
}