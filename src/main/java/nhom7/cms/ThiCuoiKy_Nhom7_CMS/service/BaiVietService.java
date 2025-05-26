package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.BaiViet;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.BaiVietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaiVietService {

    @Autowired
    private BaiVietRepository baiVietRepository;

    // Lấy tất cả bài viết
    public List<BaiViet> findAll() {
        return baiVietRepository.findAll();
    }

    // Lấy bài viết theo ID
    public Optional<BaiViet> findById(String maBaiViet) {
        return baiVietRepository.findById(maBaiViet);
    }

    // Tạo hoặc cập nhật bài viết
    public BaiViet save(BaiViet baiViet) {
        return baiVietRepository.save(baiViet);
    }

    // Xóa bài viết theo ID
    public void deleteById(String maBaiViet) {
        baiVietRepository.deleteById(maBaiViet);
    }
}