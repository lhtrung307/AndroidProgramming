package com.example.quanlithucpham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fruit> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new FruitAdapter(image_details, this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Fruit fruit = (Fruit) o;
                Toast.makeText(MainActivity.this, "Selected :" + " " + fruit.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Fruit> getListData() {
        List<Fruit> list = new ArrayList<Fruit>();
        Fruit banana = new Fruit("banana", "banana", "It's banana");
        Fruit durian = new Fruit("durian", "durian", "It's durian");
        Fruit mango = new Fruit("mango", "mango", "It's mango");


        list.add(banana);
        list.add(durian);
        list.add(mango);

        return list;
    }
}
