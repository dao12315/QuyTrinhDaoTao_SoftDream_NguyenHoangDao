package com.model;

import java.time.LocalDate;

public class SinhVien {
    private String maSinhVien;
    private String tenSinhVien;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String lop;
    private String khoa;

    public SinhVien(String maSinhVien, String tenSinhVien, String gioiTinh,
                    LocalDate ngaySinh, String lop, String khoa) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.lop = lop;
        this.khoa = khoa;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public String getLop() {
        return lop;
    }

    public String getKhoa() {
        return khoa;
    }
}