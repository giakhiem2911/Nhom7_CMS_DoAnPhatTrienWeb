package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.BaiVietRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MenuRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.TrangRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.NguoiDungRepository;

@Service
public class ThongKeService {
	@Autowired
    private BaiVietRepository baiVietRepository;
	
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TrangRepository trangRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    
    public long demBaiViet() {
        return baiVietRepository.count();
    }
    
    public long demMenu() {
        return menuRepository.count();
    }

    public long demTrang() {
        return trangRepository.count();
    }

    public long demNguoiDungHoatDong() {
        return nguoiDungRepository.count();
    }
}
