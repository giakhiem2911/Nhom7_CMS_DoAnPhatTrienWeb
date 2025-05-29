package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Trang;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.SiteInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/siteinfor")
public class SiteInforController {

    @Autowired
    private SiteInforService siteInforService;

    @GetMapping
    public String hienThiDanhSach(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "sortBy", defaultValue = "maSiteInfor") String sortBy,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sortBy));
        Page<SiteInfor> pageSiteInfor;

        if (keyword != null && !keyword.trim().isEmpty()) {
            pageSiteInfor = siteInforService.searchByKeyword(keyword.trim(), pageable);
            model.addAttribute("keyword", keyword.trim());
        } else {
            pageSiteInfor = siteInforService.getAll(pageable);
            model.addAttribute("keyword", "");
        }

        model.addAttribute("siteInfors", pageSiteInfor.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageSiteInfor.getTotalPages());
        model.addAttribute("sortBy", sortBy);

        return "siteinfor/list_siteinfor";
    }

    @GetMapping("/create")
    public String hienThiFormTaoMoi(Model model) {
        model.addAttribute("siteInfor", new SiteInfor());
        return "siteinfor/form_siteinfor";
    }

    @GetMapping("/sua/{maSiteInfor}")
    public String hienThiFormChinhSua(@PathVariable("maSiteInfor") String maSiteInfor, Model model) {
        SiteInfor siteInfor = siteInforService.getById(maSiteInfor).orElse(null);
        if (siteInfor == null) {
            return "redirect:/siteinfor";
        }
        model.addAttribute("siteInfor", siteInfor);
        return "siteinfor/form_siteinfor";
    }

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

    @GetMapping("/delete/{id}")
    public String xoaSiteInfor(@PathVariable("id") String id) {
        siteInforService.deleteById(id);
        return "redirect:/siteinfor";
    }
    
    

}
