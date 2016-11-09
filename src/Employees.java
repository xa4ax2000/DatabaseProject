
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew Hyun
 */
public class Employees extends javax.swing.JDialog {
    //state variables
    Matcher matcher;
    Connection con;
    Statement stmt;
    ResultSet rs;
    DefaultListModel DLM;
    private int currentEmployeeID, selectedID;
    private boolean employeeRecord;
    
    public Employees(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    //Constructor this dialog uses
    public Employees(java.awt.Dialog parent, boolean modal, int id){
        super(parent, modal);
        initComponents();
        //initialize variables
        this.currentEmployeeID = id;
        employeeRecord = false;
        //set-up list
        DLM = new DefaultListModel();
        employeeNames.setModel(DLM);
        //update list
        updateList();
        //add document listener to textSelected [bound to list] to identify selectedID
        textSelected.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                btnFire.setEnabled(true);
                btnPromote.setEnabled(true);
                matcher = Pattern.compile("\\d+").matcher(textSelected.getText());
                matcher.find();
                selectedID = Integer.valueOf(matcher.group());
            try{
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/Project1",
                        "username", "password");
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery("Select * from Username.users");
                while(rs.next()){
                    if(selectedID == rs.getInt("ID"))
                        break;
                }
                textEmail.setText(rs.getString("Email_Address"));
                
                rs.close();
                stmt.close();
                
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery("Select * from Username.employees");
                
                while(rs.next()){
                    if(selectedID == rs.getInt("Employee_ID")){
                        employeeRecord = true;
                        break;
                    }
                    else
                        employeeRecord = false;
                }
                
                if(employeeRecord == false){
                    rs.moveToInsertRow();
                    rs.updateInt("Employee_ID", selectedID);
                    rs.insertRow();
                    
                    rs.close();
                    stmt.close();
                    
                    stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
                    rs = stmt.executeQuery("Select * from Username.employees");
                    while(rs.next()){
                        if(selectedID == rs.getInt("Employee_ID")){
                            employeeRecord = true;
                            break;
                        }
                    }
                }
                    
                
                if(rs.getString("Job_Type") != null)
                    textJob.setText(rs.getString("Job_Type"));
                else
                    textJob.setText("Not Set");
                if(rs.getString("Salary") != null)
                    textSalary.setText("$" + rs.getDouble("Salary"));
                else
                    textSalary.setText("Not Set");
                if(rs.getString("Gender") != null)
                    textGender.setText(rs.getString("Gender"));
                else
                    textGender.setText("Not Set");
                if(rs.getString("Date_Of_Birth_Month") != null &&
                        rs.getString("Date_Of_Birth_Day") != null &&
                        rs.getString("Date_Of_Birth_Year") != null)
                    textDOB.setText(rs.getInt("Date_Of_Birth_Month")+"/"+
                            rs.getInt("Date_Of_Birth_Day")+"/"+rs.getInt("Date_Of_Birth_Year"));
                else
                    textDOB.setText("Not Set");
                
                rs.close();
                stmt.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        //If selected text is empty (something not selected) then enabled = false for buttons
        if(textSelected.getText().isEmpty()){
            btnFire.setEnabled(false);
            btnPromote.setEnabled(false);
        }
        
        //set visible
        visible();
        
    }
    
    private void visible(){
        this.setVisible(true);
    }
    
    private void updateList(){
        try { 
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Project1",
                    "username", "password");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("Select * from username.users, username.employees "
                    + "where username.employees.employee_ID = username.users.id");
            while(rs.next()){
                if(currentEmployeeID != rs.getInt("id")){
                    if(rs.getBoolean("New_Message_Fired") == false)
                        DLM.addElement(rs.getInt("id") + ": " + rs.getString("First_Name") + " " + rs.getString("Last_Name"));
                }
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        titleBar = new javax.swing.JLabel();
        companyLogoPic = new javax.swing.JLabel();
        topBanner = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeNames = new javax.swing.JList<>();
        labelDOB = new javax.swing.JLabel();
        textDOB = new javax.swing.JTextField();
        labelGender = new javax.swing.JLabel();
        textGender = new javax.swing.JTextField();
        labelSalary = new javax.swing.JLabel();
        textSalary = new javax.swing.JTextField();
        labelJob = new javax.swing.JLabel();
        textJob = new javax.swing.JTextField();
        textEmail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        labelSelectedEmployee = new javax.swing.JLabel();
        textSelected = new javax.swing.JTextField();
        btnFire = new javax.swing.JButton();
        btnPromote = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleBar.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 48)); // NOI18N
        titleBar.setForeground(new java.awt.Color(51, 51, 255));
        titleBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleBar.setText("Employee List");
        getContentPane().add(titleBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 1300, 225));

        companyLogoPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/companyLogoPic.jpg"))); // NOI18N
        getContentPane().add(companyLogoPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 225));

        topBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/basicBanner.jpg"))); // NOI18N
        getContentPane().add(topBanner, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 1300, 225));

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

        employeeNames.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 20)); // NOI18N
        employeeNames.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(employeeNames);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 280, 260));

        labelDOB.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelDOB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDOB.setText("Date of Birth");
        getContentPane().add(labelDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 530, 200, 40));

        textDOB.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textDOB.setEnabled(false);
        getContentPane().add(textDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 530, 200, 40));

        labelGender.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelGender.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelGender.setText("Gender");
        getContentPane().add(labelGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 200, 40));

        textGender.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textGender.setEnabled(false);
        getContentPane().add(textGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 480, 200, 40));

        labelSalary.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelSalary.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSalary.setText("Salary");
        getContentPane().add(labelSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 430, 200, 40));

        textSalary.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textSalary.setEnabled(false);
        getContentPane().add(textSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 430, 200, 40));

        labelJob.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelJob.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelJob.setText("Job Type");
        getContentPane().add(labelJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 200, 40));

        textJob.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textJob.setEnabled(false);
        getContentPane().add(textJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 380, 200, 40));

        textEmail.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textEmail.setEnabled(false);
        getContentPane().add(textEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 330, 200, 40));

        labelEmail.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelEmail.setText("Email Address");
        getContentPane().add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, 200, 40));

        labelSelectedEmployee.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelSelectedEmployee.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSelectedEmployee.setText("Employee Selected");
        getContentPane().add(labelSelectedEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, 200, 40));

        textSelected.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textSelected.setEnabled(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, employeeNames, org.jdesktop.beansbinding.ELProperty.create("${selectedElement}"), textSelected, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        getContentPane().add(textSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 280, 200, 40));

        btnFire.setBackground(new java.awt.Color(153, 153, 255));
        btnFire.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 30)); // NOI18N
        btnFire.setText("FIRE!");
        btnFire.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFireMousePressed(evt);
            }
        });
        getContentPane().add(btnFire, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 610, 225, 150));

        btnPromote.setBackground(new java.awt.Color(153, 153, 255));
        btnPromote.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 30)); // NOI18N
        btnPromote.setText("Promote/Demote");
        btnPromote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPromoteMousePressed(evt);
            }
        });
        getContentPane().add(btnPromote, new org.netbeans.lib.awtextra.AbsoluteConstraints(1045, 610, 225, 150));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/Background/backgroundPic.PNG"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 900));

        bindingGroup.bind();

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

    private void btnPromoteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPromoteMousePressed
        if(btnPromote.isEnabled()){
            Promote_Demote promo = new Promote_Demote(this, true, selectedID);
            if(employeeNames.getSelectedIndex() != employeeNames.getLastVisibleIndex()){
                employeeNames.setSelectedIndex(employeeNames.getSelectedIndex()+1);
                employeeNames.setSelectedIndex(employeeNames.getSelectedIndex()-1);   
            }
            else{
                employeeNames.setSelectedIndex(employeeNames.getSelectedIndex()-1);
                employeeNames.setSelectedIndex(employeeNames.getSelectedIndex()+1);   
            }
        }
    }//GEN-LAST:event_btnPromoteMousePressed

    private void btnFireMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFireMousePressed
        if(btnFire.isEnabled()){
            Fire_Employee fEmp = new Fire_Employee(this, true, selectedID);
            
            if(employeeNames.getSelectedIndex() != employeeNames.getLastVisibleIndex()){
                employeeNames.setSelectedIndex(employeeNames.getSelectedIndex()+1);
                employeeNames.setSelectedIndex(employeeNames.getSelectedIndex()-1);   
            }
            else{
                employeeNames.setSelectedIndex(employeeNames.getSelectedIndex()-1);
                employeeNames.setSelectedIndex(employeeNames.getSelectedIndex()+1);   
            }
            
            if(Fire_Employee.firebtnpress == true)
                JOptionPane.showMessageDialog(this, "Employee has been Fired!");
        }
    }//GEN-LAST:event_btnFireMousePressed

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
            java.util.logging.Logger.getLogger(Employees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Employees dialog = new Employees(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnFire;
    private javax.swing.JLabel btnMainMenu;
    private javax.swing.JButton btnPromote;
    private javax.swing.JLabel companyLogoPic;
    private javax.swing.JList<String> employeeNames;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDOB;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelGender;
    private javax.swing.JLabel labelJob;
    private javax.swing.JLabel labelSalary;
    private javax.swing.JLabel labelSelectedEmployee;
    private javax.swing.JTextField textDOB;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textGender;
    private javax.swing.JTextField textJob;
    private javax.swing.JTextField textSalary;
    private javax.swing.JTextField textSelected;
    private javax.swing.JLabel titleBar;
    private javax.swing.JLabel topBanner;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
