package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SuKien;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.SuKienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuKienServiceImpl implements SuKienService {

    @Autowired
    private SuKienRepository suKienRepository;

    @Override
    public List<SuKien> findAll() {
        return suKienRepository.findAll();
    }

    @Override
    public SuKien findById(String maSuKien) {
        return suKienRepository.findById(maSuKien).orElse(null);
    }

    @Override
    public void save(SuKien suKien) {
        suKienRepository.save(suKien);
    }

    @Override
    public void deleteById(String maSuKien) {
        suKienRepository.deleteById(maSuKien);
    }
}