
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew Hyun
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class Menu extends javax.swing.JDialog {

//declare variables
private String username, password;
private int height, width, id, employeeID;
private Dimension dim;
private boolean initialize = false;
private boolean admin, employeeExists;
public static boolean exit = false;
Connection con;
Statement stmt;
ResultSet rs;

    public Menu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Menu(java.awt.Frame parent, boolean modal, String username, 
            String password, int id) {
        super(parent, modal);
        //set size variables and dimensions
        dim = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        //store the rs.absolute id value
        this.id = id;
        //set exit and employeeExists to false when constructor called
        exit = false;
        employeeExists = false;
        initComponents();
        
        //get username and pass and store it in private variables
        this.username = username;
        this.password = password;
        
        //set up connection to the database
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Project1",
                "username", "password");           
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("Select * From Username.users");
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        
        //set initialize to true and call initFcn
        initialize = true;
        initFcn(id);
    }

    private void initFcn(int id){
        try{
            //find user in 'Username.users' table
            rs.absolute(id);
            employeeID = rs.getInt("id");
            admin = rs.getBoolean("admin");
            //Put user info on topleft
            displayUser.setText("Hello " + rs.getString("First_Name") + " " +
                rs.getString("Last_Name") + "!");
            rs.close();
            stmt.close();
            
            //check if new messages occurred
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("Select * from Username.Employees");
            while(rs.next()){
                if(rs.getInt("Employee_ID") == employeeID){
                    employeeExists = true;
                    break;
                }
                else{
                    employeeExists = false;
                }
            }
            
            if(employeeExists == false){
                rs.close();
                stmt.close();
                //open stmt and rs and create an entry for them
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery("Select * From Username.employees");
                
                //mistake is here/error is found here
                rs.moveToInsertRow();
                System.out.println(rs.getRow());
                rs.updateInt("Employee_ID", employeeID);
                rs.insertRow();
                rs.close();
                stmt.close();
                
                //re-open result set and stmt
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery("Select * From Username.users");
            }
            else{
                if(rs.getBoolean("New_Message_Promote") == true){
                    JOptionPane.showMessageDialog(this, "Congratulations! You were promoted!\nCheck your profile for the changes!");
                    rs.updateBoolean("New_Message_Promote", false);
                    rs.updateRow();
                }
                else if(rs.getBoolean("New_Message_Demote") == true){
                    JOptionPane.showMessageDialog(this, "You have been demoted! Please check your profile for the changes.");
                    rs.updateBoolean("New_Message_Demote", false);
                    rs.updateRow();
                }
                else if(rs.getBoolean("New_Message_Fired") == true){
                    JOptionPane.showMessageDialog(this, "Your account has been locked because upper management decided to let you go.");
                    rs.close();
                    stmt.close();
                    this.dispose();
                }
            }
                
            
        }catch(SQLException ex){
        JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        //disable administrator menu if user has no admin priveleges
        if(admin == false)
            adminMenu.setEnabled(false);
        //make drop down menus "invisible" until called upon
        accMenuChoice1.setVisible(false);
        accMenuChoice2.setVisible(false);
        accMenuChoice3.setVisible(false);
        orderMenuChoice1.setVisible(false);
        orderMenuChoice2.setVisible(false);
        billMenuChoice1.setVisible(false);
        billMenuChoice2.setVisible(false);
        adminMenuChoice1.setVisible(false);
        adminMenuChoice2.setVisible(false);
        
        //make dialog visible
        if (initialize = true)
            this.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExit = new javax.swing.JLabel();
        displayUser = new javax.swing.JLabel();
        adminMenuChoice2 = new javax.swing.JLabel();
        adminMenuChoice1 = new javax.swing.JLabel();
        adminMenu = new javax.swing.JLabel();
        billMenuChoice2 = new javax.swing.JLabel();
        billMenuChoice1 = new javax.swing.JLabel();
        billMenu = new javax.swing.JLabel();
        orderMenuChoice2 = new javax.swing.JLabel();
        orderMenuChoice1 = new javax.swing.JLabel();
        orderMenu = new javax.swing.JLabel();
        accMenuChoice3 = new javax.swing.JLabel();
        accMenuChoice2 = new javax.swing.JLabel();
        accMenuChoice1 = new javax.swing.JLabel();
        accMenu = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        companyLogoPic = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        topBanner = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/exitPic.PNG"))); // NOI18N
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExitMousePressed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 730, 200, 100));

        displayUser.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 25)); // NOI18N
        displayUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        displayUser.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(displayUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 0, 300, 40));

        adminMenuChoice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Admin Menu/adminMenuChoice2Pic.png"))); // NOI18N
        adminMenuChoice2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminMenuChoice2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminMenuChoice2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminMenuChoice2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                adminMenuChoice2MousePressed(evt);
            }
        });
        getContentPane().add(adminMenuChoice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1275, 260, -1, -1));

        adminMenuChoice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Admin Menu/adminMenuChoice1Pic.png"))); // NOI18N
        adminMenuChoice1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminMenuChoice1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminMenuChoice1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminMenuChoice1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                adminMenuChoice1MousePressed(evt);
            }
        });
        getContentPane().add(adminMenuChoice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1275, 180, -1, -1));

        adminMenu.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 20)); // NOI18N
        adminMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Admin Menu/adminMenuButton.png"))); // NOI18N
        adminMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        adminMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminMenuMouseExited(evt);
            }
        });
        getContentPane().add(adminMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1275, 80, -1, -1));

        billMenuChoice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Bill Menu/billMenuChoice2Pic.png"))); // NOI18N
        billMenuChoice2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        billMenuChoice2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                billMenuChoice2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                billMenuChoice2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                billMenuChoice2MousePressed(evt);
            }
        });
        getContentPane().add(billMenuChoice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 260, -1, -1));

        billMenuChoice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Bill Menu/billMenuChoice1Pic.png"))); // NOI18N
        billMenuChoice1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        billMenuChoice1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                billMenuChoice1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                billMenuChoice1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                billMenuChoice1MousePressed(evt);
            }
        });
        getContentPane().add(billMenuChoice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 180, -1, -1));

        billMenu.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 20)); // NOI18N
        billMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Bill Menu/billMenuButton.png"))); // NOI18N
        billMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        billMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                billMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                billMenuMouseExited(evt);
            }
        });
        getContentPane().add(billMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 80, -1, -1));

        orderMenuChoice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Order Menu/orderMenuChoice2Pic.png"))); // NOI18N
        orderMenuChoice2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        orderMenuChoice2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                orderMenuChoice2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                orderMenuChoice2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                orderMenuChoice2MousePressed(evt);
            }
        });
        getContentPane().add(orderMenuChoice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 260, -1, -1));

        orderMenuChoice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Order Menu/orderMenuChoice1Pic.png"))); // NOI18N
        orderMenuChoice1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        orderMenuChoice1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                orderMenuChoice1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                orderMenuChoice1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                orderMenuChoice1MousePressed(evt);
            }
        });
        getContentPane().add(orderMenuChoice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 180, -1, -1));

        orderMenu.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 20)); // NOI18N
        orderMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Order Menu/orderMenuButton.png"))); // NOI18N
        orderMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        orderMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                orderMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                orderMenuMouseExited(evt);
            }
        });
        getContentPane().add(orderMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 80, -1, -1));

        accMenuChoice3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Account Menu/accMenuChoice3Pic.png"))); // NOI18N
        accMenuChoice3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        accMenuChoice3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accMenuChoice3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accMenuChoice3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accMenuChoice3MousePressed(evt);
            }
        });
        getContentPane().add(accMenuChoice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, -1, -1));

        accMenuChoice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Account Menu/accMenuChoice2Pic.png"))); // NOI18N
        accMenuChoice2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        accMenuChoice2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accMenuChoice2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accMenuChoice2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accMenuChoice2MousePressed(evt);
            }
        });
        getContentPane().add(accMenuChoice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, -1, -1));

        accMenuChoice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Account Menu/accMenuChoice1Pic.png"))); // NOI18N
        accMenuChoice1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        accMenuChoice1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accMenuChoice1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accMenuChoice1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accMenuChoice1MousePressed(evt);
            }
        });
        getContentPane().add(accMenuChoice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, -1, -1));

        accMenu.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 20)); // NOI18N
        accMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Main Menu/Account Menu/accountMenuButton.png"))); // NOI18N
        accMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        accMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accMenuMouseExited(evt);
            }
        });
        getContentPane().add(accMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        searchText.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        getContentPane().add(searchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 1410, 40));

        btnSearch.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        btnSearch.setText("Search");
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 210, 100, 40));

        companyLogoPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/companyLogoPic.jpg"))); // NOI18N
        getContentPane().add(companyLogoPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 225));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 1560, 370));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 25)); // NOI18N
        jLabel1.setText("Your Order(s):");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 270, 40));

        topBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/basicBanner.jpg"))); // NOI18N
        getContentPane().add(topBanner, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 1300, 225));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/Background/backgroundPic.PNG"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 900));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //EXIT button when moused over, exited, and pressed
    private void btnExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMousePressed
        //create a confirmation box asking if user would like to logout or not
        logoutConfirmation a = new logoutConfirmation();
        while(true){
            //access the static variables in logoutConfirmation jDialog class
            //determine course of action based off of user's decision
            if(logoutConfirmation.decided == true && 
                    logoutConfirmation.logout == false){
                break;
            }
            else if(logoutConfirmation.decided == true &&
                    logoutConfirmation.logout == true){
                exit = true;
                Login.boolLogin = false;
                try{
                stmt.close();
                rs.close();
                }
                catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                this.dispose();
                break;
            }
        }
    }//GEN-LAST:event_btnExitMousePressed

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        //Set an image when mouse enters the jlabel area
        btnExit.setIcon(new ImageIcon(getClass().getResource("Picture/Menu Template/exitPicAfter.png")));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        //set an image when mouse exits the jlabel area
        btnExit.setIcon(new ImageIcon(getClass().getResource("Picture/Menu Template/exitPic.png")));
    }//GEN-LAST:event_btnExitMouseExited

    
    //main menu buttons when moused over and exited
    private void accMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuMouseEntered
        accMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accountMenuActiveButton.png")));
        accMenuChoice1.setVisible(true);
        accMenuChoice2.setVisible(true);
        accMenuChoice3.setVisible(true);
    }//GEN-LAST:event_accMenuMouseEntered

    private void accMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuMouseExited
        accMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accountMenuButton.png")));
        accMenuChoice1.setVisible(false);
        accMenuChoice2.setVisible(false);
        accMenuChoice3.setVisible(false);
    }//GEN-LAST:event_accMenuMouseExited

    private void orderMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderMenuMouseEntered
        orderMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuActiveButton.png")));
        orderMenuChoice1.setVisible(true);
        orderMenuChoice2.setVisible(true);
    }//GEN-LAST:event_orderMenuMouseEntered

    private void orderMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderMenuMouseExited
        orderMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuButton.png")));
        orderMenuChoice1.setVisible(false);
        orderMenuChoice2.setVisible(false);
    }//GEN-LAST:event_orderMenuMouseExited

    private void billMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMenuMouseEntered
        billMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuActiveButton.png")));
        billMenuChoice1.setVisible(true);
        billMenuChoice2.setVisible(true);
    }//GEN-LAST:event_billMenuMouseEntered

    private void billMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMenuMouseExited
        billMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuButton.png")));
        billMenuChoice1.setVisible(false);
        billMenuChoice2.setVisible(false);
    }//GEN-LAST:event_billMenuMouseExited

    private void adminMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMenuMouseEntered
        if(admin == true){
        adminMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuActiveButton.png")));
        adminMenuChoice1.setVisible(true);
        adminMenuChoice2.setVisible(true);
        }
    }//GEN-LAST:event_adminMenuMouseEntered

    private void adminMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMenuMouseExited
        if(admin == true){
        adminMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuButton.png")));
        adminMenuChoice1.setVisible(false);
        adminMenuChoice2.setVisible(false);
        }
    }//GEN-LAST:event_adminMenuMouseExited

    //drop down for ACCOUNT menu - moused over, exited, and pressed
    private void accMenuChoice1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuChoice1MouseEntered
        accMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accountMenuActiveButton.png")));
        accMenuChoice1.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accMenuChoice1-2Pic.png")));
        accMenuChoice1.setVisible(true);
        accMenuChoice2.setVisible(true);
        accMenuChoice3.setVisible(true);
    }//GEN-LAST:event_accMenuChoice1MouseEntered

    private void accMenuChoice1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuChoice1MouseExited
        accMenuChoice1.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accMenuChoice1Pic.png")));
        accMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accountMenuButton.png")));
        accMenuChoice1.setVisible(false);
        accMenuChoice2.setVisible(false);
        accMenuChoice3.setVisible(false);
    }//GEN-LAST:event_accMenuChoice1MouseExited

        //Choice1Function when pressed
    private void accMenuChoice1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuChoice1MousePressed
    try {
        //Close rs and stmt so we can create a new one with new target inside Info dialog
        rs.close();
        stmt.close();
        Info info = new Info(this, true, employeeID);
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("Select * From Username.users");
        rs.absolute(id);
    }catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
        
    }//GEN-LAST:event_accMenuChoice1MousePressed
        
    private void accMenuChoice2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuChoice2MouseEntered
        accMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accountMenuActiveButton.png")));
        accMenuChoice2.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accMenuChoice2-2Pic.png")));
        accMenuChoice1.setVisible(true);
        accMenuChoice2.setVisible(true);
        accMenuChoice3.setVisible(true);
    }//GEN-LAST:event_accMenuChoice2MouseEntered

    private void accMenuChoice2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuChoice2MouseExited
        accMenuChoice2.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accMenuChoice2Pic.png")));
        accMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accountMenuButton.png")));
        accMenuChoice1.setVisible(false);
        accMenuChoice2.setVisible(false);
        accMenuChoice3.setVisible(false);
    }//GEN-LAST:event_accMenuChoice2MouseExited

        //Choice2Function when pressed
    private void accMenuChoice2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuChoice2MousePressed
        changePassword cPass = new changePassword(this, true, con, stmt, rs);
    }//GEN-LAST:event_accMenuChoice2MousePressed

    private void accMenuChoice3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuChoice3MouseEntered
        accMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accountMenuActiveButton.png")));
        accMenuChoice3.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accMenuChoice3-2Pic.png")));   
        accMenuChoice1.setVisible(true);
        accMenuChoice2.setVisible(true);
        accMenuChoice3.setVisible(true);
    }//GEN-LAST:event_accMenuChoice3MouseEntered

    private void accMenuChoice3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuChoice3MouseExited
        accMenuChoice3.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accMenuChoice3Pic.png")));
        accMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Account Menu/accountMenuButton.png")));
        accMenuChoice1.setVisible(false);
        accMenuChoice2.setVisible(false);
        accMenuChoice3.setVisible(false);
    }//GEN-LAST:event_accMenuChoice3MouseExited

        //Choice3Function when pressed
    private void accMenuChoice3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMenuChoice3MousePressed
        //create an instance of terminate dialog
        try{
            rs.close();
            stmt.close();
        
            Terminate terminate = new Terminate(this, true, id);
        
            if(Terminate.terminateAcc == true && Terminate.passwordMatch == true){
                //re-establish connection to table
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery("Select * from Username.users");
                rs.absolute(id);
                
                //delete the current row
                rs.deleteRow();
            
                //close to update the table
                stmt.close();
                rs.close();
                
                //set exit to true
                exit = true;
                
                //dispose of dialog
                this.dispose();
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_accMenuChoice3MousePressed

    //drop down for ORDER menu - moused over, exited, and pressed
    private void orderMenuChoice1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderMenuChoice1MouseEntered
        orderMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuActiveButton.png")));
        orderMenuChoice1.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuChoice1-2Pic.png")));
        orderMenuChoice1.setVisible(true);
        orderMenuChoice2.setVisible(true);
    }//GEN-LAST:event_orderMenuChoice1MouseEntered

    private void orderMenuChoice1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderMenuChoice1MouseExited
        orderMenuChoice1.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuChoice1Pic.png")));
        orderMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuButton.png")));
        orderMenuChoice1.setVisible(false);
        orderMenuChoice2.setVisible(false);
    }//GEN-LAST:event_orderMenuChoice1MouseExited

        //Choice1Function when pressed
    private void orderMenuChoice1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderMenuChoice1MousePressed
        System.out.println("Order Menu Choice 1 clicked");
        /*
        try {
        //Close rs and stmt so we can create a new one with new target inside Order dialog
        rs.close();
        stmt.close();
        addOrder order = new addOrder(this, true, employeeID);
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("Select * From Username.users");
        rs.absolute(id);
    }catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }*/
    }//GEN-LAST:event_orderMenuChoice1MousePressed

    private void orderMenuChoice2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderMenuChoice2MouseEntered
        orderMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuActiveButton.png")));
        orderMenuChoice2.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuChoice2-2Pic.png")));
        orderMenuChoice1.setVisible(true);
        orderMenuChoice2.setVisible(true);
    }//GEN-LAST:event_orderMenuChoice2MouseEntered

    private void orderMenuChoice2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderMenuChoice2MouseExited
        orderMenuChoice2.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuChoice2Pic.png")));
        orderMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Order Menu/orderMenuButton.png")));
        orderMenuChoice1.setVisible(false);
        orderMenuChoice2.setVisible(false);
    }//GEN-LAST:event_orderMenuChoice2MouseExited

        //Choice2Function when pressed
    private void orderMenuChoice2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderMenuChoice2MousePressed
        System.out.println("orderedclicked2");
    }//GEN-LAST:event_orderMenuChoice2MousePressed

    //drop down for BILL menu - moused over, exited, and pressed
    private void billMenuChoice1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMenuChoice1MouseEntered
        billMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuActiveButton.png")));
        billMenuChoice1.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuChoice1-2Pic.png")));
        billMenuChoice1.setVisible(true);
        billMenuChoice2.setVisible(true);
    }//GEN-LAST:event_billMenuChoice1MouseEntered

    private void billMenuChoice1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMenuChoice1MouseExited
        billMenuChoice1.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuChoice1Pic.png")));
        billMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuButton.png")));
        billMenuChoice1.setVisible(false);
        billMenuChoice2.setVisible(false);
    }//GEN-LAST:event_billMenuChoice1MouseExited

        //Choice1Function when pressed
    private void billMenuChoice1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMenuChoice1MousePressed
        System.out.println("billclicked1");
    }//GEN-LAST:event_billMenuChoice1MousePressed

    private void billMenuChoice2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMenuChoice2MouseEntered
        billMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuActiveButton.png")));
        billMenuChoice2.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuChoice2-2Pic.png")));
        billMenuChoice1.setVisible(true);
        billMenuChoice2.setVisible(true);
    }//GEN-LAST:event_billMenuChoice2MouseEntered

    private void billMenuChoice2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMenuChoice2MouseExited
        billMenuChoice2.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuChoice2Pic.png")));
        billMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Bill Menu/billMenuButton.png")));
        billMenuChoice1.setVisible(false);
        billMenuChoice2.setVisible(false);
    }//GEN-LAST:event_billMenuChoice2MouseExited

        //Choice2Function when pressed
    private void billMenuChoice2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMenuChoice2MousePressed
        System.out.println("billclicked2");
    }//GEN-LAST:event_billMenuChoice2MousePressed

    //drop down for ADMIN menu - moused over, exited, and pressed
    private void adminMenuChoice1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMenuChoice1MouseEntered
        if(admin == true){
        adminMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuActiveButton.png")));
        adminMenuChoice1.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuChoice1-2Pic.png")));
        adminMenuChoice1.setVisible(true);
        adminMenuChoice2.setVisible(true);
        }
    }//GEN-LAST:event_adminMenuChoice1MouseEntered

    private void adminMenuChoice1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMenuChoice1MouseExited
        if(admin == true){
        adminMenuChoice1.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuChoice1Pic.png")));
        adminMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuButton.png")));
        adminMenuChoice1.setVisible(false);
        adminMenuChoice2.setVisible(false);
        }
    }//GEN-LAST:event_adminMenuChoice1MouseExited

        //Choice1Function when pressed
    private void adminMenuChoice1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMenuChoice1MousePressed
        if(admin == true){
            try{
                rs.close();
                stmt.close();
                
                Employees e = new Employees(this, true, employeeID);
                
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery("Select * From Username.users");
                rs.absolute(id);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_adminMenuChoice1MousePressed

    private void adminMenuChoice2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMenuChoice2MouseEntered
        if(admin == true){
        adminMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuActiveButton.png")));
        adminMenuChoice2.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuChoice2-2Pic.png")));
        adminMenuChoice1.setVisible(true);
        adminMenuChoice2.setVisible(true);
        }
    }//GEN-LAST:event_adminMenuChoice2MouseEntered

    private void adminMenuChoice2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMenuChoice2MouseExited
        if(admin == true){
        adminMenuChoice2.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuChoice2Pic.png")));
        adminMenu.setIcon(new ImageIcon(getClass().getResource("Picture/Main Menu/Admin Menu/adminMenuButton.png")));
        adminMenuChoice1.setVisible(false);
        adminMenuChoice2.setVisible(false);
        }
    }//GEN-LAST:event_adminMenuChoice2MouseExited

        //Choice2Function when pressed
    private void adminMenuChoice2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMenuChoice2MousePressed
        if(admin == true){
            try{
                    //close rs and stmt 
                    rs.close();
                    stmt.close();
                    
                    AffiliatedCompanies affComp = new AffiliatedCompanies(this, true, employeeID);

                    //reopen rs and stmt
                    stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
                    rs = stmt.executeQuery("Select * From Username.users");
                    rs.absolute(id);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
        }
    }//GEN-LAST:event_adminMenuChoice2MousePressed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menu dialog = new Menu(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel accMenu;
    private javax.swing.JLabel accMenuChoice1;
    private javax.swing.JLabel accMenuChoice2;
    private javax.swing.JLabel accMenuChoice3;
    private javax.swing.JLabel adminMenu;
    private javax.swing.JLabel adminMenuChoice1;
    private javax.swing.JLabel adminMenuChoice2;
    private javax.swing.JLabel background;
    private javax.swing.JLabel billMenu;
    private javax.swing.JLabel billMenuChoice1;
    private javax.swing.JLabel billMenuChoice2;
    private javax.swing.JLabel btnExit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel companyLogoPic;
    private javax.swing.JLabel displayUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel orderMenu;
    private javax.swing.JLabel orderMenuChoice1;
    private javax.swing.JLabel orderMenuChoice2;
    private javax.swing.JTextField searchText;
    private javax.swing.JLabel topBanner;
    // End of variables declaration//GEN-END:variables
}
