package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.SiteInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/siteinfor")
public class SiteInforController {

    @Autowired
    private SiteInforService siteInforService;

    // 1. Hiển thị danh sách SiteInfor, có hỗ trợ tìm kiếm theo keyword
    @GetMapping
    public String hienThiDanhSach(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        List<SiteInfor> danhSachSiteInfor;
        if (keyword != null && !keyword.trim().isEmpty()) {
            danhSachSiteInfor = siteInforService.searchByKeyword(keyword.trim());
            model.addAttribute("keyword", keyword.trim());
        } else {
            danhSachSiteInfor = siteInforService.getAll();
        }

        model.addAttribute("danhSachSiteInfor", danhSachSiteInfor);
        return "siteinfor/list_siteinfor"; // Thymeleaf template
    }

    // 2. Hiển thị form tạo mới SiteInfor
    @GetMapping("/create")
    public String hienThiFormTaoMoi(Model model) {
        model.addAttribute("siteInfor", new SiteInfor());
        return "siteinfor/form_siteinfor";
    }

    // 3. Hiển thị form chỉnh sửa SiteInfor theo ID
    @GetMapping("/edit/{id}")
    public String hienThiFormChinhSua(@PathVariable String id, Model model) {
        Optional<SiteInfor> optionalSiteInfor = siteInforService.getById(id);
        if (optionalSiteInfor.isPresent()) {
            model.addAttribute("siteInfor", optionalSiteInfor.get());
            return "siteinfor/form_siteinfor";
        } else {
            return "redirect:/siteinfor";
        }
    }

    // 4. Lưu SiteInfor (tạo mới hoặc cập nhật)
    @PostMapping("/save")
    public String luuSiteInfor(@Validated @ModelAttribute("siteInfor") SiteInfor siteInfor,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "siteinfor/form_siteinfor";
        }

        siteInforService.save(siteInfor);
        return "redirect:/siteinfor";
    }

    // 5. Xóa SiteInfor theo ID
    @GetMapping("/delete/{id}")
    public String xoaSiteInfor(@PathVariable String id) {
        siteInforService.deleteById(id);
        return "redirect:/siteinfor";
    }
}
