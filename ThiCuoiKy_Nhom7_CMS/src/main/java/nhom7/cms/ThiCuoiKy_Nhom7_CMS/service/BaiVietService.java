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

    public List<BaiViet> getAllBaiViet() {
        return baiVietRepository.findAll();
    }

    public Optional<BaiViet> getBaiVietById(Long id) {
        return baiVietRepository.findById(id);
    }

    public BaiViet saveBaiViet(BaiViet baiViet) {
        return baiVietRepository.save(baiViet);
    }

    public void deleteBaiViet(Long id) {
        baiVietRepository.deleteById(id);
    }
}
