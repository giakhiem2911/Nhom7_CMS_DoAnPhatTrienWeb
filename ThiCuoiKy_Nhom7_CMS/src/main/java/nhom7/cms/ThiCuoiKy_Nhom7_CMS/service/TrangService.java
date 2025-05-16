package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Trang;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.TrangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrangService {

    @Autowired
    private TrangRepository trangRepository;

    public List<Trang> getAllTrang() {
        return trangRepository.findAll();
    }

    public Optional<Trang> getTrangById(Long id) {
        return trangRepository.findById(id);
    }

    public Trang saveTrang(Trang trang) {
        return trangRepository.save(trang);
    }

    public void deleteTrang(Long id) {
        trangRepository.deleteById(id);
    }
}
