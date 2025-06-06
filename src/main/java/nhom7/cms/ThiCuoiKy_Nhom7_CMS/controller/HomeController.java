package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MenuRepository;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.ThongKeService;

@Controller
public class HomeController {
    
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        // Kiểm tra xem có người dùng đăng nhập không
    	Object nguoiDung = request.getSession().getAttribute("currentUser");
    	if (nguoiDung == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang login
            return "redirect:/sign_in";
        }
        // Lấy số liệu thống kê
    	long postCount = thongKeService.demBaiViet();
        long menuCount = thongKeService.demMenu();
        long pageCount = thongKeService.demTrang();
        long activeUsers = thongKeService.demNguoiDungHoatDong();
        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("title", "Bảng điều khiển - NHOM7_CMS");

        model.addAttribute("postCount", postCount);
        model.addAttribute("menuCount", menuCount);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("activeUsers", activeUsers);
        return "index";
    }
}