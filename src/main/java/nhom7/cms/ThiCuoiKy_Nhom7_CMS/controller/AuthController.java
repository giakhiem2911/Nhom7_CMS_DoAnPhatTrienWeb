package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/sign_in")
    public String signIn() {
        return "auth/sign_in";
    }
    
    @GetMapping("/sign_up")
    public String signUp() {
        return "auth/sign_up";
    }
}