package com.example.bai3tuan2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup gr;
    ImageView v;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gr = findViewById(R.id.radioGroup);
        v = findViewById(R.id.imageView3);

       gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               id = gr.getCheckedRadioButtonId();
                setImage(id);
           }
       });
    }

    private void setImage(int id) {
        switch (id) {
            case R.id.btnBird: {
                v.setImageResource(R.drawable.bird);
                break;
            }
            case R.id.btnCat: {
                v.setImageResource(R.drawable.cat);
                break;
            }
            case R.id.btnDog: {
                v.setImageResource(R.drawable.dogjpg);
                break;
            }
            case R.id.btnRabbit: {
                v.setImageResource(R.drawable.rabbit);
                break;
            }
            case R.id.btnPig: {
                v.setImageResource(R.drawable.pig_);
                break;
            }
        }
    }
}