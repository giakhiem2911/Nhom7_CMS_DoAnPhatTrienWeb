package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SuKien;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.SuKienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuKienService {

    @Autowired
    private SuKienRepository suKienRepository;

    // Lấy tất cả sự kiện
    public List<SuKien> findAll() {
        return suKienRepository.findAll();
    }

    // Lấy sự kiện theo ID
    public Optional<SuKien> findById(String maSuKien) {
        return suKienRepository.findById(maSuKien);
    }

    // Tạo hoặc cập nhật sự kiện
    public SuKien save(SuKien suKien) {
        return suKienRepository.save(suKien);
    }

    // Xóa sự kiện theo ID
    public void deleteById(String maSuKien) {
        suKienRepository.deleteById(maSuKien);
    }
}