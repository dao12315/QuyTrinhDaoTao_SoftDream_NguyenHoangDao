package com.model;

import java.math.BigDecimal;

public class MonHoc {
    private String maMonHoc;
    private String tenMonHoc;
    private int soTietHoc;
    private BigDecimal tyLeQuaTrinh;
    private BigDecimal tyLeThanhPhan;

    public MonHoc(String maMonHoc, String tenMonHoc, int soTietHoc,
                  BigDecimal tyLeQuaTrinh, BigDecimal tyLeThanhPhan) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTietHoc = soTietHoc;
        this.tyLeQuaTrinh = tyLeQuaTrinh;
        this.tyLeThanhPhan = tyLeThanhPhan;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public int getSoTietHoc() {
        return soTietHoc;
    }

    public BigDecimal getTyLeQuaTrinh() {
        return tyLeQuaTrinh;
    }

    public BigDecimal getTyLeThanhPhan() {
        return tyLeThanhPhan;
    }
}