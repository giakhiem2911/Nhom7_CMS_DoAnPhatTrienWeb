package nhom7.cms.ThiCuoiKy_Nhom7_CMS.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SiteInfor")
public class SiteInfor {

    @Id
    @Column(name = "MaSiteInfor", length = 256)
    private String maSiteInfor;

    @Column(name = "TenSite", length = 256)
    private String tenSite;

    @Column(name = "Logo", length = 256)
    private String logo;

    @Column(name = "Email", length = 256)
    private String email;

    @Column(name = "SoDienThoai", length = 256)
    private String soDienThoai;

    @Column(name = "Facebook", length = 256)
    private String facebook;

    @Column(name = "DiaChi", length = 256)
    private String diaChi;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
    private LocalDateTime ngayCapNhat;

    // Constructors
    public SiteInfor() {}

    public SiteInfor(String maSiteInfor, String tenSite, String logo, String email, String soDienThoai,
                     String facebook, String diaChi, LocalDateTime ngayTao, LocalDateTime ngayCapNhat) {
        this.maSiteInfor = maSiteInfor;
        this.tenSite = tenSite;
        this.logo = logo;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.facebook = facebook;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }

    // Getters and Setters
    public String getMaSiteInfor() { return maSiteInfor; }
    public void setMaSiteInfor(String maSiteInfor) { this.maSiteInfor = maSiteInfor; }

    public String getTenSite() { return tenSite; }
    public void setTenSite(String tenSite) { this.tenSite = tenSite; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getFacebook() { return facebook; }
    public void setFacebook(String facebook) { this.facebook = facebook; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public LocalDateTime getNgayTao() { return ngayTao; }
    public void setNgayTao(LocalDateTime ngayTao) { this.ngayTao = ngayTao; }

    public LocalDateTime getNgayCapNhat() { return ngayCapNhat; }
    public void setNgayCapNhat(LocalDateTime ngayCapNhat) { this.ngayCapNhat = ngayCapNhat; }
}
