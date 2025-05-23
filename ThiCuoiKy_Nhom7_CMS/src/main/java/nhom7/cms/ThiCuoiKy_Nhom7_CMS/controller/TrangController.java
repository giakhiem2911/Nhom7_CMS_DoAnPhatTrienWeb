package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Trang;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.TrangRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.TrangService;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MenuRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.NguoiDungRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.SiteInforRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/trang")
public class TrangController {

    @Autowired
    private TrangRepository trangRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    
    @Autowired
    private TrangService trangService;

    @Autowired
    private SiteInforRepository siteInforRepository;

    @GetMapping
    public String listTrang(Model model) {
        List<Trang> trangs = trangRepository.findAll();
        model.addAttribute("dsTrang", trangs);
        return "trang/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("trang", new Trang());
        model.addAttribute("danhSachMenu", menuRepository.findAll());
        model.addAttribute("danhSachNguoiDung", nguoiDungRepository.findAll());
        return "trang/form";
    }

    @PostMapping("/save")
    public String saveTrang(@ModelAttribute("trang") Trang trang) {
        if (trang.getNgayTao() == null) {
            trang.setNgayTao(LocalDateTime.now());
        }
        // Gán người dùng nếu có như mình đã nói ở trên
        if (trang.getNguoiDung() != null && trang.getNguoiDung().getMaNguoiDung() != null) {
            NguoiDung nguoiDung = nguoiDungRepository.findById(trang.getNguoiDung().getMaNguoiDung()).orElse(null);
            trang.setNguoiDung(nguoiDung);
        }
        trangRepository.save(trang);
        return "redirect:/trang";
    }

    @GetMapping("/edit/{maTrang}")
    public String showEditForm(@PathVariable String maTrang, Model model) {
        Trang trang = trangRepository.findById(maTrang)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy trang: " + maTrang));
        model.addAttribute("trang", trang);
        model.addAttribute("danhSachMenu", menuRepository.findAll());
        model.addAttribute("danhSachNguoiDung", nguoiDungRepository.findAll());
        return "trang/form";
    }

    @GetMapping("/delete/{maTrang}")
    public String deleteTrang(@PathVariable String maTrang) {
        trangRepository.deleteById(maTrang);
        return "redirect:/trang";
    }
    @GetMapping("/{duongDan}")
    public String hienThiTrang(@PathVariable("duongDan") String duongDan, Model model) {
        Trang trang = trangService.findByDuongDan(duongDan);
        System.out.println("Trang lấy được: " + trang);
        if (trang == null || !trang.getTrangThai().equals("PUBLISH")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy trang");
        }
        model.addAttribute("siteInfor", siteInforRepository.findFirstByOrderByMaSiteInforAsc());
        model.addAttribute("trang", trang);
        return "trang/detail";
    }

}