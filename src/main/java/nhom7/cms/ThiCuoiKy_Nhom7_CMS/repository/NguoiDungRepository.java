package nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {
	Page<NguoiDung> findByEmailContainingIgnoreCaseOrTenDangNhapContainingIgnoreCase(String email, String tenDangNhap, Pageable pageable);
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
    Optional<NguoiDung> findByEmail(String email);
    Optional<NguoiDung> findByTenDangNhapOrEmail(String tenDangNhap, String email);
}