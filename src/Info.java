
import javax.swing.ImageIcon;
import java.sql.*;
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
public class Info extends javax.swing.JDialog {


    //State variables
    private boolean infoExists = false;
    private int id;
    private String month, day, year;
    Connection con;
    Statement stmt;
    ResultSet rs;
    
    public Info(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    //Constructor used in this dialog
    public Info(java.awt.Dialog parent, boolean modal, int id){
        super(parent, modal);
        initComponents();
        btnOK.setVisible(false);
        btnCancel.setVisible(false);
        
        //initialize variable everytime it's opened
        this.id = id;
        infoExists = false;
        
        //setup connection to database
        try {   
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Project1",
                    "username", "password");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT * FROM username.employees");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }

        
        //after everythign has been initialized run this method
        initFcn();
    }
    
    private void initFcn(){
        //Search through Username.Employees to see if this employee exists
        //If not, create an entry
        try{
            while(rs.next()){
                if(rs.getInt("Employee_ID") == id){
                    rs.close();
                    stmt.close();
                    infoExists = true;
                    //if it exists, run the method to select/join two tables
                    InfoQuery();
                    break;
                }
                else
                    infoExists = false;
            }
            if(infoExists == false){
                createUserEntry();
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        //make componenets visible (make ok and cancel buttons not visible)
        this.setVisible(true);
    }
    
    private void createUserEntry(){
        //Insert user into the table/row
        try{
            rs.moveToInsertRow();
            rs.updateInt("Employee_ID", id);
            rs.insertRow();
            
            rs.close();
            stmt.close();
            
            //now that it exists, run this method to join two tables
            InfoQuery();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    private void InfoQuery(){
        try{
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT * FROM Username.Users, "
                    + "Username.Employees WHERE Username.Users.ID = "
                    + "Username.Employees.Employee_ID");
            while(rs.next()){
                if(rs.getInt("Employee_ID") == id){
                    DisplayInfo();
                    break;
                }
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    private void DisplayInfo(){
        //Display the info in the textFields
        try{
            //Information that was already created will be displayed in the text fields
            textFirstName.setText(rs.getString("First_Name"));
            textLastName.setText(rs.getString("Last_Name"));
            textEmail.setText(rs.getString("Email_Address"));
            if(rs.getBoolean("admin") == true)
                textAdminAccess.setText("True");
            else
                textAdminAccess.setText("False");
            //New Information that may or may not exist will be displayed
            if(rs.getString("Job_Type") != null)
                textJob.setText(rs.getString("Job_Type"));
            else
                textJob.setText("No record in File. Please Update.");
            if(rs.getString("Date_Of_Birth_Month") != null &&
                    rs.getString("Date_Of_Birth_Day") != null &&
                    rs.getString("Date_Of_Birth_Year") != null)
                textDOB.setText(rs.getString("Date_Of_Birth_Month") + "/" +
                        rs.getString("Date_Of_Birth_Day") + "/" + 
                        rs.getString("Date_Of_Birth_Year"));
            else
                textDOB.setText("No record in File. Please Update.");
            if(rs.getString("Salary") != null)
                textSalary.setText("$" + String.valueOf(rs.getInt("Salary")));
            else
                textSalary.setText("No record in File. Please Update.");
            if(rs.getString("Gender") != null)
                textGender.setText(rs.getString("Gender"));
            else
                textGender.setText("No record in File. Please Update.");

        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
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

        companyLogoPic = new javax.swing.JLabel();
        titleBar = new javax.swing.JLabel();
        topBanner = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JLabel();
        labelFirstName = new javax.swing.JLabel();
        textFirstName = new javax.swing.JTextField();
        labelLastName = new javax.swing.JLabel();
        textLastName = new javax.swing.JTextField();
        labelSalary = new javax.swing.JLabel();
        textSalary = new javax.swing.JTextField();
        labelGender = new javax.swing.JLabel();
        textGender = new javax.swing.JTextField();
        labelJob = new javax.swing.JLabel();
        textJob = new javax.swing.JTextField();
        labelDOB = new javax.swing.JLabel();
        textDOB = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        textEmail = new javax.swing.JTextField();
        labelAdminAccess = new javax.swing.JLabel();
        textAdminAccess = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        companyLogoPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/companyLogoPic.jpg"))); // NOI18N
        getContentPane().add(companyLogoPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 225));

        titleBar.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 48)); // NOI18N
        titleBar.setForeground(new java.awt.Color(51, 51, 255));
        titleBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleBar.setText("Account Information");
        getContentPane().add(titleBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 1300, 225));

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

        labelFirstName.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelFirstName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelFirstName.setText("First Name");
        getContentPane().add(labelFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 277, 200, 40));

        textFirstName.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textFirstName.setEnabled(false);
        getContentPane().add(textFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 277, 200, 40));

        labelLastName.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelLastName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelLastName.setText("Last Name");
        getContentPane().add(labelLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 337, 200, 40));

        textLastName.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textLastName.setEnabled(false);
        getContentPane().add(textLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 337, 200, 40));

        labelSalary.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelSalary.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSalary.setText("Salary");
        getContentPane().add(labelSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 277, 200, 40));

