package com.thick124.lop124LTTD03.nhom11;


public class SinhVien {
    private String name;
    private String masv;

    // Constructor mặc định cần thiết cho Firebase
    public SinhVien() {
    }

    // Constructor đầy đủ
    public SinhVien(String name, String masv) {
        this.name = name;
        this.masv = masv;
    }

    // Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }
}

