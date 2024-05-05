package shop.views.components;

import java.sql.SQLException;
import java.util.Date;
import lombok.Setter;
import shop.configuration.AuthAccess;
import shop.configuration.Config;
import shop.configuration.env;
import shop.configuration.security;
import shop.models.dao.*;
import shop.models.entities.*;
import shop.services.sql.DAOModel;
import shop.services.staticControl.DetailControl;
import shop.services.staticControl.Message;
import shop.controls.FileControl;
import shop.services.staticControl.Lib;
import shop.services.staticControl.View;
import shop.views.windows.dlg_detailProduct;
import shop.views.windows.frame_ViewControl;

public class pnl_productDetail extends javax.swing.JPanel implements DetailControl<Product> {

    public pnl_productDetail() {
        initComponents();
        this.init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_subject = new shop.views.custom.JLabel2();
        pnl_img = new javax.swing.JPanel();
        lbl_image = new shop.views.custom.JLabel2();
        btn_export_excel = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        pnl_info = new javax.swing.JPanel();
        lbl_textfield = new shop.views.custom.JLabel2();
        txt_id = new com.toedter.components.JSpinField();
        lbl_textfield1 = new shop.views.custom.JLabel2();
        txt_name = new javax.swing.JTextField();
        cb_isCheck = new javax.swing.JCheckBox();
        txt_price = new com.toedter.components.JSpinField();
        txt_quantity = new com.toedter.components.JSpinField();
        lbl_textfield2 = new shop.views.custom.JLabel2();
        dcs_regTime = new com.toedter.calendar.JDateChooser();
        cbx_cate = new javax.swing.JComboBox<>();
        cbx_user = new javax.swing.JComboBox<>();
        scr_note = new javax.swing.JScrollPane();
        txt_note = new javax.swing.JTextArea();
        pnl_control = new javax.swing.JPanel();
        btn_read = new shop.views.custom.JLabel2();
        btn_insert = new shop.views.custom.JLabel2();
        btn_previos = new shop.views.custom.JLabel2();
        btn_next = new shop.views.custom.JLabel2();
        btn_update = new shop.views.custom.JLabel2();
        btn_delete = new shop.views.custom.JLabel2();
        btn_fisrt = new shop.views.custom.JLabel2();
        btn_last = new shop.views.custom.JLabel2();

        setLayout(new java.awt.BorderLayout());

        lbl_subject.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 3, 0, new java.awt.Color(255, 153, 51)));
        lbl_subject.setForeground(new java.awt.Color(255, 153, 51));
        lbl_subject.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_subject.setText("product's subject");
        lbl_subject.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        lbl_subject.setOutLine(new java.awt.Color(255, 255, 255));
        add(lbl_subject, java.awt.BorderLayout.PAGE_START);

        lbl_image.setForeground(new java.awt.Color(255, 153, 51));
        lbl_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo.png"))); // NOI18N
        lbl_image.setText("default.png");
        lbl_image.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        lbl_image.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_image.setOutLine(new java.awt.Color(255, 255, 255));
        lbl_image.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btn_export_excel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_export_excel.setForeground(new java.awt.Color(0, 204, 0));
        btn_export_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/seo-report.png"))); // NOI18N
        btn_export_excel.setText("EXPORT");
        btn_export_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_export_excelActionPerformed(evt);
            }
        });

        btn_clear.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_clear.setForeground(new java.awt.Color(102, 102, 102));
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loading.png"))); // NOI18N
        btn_clear.setText("làm mới");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_imgLayout = new javax.swing.GroupLayout(pnl_img);
        pnl_img.setLayout(pnl_imgLayout);
        pnl_imgLayout.setHorizontalGroup(
            pnl_imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
            .addComponent(btn_export_excel, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
            .addComponent(btn_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_imgLayout.setVerticalGroup(
            pnl_imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_imgLayout.createSequentialGroup()
                .addComponent(btn_export_excel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_clear)
                .addContainerGap())
        );

        add(pnl_img, java.awt.BorderLayout.LINE_START);

        lbl_textfield.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(255, 153, 51)));
        lbl_textfield.setForeground(new java.awt.Color(255, 153, 51));
        lbl_textfield.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_textfield.setText("Mã sản phẩm");
        lbl_textfield.setToolTipText("");
        lbl_textfield.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        lbl_textfield.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_textfield.setOutLine(new java.awt.Color(255, 255, 255));
        lbl_textfield.setVerifyInputWhenFocusTarget(false);

        lbl_textfield1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(255, 153, 51)));
        lbl_textfield1.setForeground(new java.awt.Color(255, 153, 51));
        lbl_textfield1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_textfield1.setText("Tên sản phẩm");
        lbl_textfield1.setToolTipText("");
        lbl_textfield1.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        lbl_textfield1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_textfield1.setOutLine(new java.awt.Color(255, 255, 255));
        lbl_textfield1.setVerifyInputWhenFocusTarget(false);

        txt_name.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        txt_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cb_isCheck.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        cb_isCheck.setText("mở bán");
        cb_isCheck.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "active", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14), new java.awt.Color(255, 153, 51))); // NOI18N
        cb_isCheck.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        cb_isCheck.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        txt_price.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14), new java.awt.Color(255, 153, 51))); // NOI18N
        txt_price.setMinimumSize(new java.awt.Dimension(64, 30));
        txt_price.setPreferredSize(new java.awt.Dimension(64, 25));

        txt_quantity.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14), new java.awt.Color(255, 153, 51))); // NOI18N
        txt_quantity.setMinimumSize(new java.awt.Dimension(64, 30));
        txt_quantity.setPreferredSize(new java.awt.Dimension(64, 25));

        lbl_textfield2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(255, 153, 51)));
        lbl_textfield2.setForeground(new java.awt.Color(255, 153, 51));
        lbl_textfield2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_textfield2.setText("Thời gian tạo");
        lbl_textfield2.setToolTipText("");
        lbl_textfield2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        lbl_textfield2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_textfield2.setOutLine(new java.awt.Color(255, 255, 255));
        lbl_textfield2.setVerifyInputWhenFocusTarget(false);

        dcs_regTime.setDateFormatString("EEEE, dd-MM-yyyy hh:mm:ss");
        dcs_regTime.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N

        cbx_cate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbx_cate.setForeground(new java.awt.Color(255, 153, 51));
        cbx_cate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Category", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 153, 51))); // NOI18N
        cbx_cate.setPreferredSize(new java.awt.Dimension(88, 50));

        cbx_user.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbx_user.setForeground(new java.awt.Color(255, 153, 51));
        cbx_user.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 153, 51))); // NOI18N
        cbx_user.setPreferredSize(new java.awt.Dimension(88, 50));

        scr_note.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ghi chú", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 153, 51))); // NOI18N

        txt_note.setColumns(20);
        txt_note.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        txt_note.setForeground(new java.awt.Color(255, 153, 51));
        txt_note.setLineWrap(true);
        txt_note.setRows(3);
        scr_note.setViewportView(txt_note);

        javax.swing.GroupLayout pnl_infoLayout = new javax.swing.GroupLayout(pnl_info);
        pnl_info.setLayout(pnl_infoLayout);
        pnl_infoLayout.setHorizontalGroup(
            pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scr_note)
                    .addGroup(pnl_infoLayout.createSequentialGroup()
                        .addComponent(cbx_cate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_user, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnl_infoLayout.createSequentialGroup()
                        .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl_textfield2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_isCheck, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_textfield, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(lbl_textfield1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_name)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_infoLayout.createSequentialGroup()
                                .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_quantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txt_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcs_regTime, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pnl_infoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbl_textfield, lbl_textfield1, lbl_textfield2});

        pnl_infoLayout.setVerticalGroup(
            pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_textfield1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_quantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_isCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcs_regTime, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_textfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbx_cate, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(cbx_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scr_note, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_infoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbx_cate, cbx_user});

        pnl_infoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dcs_regTime, lbl_textfield, lbl_textfield1, lbl_textfield2, txt_id, txt_name});

        add(pnl_info, java.awt.BorderLayout.CENTER);

        pnl_control.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_control.setLayout(new java.awt.GridLayout(0, 4, 3, 3));

        btn_read.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));
        btn_read.setForeground(new java.awt.Color(0, 153, 204));
        btn_read.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btn_read.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file.png"))); // NOI18N
        btn_read.setText("Thông tin");
        btn_read.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_read.setIconTextGap(5);
        btn_read.setOutLine(new java.awt.Color(0, 0, 0));
        btn_read.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_readMouseClicked(evt);
            }
        });
        pnl_control.add(btn_read);

        btn_insert.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));
        btn_insert.setForeground(new java.awt.Color(0, 255, 0));
        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/insert.png"))); // NOI18N
        btn_insert.setText("Thêm");
        btn_insert.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_insert.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btn_insert.setIconTextGap(5);
        btn_insert.setOutLine(new java.awt.Color(0, 0, 0));
        btn_insert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_insertMouseClicked(evt);
            }
        });
        pnl_control.add(btn_insert);

        btn_previos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));
        btn_previos.setForeground(new java.awt.Color(0, 255, 255));
        btn_previos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btn_previos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/backward.png"))); // NOI18N
        btn_previos.setText("Trước đó");
        btn_previos.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_previos.setIconTextGap(5);
        btn_previos.setOutLine(new java.awt.Color(0, 0, 0));
        btn_previos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_previosMouseClicked(evt);
            }
        });
        pnl_control.add(btn_previos);

        btn_next.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));
        btn_next.setForeground(new java.awt.Color(0, 255, 255));
        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/forward.png"))); // NOI18N
        btn_next.setText("Tiếp theo");
        btn_next.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_next.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btn_next.setIconTextGap(5);
        btn_next.setOutLine(new java.awt.Color(0, 0, 0));
        btn_next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nextMouseClicked(evt);
            }
        });
        pnl_control.add(btn_next);

        btn_update.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));
        btn_update.setForeground(new java.awt.Color(255, 204, 51));
        btn_update.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loop-arrow.png"))); // NOI18N
        btn_update.setText("Cập nhật");
        btn_update.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_update.setIconTextGap(5);
        btn_update.setOutLine(new java.awt.Color(0, 0, 0));
        btn_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateMouseClicked(evt);
            }
        });
        pnl_control.add(btn_update);

        btn_delete.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));
        btn_delete.setForeground(new java.awt.Color(255, 51, 51));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btn_delete.setText("Xóa");
        btn_delete.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btn_delete.setIconTextGap(5);
        btn_delete.setOutLine(new java.awt.Color(0, 0, 0));
        btn_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteMouseClicked(evt);
            }
        });
        pnl_control.add(btn_delete);

        btn_fisrt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));
        btn_fisrt.setForeground(new java.awt.Color(0, 255, 255));
        btn_fisrt.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btn_fisrt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fast-backward.png"))); // NOI18N
        btn_fisrt.setText("Đầu tiên");
        btn_fisrt.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_fisrt.setIconTextGap(5);
        btn_fisrt.setOutLine(new java.awt.Color(0, 0, 0));
        btn_fisrt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_fisrtMouseClicked(evt);
            }
        });
        pnl_control.add(btn_fisrt);

        btn_last.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));
        btn_last.setForeground(new java.awt.Color(0, 255, 255));
        btn_last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fast-forward.png"))); // NOI18N
        btn_last.setText("Cuối cùng");
        btn_last.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_last.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btn_last.setIconTextGap(5);
        btn_last.setOutLine(new java.awt.Color(0, 0, 0));
        btn_last.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_lastMouseClicked(evt);
            }
        });
        pnl_control.add(btn_last);

        add(pnl_control, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_readMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_readMouseClicked
        this.crud(frame_ViewControl.C_TYPE.READ);
    }//GEN-LAST:event_btn_readMouseClicked

    private void btn_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_insertMouseClicked
        this.crud(frame_ViewControl.C_TYPE.INSERT);
    }//GEN-LAST:event_btn_insertMouseClicked

    private void btn_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateMouseClicked
        this.crud(frame_ViewControl.C_TYPE.UPDATE);
    }//GEN-LAST:event_btn_updateMouseClicked

    private void btn_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteMouseClicked
        this.crud(frame_ViewControl.C_TYPE.DELETE);
    }//GEN-LAST:event_btn_deleteMouseClicked

    private void btn_previosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_previosMouseClicked
        root.navigator(frame_ViewControl.N_TYPE.PREV);
    }//GEN-LAST:event_btn_previosMouseClicked

    private void btn_nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nextMouseClicked
        Long key = Long.valueOf(txt_id.getValue());
        root.navigator(frame_ViewControl.N_TYPE.NEXT);
    }//GEN-LAST:event_btn_nextMouseClicked

    private void btn_fisrtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fisrtMouseClicked
        root.navigator(frame_ViewControl.N_TYPE.FIRST);
    }//GEN-LAST:event_btn_fisrtMouseClicked

    private void btn_lastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lastMouseClicked
        root.navigator(frame_ViewControl.N_TYPE.LAST);
    }//GEN-LAST:event_btn_lastMouseClicked

    private void btn_export_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export_excelActionPerformed
        Product entity = this.getFormData();
        Object oCate = cbx_cate.getSelectedItem();
        Object oUser = cbx_user.getSelectedItem();

        root.getExportControl().jaspertReportDetail(entity, oCate, oUser);
    }//GEN-LAST:event_btn_export_excelActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        this.setDetail(new Product());
    }//GEN-LAST:event_btn_clearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private shop.views.custom.JLabel2 btn_delete;
    private javax.swing.JButton btn_export_excel;
    private shop.views.custom.JLabel2 btn_fisrt;
    private shop.views.custom.JLabel2 btn_insert;
    private shop.views.custom.JLabel2 btn_last;
    private shop.views.custom.JLabel2 btn_next;
    private shop.views.custom.JLabel2 btn_previos;
    private shop.views.custom.JLabel2 btn_read;
    private shop.views.custom.JLabel2 btn_update;
    private javax.swing.JCheckBox cb_isCheck;
    private javax.swing.JComboBox<Category> cbx_cate;
    private javax.swing.JComboBox<User> cbx_user;
    private com.toedter.calendar.JDateChooser dcs_regTime;
    private shop.views.custom.JLabel2 lbl_image;
    private shop.views.custom.JLabel2 lbl_subject;
    private shop.views.custom.JLabel2 lbl_textfield;
    private shop.views.custom.JLabel2 lbl_textfield1;
    private shop.views.custom.JLabel2 lbl_textfield2;
    private javax.swing.JPanel pnl_control;
    private javax.swing.JPanel pnl_img;
    private javax.swing.JPanel pnl_info;
    private javax.swing.JScrollPane scr_note;
    private com.toedter.components.JSpinField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextArea txt_note;
    private com.toedter.components.JSpinField txt_price;
    private com.toedter.components.JSpinField txt_quantity;
    // End of variables declaration//GEN-END:variables
    private static final Config.folder FOLDER = Config.folder.product;
    private static final String DEFAULT_IMG = env.DEFAULT_IMG;
    private static final ProductDAO pdao = DAOModel.PRODUCT;
    private static final CateDAO cdao = DAOModel.CATE;
    private static final UserDAO udao = DAOModel.USER;
    private FileControl fileControl;
    private String dataImg = null;

    @Setter
    private frame_ViewControl root;

    private void init() {
        this.fileControl = new FileControl(lbl_image, FOLDER, DEFAULT_IMG);

        cbx_cate.removeAll();
        cbx_user.removeAll();
        // fill all comboboxes
        cdao.getMap().forEach((k, e) -> cbx_cate.addItem(e));
        udao.getMap().forEach((k, e) -> cbx_user.addItem(e));
        // access control
        this.authControl();
    }

    void crud(frame_ViewControl.C_TYPE type) {
        try {
            Long key = Long.valueOf(txt_id.getValue());
            Product e = this.getFormData();

            switch (type) {
                case READ:
                    root.crud(type, key);
                    break;
                case INSERT:
                    if (checkForm(e) && root.crud(type, e)) {
                        fileControl.save();
                    }
                    break;
                case UPDATE:
                    if (checkForm(e) && root.crud(type, e)) {
                        dataImg = fileControl.update(dataImg) ? e.getImage() : null;
                    }
                    break;
                case DELETE:
                    String fileName = lbl_image.getText();
                    if (root.crud(type, key)) {
                        fileControl.delete(fileName);
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (SQLException ex) {
            String mes = ex.getMessage();
            Message.alert(this, mes, mes, Message.TYPE.ERROR);
        }
    }

    @Override
    public void setDetail(Product e) {
        if (e == null) {
            e = new Product();
        }

        Category cate = e.getCg_id() == null ? null : cdao.getById(e.getCg_id());
        User user = e.getU_id() == null ? null : udao.getById(e.getU_id());

        this.showImage(e.getImage());
        this.lbl_subject.setText(e.toString2());
        this.txt_id.setValue((int) e.getPrid());
        this.txt_name.setText(e.getName());
        this.txt_note.setText(e.getNote());
        this.cb_isCheck.setSelected(e.isActive());
        this.txt_price.setValue((int) e.getPrice());
        this.txt_quantity.setValue(e.getQuantity());
        this.dcs_regTime.setDate(e.getRegTime());

        this.cbx_cate.setSelectedItem(cate);
        this.cbx_user.setSelectedItem(user);

    }

    @Override
    public void showDialogDetail(Product p) {
        new dlg_detailProduct(p,
                (Category) cbx_cate.getSelectedItem(),
                (User) cbx_user.getSelectedItem()
        ).setVisible(true);
    }

    private Product getFormData() {
        Long prid = Long.valueOf(txt_id.getValue());
        String name = txt_name.getText();
        String note = txt_note.getText();
        boolean active = cb_isCheck.isSelected();
        float price = txt_price.getValue();
        int quantity = txt_quantity.getValue();
        Date regTime = dcs_regTime.getDate();
        String image = lbl_image.getText();
        Integer cg_id = ((Category) cbx_cate.getSelectedItem()).getCgid();
        String u_id = ((User) cbx_user.getSelectedItem()).getUsername();

        return new Product(prid, name, note, active, price, quantity, regTime, image, cg_id, u_id);
    }

    private void authControl() {
        boolean isAdmin = security.hasAnyRoles(AuthAccess.ROLE.ADMIN);
        
        cb_isCheck.setToolTipText("Chờ admin duyệt sản phẩm!");
        cb_isCheck.setEnabled(isAdmin);
        cbx_user.setEnabled(isAdmin);
        if(!isAdmin) {
            cbx_user.setSelectedItem(security.getUser());
        }
    }

    private boolean checkForm(Product e) {

        return true;
    }

    private void showImage(String fileName) {
        dataImg = fileName;
        String pathImg = Lib.getFile(FOLDER, fileName, DEFAULT_IMG).toString();
        View.setImage(lbl_image, pathImg, 150, 150);
        this.lbl_image.setText(dataImg == null || dataImg.isEmpty() ? DEFAULT_IMG : dataImg);
    }
}
