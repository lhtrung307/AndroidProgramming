package com.example.testsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView datalist;
    EditText studentid;
    EditText studentname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datalist = (TextView) findViewById(R.id.txtData);
        studentid = (EditText) findViewById(R.id.studentid);
        studentname = (EditText) findViewById(R.id.studentname);
    }
    public void addStudent(View view) {
        //khởi tạo đối tượng xử lý dữ liệu
        DataHandler dbHandler = new DataHandler(this);
        //nhận id
        int id = Integer.parseInt(studentid.getText().toString());
        //nhận name
        String name = studentname.getText().toString();
        //gán id và name đến đối tượng Student
        Students student = new Students(id, name);
        //thêm đối tượng Student đến bảng dữ liệu
        dbHandler.addDataHandler(student);
        //xóa sạch các PlainText
        studentid.setText("");
        studentname.setText("");
    }
    public void loadStudents(View view) {
        //khởi tạo đối tượng xử lý dữ liệu
        DataHandler dbHandler = new DataHandler(this);
        //hiển thị dữ liệu
        datalist.setText(dbHandler.loadDataHandler());
        //xóa sạch các PlainText
        studentid.setText("");
        studentname.setText("");
    }
}
