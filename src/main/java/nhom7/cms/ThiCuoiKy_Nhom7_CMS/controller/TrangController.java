package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.BaiViet;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SiteInfor;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.SuKien;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.ThongBao;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Trang;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.TrangRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.SiteInforService;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.SuKienService;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.ThongBaoService;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.TrangService;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.BaiVietRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MenuRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.NguoiDungRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.SiteInforRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        String maSiteInfor = "khoa-cong-nghe-thong-tin";
        SiteInfor siteInfor = siteInforRepository.findById(maSiteInfor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy site"));
        model.addAttribute("siteInfor", siteInfor);
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
    
    @Autowired
    private BaiVietRepository baiVietRepository;
    private String stripHtml(String html) {
        if (html == null) return "";
        return Jsoup.parse(html).text();
    }
//    @GetMapping("/trang-chu")
//    public String trangChu(Model model) {
//        // Lấy thông tin site
//        model.addAttribute("siteInfor", siteInforRepository.findFirstByOrderByMaSiteInforAsc());
//
//        // Lấy trang chủ (nếu bạn có dữ liệu trang chủ trong bảng Trang)
//        Trang trangChu = trangService.findByDuongDan("trang-chu");
//        model.addAttribute("trang", trangChu);
//
//        // Lấy danh sách bài viết thuộc danh mục "Tin tức"
//        List<BaiViet> danhSachTinTuc = baiVietRepository.findByDanhMucTen("Tin tức");
//        for (BaiViet baiViet : danhSachTinTuc) {
//            baiViet.setNoiDung(stripHtml(baiViet.getNoiDung()));
//        }
//        model.addAttribute("danhSachTinTuc", danhSachTinTuc);
//
//        return "trang/detail";
//    }
    // Thêm danh sách SiteInfor vào model cho mọi request trong controller này
    
    @ModelAttribute("danhSachSiteInfor")
    public List<SiteInfor> danhSachSiteInfor() {
        return siteInforRepository.findAll();
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
    
    @GetMapping("/{maSiteInfor}/{duongDan}")
    public String hienThiTrangTheoSite(
            @PathVariable String maSiteInfor,
            @PathVariable String duongDan,
            Model model) {

        SiteInfor siteInfor = siteInforRepository.findById(maSiteInfor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy site"));

        Trang trang = trangService.findByDuongDan(duongDan);
        // hiển thị danh sách tin tức ở trang chủ
        if (trang == null || !"PUBLISH".equalsIgnoreCase(trang.getTrangThai())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy trang");
        }
        if ("trang-chu".equalsIgnoreCase(trang.getDuongDan())) {
            List<BaiViet> danhSachTinTuc = baiVietRepository.findByDanhMucTen("Tin tức");
            for (BaiViet bv : danhSachTinTuc) {
                bv.setNoiDung(stripHtml(bv.getNoiDung()));
            }
            model.addAttribute("danhSachTinTuc", danhSachTinTuc);
        }

        // Lấy danh sách bài viết liên quan theo tên chuyên mục = tên trang
        List<BaiViet> danhSachBaiVietLienQuan = baiVietRepository.findByDanhMucTen(trang.getTieuDe());
        for (BaiViet bv : danhSachBaiVietLienQuan) {
            bv.setNoiDung(stripHtml(bv.getNoiDung()));
        }

        List<Trang> danhSachTrang = trangService.findAll();
        List<ThongBao> danhSachThongBao = thongBaoService.findAll();
        for (ThongBao tb : danhSachThongBao) {
            tb.setNoiDung(stripHtml(tb.getNoiDung()));
        }

        model.addAttribute("siteInfor", siteInfor);
        model.addAttribute("trang", trang);
        model.addAttribute("danhSachTrang", danhSachTrang);
        model.addAttribute("danhSachThongBao", danhSachThongBao);
        model.addAttribute("danhSachBaiViet", danhSachBaiVietLienQuan);

        return "trang/detail";
    }

    @Autowired
    private SuKienService suKienService;

    @GetMapping("{maSiteInfor}/su-kien-hoi-thao")
    public String hienThiSuKienHoiThao(Model model, @PathVariable String maSiteInfor) {
        List<SuKien> danhSachSuKien = suKienService.findAll();
        for (SuKien suKien : danhSachSuKien) {
        	suKien.setMoTa(stripHtml(suKien.getMoTa()));
        }
        model.addAttribute("danhSachSuKien", danhSachSuKien);
        
        List<ThongBao> danhSachThongBao = thongBaoService.findAll();
        danhSachThongBao.forEach(tb -> System.out.println("NoiDung: " + tb.getNoiDung()));
        for (ThongBao tb : danhSachThongBao) {
            tb.setNoiDung(stripHtml(tb.getNoiDung()));
        }
        
        model.addAttribute("danhSachThongBao", danhSachThongBao);

        SiteInfor siteInfor = siteInforRepository.findById(maSiteInfor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy site"));// tuỳ logic bạn triển khai
        
        List<Trang> danhSachTrang = trangService.findAll();
        model.addAttribute("danhSachTrang", danhSachTrang);
        model.addAttribute("siteInfor", siteInfor);

        return "trang/su_kien_hoi_thao"; // tạo file này
    }
    
    @Autowired
    private ThongBaoService thongBaoService;
    
    @GetMapping("{maSiteInfor}/thong-bao")
    public String hienThiThongBao(Model model, @PathVariable String maSiteInfor) {
        List<ThongBao> danhSachThongBao = thongBaoService.findAll();
        danhSachThongBao.forEach(tb -> System.out.println("NoiDung: " + tb.getNoiDung()));
        for (ThongBao tb : danhSachThongBao) {
            tb.setNoiDung(stripHtml(tb.getNoiDung()));
        }
        model.addAttribute("danhSachThongBao", danhSachThongBao);

        SiteInfor siteInfor = siteInforRepository.findById(maSiteInfor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy site"));
        
        List<Trang> danhSachTrang = trangService.findAll();
        model.addAttribute("danhSachTrang", danhSachTrang);
        model.addAttribute("siteInfor", siteInfor);

        return "trang/thong_bao"; 
    }
    
    @GetMapping("/{maSiteInfor}/su-kien-hoi-thao/{maSuKien}")
    public String chiTietSuKien(@PathVariable("maSiteInfor") String maSiteInfor,
                                @PathVariable("maSuKien") String maSuKien,
                                Model model) {
        List<ThongBao> danhSachThongBao = thongBaoService.findAll();
        danhSachThongBao.forEach(tb -> System.out.println("NoiDung: " + tb.getNoiDung()));
        for (ThongBao tb : danhSachThongBao) {
            tb.setNoiDung(stripHtml(tb.getNoiDung()));
        }
        model.addAttribute("danhSachThongBao", danhSachThongBao);
        SuKien suKien = suKienService.findById(maSuKien);
        SiteInfor siteInfor = siteInforRepository.findById(maSiteInfor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy site"));
        List<Trang> danhSachTrang = trangService.findAll();
        model.addAttribute("danhSachTrang", danhSachTrang);
        model.addAttribute("suKien", suKien);
        model.addAttribute("siteInfor", siteInfor);

        return "sukien/chi-tiet-su-kien";
    }
    @GetMapping("/{maSiteInfor}/thong-bao/{maThongBao}")
    public String xemChiTietThongBao(@PathVariable("maSiteInfor") String maSiteInfor,
                                     @PathVariable("maThongBao") String maThongBao,
                                     Model model) {
        List<ThongBao> danhSachThongBao = thongBaoService.findAll();
        danhSachThongBao.forEach(tb -> System.out.println("NoiDung: " + tb.getNoiDung()));
        for (ThongBao tb : danhSachThongBao) {
            tb.setNoiDung(stripHtml(tb.getNoiDung()));
        }
        ThongBao thongBao = thongBaoService.findById(maThongBao)
        	    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy thông báo"));
        	model.addAttribute("thongBao", thongBao);
        SiteInfor siteInfor = siteInforRepository.findById(maSiteInfor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy site"));
        
        List<Trang> danhSachTrang = trangService.findAll();
        model.addAttribute("danhSachTrang", danhSachTrang);
        model.addAttribute("danhSachThongBao", danhSachThongBao);
        model.addAttribute("siteInfor", siteInfor);
        return "thongbao/chi_tiet_thong_bao";
    }

}