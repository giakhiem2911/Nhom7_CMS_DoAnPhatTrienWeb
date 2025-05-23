package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.SiteInforRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteInforServiceImpl implements SiteInforService {

    @Autowired
    private SiteInforRepository siteInforRepository;

    @Override
    public List<SiteInfor> getAll() {
        return siteInforRepository.findAll();
    }

    @Override
    public Optional<SiteInfor> getById(String id) {
        return siteInforRepository.findById(id);
    }

    @Override
    public void save(SiteInfor siteInfor) {
        if (siteInfor.getNgayTao() == null) {
            siteInfor.setNgayTao(java.time.LocalDateTime.now());
        }
        siteInfor.setNgayCapNhat(java.time.LocalDateTime.now());
        siteInforRepository.save(siteInfor);
    }

    @Override
    public void deleteById(String id) {
        siteInforRepository.deleteById(id);
    }

    @Override
    public List<SiteInfor> searchByKeyword(String keyword) {
        return siteInforRepository.findByTenSiteContainingIgnoreCaseOrDiaChiContainingIgnoreCase(keyword, keyword);
    }

    // --- Thêm 2 phương thức phân trang ---

    @Override
    public Page<SiteInfor> getAll(Pageable pageable) {
        return siteInforRepository.findAll(pageable);
    }

    @Override
    public Page<SiteInfor> searchByKeyword(String keyword, Pageable pageable) {
        return siteInforRepository.findByTenSiteContainingIgnoreCaseOrDiaChiContainingIgnoreCase(keyword, keyword, pageable);
    }
}
