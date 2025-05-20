package nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
    List<NguoiDung> findAll();
}