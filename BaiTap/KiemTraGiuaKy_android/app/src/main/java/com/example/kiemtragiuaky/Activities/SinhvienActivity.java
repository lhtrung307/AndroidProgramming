package com.example.kiemtragiuaky.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiemtragiuaky.Adapters.SinhvienAdapter;
import com.example.kiemtragiuaky.Models.Khoa;
import com.example.kiemtragiuaky.Models.SinhVien;
import com.example.kiemtragiuaky.R;
import com.example.kiemtragiuaky.sqLite.DatabaseManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SinhvienActivity extends AppCompatActivity {
    int pos;
    DatabaseManager dbm;
    List<Khoa> list_khoa;
    List<SinhVien> listSinhVien;
    SinhvienAdapter sinhVienAdapter = null;
    ArrayAdapter<Khoa> khoaArrayAdapter = null;
    @BindView(R.id.cmbKhoa)
    Spinner cmbKhoa;
    @BindView(R.id.txtHovaten)
    EditText txtHovaten;
    @BindView(R.id.txtNgaysinh)
    TextView txtNgaysinh;
    @BindView(R.id.txtDiachi)
    EditText txtDiachi;
    @BindView(R.id.txtSodienthoai)
    EditText txSodienthoai;
    @BindView(R.id.iconXoa)
    TextView iconXoa;
    @BindView(R.id.lvSinhvien)
    ListView lvSinhvien;
    @BindView(R.id.btnThemSV)
    Button btnThemSV;
    @BindView(R.id.btnSuaSV)
    Button btnSuaSV;
    @BindView(R.id.btnXoaSV)
    Button btnXoaSV;
    @BindView(R.id.btnNam)
    RadioButton btnNam;
    @BindView(R.id.btnNu)
    RadioButton btnNu;
    @BindView(R.id.iconCalendar)
    ImageView iconCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien);
        ButterKnife.bind(this);
        Init();
        InitAction();
    }

    private void Init() {
        dbm = new DatabaseManager(this);
        list_khoa = dbm.getListKhoa();
        if (list_khoa.size() > 0) {
            khoaArrayAdapter = new ArrayAdapter<Khoa>(this,
                    android.R.layout.simple_spinner_item,
                    list_khoa);
            khoaArrayAdapter.setDropDownViewResource
                    (android.R.layout.simple_spinner_dropdown_item);
            cmbKhoa.setAdapter(khoaArrayAdapter);
        }

        lvSinhvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                txtHovaten.setText(listSinhVien.get(position).getHoVaTen());
                txSodienthoai.setText(listSinhVien.get(position).getDienThoai());
                Log.e("",listSinhVien.get(position).getDienThoai()+"");
                txtDiachi.setText(listSinhVien.get(position).getDiachi());
                txtNgaysinh.setText(listSinhVien.get(position).getNgaySinh());
                if (listSinhVien.get(position).getPhai() == 0)
                    btnNam.setChecked(true);
                else
                    btnNu.setChecked(true);
            }
        });

        lvSinhvien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listSinhVien.get(position).setChecked(!listSinhVien.get(position).isChecked());
                sinhVienAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void InitAction() {
        cmbKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            dbm = new DatabaseManager(SinhvienActivity.this);
            listSinhVien = dbm.getListSinhVien(list_khoa.get(position).getMakhoa());
            if (listSinhVien.size() > 0) {
                sinhVienAdapter = new SinhvienAdapter(listSinhVien, SinhvienActivity.this);
                lvSinhvien.setAdapter(sinhVienAdapter);
                sinhVienAdapter.notifyDataSetChanged();
            }
            txtHovaten.setText("");
            txSodienthoai.setText("");
            txtDiachi.setText("");
            txtNgaysinh.setText("");
            btnNam.setChecked(true);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });
    }

    @OnClick({R.id.iconXoa, R.id.btnThemSV, R.id.btnSuaSV, R.id.btnXoaSV, R.id.iconCalendar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iconXoa:
                if(listSinhVien.size()>0){
                    List<String> listRemove = new ArrayList<>();
                    for(int i = listSinhVien.size()-1;i>=0;i--){
                        if(listSinhVien.get(i).isChecked()){
                            listRemove.add(String.valueOf(listSinhVien.get(i).getMaSV()));
                            listSinhVien.remove(i);
                        }
                    }
                    String whereClause = "MaSV IN ("+ new String(new char[listRemove.size()-1]).replace("\0", "?,") + "?)";
                    boolean issucess = dbm.delete("SinhVien",whereClause,listRemove.toArray(new String[]{}));
                    if(issucess){
                        Toast.makeText(this, "Xóa thành công ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Xóa thất bại ", Toast.LENGTH_SHORT).show();
                    }
                    sinhVienAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.btnThemSV:
                if (!CheckNull().equals(""))
                    Toast.makeText(this, CheckNull(), Toast.LENGTH_SHORT).show();
                else {
                    SinhVien sv = new SinhVien((listSinhVien.get(listSinhVien.size() - 1).getMaSV()) + 1, txtHovaten.getText().toString(), btnNam.isChecked() ? 0 : 1, txtNgaysinh.getText().toString(), txtDiachi.getText().toString(), txSodienthoai.getText().toString(), listSinhVien.get(listSinhVien.size() - 1).getMakhoa(), false);
                    listSinhVien.add(sv);
                    sinhVienAdapter.notifyDataSetChanged();
                    boolean isSucess = dbm.insert("SinhVien", new String[]{"HoVaTen", "Phai", "NgaySinh", "DiaChi", "DienThoai", "MaKhoa"}, new String[]{txtHovaten.getText().toString(),
                            btnNam.isChecked() ? 0 + "" : 1 + "", txtNgaysinh.getText().toString(), txtDiachi.getText().toString(),
                            txSodienthoai.getText().toString(), listSinhVien.get(listSinhVien.size() - 1).getMakhoa() + ""});
                    if (isSucess)
                        Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnSuaSV:
                if (!CheckNull().equals(""))
                    Toast.makeText(this, CheckNull(), Toast.LENGTH_SHORT).show();
                else {
                    String[] data = new String[]{txtHovaten.getText().toString(), btnNam.isChecked() ? 0 + "" : 1 + "", txtNgaysinh.getText().toString(),
                            txtDiachi.getText().toString(), txSodienthoai.getText().toString(), listSinhVien.get(listSinhVien.size() - 1).getMakhoa() + ""};
                    listSinhVien.get(pos).setHoVaTen(txtHovaten.getText().toString());
                    listSinhVien.get(pos).setPhai(btnNam.isChecked() ? 0 : 1);
                    listSinhVien.get(pos).setDiachi(txtDiachi.getText().toString());
                    listSinhVien.get(pos).setDienThoai(txSodienthoai.getText().toString());
                    sinhVienAdapter.notifyDataSetChanged();
                    boolean isSucess = dbm.update("SinhVien", new String[]{"HoVaTen", "Phai","NgaySinh","DiaChi","DienThoai"}, data, "MaSV = ?", new String[]{listSinhVien.get(pos).getMaSV()+""});
                    if (isSucess)
                        Toast.makeText(this, "Đã cập nhật thành công", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnXoaSV:
                for(int i =0;i<listSinhVien.size();i++){
                    listSinhVien.get(i).setChecked(true);
                }
                sinhVienAdapter.notifyDataSetChanged();
                break;
            case R.id.iconCalendar:
                ChonNgay();
                break;
        }
    }

    private void ChonNgay() {
        final Calendar cal = Calendar.getInstance();
        int ngay = cal.get(Calendar.DATE);
        int thang = cal.get(Calendar.MONTH);
        int nam = cal.get(Calendar.YEAR);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(SinhvienActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txtNgaysinh.setText(simpleDateFormat.format(cal.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }

    private String CheckNull() {
        return txtHovaten.getText().toString().equals("") ? "Họ tên không được để trống" : txtNgaysinh.getText().toString().equals("") ? "Ngày sinh không được để trống" : "";
    }
}
