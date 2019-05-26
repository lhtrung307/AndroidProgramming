package com.example.kiemtragiuaky.sqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.kiemtragiuaky.Models.Diem;
import com.example.kiemtragiuaky.Models.Khoa;
import com.example.kiemtragiuaky.Models.Mon;
import com.example.kiemtragiuaky.Models.SinhVien;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_PATH = Environment.getDataDirectory()
            + "/data/com.example.kiemtragiuaky/database";
    private static final String DB_NAME = "studentsdb.db";
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;
    // Table SQLite
    private static String TABLE_KHOA = "KHOA";
    private static String TABLE_DIEM = "Diem";
    private static String TABLE_MON = "Mon";
    private static String TABLE_SinhVien = "SinhVien";
    // Query
    private static String GET_ALL_KHOA = "SELECT * FROM " + TABLE_KHOA;
    private static String GET_ALL_MON = "SELECT * FROM " + TABLE_MON;
    private static String GET_ALL_SinhVien_BY_MaKhoa = "SELECT * FROM " + TABLE_SinhVien+" where MaKhoa = ";

    public DatabaseManager(Context mContext) {
        this.mContext = mContext;
        copyDataBase();
    }

    private void copyDataBase() {
        new File(DB_PATH).mkdir();
        File file = new File(DB_PATH + "/" + DB_NAME);
        try {
            if (file.exists()) {
                // Toast.makeText(mContext, "Copy exist", Toast.LENGTH_SHORT).show();
                return;
            }
            InputStream inputStream = mContext.getAssets().open(DB_NAME);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int len;
            byte buff[] = new byte[1024];
            while ((len = inputStream.read(buff)) > 0) {
                fileOutputStream.write(buff, 0, len);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDataBase() {
        if ((mSQLiteDatabase == null) || !mSQLiteDatabase.isOpen()) {
            mSQLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDataBase() {
        if ((mSQLiteDatabase != null) || mSQLiteDatabase.isOpen()) {
            mSQLiteDatabase.close();
        }
    }

    public List<Khoa> getListKhoa() {
        List<Khoa> listKhoa = new ArrayList<>();
        openDataBase();
        Cursor cursor = mSQLiteDatabase.rawQuery(GET_ALL_KHOA, null);
        if (cursor == null) {
            return null;
        }
        int indexMaKhoa = cursor.getColumnIndex("MaKhoa");
        int indexTenKhoa = cursor.getColumnIndex("TenKhoa");
        cursor.moveToFirst();
        int maKhoa;
        String tenKhoa;
        while (!cursor.isAfterLast()) {
            maKhoa = Integer.parseInt(cursor.getString(indexMaKhoa));
            tenKhoa = cursor.getString(indexTenKhoa);
            listKhoa.add(new Khoa(maKhoa, tenKhoa, false));
            cursor.moveToNext();
        }
        cursor.close();
        closeDataBase();
        return listKhoa;
    }

    public List<Mon> getListMon() {
        List<Mon> listMon = new ArrayList<>();
        openDataBase();
        Cursor cursor = mSQLiteDatabase.rawQuery(GET_ALL_MON, null);
        if (cursor == null) {
            return null;
        }
        int indexMaMon = cursor.getColumnIndex("MaMob");
        int indexTenMon = cursor.getColumnIndex("TenMon");
        int indexSoTiet = cursor.getColumnIndex("SoTiet");
        cursor.moveToFirst();
        int maMon,sotiet;
        String tenMon;
        while (!cursor.isAfterLast()) {
            maMon = Integer.parseInt(cursor.getString(indexMaMon));
            tenMon = cursor.getString(indexTenMon);
            sotiet = Integer.parseInt(cursor.getString(indexSoTiet));
            listMon.add(new Mon(maMon, tenMon, sotiet,false));
            cursor.moveToNext();
        }
        cursor.close();
        closeDataBase();
        return listMon;
    }

    public List<SinhVien> getListSinhVien(int makhoa) {
        List<SinhVien> listMon = new ArrayList<>();
        openDataBase();
        Cursor cursor = mSQLiteDatabase.rawQuery(GET_ALL_SinhVien_BY_MaKhoa+" "+makhoa+"", null);
        if (cursor == null) {
            return null;
        }
        int indexMaSv = cursor.getColumnIndex("MaSV");
        int indexHoten = cursor.getColumnIndex("HoVaTen");
        int indexPhai = cursor.getColumnIndex("Phai");
        int indexNgaysinh = cursor.getColumnIndex("NgaySinh");
        int indexDiachi = cursor.getColumnIndex("DiaChi");
        int indexDienThoai = cursor.getColumnIndex("DienThoai");
        cursor.moveToFirst();
        int maSV,phai;
        String hoTen, ngaySinh, diaChi, soDienThoai ;
        while (!cursor.isAfterLast()) {
            maSV = Integer.parseInt(cursor.getString(indexMaSv));
            hoTen = cursor.getString(indexHoten);
            phai = Integer.parseInt(cursor.getString(indexPhai));
            ngaySinh = cursor.getString(indexNgaysinh);
            diaChi = cursor.getString(indexDiachi);
            soDienThoai = cursor.getString(indexDienThoai);
            listMon.add(new SinhVien(maSV,hoTen,phai,ngaySinh,diaChi,soDienThoai,makhoa,false));
            cursor.moveToNext();
        }
        cursor.close();
        closeDataBase();
        return listMon;
    }

    public Diem getMarkByKey(int maSV, int maMH) {
        Diem diem;
        openDataBase();
        Cursor cursor = mSQLiteDatabase.rawQuery("Select Diem from Diem where MaSV = "+maSV+" AND MaMon = "+maMH, null);
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        float DIEM;
        DIEM = Float.parseFloat(cursor.getString(0));
        diem = new Diem(maSV,maMH,DIEM);
        cursor.close();
        closeDataBase();
        return diem;
    }
    public boolean insert(String tableName, String[] colums, String[] dataColums) {
        openDataBase();
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < colums.length; ++i) {
            contentValues.put(colums[i], dataColums[i]);
        }
        long result = mSQLiteDatabase.insert(tableName, null, contentValues);
        closeDataBase();
        return result > -1;// -1 error, > -1 success
    }

    public boolean update(String tableName, String[] colums, String[] dataColums, String where, String[] whereArgs) {
        openDataBase();
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < colums.length; ++i) {
            contentValues.put(colums[i], dataColums[i]);
        }
        long result = mSQLiteDatabase.update(tableName, contentValues, where, whereArgs);
        closeDataBase();
        return result > 0;// -1 error, > -1 success
    }

    public boolean delete(String tableName, String where, String[] whereArgs) {
        openDataBase();
        long result = mSQLiteDatabase.delete(tableName, where, whereArgs);
        closeDataBase();
        return result > 0;// -1 error, > -1 success
    }
}

