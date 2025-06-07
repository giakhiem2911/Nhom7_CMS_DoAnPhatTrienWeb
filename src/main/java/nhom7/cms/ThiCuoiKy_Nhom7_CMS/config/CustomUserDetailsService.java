package nhom7.cms.ThiCuoiKy_Nhom7_CMS.config;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhapOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Người dùng không tồn tại"));

        String maVaiTro = nguoiDung.getVaiTro().getMaVaiTro();
        String role = switch (maVaiTro) {
	        case "VT001" -> "ADMIN";
	        case "VT002" -> "USER";
	        default -> "GUEST";
	    };

        return User.builder()
                .username(nguoiDung.getTenDangNhap())
                .password(nguoiDung.getMatKhau()) // Đã được mã hóa (BCrypt)
                .roles(role)
                .build();
    }
}
