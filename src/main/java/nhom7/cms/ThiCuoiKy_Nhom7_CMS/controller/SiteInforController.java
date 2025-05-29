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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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
    public String saveSiteInfor(@Validated @ModelAttribute("siteInfor") SiteInfor siteInfor,
                               @RequestParam("logoFile") MultipartFile logoFile) {

        if (!logoFile.isEmpty()) {
            try {
                // Tên file gốc
                String fileName = logoFile.getOriginalFilename();

                // Đường dẫn lưu file: thư mục "uploads" ngay trong thư mục project
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }

                // Lưu file
                Path filePath = Paths.get(uploadDir + fileName);
                Files.write(filePath, logoFile.getBytes());

                // Lưu đường dẫn file tương đối để hiển thị trên web
                siteInfor.setLogo("/uploads/" + fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        siteInfor.setNgayCapNhat(LocalDateTime.now());
        // Lưu siteInfor vào DB
        siteInforService.save(siteInfor);

        return "redirect:/siteinfor";
    }



    @GetMapping("/delete/{id}")
    public String xoaSiteInfor(@PathVariable("id") String id) {
        siteInforService.deleteById(id);
        return "redirect:/siteinfor";
    }
    
    

}
