package shop.utils;

import lombok.AllArgsConstructor;

public class MailDataSet {

    @AllArgsConstructor
    private enum HTML {

        // "?title", "?heading", "?code", "?note", "?footer"
        GET_CODE("<html><head><meta charset='UTF-8'><title>?title</title></head><body><div style='padding: .25rem;'><h1 style='text-align: center'>?heading</h1><hr><div style='text-align: center'><em>Mã xác nhận từ hệ thống:&nbsp;</em><strong style='border: 1px solid #000000; padding: .25rem 1rem; border-radius: .5rem;'>?code</strong><p>?note</p></div><hr><div>?footer</div></div></body></html>"),
        ANY("<h1>MAIL FROM SHOP_APP</h1>");

        private final String value;
    }

    protected static String htmlTempCode(String title, String heading, String code, String note, String footer) {
        String html = HTML.GET_CODE.value;
        return html.replace("?title", title)
                .replace("?heading", heading)
                .replace("?code", code)
                .replace("?note", note)
                .replace("?footer", footer);
    }

    public static String htmlGetCode(String code) {
        String title = "SHOP_APP GENERATE CODE";
        String heading = "SHOP_APP nhận được yêu cầu cung cấp mã xác thực";
        String note = "Cảm ơn bạn đã sử dụng dịch vụ của tôi<div style='text-align: start'><b>THÂN ÁI!</b></div>";
        String footer = "<em>Thông tin khác về tôi: </em><ol><li><a href='https://github.com/studev1922'>github.studev</a></li><li><a href='mailto:sdhoa1922@gmail.com'>sdhoa1922@gmail.com</a></li></ol>";

        return MailDataSet.htmlTempCode(title, heading, code, note, footer);
    }

}
