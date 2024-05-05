package shop.views.custom;

import java.awt.Color;
import java.awt.Graphics;

/**
 * "Nếu sài IDE netbean thì build project trước khi mở file các file design.form"<br>
 * Jlabel2 extends from javax.swing.JLabel has functions
 *
 * @see #getOutLine() to get text outline color or transparent
 * @see #setOutLine() to set text outline color or null
 * @see javax.swing.JLabel
 */
public class JLabel2 extends javax.swing.JLabel {

    private boolean isOutline = false;
    private Color lineColor = new Color(0, 0, 0, 0); // transparent
    private boolean forceTransparent = false;

    public Color getOutLine() {
        return lineColor;
    }

    /**
     * default transparent
     *
     * @see java.awt.Color
     * @param color is null to set transparent
     */
    public void setOutLine(Color color) {
        this.lineColor = color == null ? this.lineColor : color;
        this.invalidate();
    }

    @Override
    public Color getForeground() {
        return isOutline ? lineColor : super.getForeground();
    }

    @Override
    public boolean isOpaque() {
        return forceTransparent ? false : super.isOpaque();
    }

    @Override
    public void paint(Graphics g) {

        String text = getText();
        if (text == null || text.length() == 0) {
            super.paint(g);
            return;
        }

        if (isOpaque()) {
            super.paint(g);
        }

        // WN - N - NE
        //  W - C - E
        // SW - S - ES
        isOutline = true;
        forceTransparent = true;
        this.translate(g, -1, -1); // W-N
        this.translate(g, 1, 0); // N
        this.translate(g, 1, 0); // N-E
        this.translate(g, 0, 1); // E
        this.translate(g, 0, 1); // E-S
        this.translate(g, -1, 0); // S
        this.translate(g, -1, 0); // S-W
        this.translate(g, 0, -1); // W
        this.translate(g, 1, 0); // C
        forceTransparent = false;
        isOutline = false;
        super.paint(g);
    }

    private void translate(Graphics g, int x, int y) {
        g.translate(x, y);
        super.paint(g);
    }
}
