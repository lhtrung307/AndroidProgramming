package com.example.testsqlite;

public class Students {
    private int _studentid;
    private String _studentname;

    public Students(){}

    public Students(int _studentid, String _studentname) {
        this._studentid = _studentid;
        this._studentname = _studentname;
    }

    public int get_studentid() {
        return _studentid;
    }

    public void set_studentid(int _studentid) {
        this._studentid = _studentid;
    }

    public String get_studentname() {
        return _studentname;
    }

    public void set_studentname(String _studentname) {
        this._studentname = _studentname;
    }
}
