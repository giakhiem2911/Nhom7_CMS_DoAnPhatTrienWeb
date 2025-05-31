package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.BaiViet;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.DanhMuc;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Trang;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.BaiVietRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.DanhMucRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.NguoiDungRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.SiteInforRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.TrangService;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
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

    @Autowired
    private TrangService trangService;
    
    @GetMapping
    public String danhSachBaiViet(Model model) {
        List<BaiViet> danhSach = baiVietRepository.findAll();
        model.addAttribute("danhSachBaiViet", danhSach);
        return "bai_viet/list_bai_viet";
    }
    @ModelAttribute("danhSachSiteInfor")
    public List<SiteInfor> danhSachSiteInfor() {
        return siteInforRepository.findAll();
    }
    @GetMapping("/{maSiteInfor}/{id}")
    public String chiTietBaiViet(@PathVariable String id, Model model, @PathVariable String maSiteInfor) {
    	SiteInfor siteInfor = siteInforRepository.findById(maSiteInfor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy site"));
        Optional<BaiViet> optionalBaiViet = baiVietRepository.findById(id);
        if (optionalBaiViet.isPresent()) {
            model.addAttribute("baiViet", optionalBaiViet.get());
        } else {
            return "error/404";
        }
        List<Trang> danhSachTrang = trangService.findAll();
        model.addAttribute("danhSachTrang", danhSachTrang);
        model.addAttribute("siteInfor", siteInfor);
        return "bai_viet/chitiet";
    }

    @GetMapping("/add")
    public String formThemBaiViet(Model model) {
    	BaiViet baiViet = new BaiViet();
        model.addAttribute("baiViet", baiViet);
        baiViet.setNgayXuatBan(LocalDateTime.now());
        baiViet.setNgayCapNhat(LocalDateTime.now());
        
        model.addAttribute("danhSachDanhMuc", danhMucRepository.findAll());
        model.addAttribute("danhSachNguoiDung", nguoiDungRepository.findAll());
        return "bai_viet/form_bai_viet";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("anhDaiDien");
    }

    @PostMapping("/luu")
    public String luuBaiViet(@ModelAttribute BaiViet baiViet,
                             @RequestParam("maNguoiDung") String maNguoiDung,
                             @RequestParam("maDanhMuc") String maDanhMuc,
                             @RequestParam("anhDaiDien") MultipartFile file) {

        NguoiDung nguoiDung = nguoiDungRepository.findById(maNguoiDung).orElse(null);
        DanhMuc danhMuc = danhMucRepository.findById(maDanhMuc).orElse(null);

        baiViet.setNguoiDung(nguoiDung);
        baiViet.setDanhMuc(danhMuc);

        // Xử lý ảnh đại diện (lưu ngoài resources)
        if (!file.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/uploads";

                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                Path filePath = Paths.get(uploadDir, fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                baiViet.setAnhDaiDien("/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        LocalDateTime now = LocalDateTime.now();
        if (baiViet.getMaBaiViet() == null || baiVietRepository.findById(baiViet.getMaBaiViet()).isEmpty()) {
            // Bài viết mới
            baiViet.setNgayXuatBan(now);
        }

        // Cập nhật thời gian cập nhật
        baiViet.setNgayCapNhat(now);
        
        baiVietRepository.save(baiViet);
        return "redirect:/baiviet";
    }

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

    @GetMapping("/xoa/{id}")
    public String xoaBaiViet(@PathVariable("id") String id) {
        baiVietRepository.deleteById(id);
        return "redirect:/baiviet";
    }

}
