package nhom7.cms.ThiCuoiKy_Nhom7_CMS.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Menu")
public class Menu {

    @Id
    @Column(name = "MaMenu")
    private String maMenu;

    @Column(name = "MaNguoiDung")
    private String maNguoiDung;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "ThuTuHienThi")
    private Integer thuTuHienThi;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", insertable = false, updatable = false)
    private NguoiDung nguoiDung;

    // Constructors
    public Menu() {}

    public Menu(String maMenu, String maNguoiDung, String ten, Integer thuTuHienThi) {
        this.maMenu = maMenu;
        this.maNguoiDung = maNguoiDung;
        this.ten = ten;
        this.thuTuHienThi = thuTuHienThi;
    }

    // Getters and Setters
    public String getMaMenu() { return maMenu; }
    public void setMaMenu(String maMenu) { this.maMenu = maMenu; }
    public String getMaNguoiDung() { return maNguoiDung; }
    public void setMaNguoiDung(String maNguoiDung) { this.maNguoiDung = maNguoiDung; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public Integer getThuTuHienThi() { return thuTuHienThi; }
    public void setThuTuHienThi(Integer thuTuHienThi) { this.thuTuHienThi = thuTuHienThi; }
    public NguoiDung getNguoiDung() { return nguoiDung; }
    public void setNguoiDung(NguoiDung nguoiDung) { this.nguoiDung = nguoiDung; }
}