package shop.services.staticControl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public interface util {

    public static BufferedImage generateQrCode(String text, int width, int height) throws Exception {
        BitMatrix matrix = new QRCodeWriter().encode(text,
                BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    public static Object[] addArr(boolean last, Object[] base, Object value) {
        Object[] agrs = new Object[base.length + 1];
        System.arraycopy(base, 0, agrs, last ? 0 : 1, base.length);
        agrs[last ? base.length : 0] = value;
        return agrs;
    }

    public static void exportExcel(DefaultTableModel model) throws Exception {
        // header name
        String[] columnNames = new String[model.getColumnCount()];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = model.getColumnName(i);
        }

        // copy data
        Object[][] data = new Object[model.getRowCount()][model.getColumnCount()];
        List<Vector> list = model.getDataVector();
        int size = list.size();
        for (int index = 0; index < size; index++) {
            data[index] = list.get(index).toArray();
        }

        // write file data
        File file = ExcelExport.writeExcel(columnNames, data, null);
        if (file != null && file.canRead()) {
            Desktop.getDesktop().open(file);
        }
    }

    @AllArgsConstructor
    public static enum TypeExport {
        PRODUCTS("product_list.jrxml", "product_detail.jrxml"),
        CATEGORIES("category_list.jrxml", "category_detail.jrxml"),
        USERS("user_list.jrxml", "user_detail.jrxml");

        private final static String FOLDER = "/jasperReport";
        private final String fileList;
        private final String fileDetail;

        public InputStream getListAbsolutePath() {
            String path = new StringBuilder(FOLDER).append('/')
                    .append(fileList).toString();
            return fileList == null || fileList.isEmpty()
                    ? null : util.class.getResourceAsStream(path);
        }

        public InputStream getDetailAbsolutePath() {
            String path = new StringBuilder(FOLDER).append('/')
                    .append(fileDetail).toString();
            return fileDetail == null || fileDetail.isEmpty()
                    ? null : util.class.getResourceAsStream(path);
        }
    }

    public static void jasperReport(Map<String, Object> parameters, InputStream is) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load(is);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        JasperViewer.viewReport(jasperPrint, false);
    }
}
