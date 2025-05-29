package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @PostMapping("/sign_in")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        Optional<NguoiDung> optNguoiDung = nguoiDungService.findByUsernameOrEmail(username);

        if (optNguoiDung.isPresent()) {
            NguoiDung nguoiDung = optNguoiDung.get();
            if (nguoiDung.getMatKhau().equals(password)) {
                model.addAttribute("user", nguoiDung);
                return "redirect:/";
            } else {
                model.addAttribute("error", "Mật khẩu không đúng");
                return "auth/sign_in";
            }
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc email không tồn tại");
            return "auth/sign_in";
        }
    }
}
