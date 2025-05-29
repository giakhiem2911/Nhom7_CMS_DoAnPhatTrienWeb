package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.DanhMuc;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanhMucService {

    @Autowired
    private DanhMucRepository danhMucRepository;

    // Lấy tất cả danh mục
    public List<DanhMuc> findAll() {
        return danhMucRepository.findAll();
    }

    // Lấy danh mục theo ID
    public Optional<DanhMuc> findById(String maDanhMuc) {
        return danhMucRepository.findById(maDanhMuc);
    }

    // Tạo hoặc cập nhật danh mục
    public DanhMuc save(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }

    // Xóa danh mục theo ID
    public void deleteById(String maDanhMuc) {
        danhMucRepository.deleteById(maDanhMuc);
    }
}