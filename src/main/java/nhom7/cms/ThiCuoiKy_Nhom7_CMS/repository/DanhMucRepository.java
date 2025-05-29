package nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.DanhMuc;

public interface DanhMucRepository extends JpaRepository<DanhMuc, String> {
	Page<DanhMuc> findByTenContainingIgnoreCase(String ten, Pageable pageable);
}
