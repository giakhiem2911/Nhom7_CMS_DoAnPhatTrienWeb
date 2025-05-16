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

    public List<ThongBao> getAllThongBao() {
        return thongBaoRepository.findAll();
    }

    public Optional<ThongBao> getThongBaoById(Long id) {
        return thongBaoRepository.findById(id);
    }

    public ThongBao saveThongBao(ThongBao thongBao) {
        return thongBaoRepository.save(thongBao);
    }

    public void deleteThongBao(Long id) {
        thongBaoRepository.deleteById(id);
    }
}
