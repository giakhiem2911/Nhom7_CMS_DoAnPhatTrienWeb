package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Controller
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @PostMapping("/sign_in")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        Optional<NguoiDung> optNguoiDung = nguoiDungService.findByUsernameOrEmail(username);

        if (optNguoiDung.isPresent()) {
            NguoiDung nguoiDung = optNguoiDung.get();
            if (nguoiDung.getMatKhau().equals(password)) {
                session.setAttribute("currentUser", nguoiDung);

                // Kiểm tra vai trò từ entity VaiTro
                String tenVaiTro = nguoiDung.getVaiTro().getTenVaiTro(); // VD: "admin", "user"
                if ("user".equalsIgnoreCase(tenVaiTro)) {
                    return "redirect:http://localhost:8080/trang/khoa-cong-nghe-thong-tin/trang-chu";
                } else {
                    return "redirect:/"; // admin thì về trang chủ mặc định
                }

            } else {
                model.addAttribute("error", "Mật khẩu không đúng");
                return "auth/sign_in";
            }
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc email không tồn tại");
            return "auth/sign_in";
        }
    }
 // Trang đăng nhập GET - kiểm tra nếu đã đăng nhập thì chuyển hướng sang sign_out
    @GetMapping("/sign_in")
    public String showLoginForm(HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/sign_out"; // Đã đăng nhập -> chuyển sang trang sign_out
        }
        return "auth/sign_in";
    }
 // Trang sign_out hiển thị thông tin người dùng và nút đăng xuất
    @GetMapping("/sign_out")
    public String showSignOutPage(HttpSession session, Model model) {
        NguoiDung currentUser = (NguoiDung) session.getAttribute("currentUser");
        if (currentUser == null) {
            // Nếu chưa đăng nhập thì chuyển về trang đăng nhập
            return "redirect:/sign_in";
        }
        model.addAttribute("user", currentUser);
        return "auth/sign_out";  // View sign_out.html (tạo bên dưới)
    }
 // Xử lý đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa session để đăng xuất
        return "redirect:/"; // Quay về trang đăng nhập
    }
    
    
 // Phương thức mới để hiển thị danh sách người dùng
    @GetMapping("/nguoidung")
    public String hienThiDanhSachNguoiDung(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model) {

        int pageSize = 5; // Số dòng mỗi trang
        Page<NguoiDung> nguoiDungPage;

        if (keyword != null && !keyword.isEmpty()) {
            nguoiDungPage = nguoiDungService.searchNguoiDung(keyword, PageRequest.of(page, pageSize));
        } else {
            nguoiDungPage = nguoiDungService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("nguoiDungs", nguoiDungPage.getContent());
        model.addAttribute("totalPages", nguoiDungPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "nguoidung/list"; // Tên file Thymeleaf: `list.html` trong thư mục `templates/nguoidung`
    }

 // Phương thức hiển thị form thêm người dùng đã có sẵn
    @GetMapping("/nguoidung/form")
    public String showAddForm(Model model) {
        model.addAttribute("user", new NguoiDung());
        model.addAttribute("vaiTroList", nguoiDungService.getVaiTroList());
        return "nguoidung/form";
    }

    // Phương thức xử lý lưu người dùng mới hoặc cập nhật
    @PostMapping("/nguoidung/save")
    public String saveUser(@ModelAttribute("user") NguoiDung nguoiDung, Model model) {
        // Kiểm tra hoặc xử lý thêm nếu cần (vd: kiểm tra trùng username/email, mã hóa mật khẩu...)

        nguoiDungService.save(nguoiDung);
        return "redirect:/nguoidung"; // Trở về danh sách người dùng
    }
}