        textSalary.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textSalary.setEnabled(false);
        textSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textSalaryMousePressed(evt);
            }
        });
        getContentPane().add(textSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 277, 200, 40));

        labelGender.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelGender.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelGender.setText("Gender");
        getContentPane().add(labelGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 337, 200, 40));

        textGender.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textGender.setEnabled(false);
        textGender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textGenderMousePressed(evt);
            }
        });
        getContentPane().add(textGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 337, 200, 40));

        labelJob.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelJob.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelJob.setText("Job Type");
        getContentPane().add(labelJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 397, 200, 40));

        textJob.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textJob.setEnabled(false);
        textJob.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textJobMousePressed(evt);
            }
        });
        getContentPane().add(textJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 397, 200, 40));

        labelDOB.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelDOB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDOB.setText("Date Of Birth");
        getContentPane().add(labelDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 457, 200, 40));

        textDOB.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textDOB.setEnabled(false);
        textDOB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textDOBMousePressed(evt);
            }
        });
        getContentPane().add(textDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 457, 200, 40));

        labelEmail.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelEmail.setText("E-mail Address");
        getContentPane().add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 397, 200, 40));

        textEmail.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 15)); // NOI18N
        textEmail.setEnabled(false);
        getContentPane().add(textEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 397, 200, 40));

        labelAdminAccess.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        labelAdminAccess.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelAdminAccess.setText("Administrative Access");
        getContentPane().add(labelAdminAccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 457, 200, 40));

        textAdminAccess.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 25)); // NOI18N
        textAdminAccess.setEnabled(false);
        getContentPane().add(textAdminAccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 457, 200, 40));

        btnCancel.setBackground(new java.awt.Color(153, 153, 255));
        btnCancel.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 30)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelMousePressed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 600, 160, 110));

        btnOK.setBackground(new java.awt.Color(153, 153, 255));
        btnOK.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 30)); // NOI18N
        btnOK.setText("OK");
        btnOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnOKMousePressed(evt);
            }
        });
        getContentPane().add(btnOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 600, 160, 110));

        btnUpdate.setBackground(new java.awt.Color(153, 153, 255));
        btnUpdate.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 30)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUpdateMousePressed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 600, 160, 110));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Menu Template/Background/backgroundPic.PNG"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 900));

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
        try {
            //When button is pressed, dispose of this instance and set goToMain to true
            rs.close();
            stmt.close();
            this.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnMainMenuMousePressed

    private void btnUpdateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMousePressed
        //if update button is pressed, close ResultSet and Statement
        try{
        rs.close();
        stmt.close();
        }catch(SQLException ex){
        JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
        //if user pushes "Update" make text enabled and change "null" text fields to directions
        textFirstName.setEnabled(true);
        textLastName.setEnabled(true);
        textEmail.setEnabled(true);
        textSalary.setEnabled(true);
        if(textSalary.getText().equals("No record in File. Please Update."))
            textSalary.setText("Enter a decimal value of your salary.");
        else if(textSalary.getText().contains("$")){
            String salary = textSalary.getText().startsWith("$") ? 
                    textSalary.getText().substring(1) : textSalary.getText();
            textSalary.setText(salary);
        }
        textGender.setEnabled(true);
        if(textGender.getText().equals("No record in File. Please Update."))
            textGender.setText("Enter your gender: 'Female' or 'Male'");
        textJob.setEnabled(true);
        if(textJob.getText().equals("No record in File. Please Update."))
            textJob.setText("Enter the title of your job.");
        textDOB.setEnabled(true);
        if(textDOB.getText().equals("No record in File. Please Update."))
            textDOB.setText("Enter your date of birth: 'mm/dd/yyyy'");
        
        //set update button + main menu button as invis and ok + cancel button as visible
        btnUpdate.setVisible(false);
        btnOK.setVisible(true);
        btnCancel.setVisible(true);
        btnMainMenu.setVisible(false);
    }//GEN-LAST:event_btnUpdateMousePressed

    private void btnOKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOKMousePressed
        //error variables
        boolean errorSalary = false;
        boolean errorDate = false;
        boolean errorGender = false;
        try{
            //update username.users table
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("Select * From Username.users");
            while(rs.next()){
                if(rs.getInt("id") == id)
                    break;
            }
            //First Name
            rs.updateString("First_Name", textFirstName.getText());
            //Last Name
            rs.updateString("Last_Name", textLastName.getText());
            //Email Address
            rs.updateString("Email_Address", textEmail.getText());
            //update row
            rs.updateRow();
            rs.close();
            
            //update username.employees table
            rs = stmt.executeQuery("Select * From Username.Employees");
            while(rs.next()){
                if(rs.getInt("Employee_ID") == id)
                    break;
            }
            //salary
            if(textSalary.getText().isEmpty() || textSalary.getText().matches("[a-zA-Z]+") == true){
                JOptionPane.showMessageDialog(this, "Salary is either not filled or a numerical value was not entered.");
                errorSalary = true;
            }
            else if(textSalary.getText().startsWith("$")){
                rs.updateDouble("Salary", Double.parseDouble(textSalary.getText().substring(1)));
                errorSalary = false;
            }
            else{
                errorSalary = false;
                rs.updateDouble("Salary", Double.parseDouble(textSalary.getText()));
                rs.updateRow();
            }
            //Job Type
            rs.updateString("Job_Type", textJob.getText());
            rs.updateRow();
            //Date
            String[] date = textDOB.getText().split("/");
            month = date[0];
            day = date[1];
            year = date[2];
            //check if month, day, and year match criteria
            //update Date
            if(month.matches("[0-9]+")){
                if(Integer.parseInt(month) < 13 && Integer.parseInt(month) > 0)
                    rs.updateInt("Date_Of_Birth_Month", Integer.parseInt(month));
                else{
                    JOptionPane.showMessageDialog(this, "The month should be within 1-12");
                    errorDate = true;
                }
                if(day.matches("[0-9]+")){
                    if(Integer.parseInt(day) < 32 && Integer.parseInt(day) > 0)
                        rs.updateInt("Date_Of_Birth_Day", Integer.parseInt(day));
                    else{
                        JOptionPane.showMessageDialog(this, "The day should be within 1-31");
                        errorDate = true;
                    }
                    if(year.matches("[0-9]+")){
                        if(Integer.parseInt(year) < 3000 && Integer.parseInt(year) > 1800){
                            rs.updateInt("Date_Of_Birth_Year", Integer.parseInt(year));
                            rs.updateRow();
                            errorDate = false;
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "The year should be in the format 'yyyy'.");
                            errorDate = true;
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "The year you entered was " + year + ". Please only enter numerical values.");
                        errorDate = true;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "The day you entered was " + day + ". Please only enter numerical values.");
                    errorDate = true;
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "The month you entered was " + month + ". Please only enter numerical values.");
                errorDate = true;
            }
            //Update Gender
            if(textGender.getText().equals("Female") || textGender.getText().equals("female")){
                rs.updateString("Gender", "Female");
                rs.updateRow();
                errorGender = false;
            }
            else if(textGender.getText().equals("Male") || textGender.getText().equals("male")){
                rs.updateString("Gender", "Male");
                rs.updateRow();
                errorGender = false;
            }
            else{
                JOptionPane.showMessageDialog(this, "Please input either 'Male' or 'Female'.");
                errorGender = true;
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
        if(errorSalary == false && errorGender == false && errorDate == false){
        // if user pushes "OK" apply changes and then revert everything back to previous conditions
        textFirstName.setEnabled(false);
        textLastName.setEnabled(false);
        textEmail.setEnabled(false);
        textSalary.setEnabled(false);
        textGender.setEnabled(false);
        textJob.setEnabled(false);
        textDOB.setEnabled(false);
        
        //set update button + main menu button as invis and ok + cancel button as visible
        btnUpdate.setVisible(true);
        btnOK.setVisible(false);
        btnCancel.setVisible(false);
        btnMainMenu.setVisible(true);
        
        InfoQuery();
        DisplayInfo();
        }
    }//GEN-LAST:event_btnOKMousePressed

    private void btnCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMousePressed
        //If cancel button is pushed, re-open ResultSet and Statement
        InfoQuery();
        try {
            // if user pushes "Cancel" revert everything from when update was pressed  FOR FUTURE NOTE: COULD PROBABLY HAVE CALLED DisplayInfo() method.
            textFirstName.setEnabled(false);
            textFirstName.setText(rs.getString("First_Name"));
            textLastName.setEnabled(false);
            textLastName.setText(rs.getString("Last_Name"));
            textEmail.setEnabled(false);
            textEmail.setText(rs.getString("Email_Address"));
            textSalary.setEnabled(false);
            if(rs.getInt("Salary") != 0)
                textSalary.setText(String.valueOf(rs.getInt("Salary")));
            else
                textSalary.setText("No record in File. Please Update.");
            textGender.setEnabled(false);
            if(rs.getString("Gender") != null)
                textGender.setText(rs.getString("Gender"));
            else
                textGender.setText("No record in File. Please Update.");
            textJob.setEnabled(false);
            if(rs.getString("Job_Type") != null)
                textJob.setText(rs.getString("Job_Type"));
            else
                textJob.setText("No record in File. Please Update.");
            textDOB.setEnabled(false);
            if(rs.getString("Date_Of_Birth_Month") != null && 
                    rs.getString("Date_Of_Birth_Day") != null &&
                    rs.getString("Date_Of_Birth_Year") != null)
                textDOB.setText(rs.getInt("Date_Of_Birth_Month") + "/" +
                        rs.getInt("Date_Of_Birth_Day") + "/" + 
                        rs.getInt("Date_Of_Birth_Year"));
            else
                textDOB.setText("No record in File. Please Update.");
            
            //set update button + main menu button as invis and ok + cancel button as visible
            btnUpdate.setVisible(true);
            btnOK.setVisible(false);
            btnCancel.setVisible(false);
            btnMainMenu.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnCancelMousePressed

    private void textSalaryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textSalaryMousePressed
        //Erase pre-written message when clicked
        if(textSalary.getText().equals("Enter a decimal value of your salary."))
            textSalary.setText("");
    }//GEN-LAST:event_textSalaryMousePressed

    private void textGenderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textGenderMousePressed
        //Erase pre-written message when clicked
        if(textGender.getText().equals("Enter your gender: 'Female' or 'Male'"))
            textGender.setText("");
    }//GEN-LAST:event_textGenderMousePressed

    private void textJobMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textJobMousePressed
        //Erase pre-written message when clicked
        if(textJob.getText().equals("Enter the title of your job."))
            textJob.setText("");
    }//GEN-LAST:event_textJobMousePressed

    private void textDOBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textDOBMousePressed
        //Erase pre-written message when clicked
        if(textDOB.getText().equals("Enter your date of birth: 'mm/dd/yyyy'"))
            textDOB.setText("");
    }//GEN-LAST:event_textDOBMousePressed

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
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Info dialog = new Info(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel btnMainMenu;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel companyLogoPic;
    private javax.swing.JLabel labelAdminAccess;
    private javax.swing.JLabel labelDOB;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelFirstName;
    private javax.swing.JLabel labelGender;
    private javax.swing.JLabel labelJob;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel labelSalary;
    private javax.swing.JTextField textAdminAccess;
    private javax.swing.JTextField textDOB;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textFirstName;
    private javax.swing.JTextField textGender;
    private javax.swing.JTextField textJob;
    private javax.swing.JTextField textLastName;
    private javax.swing.JTextField textSalary;
    private javax.swing.JLabel titleBar;
    private javax.swing.JLabel topBanner;
    // End of variables declaration//GEN-END:variables
}
