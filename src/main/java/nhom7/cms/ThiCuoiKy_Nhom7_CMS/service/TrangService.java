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

    // Lấy tất cả trang
    public List<Trang> findAll() {
        return trangRepository.findAll();
    }

    // Lấy trang theo ID
    public Optional<Trang> findById(String loaiDoiTuong) {
        return trangRepository.findById(loaiDoiTuong);
    }

    // Tạo hoặc cập nhật trang
    public Trang save(Trang trang) {
        return trangRepository.save(trang);
    }

    // Xóa trang theo ID
    public void deleteById(String loaiDoiTuong) {
        trangRepository.deleteById(loaiDoiTuong);
    }
    public Trang findByDuongDan(String duongDan) {
        return trangRepository.findByDuongDan(duongDan);
    }

}