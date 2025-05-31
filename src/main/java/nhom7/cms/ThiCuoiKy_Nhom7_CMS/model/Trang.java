package nhom7.cms.ThiCuoiKy_Nhom7_CMS.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Trang")
public class Trang {

    @Id
    @Column(name = "MaTrang")
    private String maTrang;

    @ManyToOne
    @JoinColumn(name = "MaMenu")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung")
    private NguoiDung nguoiDung;
    
    @Column(name = "TieuDe")
    private String tieuDe;

    @Column(name = "DuongDan")
    private String duongDan;

    @Column(name = "NoiDung", columnDefinition = "NVARCHAR(MAX)")
    private String noiDung;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;
    
    // Getters and setters
    public String getMaTrang() { return maTrang; }
    public void setMaTrang(String maTrang) { this.maTrang = maTrang; }
    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }
    public NguoiDung getNguoiDung() {
		return nguoiDung;
	}
    public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}
    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }
    public String getDuongDan() { return duongDan; }
    public void setDuongDan(String duongDan) { this.duongDan = duongDan; }
    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}