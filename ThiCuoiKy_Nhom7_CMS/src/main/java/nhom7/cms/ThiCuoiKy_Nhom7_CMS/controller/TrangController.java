package nhom7.cms.ThiCuoiKy_Nhom7_CMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.Trang;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.TrangRepository;

@Controller
@RequestMapping("/trang")
public class TrangController {

    @Autowired
    private TrangRepository trangRepository;

    @GetMapping("/{duongDan}")
    public String viewPage(@PathVariable String duongDan, Model model) {
        Trang trang = trangRepository.findByDuongDan(duongDan)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("trang", trang);
        return "trang/detail";
    }
}

