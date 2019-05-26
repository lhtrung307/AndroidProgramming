package com.example.kiemtragiuaky.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiemtragiuaky.Adapters.KhoaAdapter;
import com.example.kiemtragiuaky.Models.Khoa;
import com.example.kiemtragiuaky.R;
import com.example.kiemtragiuaky.sqLite.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KhoaActivity extends AppCompatActivity {
    int Pos;
    String makhoa;
    List<Khoa> listKhoa;
    KhoaAdapter khoaAdapter;
    DatabaseManager dbm;
    ListView lvKhoa;
    @BindView(R.id.btnThem)
    Button btnThem;
    @BindView(R.id.btnSua)
    Button btnSua;
    @BindView(R.id.btnXoa)
    Button btnXoa;
    @BindView(R.id.txtTenKhoa)
    EditText txtTenkhoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa);
        ButterKnife.bind(this);
        lvKhoa = findViewById(R.id.lvListKhoa);
        Init();
        InitAction();
    }

    private void InitAction() {
        lvKhoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pos = position;
                makhoa = listKhoa.get(position).getMakhoa()+"";
                txtTenkhoa.setText(listKhoa.get(position).getTenkhoa()+"");
                khoaAdapter.notifyDataSetChanged();
            }
        });
        lvKhoa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listKhoa.get(position).setChecked(!listKhoa.get(position).isChecked());
                return false;
            }
        });
    }

    private void Init() {
        dbm = new DatabaseManager(this);
        listKhoa = dbm.getListKhoa();
        if (listKhoa.size() > 0) {
            khoaAdapter = new KhoaAdapter(listKhoa, this);
            lvKhoa.setAdapter(khoaAdapter);
        }
    }

    private boolean isExists(){
        for(int i =0;i< listKhoa.size();i++){
             if(listKhoa.get(i).getTenkhoa().equals(txtTenkhoa.getText().toString())){
                Toast.makeText(this,"Tên khoa đã tồn tại",Toast.LENGTH_LONG).show();
                return true;
             }
        }
        return false;
    }

    @OnClick({ R.id.btnThem, R.id.btnSua, R.id.btnXoa,R.id.iconXoa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnThem:
                if(txtTenkhoa.getText().toString().equals("")){
                    Toast.makeText(this,"Tên khoa không được để trống",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!isExists()){
                    Khoa khoa = new Khoa((listKhoa.get(listKhoa.size()-1).getMakhoa())+1,txtTenkhoa.getText().toString(),false);
                    listKhoa.add(khoa);
                    khoaAdapter.notifyDataSetChanged();
                    boolean isSucess = dbm.insert("KHOA",new String[]{"TenKhoa"},new String[]{txtTenkhoa.getText().toString()});
                    if(isSucess)
                        Toast.makeText(this,"Đã thêm thành công",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnSua:
                if(txtTenkhoa.getText().toString().equals("")){
                    Toast.makeText(this,"Tên khoa không được để trống",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!isExists()){
                    String[] data = new String[]{txtTenkhoa.getText().toString()};
                    listKhoa.get(Pos).setTenkhoa(txtTenkhoa.getText().toString());
                    khoaAdapter.notifyDataSetChanged();
                    boolean isSucess = dbm.update("KHOA",new String[]{"TenKhoa"},data,"MaKhoa = ?",new String[]{makhoa});
                    if(isSucess)
                        Toast.makeText(this,"Đã cập nhật thành công",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnXoa:
                for(int i =0;i<listKhoa.size();i++){
                    listKhoa.get(i).setChecked(true);
                }
                khoaAdapter.notifyDataSetChanged();
                break;
            case R.id.iconXoa:
                if(listKhoa.size()>0){
                    List<String> listRemove = new ArrayList<>();
                    for(int i = listKhoa.size()-1;i>=0;i--){
                        if(listKhoa.get(i).isChecked()){
                            listRemove.add(String.valueOf(listKhoa.get(i).getMakhoa()));
                            listKhoa.remove(i);
                        }
                    }
                    String whereClause = "MaKhoa IN ("+ new String(new char[listRemove.size()-1]).replace("\0", "?,") + "?)";
                    boolean issucess = dbm.delete("KHOA",whereClause,listRemove.toArray(new String[]{}));
                    if(issucess){
                        Toast.makeText(this, "Xóa thành công ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Xóa thất bại ", Toast.LENGTH_SHORT).show();
                    }
                    khoaAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
