package nhom7.cms.ThiCuoiKy_Nhom7_CMS.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BaiViet")
public class BaiViet {
    @Id
    @Column(name = "MaBaiViet")
    private String maBaiViet;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "MaDanhMuc")
    private DanhMuc danhMuc;

    @Column(name = "TieuDe")
    private String tieuDe;

    @Column(name = "DuongDan")
    private String duongDan;

    @Column(name = "NoiDung")
    private String noiDung;

    @Column(name = "AnhDaiDien")
    private String anhDaiDien;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "FileDinhKem")
    private String fileDinhKem;

    @Column(name = "NgayXuatBan")
    private LocalDateTime ngayXuatBan;

    @Column(name = "NgayCapNhat")
    private LocalDateTime ngayCapNhat;

    // Constructors
    public BaiViet() {}

    // Getters and Setters
    public String getMaBaiViet() { return maBaiViet; }
    public void setMaBaiViet(String maBaiViet) { this.maBaiViet = maBaiViet; }
    public NguoiDung getNguoiDung() { return nguoiDung; }
    public void setNguoiDung(NguoiDung nguoiDung) { this.nguoiDung = nguoiDung; }
    public DanhMuc getDanhMuc() { return danhMuc; }
    public void setDanhMuc(DanhMuc danhMuc) { this.danhMuc = danhMuc; }
    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }
    public String getDuongDan() { return duongDan; }
    public void setDuongDan(String duongDan) { this.duongDan = duongDan; }
    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }
    public String getAnhDaiDien() { return anhDaiDien; }
    public void setAnhDaiDien(String anhDaiDien) { this.anhDaiDien = anhDaiDien; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public String getFileDinhKem() { return fileDinhKem; }
    public void setFileDinhKem(String fileDinhKem) { this.fileDinhKem = fileDinhKem; }
    public LocalDateTime getNgayXuatBan() { return ngayXuatBan; }
    public void setNgayXuatBan(LocalDateTime ngayXuatBan) { this.ngayXuatBan = ngayXuatBan; }
    public LocalDateTime getNgayCapNhat() { return ngayCapNhat; }
    public void setNgayCapNhat(LocalDateTime ngayCapNhat) { this.ngayCapNhat = ngayCapNhat; }
}