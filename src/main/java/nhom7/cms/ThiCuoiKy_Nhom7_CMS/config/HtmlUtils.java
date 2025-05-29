package nhom7.cms.ThiCuoiKy_Nhom7_CMS.config;

import org.jsoup.Jsoup;

public class HtmlUtils {
    public static String stripHtml(String html) {
        if (html == null) return "";
        return Jsoup.parse(html).text();
    }
}

