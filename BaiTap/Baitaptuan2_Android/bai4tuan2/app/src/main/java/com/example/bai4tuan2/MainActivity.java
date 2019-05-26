package com.example.bai4tuan2;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText format,num;
    RadioGroup gr;
    Button btn;
    TextView v;
    CheckBox c1,c2,c3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                format= findViewById(R.id.txtFormat);
                num=  findViewById(R.id.txtNum);
                check_empty();
                format_text();
            }
        });
    }
    private  void check_empty(){
        if(format.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Chưa nhập chuỗi cần format", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(num.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Chưa nhập số", Toast.LENGTH_SHORT).show();
            return;
        }
    }
//    private void check_Odd_Even(){
//        gr = findViewById(R.id.group);
//        int id = gr.getCheckedRadioButtonId();
//        switch (id){
//            case R.id.btnOdd:{
//                Toast.makeText(MainActivity.this, "bạn đã chọn odd", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            case R.id.btnEvent:{
//                Toast.makeText(MainActivity.this, "bạn đã chọn even", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            case R.id.btnBoth:{
//                Toast.makeText(MainActivity.this, "bạn đã chọn both", Toast.LENGTH_SHORT).show();
//                break;
//            }
//
//        }
//    }
    private void reset(){
        v = (TextView) findViewById(R.id.viewResult);
        v.setBackgroundResource(R.color.colorLightGrey);
        v.setTextColor(Color.parseColor("#000000"));
        v.setGravity(Gravity.LEFT);
    }
    private void format_text(){
        c1 = findViewById(R.id.cbBG);
        c2= findViewById(R.id.cbCenter);
        c3 = findViewById(R.id.cbTxtColor);
        v = (TextView) findViewById(R.id.viewResult);
        gr = findViewById(R.id.group);
        int id = gr.getCheckedRadioButtonId();
        if(c1.isChecked()){
            switch (id){
                case R.id.btnOdd:{
                    if(Integer.parseInt(num.getText().toString())%2!=0){
                        v.setBackgroundResource(R.color.colorOrange);
                    }
                    else reset();
                    break;
                }
                case R.id.btnEvent:{
                    if(Integer.parseInt(num.getText().toString())%2==0){
                        v.setBackgroundResource(R.color.colorOrange);
                    }
                    else reset();
                    break;
                }
                case R.id.btnBoth:{
                    v.setBackgroundResource(R.color.colorOrange);
                    break;
                }
            }
        }
        else{
            v.setBackgroundResource(R.color.colorLightGrey);
        }
        if(c2.isChecked()){
            switch (id){
                case R.id.btnOdd:{
                    if(Integer.parseInt(num.getText().toString())%2!=0){
                        v.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    else reset();
                    break;
                }
                case R.id.btnEvent:{
                    if(Integer.parseInt(num.getText().toString())%2==0){
                        v.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    else reset();
                    break;
                }
                case R.id.btnBoth:{
                    v.setGravity(Gravity.CENTER_HORIZONTAL);
                    break;
                }
            }
        }
        else{
            v.setGravity(Gravity.LEFT);
        }
//        if(c2.isChecked()) v.setGravity(Gravity.CENTER_HORIZONTAL);
//        if(c3.isChecked()) v.setTextColor(Color.parseColor("#FFFFFF"));
        if(c3.isChecked()){
            switch (id){
                case R.id.btnOdd:{
                    if(Integer.parseInt(num.getText().toString())%2!=0){
                        v.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                    else reset();
                    break;
                }
                case R.id.btnEvent:{
                    if(Integer.parseInt(num.getText().toString())%2==0){
                        v.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                    else reset();
                    break;
                }
                case R.id.btnBoth:{
                    v.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                }
            }
        }
        else
            v.setTextColor(Color.parseColor("#000000"));
//        check_Odd_Even();
        ((TextView) v).setText(format.getText().toString()+" "+num.getText().toString());
    }
}
