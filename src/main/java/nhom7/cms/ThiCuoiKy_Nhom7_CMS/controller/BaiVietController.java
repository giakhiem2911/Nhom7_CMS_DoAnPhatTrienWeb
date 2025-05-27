package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.BaiViet;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.DanhMuc;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.BaiVietRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.DanhMucRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.NguoiDungRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.SiteInforRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.BaiVietService;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.SiteInforService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/baiviet")
public class BaiVietController {

    @Autowired
    private BaiVietRepository baiVietRepository;

    @Autowired
    private SiteInforRepository siteInforRepository;
    
    @Autowired
    private DanhMucRepository danhMucRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    // Hiển thị danh sách bài viết
    @GetMapping
    public String danhSachBaiViet(Model model) {
        List<BaiViet> danhSach = baiVietRepository.findAll();
        model.addAttribute("danhSachBaiViet", danhSach);
        return "bai_viet/list_bai_viet";
    }
    
    @GetMapping("/{id}")
    public String chiTietBaiViet(@PathVariable String id, Model model) {
        Optional<BaiViet> optionalBaiViet = baiVietRepository.findById(id);
        if (optionalBaiViet.isPresent()) {
            model.addAttribute("baiViet", optionalBaiViet.get());
        } else {
            return "error/404";
        }
        model.addAttribute("siteInfor", siteInforRepository.findFirstByOrderByMaSiteInforAsc());
        return "bai_viet/chitiet";
    }

    
    // Hiển thị form thêm bài viết
    @GetMapping("/add")
    public String formThemBaiViet(Model model) {
        model.addAttribute("baiViet", new BaiViet());
        model.addAttribute("danhSachDanhMuc", danhMucRepository.findAll());
        model.addAttribute("danhSachNguoiDung", nguoiDungRepository.findAll());
        return "bai_viet/form_bai_viet";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("anhDaiDien");
    }
    // Lưu bài viết mới hoặc cập nhật
    @PostMapping("/luu")
    public String luuBaiViet(@ModelAttribute BaiViet baiViet,
                             @RequestParam("maNguoiDung") String maNguoiDung,
                             @RequestParam("maDanhMuc") String maDanhMuc,
                             @RequestParam("anhDaiDien") MultipartFile file) {

        NguoiDung nguoiDung = nguoiDungRepository.findById(maNguoiDung).orElse(null);
        DanhMuc danhMuc = danhMucRepository.findById(maDanhMuc).orElse(null);

        baiViet.setNguoiDung(nguoiDung);
        baiViet.setDanhMuc(danhMuc);

        // Xử lý ảnh đại diện
        if (!file.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String relativePath = "src/main/resources/static/images/";
                File uploadDir = new File(relativePath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                Path filePath = Paths.get(relativePath + fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Lưu đường dẫn để hiển thị (đường dẫn tĩnh)
                baiViet.setAnhDaiDien("/images/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (baiViet.getMaBaiViet() == null || baiViet.getMaBaiViet().isEmpty()) {
            baiViet.setMaBaiViet(UUID.randomUUID().toString());
            baiViet.setNgayXuatBan(LocalDateTime.now());
        }

        baiViet.setNgayCapNhat(LocalDateTime.now());
        baiVietRepository.save(baiViet);
        return "redirect:/baiviet";
    }


    // Hiển thị form sửa bài viết
    @GetMapping("/sua/{id}")
    public String formSuaBaiViet(@PathVariable("id") String id, Model model) {
        BaiViet baiViet = baiVietRepository.findById(id).orElse(null);
        if (baiViet == null) {
            return "redirect:/baiviet";
        }

        model.addAttribute("baiViet", baiViet);
        model.addAttribute("danhSachDanhMuc", danhMucRepository.findAll());
        model.addAttribute("danhSachNguoiDung", nguoiDungRepository.findAll());
        return "bai_viet/form_bai_viet";
    }

    // Xóa bài viết
    @GetMapping("/xoa/{id}")
    public String xoaBaiViet(@PathVariable("id") String id) {
        baiVietRepository.deleteById(id);
        return "redirect:/baiviet";
    }
}
