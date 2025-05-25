package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MenuRepository;

@Controller
public class HomeController {

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Bảng điều khiển - NHOM7_CMS");
        model.addAttribute("menuCount", menuRepository.count());
        model.addAttribute("pageCount", 5);
        model.addAttribute("activeUsers", 3);
        return "index";
    }
}