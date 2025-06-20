package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Controller
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/sign_in")
//    public String login(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            Model model,
//            HttpSession session) {
//
//        Optional<NguoiDung> optNguoiDung = nguoiDungService.findByUsernameOrEmail(username);
//
//        if (optNguoiDung.isPresent()) {
//            NguoiDung nguoiDung = optNguoiDung.get();
//            if (passwordEncoder.matches(password, nguoiDung.getMatKhau())) {
//                session.setAttribute("currentUser", nguoiDung);
//
//                // Kiểm tra vai trò từ entity VaiTro
//                String tenVaiTro = nguoiDung.getVaiTro().getTenVaiTro();
//                if ("user".equalsIgnoreCase(tenVaiTro)) {
//                    return "redirect:http://localhost:8080/trang/khoa-cong-nghe-thong-tin/trang-chu";
//                } else {
//                    return "redirect:/";
//                }
//
//            } else {
//                model.addAttribute("error", "Mật khẩu không đúng");
//                return "auth/sign_in";
//            }
//        } else {
//            model.addAttribute("error", "Tên đăng nhập hoặc email không tồn tại");
//            return "auth/sign_in";
//        }
//    }
    @GetMapping("/sign_in")
    public String showLoginForm() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        return "auth/sign_in";
    }

    
     @GetMapping("/sign_out")
    public String showSignOutPage(HttpSession session, Model model) {
        NguoiDung currentUser = (NguoiDung) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/sign_in";
        }
        model.addAttribute("user", currentUser);
        return "auth/sign_out";
    }
 // Xử lý đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa session để đăng xuất
        return "redirect:/sign_in"; // Quay về trang đăng nhập
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

        return "nguoidung/list";
    }

 // Phương thức hiển thị form thêm người dùng đã có sẵn
    @GetMapping("/nguoidung/form")
    public String showAddForm(Model model) {
        model.addAttribute("user", new NguoiDung());
        model.addAttribute("vaiTroList", nguoiDungService.getVaiTroList());
        return "nguoidung/form";
    }
    
 // SỬA người dùng
    @GetMapping("/nguoidung/edit/{id}")
    public String editUser(@PathVariable("id") String id, Model model) {
        Optional<NguoiDung> nguoiDung = nguoiDungService.findById(id);
        if (nguoiDung.isPresent()) {
            model.addAttribute("user", nguoiDung.get());
            model.addAttribute("vaiTroList", nguoiDungService.getVaiTroList());
            return "nguoidung/form";
        } else {
            return "redirect:/nguoidung";
        }
    }

    // XÓA người dùng
    @GetMapping("/nguoidung/delete/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        nguoiDungService.deleteById(id);
        return "redirect:/nguoidung";
    }

    
 // Phương thức xử lý lưu người dùng mới hoặc cập nhật
    @PostMapping("/nguoidung/save")
    public String saveUser(@ModelAttribute("user") NguoiDung nguoiDung, Model model) {

        Optional<NguoiDung> existingUserOpt = nguoiDungService.findById(nguoiDung.getMaNguoiDung());
        if (existingUserOpt.isPresent()) {
            NguoiDung existingUser = existingUserOpt.get();

            // Nếu mật khẩu nhập vào rỗng, giữ nguyên mật khẩu cũ
            if (nguoiDung.getMatKhau() == null || nguoiDung.getMatKhau().isEmpty()) {
                nguoiDung.setMatKhau(existingUser.getMatKhau());
            } else {
                // Nếu mật khẩu mới, mã hóa nếu chưa mã hóa
                if (!nguoiDung.getMatKhau().startsWith("$2a$")) {
                    String encodedPassword = passwordEncoder.encode(nguoiDung.getMatKhau());
                    nguoiDung.setMatKhau(encodedPassword);
                }
            }
        } else {
            // Thêm mới: mã hóa mật khẩu
            if (!nguoiDung.getMatKhau().startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(nguoiDung.getMatKhau());
                nguoiDung.setMatKhau(encodedPassword);
            }
        }

        nguoiDungService.save(nguoiDung);
        return "redirect:/nguoidung";
    }

}
