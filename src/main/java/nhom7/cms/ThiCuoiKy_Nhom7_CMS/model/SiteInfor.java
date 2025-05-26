package nhom7.cms.ThiCuoiKy_Nhom7_CMS.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SiteInfor")
public class SiteInfor {

    @Id
    @Column(name = "MaSiteInfor", length = 256, nullable = false)
    private String maSiteInfor;

    @Column(name = "TenSite", length = 256, columnDefinition = "VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String tenSite;

    @Column(name = "Logo", length = 256, columnDefinition = "VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String logo;

    @Column(name = "Email", length = 256, columnDefinition = "VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String email;

    @Column(name = "SoDienThoai", length = 256, columnDefinition = "VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String soDienThoai;

    @Column(name = "Facebook", length = 256, columnDefinition = "VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String facebook;

    @Column(name = "DiaChi", length = 256, columnDefinition = "VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String diaChi;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
    private LocalDateTime ngayCapNhat;

    // Constructors
    public SiteInfor() {}

    // Getters & Setters
    public String getMaSiteInfor() {
        return maSiteInfor;
    }

    public void setMaSiteInfor(String maSiteInfor) {
        this.maSiteInfor = maSiteInfor;
    }

    public String getTenSite() {
        return tenSite;
    }

    public void setTenSite(String tenSite) {
        this.tenSite = tenSite;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public LocalDateTime getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(LocalDateTime ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
}
