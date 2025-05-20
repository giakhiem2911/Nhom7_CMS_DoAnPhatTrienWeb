package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SuKien;

import java.util.List;

public interface SuKienService {
    List<SuKien> findAll();
    SuKien findById(String maSuKien);
    void save(SuKien suKien);
    void deleteById(String maSuKien);
}
