package nhom7.cms.ThiCuoiKy_Nhom7_CMS.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.service.NguoiDungService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private NguoiDungService nguoiDungService;

    @ModelAttribute
    public void addCurrentUserToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            Optional<NguoiDung> nguoiDungOpt = nguoiDungService.findByUsernameOrEmail(username);
            nguoiDungOpt.ifPresent(nguoiDung -> model.addAttribute("currentUser", nguoiDung));
        }
    }
}

