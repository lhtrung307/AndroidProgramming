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

import com.example.kiemtragiuaky.Adapters.MonhocAdapter;
import com.example.kiemtragiuaky.Models.Mon;
import com.example.kiemtragiuaky.R;
import com.example.kiemtragiuaky.sqLite.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonhocActivity extends AppCompatActivity {
    String mamon;
    int Pos;
    List<Mon> listMon;
    DatabaseManager dbm;
    MonhocAdapter monhocAdapter;
    TextView tvisChecked;
    @BindView(R.id.lvListMon)
    ListView lvMon;
    @BindView(R.id.btnThemMH)
    Button btnThem;
    @BindView(R.id.btnSuaMH)
    Button btnSua;
    @BindView(R.id.btnXoaMH)
    Button btnXoa;
    @BindView(R.id.txtTenMon)
    EditText txtTenmon;
    @BindView(R.id.txtSoTiet)
    EditText txtSotiet;
    @BindView(R.id.iconXoa)
    TextView iconXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monhoc);
        ButterKnife.bind(this);
        Init();
        InitAction();

    }

    private void Init() {
        dbm = new DatabaseManager(this);
        listMon = dbm.getListMon();
        if (listMon.size() > 0) {
            monhocAdapter = new MonhocAdapter(listMon, this);
            lvMon.setAdapter(monhocAdapter);
        }
    }

    private void InitAction() {
        lvMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pos = position;
                mamon = listMon.get(position).getMaMon() + "";
                txtTenmon.setText(listMon.get(position).getTenMon() + "");
                txtSotiet.setText(listMon.get(position).getSoTiet() + "");
                monhocAdapter.notifyDataSetChanged();
            }
        });
        lvMon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listMon.get(position).setChecked(!listMon.get(position).isChecked());
                return false;
            }
        });
    }

    private boolean isExists() {
        for (int i = 0; i < listMon.size(); i++) {
            if (listMon.get(i).getTenMon().equals(txtTenmon.getText().toString())) {
                Toast.makeText(this, "Tên môn đã tồn tại", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    private boolean checkToUpdate() {
        for (int i = 0; i < listMon.size(); i++) {
            if (listMon.get(i).getTenMon().equals(txtTenmon.getText().toString()) && i != Pos) {
                Toast.makeText(this, "Tên môn đã tồn tại", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    @OnClick({R.id.iconXoa, R.id.btnThemMH, R.id.btnSuaMH, R.id.btnXoaMH})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iconXoa:
                if(listMon.size()>0){
                    List<String> listRemove = new ArrayList<>();
                    for(int i = listMon.size()-1;i>=0;i--){
                        if(listMon.get(i).isChecked()){
                            listRemove.add(String.valueOf(listMon.get(i).getMaMon()));
                            listMon.remove(i);
                        }
                    }
                    String whereClause = "MaMob IN ("+ new String(new char[listRemove.size()-1]).replace("\0", "?,") + "?)";
                    boolean issucess = dbm.delete("Mon",whereClause,listRemove.toArray(new String[]{}));
                    if(issucess){
                        Toast.makeText(this, "Xóa thành công ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Xóa thất bại ", Toast.LENGTH_SHORT).show();
                    }
                    monhocAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.btnThemMH:
                if (txtTenmon.getText().toString().equals("")) {
                    Toast.makeText(this, "Tên khoa không được để trống", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!isExists()) {
                    Mon mon = new Mon((listMon.get(listMon.size() - 1).getMaMon()) + 1, txtTenmon.getText().toString(), Integer.parseInt(txtSotiet.getText().toString()), false);
                    listMon.add(mon);
                    monhocAdapter.notifyDataSetChanged();
                    boolean isSucess = dbm.insert("Mon", new String[]{"TenMon", "SoTiet"}, new String[]{txtTenmon.getText().toString(), txtSotiet.getText().toString()});
                    if (isSucess)
                        Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnSuaMH:
                if (txtTenmon.getText().toString().equals("")) {
                    Toast.makeText(this, "Tên khoa không được để trống", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!checkToUpdate()) {
                    String[] data = new String[]{txtTenmon.getText().toString(), txtSotiet.getText().toString()};
                    listMon.get(Pos).setTenMon(txtTenmon.getText().toString());
                    listMon.get(Pos).setSoTiet(Integer.parseInt(txtSotiet.getText().toString()));
                    monhocAdapter.notifyDataSetChanged();
                    boolean isSucess = dbm.update("Mon", new String[]{"TenMon", "SoTiet"}, data, "MaMob = ?", new String[]{mamon});
                    if (isSucess)
                        Toast.makeText(this, "Đã cập nhật thành công", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnXoaMH:
                for(int i =0;i<listMon.size();i++){
                    listMon.get(i).setChecked(true);
                }
                monhocAdapter.notifyDataSetChanged();
                break;
        }
    }
}
