package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Menu;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MenuRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.NguoiDungRepository;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @GetMapping()
    public String listMenu(Model model) {
        model.addAttribute("menus", menuRepository.findAll());
        return "menu/list";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("nguoiDungs", nguoiDungRepository.findAll());
        return "menu/form";
    }

    @PostMapping("/save")
    public String saveMenu(@ModelAttribute("menu") Menu menu) {
        menuRepository.save(menu);
        return "redirect:/menu";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Menu menu = menuRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy menu: " + id));
        model.addAttribute("menu", menu);
        model.addAttribute("nguoiDungs", nguoiDungRepository.findAll());
        return "menu/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable("id") String id) {
        menuRepository.deleteById(id);
        return "redirect:/menu";
    }
}


