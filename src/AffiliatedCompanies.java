
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew Hyun
 */
public class AffiliatedCompanies extends javax.swing.JDialog {

    //class variables
    private int currentEmployeeID;
    DefaultListModel DLMCompany, DLMCategory;
    Connection con;
    Statement stmt;
    ResultSet rs;
    
    public AffiliatedCompanies(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }

    public AffiliatedCompanies(java.awt.Dialog parent, boolean modal, int employeeID) {
        super(parent, modal);
        initComponents();

        //initialize class variables
        this.currentEmployeeID = employeeID;
        DLMCompany = new DefaultListModel();
        DLMCategory = new DefaultListModel();
        listCompany.setModel(DLMCompany);
        listCategory.setModel(DLMCategory);
        
        //to update the list of Affiliated companies
        updateCompanyList();

        //set visible
        visible();
        System.out.println("Done");
    }

    private void visible(){
        this.setVisible(true);
    }
    
    private void updateCompanyList(){
        try { 
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Project1",
                    "username", "password");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("Select Distinct Company_Name from username.afilliated_companies");
            while(rs.next()){
                DLMCompany.addElement(rs.getString("Company_Name"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleBar = new javax.swing.JLabel();
        topBanner = new javax.swing.JLabel();
        companyLogoPic = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JLabel();
        labelCompanyName = new javax.swing.JLabel();
        textCompanyName = new javax.swing.JTextField();
        textProductName = new javax.swing.JTextField();
        labelProductName = new javax.swing.JLabel();
        labelProductCategory = new javax.swing.JLabel();
        textProductCategory = new javax.swing.JTextField();
        labelProductUnitWeight = new javax.swing.JLabel();
        textProductUnitWeight = new javax.swing.JTextField();
        textProductPrice1 = new javax.swing.JTextField();
        labelProductPrice1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProducts = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCompany = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listCategory = new javax.swing.JList<>();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1600, 900));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleBar.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 48)); // NOI18N
        titleBar.setForeground(new java.awt.Color(51, 51, 255));
        titleBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleBar.setText("Affiliated Companies");
        getContentPane().add(titleBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 1300, 225));

        topBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/basicBanner.jpg"))); // NOI18N
        getContentPane().add(topBanner, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 1300, 225));

        companyLogoPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/companyLogoPic.jpg"))); // NOI18N
        getContentPane().add(companyLogoPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 225));

        btnMainMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/btnBackToMainMenu.png"))); // NOI18N
        btnMainMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMainMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMainMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMainMenuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMainMenuMousePressed(evt);
            }
        });
        getContentPane().add(btnMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 730, 200, 100));

        labelCompanyName.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelCompanyName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCompanyName.setText("Company Name");
        getContentPane().add(labelCompanyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 277, 200, 40));

        textCompanyName.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        getContentPane().add(textCompanyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 277, 200, 40));

        textProductName.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        getContentPane().add(textProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 337, 200, 40));

        labelProductName.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelProductName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelProductName.setText("Product Name");
        getContentPane().add(labelProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 337, 200, 40));

        labelProductCategory.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelProductCategory.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelProductCategory.setText("Category");
        getContentPane().add(labelProductCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 397, 200, 40));

        textProductCategory.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        getContentPane().add(textProductCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 397, 200, 40));

        labelProductUnitWeight.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelProductUnitWeight.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelProductUnitWeight.setText("Unit Weight");
        getContentPane().add(labelProductUnitWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 457, 200, 40));

        textProductUnitWeight.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        getContentPane().add(textProductUnitWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 457, 200, 40));

        textProductPrice1.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        getContentPane().add(textProductPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 517, 200, 40));

        labelProductPrice1.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelProductPrice1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelProductPrice1.setText("Price per Unit");
        getContentPane().add(labelProductPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 517, 200, 40));

        tableProducts.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 20)); // NOI18N
        tableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableProducts.setColumnSelectionAllowed(true);
        tableProducts.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tableProducts);
        tableProducts.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 290, -1, 270));

        listCompany.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 20)); // NOI18N
        listCompany.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listCompany);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Company", jPanel1);

        listCategory.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 20)); // NOI18N
        listCategory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(listCategory);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Category", jPanel2);

        jTabbedPane1.setEnabledAt(1, false);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 270, 230, 290));

        btnAdd.setBackground(new java.awt.Color(153, 153, 255));
        btnAdd.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 30)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddMousePressed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 610, 225, 150));

        btnDelete.setBackground(new java.awt.Color(153, 153, 255));
        btnDelete.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 30)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteMousePressed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 610, 225, 150));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/Background/backgroundPic.PNG"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMainMenuMouseEntered
        //Set an image when mouse enters the jlabel area
        btnMainMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Menu Template/btnBackToMainMenuAfter.png")));
    }//GEN-LAST:event_btnMainMenuMouseEntered

    private void btnMainMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMainMenuMouseExited
        //set an image when mouse exits the jlabel area
        btnMainMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Menu Template/btnBackToMainMenu.png")));
    }//GEN-LAST:event_btnMainMenuMouseExited

    private void btnMainMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMainMenuMousePressed
        this.dispose();
    }//GEN-LAST:event_btnMainMenuMousePressed

    private void btnAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMousePressed

    }//GEN-LAST:event_btnAddMousePressed

    private void btnDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMousePressed

    }//GEN-LAST:event_btnDeleteMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AffiliatedCompanies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AffiliatedCompanies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AffiliatedCompanies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AffiliatedCompanies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AffiliatedCompanies dialog = new AffiliatedCompanies(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JLabel btnMainMenu;
    private javax.swing.JLabel companyLogoPic;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCompanyName;
    private javax.swing.JLabel labelProductCategory;
    private javax.swing.JLabel labelProductName;
    private javax.swing.JLabel labelProductPrice1;
    private javax.swing.JLabel labelProductUnitWeight;
    private javax.swing.JList<String> listCategory;
    private javax.swing.JList<String> listCompany;
    private javax.swing.JTable tableProducts;
    private javax.swing.JTextField textCompanyName;
    private javax.swing.JTextField textProductCategory;
    private javax.swing.JTextField textProductName;
    private javax.swing.JTextField textProductPrice1;
    private javax.swing.JTextField textProductUnitWeight;
    private javax.swing.JLabel titleBar;
    private javax.swing.JLabel topBanner;
    // End of variables declaration//GEN-END:variables
}
