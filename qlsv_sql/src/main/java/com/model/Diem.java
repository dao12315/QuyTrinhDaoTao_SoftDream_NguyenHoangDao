package com.model;


import java.math.BigDecimal;

public class Diem {
    private String maSinhVien;
    private String tenSinhVien;
    private String maMonHoc;
    private String tenMonHoc;
    private BigDecimal diemQuaTrinh;
    private BigDecimal diemThanhPhan;
    private BigDecimal diemTongKet;
    private String ketQua;

    public Diem(String maSinhVien, String tenSinhVien,
                String maMonHoc, String tenMonHoc,
                BigDecimal diemQuaTrinh, BigDecimal diemThanhPhan,
                BigDecimal diemTongKet, String ketQua) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.diemQuaTrinh = diemQuaTrinh;
        this.diemThanhPhan = diemThanhPhan;
        this.diemTongKet = diemTongKet;
        this.ketQua = ketQua;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public BigDecimal getDiemQuaTrinh() {
        return diemQuaTrinh;
    }

    public BigDecimal getDiemThanhPhan() {
        return diemThanhPhan;
    }

    public BigDecimal getDiemTongKet() {
        return diemTongKet;
    }

    public String getKetQua() {
        return ketQua;
    }
}