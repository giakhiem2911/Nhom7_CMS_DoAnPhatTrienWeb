package nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuKienRepository extends JpaRepository<SuKien, String> {
}