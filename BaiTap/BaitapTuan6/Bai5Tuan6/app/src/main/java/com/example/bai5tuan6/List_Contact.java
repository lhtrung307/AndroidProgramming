package com.example.bai5tuan6;

import android.widget.ListView;

import java.util.ArrayList;

public class List_Contact {
    private ArrayList<Contact> List_cont = null;

    public List_Contact(){}

    public List_Contact(ArrayList<Contact> list_cont) {
        List_cont = list_cont;
    }

    public boolean isDuplicate(Contact c) {
        for (Contact c1 : List_cont ){
            if (c1.getName().trim().equalsIgnoreCase(c.getName().trim())&&c1.getPhone_number().equals(c.getPhone_number()))
                return true;
        }
        return false;
    }

    public boolean addPhone_Number(Contact p){
        boolean isDup = isDuplicate(p);
        if (!isDup) {
            return List_cont.add(p);
        }
        return !isDup;
    }
}
