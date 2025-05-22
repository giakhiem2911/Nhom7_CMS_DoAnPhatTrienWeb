package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping
    public String listMenu(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "maMenu") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sortBy));
        Page<Menu> menuPage;

        // Tìm kiếm nếu có từ khóa, nếu không lấy tất cả
        if (keyword != null && !keyword.trim().isEmpty()) {
            menuPage = menuRepository.findByTenContainingIgnoreCase(keyword, pageable);
        } else {
            menuPage = menuRepository.findAll(pageable);
        }

        model.addAttribute("menus", menuPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", menuPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
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

    @GetMapping("/edit/{maMenu}")
    public String showEditForm(@PathVariable("maMenu") String maMenu, Model model) {
        Menu menu = menuRepository.findById(maMenu)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy menu: " + maMenu));
        model.addAttribute("menu", menu);
        model.addAttribute("nguoiDungs", nguoiDungRepository.findAll());
        return "menu/form";
    }

    @GetMapping("/delete/{maMenu}")
    public String deleteMenu(@PathVariable("maMenu") String maMenu) {
        menuRepository.deleteById(maMenu);
        return "redirect:/menu";
    }
}