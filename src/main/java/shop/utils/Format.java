package shop.utils;

import shop.configuration.env;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Format {

    public static final SimpleDateFormat DATE = new SimpleDateFormat(
            env.app.timeFormat.toString(),
            new Locale(env.app.language.toString())
    );

    public static final String date(Object date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
