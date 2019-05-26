package com.example.baitaptuan4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNextQuestion();
            }
        });
    }

    public void toBackQuestion(){
        Intent intent = new Intent(MainActivity.this, CauHoi2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.startActivity(intent);
    }

    public void toNextQuestion(){
        Intent intent = new Intent(MainActivity.this, CauHoi2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.startActivity(intent);
    }


}
