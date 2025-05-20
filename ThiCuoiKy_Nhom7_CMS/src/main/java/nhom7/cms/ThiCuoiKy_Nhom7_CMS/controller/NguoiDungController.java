package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.NguoiDungRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.VaiTroRepository;

import java.util.List;

@Controller
@RequestMapping("/nguoidung")
public class NguoiDungController {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @GetMapping
    public String listNguoiDung(Model model) {
        List<NguoiDung> nguoiDungs = nguoiDungRepository.findAll();
        model.addAttribute("nguoiDungs", nguoiDungs);
        return "nguoidung/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new NguoiDung());
        return "nguoidung/form";
    }

    @PostMapping("/save")
    public String saveNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
        return "redirect:/nguoidung";
    }

    @GetMapping("/edit/{maNguoiDung}")
    public String showEditForm(@PathVariable String maNguoiDung, Model model) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(maNguoiDung)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng: " + maNguoiDung));
        model.addAttribute("nguoiDung", nguoiDung);
        model.addAttribute("vaiTros", vaiTroRepository.findAll());
        return "nguoidung/form";
    }

    @GetMapping("/delete/{maNguoiDung}")
    public String deleteNguoiDung(@PathVariable String maNguoiDung) {
        nguoiDungRepository.deleteById(maNguoiDung);
        return "redirect:/nguoidung";
    }
}
