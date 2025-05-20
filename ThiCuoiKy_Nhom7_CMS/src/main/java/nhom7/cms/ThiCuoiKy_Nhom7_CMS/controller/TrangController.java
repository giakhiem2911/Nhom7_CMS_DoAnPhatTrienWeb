package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Trang;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.TrangRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MenuRepository;

import java.util.List;

@Controller
@RequestMapping("/trang")
public class TrangController {

    @Autowired
    private TrangRepository trangRepository;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    public String listTrang(Model model) {
        List<Trang> trangs = trangRepository.findAll();
        model.addAttribute("trangs", trangs);
        return "trang/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("trang", new Trang());
        model.addAttribute("danhSachMenu", menuRepository.findAll());
        return "trang/form";
    }

    @PostMapping("/save")
    public String saveTrang(@ModelAttribute("trang") Trang trang) {
        trangRepository.save(trang);
        return "redirect:/trang";
    }

    @GetMapping("/edit/{loaiDoiTuong}")
    public String showEditForm(@PathVariable String loaiDoiTuong, Model model) {
        Trang trang = trangRepository.findById(loaiDoiTuong)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy trang: " + loaiDoiTuong));
        model.addAttribute("trang", trang);
        model.addAttribute("danhSachMenu", menuRepository.findAll());
        return "trang/form";
    }

    @GetMapping("/delete/{loaiDoiTuong}")
    public String deleteTrang(@PathVariable String loaiDoiTuong) {
        trangRepository.deleteById(loaiDoiTuong);
        return "redirect:/trang";
    }
}