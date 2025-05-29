package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.ThongBao;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.ThongBaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThongBaoService {

    @Autowired
    private ThongBaoRepository thongBaoRepository;

    public List<ThongBao> findAll() {
        return thongBaoRepository.findAll();
    }

    public Optional<ThongBao> findById(String maThongBao) {
        return thongBaoRepository.findById(maThongBao);
    }

    public ThongBao save(ThongBao thongBao) {
        return thongBaoRepository.save(thongBao);
    }

    public void deleteById(String maThongBao) {
        thongBaoRepository.deleteById(maThongBao);
    }
}
