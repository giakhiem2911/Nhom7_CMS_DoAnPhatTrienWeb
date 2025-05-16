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

    public List<SuKien> getAllSuKien() {
        return suKienRepository.findAll();
    }

    public Optional<SuKien> getSuKienById(Long id) {
        return suKienRepository.findById(id);
    }

    public SuKien saveSuKien(SuKien suKien) {
        return suKienRepository.save(suKien);
    }

    public void deleteSuKien(Long id) {
        suKienRepository.deleteById(id);
    }
}
