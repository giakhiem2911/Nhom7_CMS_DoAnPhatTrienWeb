package nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteInforRepository extends JpaRepository<SiteInfor, String> {

    List<SiteInfor> findByTenSiteContainingIgnoreCaseOrDiaChiContainingIgnoreCase(String tenSite, String diaChi);

    Page<SiteInfor> findByTenSiteContainingIgnoreCaseOrDiaChiContainingIgnoreCase(String tenSite, String diaChi, Pageable pageable);

}
