package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.ThongBao;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.ThongBaoService;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/thongbao")
public class ThongBaoController {

    @Autowired
    private ThongBaoService thongBaoService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping
    public String danhSachThongBao(Model model) {
        model.addAttribute("danhSachThongBao", thongBaoService.findAll());
        return "thongbao/list_thongbao";
 
    }

    @GetMapping("/tao")
    public String hienThiFormTao(Model model) {
        model.addAttribute("thongBao", new ThongBao());
        model.addAttribute("danhSachNguoiDung", nguoiDungService.findAll()); 
        return "thongbao/form_thong_bao"; 
    }

    @PostMapping("/luu")
    public String luuThongBao(@ModelAttribute("thongBao") ThongBao thongBao) {
        thongBaoService.save(thongBao);
        return "redirect:/thongbao";
    }

    @GetMapping("/sua/{id}")
    public String suaThongBao(@PathVariable("id") String maThongBao, Model model) {
        Optional<ThongBao> thongBaoOptional = thongBaoService.findById(maThongBao);
        if (thongBaoOptional.isPresent()) {
            model.addAttribute("thongBao", thongBaoOptional.get());
            model.addAttribute("danhSachNguoiDung", nguoiDungService.findAll());
            return "thongbao/form_thong_bao"; 
        } else {
            return "redirect:/thongbao";
        }
    }

    @GetMapping("/xoa/{id}")
    public String xoaThongBao(@PathVariable("id") String maThongBao) {
        thongBaoService.deleteById(maThongBao);
        return "redirect:/thongbao";
    }
}
