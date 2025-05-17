package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.VaiTro;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaiTroService {

    @Autowired
    private VaiTroRepository vaiTroRepository;

    public List<VaiTro> getAllVaiTro() {
        return vaiTroRepository.findAll();
    }

    public Optional<VaiTro> getVaiTroById(Long id) {
        return vaiTroRepository.findById(id);
    }

    public VaiTro saveVaiTro(VaiTro vaiTro) {
        return vaiTroRepository.save(vaiTro);
    }

    public void deleteVaiTro(Long id) {
        vaiTroRepository.deleteById(id);
    }
}
