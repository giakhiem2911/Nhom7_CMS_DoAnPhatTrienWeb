package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    // Đường dẫn lưu ảnh trên ổ đĩa
    private static final String UPLOAD_DIR = "uploads/images/";

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file, Model model) {
        if (!file.isEmpty()) {
            try {
                // Tạo thư mục nếu chưa tồn tại
                File dir = new File(UPLOAD_DIR);
                if (!dir.exists()) dir.mkdirs();

                // Lưu file
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                Files.write(path, bytes);

                model.addAttribute("message", "Tải lên thành công!");
                model.addAttribute("imageUrl", "/images/" + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Lỗi khi tải lên!");
            }
        } else {
            model.addAttribute("message", "Chưa chọn file!");
        }

        return "menu/form"; // hoặc redirect nếu cần
    }
}
