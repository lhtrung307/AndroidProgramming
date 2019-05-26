package com.example.b2changeinforimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox checkSun = findViewById(R.id.checkBoxSun);
        final CheckBox checkCloud = findViewById(R.id.checkBoxCloud);
        CheckBox checkHouse = findViewById(R.id.checkBoxHouse);
        CheckBox checkGrass = findViewById(R.id.checkBoxGrass);
        final ImageView sun = findViewById(R.id.sun);
        final ImageView cloud = findViewById(R.id.cloud);
        final ImageView house = findViewById(R.id.house);
        final ImageView grass1 = findViewById(R.id.grass1);
        final ImageView grass2 = findViewById(R.id.grass2);
        checkSun.setChecked(true);
        checkCloud.setChecked(true);
        checkHouse.setChecked(true);
        checkGrass.setChecked(true);

        checkSun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    sun.setVisibility(View.VISIBLE);
                else
                    sun.setVisibility(View.INVISIBLE);
            }
        });

        checkCloud.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    cloud.setVisibility(View.VISIBLE);
                else
                    cloud.setVisibility(View.INVISIBLE);
            }
        });

        checkHouse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    house.setVisibility(View.VISIBLE);
                else
                    house.setVisibility(View.INVISIBLE);
            }
        });

        checkGrass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    grass1.setVisibility(View.VISIBLE);
                    grass2.setVisibility(View.VISIBLE);
                }

                else{
                    grass1.setVisibility(View.INVISIBLE);
                    grass2.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
}
