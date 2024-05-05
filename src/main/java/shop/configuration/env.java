package shop.configuration;

import shop.services.staticControl.Lib;
import java.awt.Font;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

public class env implements Config {

    // "LOAD BACKGROUNDS"
    public static final File BGRS = Lib.getDir(folder.background.toString());
    public static final URL PATH_LOGO = env.class.getResource(
            "/icons/" + env.PROPERTIES.getProperty("app.icon.image", env.DEFAULT_IMG)
    );
    public static final ImageIcon LOGO = new ImageIcon(PATH_LOGO);

    // "SHOW TITLE"
    public static final String[] HEADINGS = Lib.readAllLines(
            folder.data, "carousel.txt"
    ).toArray(new String[1]);

    // "LOAD FONT"
    public static Font FONT = new Font(
            app.fontFamily.toString(), 0,
            Integer.parseInt(app.fontSize.toString())
    );

    public final static String DEFAULT_IMG = "default.png";
}
