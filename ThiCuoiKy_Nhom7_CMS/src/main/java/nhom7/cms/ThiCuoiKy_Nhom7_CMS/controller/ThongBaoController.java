package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.ThongBao;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.ThongBaoService;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.NguoiDungService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/thongbao")
public class ThongBaoController {

    @Autowired
    private ThongBaoService thongBaoService;

    @Autowired
    private NguoiDungService nguoiDungService;

    // Danh sách thông báo
    @GetMapping
    public String danhSachThongBao(Model model) {
        model.addAttribute("danhSachThongBao", thongBaoService.findAll());
        return "thongbao/list_thongbao";
    }

    // Form tạo mới
    @GetMapping("/tao")
    public String hienThiFormTao(Model model) {
        model.addAttribute("thongBao", new ThongBao());
        model.addAttribute("danhSachNguoiDung", nguoiDungService.findAll());
        return "thongbao/form_thong_bao";
    }

    // Form sửa
    @GetMapping("/sua/{maThongBao}")
    public String hienThiFormSua(@PathVariable("maThongBao") String maThongBao, Model model) {
        Optional<ThongBao> thongBaoOpt = thongBaoService.findById(maThongBao);
        if (thongBaoOpt.isPresent()) {
            model.addAttribute("thongBao", thongBaoOpt.get());
            model.addAttribute("danhSachNguoiDung", nguoiDungService.findAll());
            return "thongbao/form_thong_bao";
        } else {
            return "redirect:/thongbao";
        }
    }

    // Lưu (tạo/sửa) với validate
    @PostMapping("/save")
    public String luuThongBao(@Validated @ModelAttribute("thongBao") ThongBao thongBao,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("danhSachNguoiDung", nguoiDungService.findAll());
            return "thongbao/form_thong_bao";
        }

        try {
            thongBaoService.save(thongBao);
            redirectAttributes.addFlashAttribute("successMessage", "Lưu thông báo thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi lưu: " + e.getMessage());
        }

        return "redirect:/thongbao";
    }

    // Xóa thông báo
    @GetMapping("/xoa/{maThongBao}")
    public String xoaThongBao(@PathVariable("maThongBao") String maThongBao,
                              RedirectAttributes redirectAttributes) {
        try {
            thongBaoService.deleteById(maThongBao);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thông báo thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa thông báo: " + e.getMessage());
        }
        return "redirect:/thongbao";
    }
}
