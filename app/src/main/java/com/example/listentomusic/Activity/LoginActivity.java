package com.example.listentomusic.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listentomusic.R;
import com.example.listentomusic.Service.APIService;
import com.example.listentomusic.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtuser,edtpassword;
    Button btndangnhap;
    TextView tvdangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        Xuly();

    }
    private void AnhXa(){
        edtuser=(EditText)findViewById(R.id.edittextuserlogin);
        edtpassword=(EditText)findViewById(R.id.edittextpasswordlogin);
        btndangnhap=(Button)findViewById(R.id.buttondangnhap);
        tvdangky=(TextView)findViewById(R.id.txtdangky);
    }
    private void Xuly(){
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataService = APIService.getService();
                Call<String> callback = dataService.CheckUser(edtuser.getText().toString(),edtpassword.getText().toString());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketqua =response.body();
                        if(ketqua.equals("Fail")){
                            Toast.makeText(getBaseContext(),"Tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getBaseContext(),"Bạn đã đăng nhập thành công",Toast.LENGTH_LONG).show();
                            MainActivity.username = ketqua;
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }

                });

            }
        });
        tvdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}
