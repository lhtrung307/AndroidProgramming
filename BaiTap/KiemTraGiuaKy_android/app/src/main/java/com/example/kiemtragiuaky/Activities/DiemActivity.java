package com.example.kiemtragiuaky.Activities;

import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kiemtragiuaky.Models.Diem;
import com.example.kiemtragiuaky.Models.Mon;
import com.example.kiemtragiuaky.R;
import com.example.kiemtragiuaky.sqLite.DatabaseManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiemActivity extends AppCompatActivity {
    DatabaseManager dbm;
    Diem mark;
    boolean isNullMark;
    int pos;
    List<Mon> listMon;
    ArrayAdapter<Mon> monArrayAdapter;
    @BindView(R.id.cmbMaSV)
    EditText txtMaSV;
    @BindView(R.id.cmbMaMon)
    Spinner cmbMaMon;
    @BindView(R.id.txtDiem)
    EditText txDiem;
    @BindView(R.id.iconEdit)
    ImageView iconEdit;
    @BindView(R.id.btnLoad)
    Button btnLoad;
    @BindView(R.id.btnSave)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem);
        ButterKnife.bind(this);
        Init();
    }

    private void Init() {
        dbm = new DatabaseManager(this);
        listMon = dbm.getListMon();
        if (listMon.size() > 0) {
            monArrayAdapter = new ArrayAdapter<Mon>(this,
                    android.R.layout.simple_spinner_item,
                    listMon);
            monArrayAdapter.setDropDownViewResource
                    (android.R.layout.simple_spinner_dropdown_item);
            cmbMaMon.setAdapter(monArrayAdapter);
        }

        cmbMaMon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                txDiem.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        txtMaSV.requestFocus();
    }

    @OnClick({R.id.iconEdit, R.id.btnLoad, R.id.btnSave})
    public void onViewClicked(View view) {
        boolean flag = false;
        switch (view.getId()) {
            case R.id.iconEdit:
                flag = !flag;
                if (flag != false) {
                    cmbMaMon.setEnabled(false);
                    txDiem.setEnabled(true);
                    btnLoad.setEnabled(false);
                    btnSave.setEnabled(true);
                }
                txDiem.requestFocus();
                break;
            case R.id.btnLoad:
                if (txtMaSV.getText().toString().equals("")) {
                    Toast.makeText(this, "Mã sinh viên chưa được nhập", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    mark = dbm.getMarkByKey(Integer.parseInt(txtMaSV.getText().toString()), listMon.get(pos).getMaMon());
                    txDiem.setText(mark.getDiem()+"");
                    isNullMark=false;
                    btnSave.setEnabled(true);
                    btnLoad.setEnabled(false);
                } catch (CursorIndexOutOfBoundsException ex) {
                    Toast.makeText(this,"Điểm môn này chưa được khởi tạo",Toast.LENGTH_SHORT).show();
                    isNullMark=true;
                }
                break;
            case R.id.btnSave:
                boolean isSucess;
                if (txDiem.getText().toString().equals(""))
                    Toast.makeText(this, "Điểm chưa được nhập", Toast.LENGTH_SHORT).show();
                else {
                    Log.e("Diem: ",txDiem.getText().toString());
                    String[] data = new String[]{txtMaSV.getText().toString(),listMon.get(pos).getMaMon()+"",txDiem.getText().toString()};
                    if(isNullMark){
                        isSucess = dbm.insert("Diem", new String[]{"MaSV", "MaMon","Diem"}, data);
                    }
                    else
                        isSucess = dbm.update("Diem", new String[]{"Diem"}, new String[]{txDiem.getText().toString()}, "MaSV =? AND MaMon = ?", new String[]{txtMaSV.getText().toString(),listMon.get(pos).getMaMon()+""});
                    if (isSucess)
                        Toast.makeText(this, "Đã cập nhật thành công", Toast.LENGTH_LONG).show();
                    btnSave.setEnabled(false);
                    btnLoad.setEnabled(true);
                }
                break;
        }
    }
}
