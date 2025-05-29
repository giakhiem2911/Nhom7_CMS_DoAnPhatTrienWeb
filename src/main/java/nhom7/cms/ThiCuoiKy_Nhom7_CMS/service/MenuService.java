package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Menu;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    // Lấy tất cả menu
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    // Lấy menu theo ID
    public Optional<Menu> findById(String maMenu) {
        return menuRepository.findById(maMenu);
    }

    // Tạo hoặc cập nhật menu
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    // Xóa menu theo ID
    public void deleteById(String maMenu) {
        menuRepository.deleteById(maMenu);
    }
}