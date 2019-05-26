package com.example.bai5tuan2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    List<card> list = new ArrayList<>();
    ImageView p1, p2, p3;
    Button btnPlay;
    TextView total;
    int a = -1, b = -1, c = -1;
    boolean f1 = false, f2 = false, f3 = false;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(new card(0, R.drawable.c10));
        list.add(new card(0, R.drawable.c210));
        list.add(new card(0, R.drawable.c310));
        list.add(new card(1, R.drawable.c1));
        list.add(new card(2, R.drawable.c2));
        list.add(new card(3, R.drawable.c3));
        list.add(new card(4, R.drawable.c4));
        list.add(new card(5, R.drawable.c5));
        list.add(new card(6, R.drawable.c6));
        list.add(new card(7, R.drawable.c7));
        list.add(new card(8, R.drawable.c8));
        list.add(new card(9, R.drawable.c9));
        p1 = findViewById(R.id.image1);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!f1) {
                    do {
                        a = random();
                    }while(a==b||a==c);


                    score += list.get(a).getValue();
                    p1.setImageResource(list.get(a).getPath());
                    f1 = true;
                    check_success();
                }
            }
        });
        p2 = findViewById(R.id.image2);
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!f2) {
                    do {
                        b = random();
                    }while(b==a||b==c);
                    score += list.get(b).getValue();
                    p2.setImageResource(list.get(b).getPath());
                    f2 = true;
                    check_success();
                }
            }
        });
        p3 = findViewById(R.id.image3);
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!f3) {
                    do {
                        c = random();
                    }while(c==a||c==b);
                    score += list.get(c).getValue();
                    p3.setImageResource(list.get(c).getPath());
                    f3 = true;
                    check_success();
                }
            }
        });
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                p1.setImageResource(R.drawable.baitay);
                p2.setImageResource(R.drawable.baitay);
                p3.setImageResource(R.drawable.baitay);
                f1 = false;
                f2 = false;
                f3 = false;
                total.setText("Score: ");
            }
        });
    }

    private int random() {
        Random r = new Random();
        int num = r.nextInt(list.size());
        return num;
    }

    private void check_success() {
        if (f1 && f2 && f3) {
            total = findViewById(R.id.txtScore);
            total.setText("Score: ");
            if (score >= 10) score %= 10;
            total.setText(total.getText() + "" + score);
        }
    }
}
