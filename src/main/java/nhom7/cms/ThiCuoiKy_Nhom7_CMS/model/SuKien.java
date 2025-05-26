package nhom7.cms.ThiCuoiKy_Nhom7_CMS.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SuKien")
public class SuKien {
    @Id
    @Column(name = "MaSuKien")
    private String maSuKien;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung")
    private NguoiDung nguoiDung;

    @Column(name = "TieuDe")
    private String tieuDe;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "DuongDan")
    private String duongDan;

    @Column(name = "ThoiGianBatDau")
    private LocalDateTime thoiGianBatDau;

    @Column(name = "ThoiGianKetThuc")
    private LocalDateTime thoiGianKetThuc;

    @Column(name = "DiaDiem")
    private String diaDiem;

    // Constructors
    public SuKien() {}

    // Getters and Setters
    public String getMaSuKien() { return maSuKien; }
    public void setMaSuKien(String maSuKien) { this.maSuKien = maSuKien; }
    public NguoiDung getNguoiDung() { return nguoiDung; }
    public void setNguoiDung(NguoiDung nguoiDung) { this.nguoiDung = nguoiDung; }
    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public String getDuongDan() { return duongDan; }
    public void setDuongDan(String duongDan) { this.duongDan = duongDan; }
    public LocalDateTime getThoiGianBatDau() { return thoiGianBatDau; }
    public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) { this.thoiGianBatDau = thoiGianBatDau; }
    public LocalDateTime getThoiGianKetThuc() { return thoiGianKetThuc; }
    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) { this.thoiGianKetThuc = thoiGianKetThuc; }
    public String getDiaDiem() { return diaDiem; }
    public void setDiaDiem(String diaDiem) { this.diaDiem = diaDiem; }
}