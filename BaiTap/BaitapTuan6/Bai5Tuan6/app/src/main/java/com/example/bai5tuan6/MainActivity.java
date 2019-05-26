package com.example.bai5tuan6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnSave;
    EditText txtName, txtPhone;
    ListView lvListContact;
    ArrayList<Contact> arrContact = new ArrayList<Contact>();
    ArrayAdapter<Contact> arrayListview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidgetsControl();
        addEventsForFormWidgets();
    }

    private void getWidgetsControl() {
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        btnSave = findViewById(R.id.btnSave);
        lvListContact = findViewById(R.id.lvContact);
        //
        arrayListview = new ArrayAdapter<Contact>(this,
                android.R.layout.simple_list_item_1,
                arrContact);
        lvListContact.setAdapter(arrayListview);
    }

    private boolean isDuplicate(String name, String phone) {
        if (arrContact.size() > 0) {
            for (Contact c1 : arrContact) {
                if (c1.getName().trim().equalsIgnoreCase(name) && c1.getPhone_number().equals(phone))
                    return true;
            }
            return false;
        }
        return true;
    }

    private void Add_Contact() {
        Contact c = new Contact(txtName.getText().toString(), txtPhone.getText().toString());
//        c.setName(txtName.getText().toString());
//        c.setPhone_number(txtPhone.getText().toString());
        arrContact.add(c);
        txtName.setText("");
        txtPhone.setText("");
        txtName.requestFocus();
    }

    private void addEventsForFormWidgets() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Name is not allowed empty!", Toast.LENGTH_LONG).show();
                    return;
                } else if (txtPhone.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Phone is not allowed empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                Add_Contact();
                arrayListview.notifyDataSetChanged();
            }
        });
    }
}
