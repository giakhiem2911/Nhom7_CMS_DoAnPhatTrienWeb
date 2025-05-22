package nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteInforRepository extends JpaRepository<SiteInfor, String> {
	  List<SiteInfor> findByTenSiteContainingIgnoreCaseOrDiaChiContainingIgnoreCase(String tenSite, String diaChi);
}
