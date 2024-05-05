package shop.views;

import shop.controls.ExportCategory;
import shop.controls.ExportUser;
import shop.controls.ExportProduct;
import shop.configuration.env;
import shop.services.sql.AbstractDAO;
import shop.services.sql.DAOModel;
import shop.models.entities.*;
import shop.views.components.*;
import shop.services.staticControl.View;
import shop.views.windows.frame_ViewControl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collection;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import shop.configuration.AuthAccess;
import shop.configuration.security;
import shop.services.staticControl.FilterProduct;
import shop.services.staticControl.DetailControl;
import shop.services.staticControl.ExportControl;
import shop.services.staticControl.Message;
import shop.views.windows.dlg_activeProduct;
import shop.views.windows.dlg_security_login;
import shop.services.staticControl.ViewControl;

public class App extends javax.swing.JFrame implements AuthAccess {

    public App() {
        initComponents();
        this.onload();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slp_body = new javax.swing.JSplitPane();
        slp_article = new javax.swing.JSplitPane();
        lyp_carousel = new javax.swing.JLayeredPane();
        lyp_article = new javax.swing.JLayeredPane();
        lyp_aside = new javax.swing.JLayeredPane();
        pnl_control = new javax.swing.JPanel();
        btn_mUser = new javax.swing.JButton();
        btn_mCategory = new javax.swing.JButton();
        btn_mProduct = new javax.swing.JButton();
        btn_mActiveProduct = new javax.swing.JButton();
        lbl_controlSubtitle = new javax.swing.JLabel();
        pnl_bottom = new javax.swing.JPanel();
        lbl_timeClock = new javax.swing.JLabel();
        mnu_bar = new javax.swing.JMenuBar();
        mnu_account = new javax.swing.JMenu();
        inu_login = new javax.swing.JMenuItem();
        inu_logout = new javax.swing.JMenuItem();
        inu_register = new javax.swing.JMenuItem();
        inu_forgotPass = new javax.swing.JMenuItem();
        inu_exit = new javax.swing.JMenuItem();
        mnu_control = new javax.swing.JMenu();
        c_account = new javax.swing.JMenuItem();
        c_category = new javax.swing.JMenuItem();
        c_product = new javax.swing.JMenuItem();
        c_activeProduct = new javax.swing.JMenuItem();
        mnu_help = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        slp_body.setDividerLocation(180);
        slp_body.setDividerSize(3);

        slp_article.setDividerLocation(150);
        slp_article.setDividerSize(3);
        slp_article.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        slp_article.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                slp_articleComponentResized(evt);
            }
        });

        lyp_carousel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout lyp_carouselLayout = new javax.swing.GroupLayout(lyp_carousel);
        lyp_carousel.setLayout(lyp_carouselLayout);
        lyp_carouselLayout.setHorizontalGroup(
            lyp_carouselLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );
        lyp_carouselLayout.setVerticalGroup(
            lyp_carouselLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
        );

        slp_article.setTopComponent(lyp_carousel);

        lyp_article.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout lyp_articleLayout = new javax.swing.GroupLayout(lyp_article);
        lyp_article.setLayout(lyp_articleLayout);
        lyp_articleLayout.setHorizontalGroup(
            lyp_articleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );
        lyp_articleLayout.setVerticalGroup(
            lyp_articleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );

        slp_article.setRightComponent(lyp_article);

        slp_body.setRightComponent(slp_article);

        lyp_aside.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnl_control.setLayout(new javax.swing.BoxLayout(pnl_control, javax.swing.BoxLayout.Y_AXIS));

        btn_mUser.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_mUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/management.png"))); // NOI18N
        btn_mUser.setText("Quản lý tài khoản");
        btn_mUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_accountActionPerformed(evt);
            }
        });
        pnl_control.add(btn_mUser);

        btn_mCategory.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_mCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/app.png"))); // NOI18N
        btn_mCategory.setText("Quản lý phân loại");
        btn_mCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_categoryActionPerformed(evt);
            }
        });
        pnl_control.add(btn_mCategory);

        btn_mProduct.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_mProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/box.png"))); // NOI18N
        btn_mProduct.setText("Quản lý sản phẩm");
        btn_mProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_productActionPerformed(evt);
            }
        });
        pnl_control.add(btn_mProduct);

        btn_mActiveProduct.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_mActiveProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/default.png"))); // NOI18N
        btn_mActiveProduct.setText("Duyệt sản phẩm");
        btn_mActiveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_activeProductActionPerformed(evt);
            }
        });
        pnl_control.add(btn_mActiveProduct);

        lbl_controlSubtitle.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_controlSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_controlSubtitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/default.png"))); // NOI18N
        lbl_controlSubtitle.setText("Control");
        lbl_controlSubtitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 204, 51)));

        lyp_aside.setLayer(pnl_control, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lyp_aside.setLayer(lbl_controlSubtitle, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lyp_asideLayout = new javax.swing.GroupLayout(lyp_aside);
        lyp_aside.setLayout(lyp_asideLayout);
        lyp_asideLayout.setHorizontalGroup(
            lyp_asideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lyp_asideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lyp_asideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_controlSubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(pnl_control, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        lyp_asideLayout.setVerticalGroup(
            lyp_asideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lyp_asideLayout.createSequentialGroup()
                .addComponent(lbl_controlSubtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        slp_body.setLeftComponent(lyp_aside);

        pnl_bottom.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_timeClock.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_timeClock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_timeClock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loading.png"))); // NOI18N
        lbl_timeClock.setText("time clock ...");
        lbl_timeClock.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout pnl_bottomLayout = new javax.swing.GroupLayout(pnl_bottom);
        pnl_bottom.setLayout(pnl_bottomLayout);
        pnl_bottomLayout.setHorizontalGroup(
            pnl_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_bottomLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_timeClock, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_bottomLayout.setVerticalGroup(
            pnl_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_timeClock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mnu_bar.setToolTipText("");

        mnu_account.setMnemonic('f');
        mnu_account.setText("Tài khoản");

        inu_login.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        inu_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/login.png"))); // NOI18N
        inu_login.setMnemonic('o');
        inu_login.setText("Đăng nhập");
        inu_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inu_loginActionPerformed(evt);
            }
        });
        mnu_account.add(inu_login);

        inu_logout.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        inu_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        inu_logout.setMnemonic('x');
        inu_logout.setText("Đăng Xuất");
        inu_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inu_logoutActionPerformed(evt);
            }
        });
        mnu_account.add(inu_logout);

        inu_register.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        inu_register.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-user.png"))); // NOI18N
        inu_register.setMnemonic('s');
        inu_register.setText("Đăng ký");
        inu_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inu_registerActionPerformed(evt);
            }
        });
        mnu_account.add(inu_register);

        inu_forgotPass.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        inu_forgotPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/forgot.png"))); // NOI18N
        inu_forgotPass.setMnemonic('a');
        inu_forgotPass.setText("Quên Mật Khẩu");
        inu_forgotPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inu_forgotPassActionPerformed(evt);
            }
        });
        mnu_account.add(inu_forgotPass);

        inu_exit.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        inu_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/power.png"))); // NOI18N
        inu_exit.setMnemonic('x');
        inu_exit.setText("Thoát");
        inu_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inu_exitActionPerformed(evt);
            }
        });
        mnu_account.add(inu_exit);

        mnu_bar.add(mnu_account);

        mnu_control.setMnemonic('e');
        mnu_control.setText("Chức năng");

        c_account.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/management.png"))); // NOI18N
        c_account.setMnemonic('t');
        c_account.setText("Quản lý tài khoản");
        c_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_accountActionPerformed(evt);
            }
        });
        mnu_control.add(c_account);

        c_category.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/app.png"))); // NOI18N
        c_category.setMnemonic('t');
        c_category.setText("Quản lý phân loại");
        c_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_categoryActionPerformed(evt);
            }
        });
        mnu_control.add(c_category);

        c_product.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/box.png"))); // NOI18N
        c_product.setMnemonic('t');
        c_product.setText("Quản lý sản phẩm");
        c_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_productActionPerformed(evt);
            }
        });
        mnu_control.add(c_product);

        c_activeProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/default.png"))); // NOI18N
        c_activeProduct.setMnemonic('t');
        c_activeProduct.setText("Duyệt sản phẩm");
        c_activeProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_activeProductActionPerformed(evt);
            }
        });
        mnu_control.add(c_activeProduct);

        mnu_bar.add(mnu_control);

        mnu_help.setMnemonic('h');
        mnu_help.setText("Hỗ trợ");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        mnu_help.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        mnu_help.add(aboutMenuItem);

        mnu_bar.add(mnu_help);

        setJMenuBar(mnu_bar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_bottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(slp_body)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(slp_body)
                .addGap(3, 3, 3)
                .addComponent(pnl_bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void slp_articleComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_slp_articleComponentResized
        slp_article.setDividerLocation(this.getSize().height * 1 / 3);
    }//GEN-LAST:event_slp_articleComponentResized

    private void c_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_accountActionPerformed
        securityControl(ASIDE_CONTROL.ACCOUNT, C_ACCOUNT);
    }//GEN-LAST:event_c_accountActionPerformed

    private void c_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_categoryActionPerformed
        securityControl(ASIDE_CONTROL.CATEGORY, C_CATEGORY);
    }//GEN-LAST:event_c_categoryActionPerformed

    private void c_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_productActionPerformed
        securityControl(ASIDE_CONTROL.PRODUCT, C_PRODUCT);
    }//GEN-LAST:event_c_productActionPerformed

    private void c_activeProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_activeProductActionPerformed
        securityControl(ASIDE_CONTROL.ACTIVE_PRODUCT, C_ACTIVE_PRODUCT);
    }//GEN-LAST:event_c_activeProductActionPerformed

    private void inu_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inu_loginActionPerformed
        ViewControl.S_LOGIN.setVisible(true);
    }//GEN-LAST:event_inu_loginActionPerformed

    private void inu_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inu_logoutActionPerformed
        ViewControl.view_logout();
    }//GEN-LAST:event_inu_logoutActionPerformed

    private void inu_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inu_registerActionPerformed
        ViewControl.S_REGISTER.setVisible(true);
    }//GEN-LAST:event_inu_registerActionPerformed

    private void inu_forgotPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inu_forgotPassActionPerformed
        ViewControl.S_FORGOT_PASS.setVisible(true);
    }//GEN-LAST:event_inu_forgotPassActionPerformed

    private void inu_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inu_exitActionPerformed
        ViewControl.exit();
    }//GEN-LAST:event_inu_exitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton btn_mActiveProduct;
    private javax.swing.JButton btn_mCategory;
    private javax.swing.JButton btn_mProduct;
    private javax.swing.JButton btn_mUser;
    private javax.swing.JMenuItem c_account;
    private javax.swing.JMenuItem c_activeProduct;
    private javax.swing.JMenuItem c_category;
    private javax.swing.JMenuItem c_product;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem inu_exit;
    private javax.swing.JMenuItem inu_forgotPass;
    private javax.swing.JMenuItem inu_login;
    private javax.swing.JMenuItem inu_logout;
    private javax.swing.JMenuItem inu_register;
    private javax.swing.JLabel lbl_controlSubtitle;
    private javax.swing.JLabel lbl_timeClock;
    private javax.swing.JLayeredPane lyp_article;
    private javax.swing.JLayeredPane lyp_aside;
    private javax.swing.JLayeredPane lyp_carousel;
    private javax.swing.JMenu mnu_account;
    private javax.swing.JMenuBar mnu_bar;
    private javax.swing.JMenu mnu_control;
    private javax.swing.JMenu mnu_help;
    private javax.swing.JPanel pnl_bottom;
    private javax.swing.JPanel pnl_control;
    private javax.swing.JSplitPane slp_article;
    private javax.swing.JSplitPane slp_body;
    // End of variables declaration//GEN-END:variables

    private static final Properties p = env.PROPERTIES;
    private final String pathIcon = new StringBuilder("/icons/")
            .append(p.getProperty("app.icon.image", "default.png"))
            .toString();
    private final int ratio = Integer.parseInt(
            p.getProperty("app.display.size", "50")
    );
    private final String title = p.getProperty("app.display.title");
    private Timer timeClock = null;

    private static enum ASIDE_CONTROL {
        ACCOUNT, CATEGORY, PRODUCT, ACTIVE_PRODUCT
    }

    private void onload() {
        timeClock = View.clocked(lbl_timeClock);

        View.setComponent(lyp_carousel, new pnl_carousel());
        View.setComponent(lyp_article, new pnl_productContent());
        this.setDisplay();
        this.loadControlDisplay();

        timeClock.start();
    }

    public void reload() {
        this.validate();
        this.setVisible(true);
        this.loadControlDisplay();
    }

    public void reload(boolean isReset) {
        this.reload();
        if (isReset) {
            this.onload();
        }
    }

    private void setDisplay() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screen.width * ratio / 100;
        int height = screen.height * ratio / 100;

        this.setTitle(title == null ? getTitle() : title);
        this.setIconImage(new ImageIcon(getClass().getResource(pathIcon)).getImage());
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
    }

    public void loadControlDisplay() {
        boolean isLogin = security.isLogin();

        inu_login.setEnabled(!isLogin);
        inu_register.setEnabled(!isLogin);
        inu_forgotPass.setEnabled(!isLogin);
        inu_logout.setEnabled(isLogin);
    }

    // onload article view and data
    private void loadViewControl(AbstractDAO dao, JTable table, JPanel detail, JPanel statistic, ExportControl exportControl) {
        this.dispose();

        frame_ViewControl root = new frame_ViewControl(this, dao, statistic, table, detail, exportControl);
        if (detail != null) {
            ((DetailControl) detail).setRoot(root);
        }
        root.setVisible(true);
    }

    private void securityControl(ASIDE_CONTROL type, AuthAccess.ROLE... roles) {
        if (!security.isLogin()) {
            if (Message.confirm(this,
                    " - Chức năng cần đăng nhập!\n - Mở trang đăng nhập?",
                    "CHƯA ĐĂNG NHẬP TÀI KHOẢN",
                    Message.CF_TYPE.YES_NO) > 0) {
                return;
            } else {
                new dlg_security_login(this, true).setVisible(true);
            }
        }

        if (security.hasAnyRoles(roles)) {
            this.asideControl(type);
        } else {
            this.roleMes(false);
        }
    }

    private void asideControl(ASIDE_CONTROL type) {
        Collection data;
        JTable table = null;
        AbstractDAO dao = null;
        ExportControl exportControl = null;
        JPanel detail = null, statistic = null;

        switch (type) {
            case ACCOUNT: {
                dao = DAOModel.USER;
                data = dao.getMap().values();
                table = User.table(data);
                detail = new pnl_userDetail();
                statistic = new pnl_userStatistic();
                exportControl = new ExportUser();
                break;
            }
            case CATEGORY: {
                dao = DAOModel.CATE;
                data = dao.getMap().values();
                table = Category.table(data);
                detail = new pnl_categoryDetail();
                statistic = new pnl_categoryStatistic();
                exportControl = new ExportCategory();
                break;
            }
            case PRODUCT: {
                dao = DAOModel.PRODUCT;
                data = dao.getMap().values(); // get all data if ADMIN and by uid for rest of roles
                table = Product.table(security.hasAnyRoles(AuthAccess.ROLE.ADMIN) ? data : FilterProduct
                        .getR_BY(data, FilterProduct.R_BY.USER, security.getUid())
                );
                detail = new pnl_productDetail();
                statistic = new pnl_productStatistic();
                exportControl = new ExportProduct();
                break;
            }
            case ACTIVE_PRODUCT: {
                this.dispose();
                dlg_activeProduct dlg = new dlg_activeProduct(this, true);
                dlg.setSize(this.getSize());
                dlg.setLocationRelativeTo(this);
                dlg.setVisible(true);
                return;
            }
        }

        this.loadViewControl(dao, table, detail, statistic, exportControl);
    }

    private void roleMes(boolean isAccess) {
        boolean isLogin = security.isLogin();
        String mesTitle = "BẢO MẬT HỆ THỐNG";
        String message = isLogin ? isAccess
                ? "Truy cập thông tin"
                : "Tài khoản không được truy cập chức năng này!"
                : "Cần thông tin đăng nhập!!!";
        Message.alert(this, message, mesTitle,
                isLogin && isAccess ? Message.TYPE.INFO : Message.TYPE.WARNING
        );
    }

}
