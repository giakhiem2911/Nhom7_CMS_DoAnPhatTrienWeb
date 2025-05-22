package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;

import java.util.List;
import java.util.Optional;

public interface SiteInforService {
    List<SiteInfor> getAll();
    Optional<SiteInfor> getById(String id);
    void save(SiteInfor siteInfor);
    void deleteById(String id);
    List<SiteInfor> searchByKeyword(String keyword);
}
