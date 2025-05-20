package nhom7.cms.ThiCuoiKy_Nhom7_CMS.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Trang")
public class Trang {

    @Id
    @Column(name = "LoaiDoiTuong")
    private String loaiDoiTuong;

    @ManyToOne
    @JoinColumn(name = "MaMenu")
    private Menu menu;

    @Column(name = "TieuDe")
    private String tieuDe;

    @Column(name = "DuongDan")
    private String duongDan;

    @Column(name = "NoiDung", columnDefinition = "NVARCHAR(MAX)")
    private String noiDung;

    @Column(name = "TrangThai")
    private String trangThai;

    // Getters and setters
    public String getLoaiDoiTuong() { return loaiDoiTuong; }
    public void setLoaiDoiTuong(String loaiDoiTuong) { this.loaiDoiTuong = loaiDoiTuong; }
    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }
    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }
    public String getDuongDan() { return duongDan; }
    public void setDuongDan(String duongDan) { this.duongDan = duongDan; }
    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}