package com.example.bai1tuan6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.EnumMap;

public class MainActivity extends AppCompatActivity {
    ArrayList<Employee> arrEmp = new ArrayList<Employee>();
    EmployeeAdapter myadapter = null;
    ListView lvEmployee = null;
    Button btnNhap;
    ImageView btnRemove;
    EditText editMa, editTen;
    RadioGroup genderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNhap = findViewById(R.id.btnNhap);
        btnRemove = findViewById(R.id.btnDelete);
        editMa = findViewById(R.id.txtMaNV);
        editTen = findViewById(R.id.txtTenNV);
        genderGroup = findViewById(R.id.grBtn_Gioitinh);
        myadapter = new EmployeeAdapter(this, R.layout.list_nhanvien_row, arrEmp);
        lvEmployee = (ListView)findViewById(R.id.lvEmployee);
        lvEmployee.setAdapter(myadapter);
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyNhap();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyXoa();
            }
        });

    }

    public void xulyNhap() {
        String ma = editMa.getText() + "";
        String ten = editTen.getText() + "";
        boolean gioitinh = false;//Nữ =false
        if (genderGroup.getCheckedRadioButtonId() == R.id.btnNam)
            gioitinh = true;
        //Tạo một employee
        Employee emp = new Employee();
        emp.setId(ma);
        emp.setName(ten);
        emp.setGender(gioitinh);
        //Đưa vào danh sách
        arrEmp.add(emp);
        //Sau khi update thì xóa trắng dữ liệu và cho editma focus
        editMa.setText("");
        editTen.setText("");
        editMa.requestFocus();

        //gọi hàm cập nhật giao diện
        myadapter.notifyDataSetChanged();
    }

    public void xulyXoa() {
        if (arrEmp.size() > 0) {
            for (int i = lvEmployee.getChildCount() - 1; i >= 0; i--) {
                //lấy ra dòng thứ i trong ListView
                //Dòng thứ i sẽ có 3 phần tử: ImageView, TextView, Checkbox
                View v = lvEmployee.getChildAt(i);
                //Ta chỉ lấy CheckBox ra kiểm tra
                CheckBox chk = (CheckBox) v.findViewById(R.id.checkToRemove);
                //Nếu nó Checked thì xóa ra khỏi arrEmployee
                if (chk.isChecked()) {
                    //xóa phần tử thứ i ra khỏi danh sách
                    arrEmp.remove(i);
                }
            }
        }
        //Sau khi xóa xong thì gọi update giao diện
        myadapter.notifyDataSetChanged();
    }
}

