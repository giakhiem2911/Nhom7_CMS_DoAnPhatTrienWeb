package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SiteInforService {

    List<SiteInfor> getAll();

    Optional<SiteInfor> getById(String id);

    void save(SiteInfor siteInfor);

    void deleteById(String id);

    List<SiteInfor> searchByKeyword(String keyword);

    // Thêm 2 phương thức hỗ trợ phân trang
    Page<SiteInfor> getAll(Pageable pageable);

    Page<SiteInfor> searchByKeyword(String keyword, Pageable pageable);
}
