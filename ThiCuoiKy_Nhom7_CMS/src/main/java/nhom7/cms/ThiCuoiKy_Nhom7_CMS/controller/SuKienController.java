package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SuKien;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.SuKienService;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.NguoiDungService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sukien")
public class SuKienController {

    @Autowired
    private SuKienService suKienService;

    @Autowired
    private NguoiDungService nguoiDungService;

    // Trang danh sách sự kiện
    @GetMapping
    public String hienThiDanhSachSuKien(Model model) {
        List<SuKien> danhSachSuKien = suKienService.findAll();
        model.addAttribute("danhSachSuKien", danhSachSuKien);
        return "sukien/list_sukien";
    }

    // Hiển thị form thêm sự kiện
    @GetMapping("/them")
    public String hienThiFormThemSuKien(Model model) {
        model.addAttribute("suKien", new SuKien());
        model.addAttribute("danhSachNguoiDung", nguoiDungService.findAll());
        return "sukien/form_su_kien";
    }

    // Hiển thị form chỉnh sửa sự kiện
    @GetMapping("/sua/{maSuKien}")
    public String hienThiFormSuaSuKien(@PathVariable String maSuKien, Model model) {
        SuKien suKien = suKienService.findById(maSuKien);
        if (suKien == null) {
            return "redirect:/sukien";
        }
        model.addAttribute("suKien", suKien);
        model.addAttribute("danhSachNguoiDung", nguoiDungService.findAll());
        return "sukien/form_su_kien";
    }

    // Lưu thông tin sự kiện (thêm hoặc cập nhật)
    @PostMapping("/save")
    public String luuSuKien(@ModelAttribute("suKien") SuKien suKien) {
        suKienService.save(suKien);
        return "redirect:/sukien";
    }

    // Xóa sự kiện
    @GetMapping("/xoa/{maSuKien}")
    public String xoaSuKien(@PathVariable String maSuKien) {
        suKienService.deleteById(maSuKien);
        return "redirect:/sukien";
    }
} 