package com.example.bai1tuan2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtHoten ;
    EditText txtCMND;
    String result="";
    String txtBang="";
    EditText txtBosung ;
    Button btn;
    CheckBox cb1,cb2,cb3;
    String sothic= "";
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        txtHoten =  findViewById(R.id.txtHoten);
        txtCMND = findViewById(R.id.txtCMND);
        RadioGroup radioGr = (RadioGroup) findViewById(R.id.radioGroup);
        Id = radioGr.getCheckedRadioButtonId();
        btn  =findViewById(R.id.btnGui);
        txtBosung = findViewById(R.id.txtBosung);
        cb1 = findViewById(R.id.cbBao);
        cb2 =findViewById(R.id.cbSach);
        cb3= findViewById(R.id.cbCode);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtHoten.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Chưa nhập họ tên", Toast.LENGTH_SHORT).show();
                    txtHoten.requestFocus();
                    return;
                }
                else if(txtHoten.getText().length()<3){
                    Toast.makeText(MainActivity.this, "Họ tên phải từ 3 ký tự trở lên", Toast.LENGTH_SHORT).show();
                    txtHoten.requestFocus();
                    return;
                }
                else if(txtCMND.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Chưa nhập CMND", Toast.LENGTH_SHORT).show();
                    txtCMND.requestFocus();
                    return;
                }
                else if(txtCMND.getText().length()<9){
                    Toast.makeText(MainActivity.this, "CMND phải đủ 9 số", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cb1.isChecked()){
                    sothic+=cb1.getText()+"\n";
                }
                if(cb2.isChecked()){
                    sothic+=cb2.getText()+"\n";
                }
                if(cb3.isChecked()){
                    sothic+=cb3.getText()+"\n";
                }
                switch (Id) {
                    case  R.id.btnTC:  {
                        txtBang = "Trung cấp";
                        break;
                    }
                    case  R.id.btnCD:  {
                        txtBang = "Cao đẳng";
                        break;
                    }
                    case  R.id.btnDH: {
                        txtBang = "Đại học";
                        break;
                    }
                }
                result += txtHoten.getText()+"\n"+txtCMND.getText()+"\n"+txtBang+"\n"+sothic+"\n"+txtBosung.getText()+"\n";
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Thông tin cá nhân");
                alert.setMessage(result);
// Create TextView
                final TextView input = new TextView (MainActivity.this);
                alert.setView(input);

                alert.setPositiveButton("ĐÓNG", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
                sothic="";
                result ="";
                txtBang="";
            }
        });
    }
}
