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

    // Lấy tất cả thông báo
    public List<ThongBao> findAll() {
        return thongBaoRepository.findAll();
    }

    // Lấy thông báo theo ID
    public Optional<ThongBao> findById(String maThongBao) {
        return thongBaoRepository.findById(maThongBao);
    }

    // Tạo hoặc cập nhật thông báo
    public ThongBao save(ThongBao thongBao) {
        return thongBaoRepository.save(thongBao);
    }

    // Xóa thông báo theo ID
    public void deleteById(String maThongBao) {
        thongBaoRepository.deleteById(maThongBao);
    }
}