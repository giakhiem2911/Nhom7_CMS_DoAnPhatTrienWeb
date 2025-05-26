package nhom7.cms.ThiCuoiKy_Nhom7_CMS.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DanhMuc")
public class DanhMuc {
    @Id
    @Column(name = "MaDanhMuc")
    private String maDanhMuc;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "DuongDan")
    private String duongDan;

    @Column(name = "MoTa")
    private String moTa;

    // Constructors
    public DanhMuc() {}

    // Getters and Setters
    public String getMaDanhMuc() { return maDanhMuc; }
    public void setMaDanhMuc(String maDanhMuc) { this.maDanhMuc = maDanhMuc; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public String getDuongDan() { return duongDan; }
    public void setDuongDan(String duongDan) { this.duongDan = duongDan; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}