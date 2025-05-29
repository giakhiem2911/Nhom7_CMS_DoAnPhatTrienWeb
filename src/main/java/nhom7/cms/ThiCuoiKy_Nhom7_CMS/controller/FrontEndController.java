package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Menu;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MenuRepository;

@Controller
public class FrontEndController {
	@Autowired
    private MenuRepository menuRepository;

    @GetMapping("/Frontend/trangchu")
    public String trangChu(Model model) {
        List<Menu> allMenus = menuRepository.findAll();

        model.addAttribute("allMenus", allMenus);
        return "Frontend/trangchu";
    }
    
    @GetMapping("/Frontend/MN002")
    public String viewMenu(Model model) {
    	List<Menu> allMenus = menuRepository.findAll();

        model.addAttribute("allMenus", allMenus);
        return "Frontend/baiviet"; // Không cần dấu / đầu tiên
    }
    
    
}
