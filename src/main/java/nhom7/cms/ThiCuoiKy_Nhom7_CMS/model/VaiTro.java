package nhom7.cms.ThiCuoiKy_Nhom7_CMS.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VaiTro")
public class VaiTro {
    @Id
    @Column(name = "MaVaiTro")
    private String maVaiTro;

    @Column(name = "TenVaiTro")
    private String tenVaiTro;

    @Column(name = "MoTa")
    private String moTa;

    // Constructors
    public VaiTro() {}

    // Getters and Setters
    public String getMaVaiTro() { return maVaiTro; }
    public void setMaVaiTro(String maVaiTro) { this.maVaiTro = maVaiTro; }
    public String getTenVaiTro() { return tenVaiTro; }
    public void setTenVaiTro(String tenVaiTro) { this.tenVaiTro = tenVaiTro; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}