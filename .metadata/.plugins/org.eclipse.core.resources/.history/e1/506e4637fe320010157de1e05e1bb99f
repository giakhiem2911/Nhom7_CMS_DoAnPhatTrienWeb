package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.CaiDat;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.CaiDatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaiDatService {

    @Autowired
    private CaiDatRepository caiDatRepository;

    public List<CaiDat> getAllCaiDat() {
        return caiDatRepository.findAll();
    }

    public Optional<CaiDat> getCaiDatById(Long id) {
        return caiDatRepository.findById(id);
    }

    public CaiDat saveCaiDat(CaiDat caiDat) {
        return caiDatRepository.save(caiDat);
    }

    public void deleteCaiDat(Long id) {
        caiDatRepository.deleteById(id);
    }
}
