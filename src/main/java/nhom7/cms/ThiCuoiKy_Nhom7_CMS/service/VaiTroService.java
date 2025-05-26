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

    // Lấy tất cả vai trò
    public List<VaiTro> findAll() {
        return vaiTroRepository.findAll();
    }

    // Lấy vai trò theo ID
    public Optional<VaiTro> findById(String maVaiTro) {
        return vaiTroRepository.findById(maVaiTro);
    }

    // Tạo hoặc cập nhật vai trò
    public VaiTro save(VaiTro vaiTro) {
        return vaiTroRepository.save(vaiTro);
    }

    // Xóa vai trò theo ID
    public void deleteById(String maVaiTro) {
        vaiTroRepository.deleteById(maVaiTro);
    }
}