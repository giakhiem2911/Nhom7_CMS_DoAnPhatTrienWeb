package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.NguoiDung;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    // Lấy tất cả người dùng
    public List<NguoiDung> findAll() {
        return nguoiDungRepository.findAll();
    }

    // Lấy người dùng theo ID
    public Optional<NguoiDung> findById(String maNguoiDung) {
        return nguoiDungRepository.findById(maNguoiDung);
    }

    // Lấy người dùng theo tên đăng nhập
    public Optional<NguoiDung> findByTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }
    
    // Lấy người dùng theo email
    public Optional<NguoiDung> findByUsernameOrEmail(String usernameOrEmail) {
        Optional<NguoiDung> user = nguoiDungRepository.findByTenDangNhap(usernameOrEmail);
        if (user.isEmpty()) {
            user = nguoiDungRepository.findByEmail(usernameOrEmail);
        }
        return user;
    }

    // Tạo hoặc cập nhật người dùng
    public NguoiDung save(NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    // Xóa người dùng theo ID
    public void deleteById(String maNguoiDung) {
        nguoiDungRepository.deleteById(maNguoiDung);
    }
}