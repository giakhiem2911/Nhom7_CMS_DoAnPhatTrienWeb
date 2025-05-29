package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.DanhMuc;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/danhmuc")
public class DanhMucController {

    @Autowired
    private DanhMucRepository danhMucRepository;

    // Hiển thị danh sách danh mục với tìm kiếm, phân trang, sắp xếp
    @GetMapping
    public String listDanhMuc(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "maDanhMuc") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sortBy));
        Page<DanhMuc> danhMucPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            danhMucPage = danhMucRepository.findByTenContainingIgnoreCase(keyword, pageable);
        } else {
            danhMucPage = danhMucRepository.findAll(pageable);
        }

        model.addAttribute("danhMucs", danhMucPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", danhMucPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        return "danh_muc/list_danh_muc";
    }

    // Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("danhMuc", new DanhMuc());
        return "danh_muc/form_danh_muc";
    }

    // Lưu danh mục sau khi thêm/sửa
    @PostMapping("/save")
    public String saveDanhMuc(@ModelAttribute("danhMuc") DanhMuc danhMuc) {
        danhMucRepository.save(danhMuc);
        return "redirect:/danhmuc";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{maDanhMuc}")
    public String showEditForm(@PathVariable("maDanhMuc") String maDanhMuc, Model model) {
        DanhMuc danhMuc = danhMucRepository.findById(maDanhMuc)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục: " + maDanhMuc));
        model.addAttribute("danhMuc", danhMuc);
        return "danh_muc/form_danh_muc";
    }

    // Xóa danh mục
    @GetMapping("/delete/{maDanhMuc}")
    public String deleteDanhMuc(@PathVariable("maDanhMuc") String maDanhMuc) {
        danhMucRepository.deleteById(maDanhMuc);
        return "redirect:/danhmuc";
    }
}
