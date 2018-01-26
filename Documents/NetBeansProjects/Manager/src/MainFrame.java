

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.net.ftp.FTPClient;

public class MainFrame extends javax.swing.JFrame {
    myValue mStr = new myValue();
    Properties gg = new Properties();
    InputStream inStream;
    Thread autoRefresh;
    int index,row;
    Properties code;
    Connection conn;
    String[] db = mStr.db.split(",");
    Statement st;
    ResultSet rs;
    String query2;
    static String query = "SELECT * FROM vfreport";
    FileInputStream fs;
    PreparedStatement ps,ps1,ps2,ps3;
    Thread az;
    String idEdit; //ID untuk mengedit
    
    public MainFrame() {
        Konektor k = new Konektor(db);
        conn = k.getConn();
        initComponents();
        hideBtn();
        checkSession cs =  new checkSession();
        az = new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                    cs.checkSession();
                    System.out.println("Runned");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
        az.run();
        az.interrupt();
    }
    
    public ArrayList<FinalReportUser> userList(String qry){
        ArrayList<FinalReportUser> userList = new ArrayList<>();
        try {

            st = conn.createStatement();
            rs = st.executeQuery(qry);
            FinalReportUser user;
            while(rs.next()){
                user = new FinalReportUser(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getString(12));
                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return userList;
    }
    public void showUser(String qry1){
        DefaultTableModel mdl = (DefaultTableModel)resultTbl.getModel();
        ArrayList<FinalReportUser> list = userList(qry1);
        
        Object[] row = new Object[12];
        
        for(int i=0;i<list.size();i++){
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getfName();
            row[2] = list.get(i).getlName();
            row[3] = list.get(i).gettelpCust();
            row[4] = list.get(i).getalamat();
            row[5] = list.get(i).getnomorMeja();
            row[6] = list.get(i).getListPesanan();
            row[7] = list.get(i).getketerangan();
            row[8] = list.get(i).gettotalBiaya();
            row[9] = list.get(i).getkodePembayaran();
            row[10] = list.get(i).getstatusPembayaran();
            row[11] = list.get(i).getDate();
            mdl.addRow(row);
        }
        if(list.isEmpty()){
            JOptionPane.showMessageDialog(this, mStr.nodata);
        }        
    }
    
    public void refreshData(){
        DefaultTableModel mdl = (DefaultTableModel)resultTbl.getModel();
        mdl.setRowCount(0);
        showUser(query);
    }
    
    public void autoRefresh(){
        
            autoRefresh = new Thread(){
                public void run(){
                    try {
                        System.out.println("wait");
                        for(int x=0;x<9999;x++){
                            for(int i=0;i<5;i++){
                                Integer countdown = 5 - i;
                                Thread.sleep(1000);
                                autoRefreshtxt.setText(countdown.toString());
                            }
                            refreshData();
                            autoRefreshtxt.setText(mStr.refreshed);
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            };
            autoRefresh.start();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        editFrame = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        idField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        copyBtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        filterFrame = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        combo = new javax.swing.JComboBox<>();
        jump = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        custRadio = new javax.swing.JRadioButton();
        makananRadio = new javax.swing.JRadioButton();
        minumanRadio = new javax.swing.JRadioButton();
        reportRadio = new javax.swing.JRadioButton();
        tambahRadio = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        edit = new javax.swing.JFrame();
        idTxt = new javax.swing.JTextField();
        fNameTxt = new javax.swing.JTextField();
        lNameTxt = new javax.swing.JTextField();
        custTelpTxt = new javax.swing.JTextField();
        statSdh = new javax.swing.JRadioButton();
        statBlm = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamatTxt = new javax.swing.JTextArea();
        nomorMejaTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listPesananTxt = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        keteranganTxt = new javax.swing.JTextArea();
        stat = new javax.swing.ButtonGroup();
        tambahMenu = new javax.swing.JFrame();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        fpath = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTbl = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        datesearch = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        autoChck = new javax.swing.JCheckBox();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        autoRefreshtxt = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        refreshBtn = new javax.swing.JMenuItem();
        jumpBtn = new javax.swing.JMenuItem();
        logoutBtn = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        editData = new javax.swing.JMenuItem();

        editFrame.setAlwaysOnTop(true);
        editFrame.setMinimumSize(new java.awt.Dimension(217, 200));
        editFrame.setResizable(false);
        editFrame.setType(java.awt.Window.Type.POPUP);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit data"));

        idField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idFieldKeyTyped(evt);
            }
        });

        jLabel4.setText("Insert Id :");

        copyBtn.setText("Copy ID");
        copyBtn.setEnabled(false);
        copyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyBtnActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(idField, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(copyBtn))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(copyBtn))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editFrameLayout = new javax.swing.GroupLayout(editFrame.getContentPane());
        editFrame.getContentPane().setLayout(editFrameLayout);
        editFrameLayout.setHorizontalGroup(
            editFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        editFrameLayout.setVerticalGroup(
            editFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        filterFrame.setAlwaysOnTop(true);
        filterFrame.setMinimumSize(new java.awt.Dimension(232, 172));
        filterFrame.setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));

        jLabel1.setText("Search By :");

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Nama Customer", "No Telp", "No Meja", "Kode Pesanan", "Status" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(searchBtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 90, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout filterFrameLayout = new javax.swing.GroupLayout(filterFrame.getContentPane());
        filterFrame.getContentPane().setLayout(filterFrameLayout);
        filterFrameLayout.setHorizontalGroup(
            filterFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        filterFrameLayout.setVerticalGroup(
            filterFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jump.setMinimumSize(new java.awt.Dimension(200, 194));
        jump.setResizable(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Jump to"));

        custRadio.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(custRadio);
        custRadio.setText("Customer");

        makananRadio.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(makananRadio);
        makananRadio.setText("Makanan");

        minumanRadio.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(minumanRadio);
        minumanRadio.setText("Minuman");

        reportRadio.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(reportRadio);
        reportRadio.setSelected(true);
        reportRadio.setText("Report");

        buttonGroup1.add(tambahRadio);
        tambahRadio.setText("Tambah Menu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(tambahRadio)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(custRadio)
                            .addComponent(reportRadio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minumanRadio, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(makananRadio, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportRadio)
                    .addComponent(makananRadio))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(custRadio)
                    .addComponent(minumanRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(tambahRadio)
                .addContainerGap())
        );

        jButton1.setText("Jump");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jumpLayout = new javax.swing.GroupLayout(jump.getContentPane());
        jump.getContentPane().setLayout(jumpLayout);
        jumpLayout.setHorizontalGroup(
            jumpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jumpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jumpLayout.setVerticalGroup(
            jumpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jumpLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        edit.setMinimumSize(new java.awt.Dimension(450, 494));
        edit.setResizable(false);
        edit.setType(java.awt.Window.Type.UTILITY);

        idTxt.setEditable(false);

        custTelpTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custTelpTxtActionPerformed(evt);
            }
        });
        custTelpTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                custTelpTxtKeyTyped(evt);
            }
        });

        stat.add(statSdh);
        statSdh.setText("Sudah");

        stat.add(statBlm);
        statBlm.setText("Belum");

        jLabel3.setText("ID :");

        jLabel5.setText("Nama Depan");

        jLabel6.setText("Nama Belakang");

        jLabel7.setText("No Telp Cust");

        jLabel8.setText("Status Pembayaran");

        jButton2.setText("Simpan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        alamatTxt.setColumns(20);
        alamatTxt.setRows(5);
        jScrollPane2.setViewportView(alamatTxt);

        jLabel9.setText("Nomor Meja");

        jLabel10.setText("List Pesanan");

        listPesananTxt.setColumns(20);
        listPesananTxt.setRows(5);
        jScrollPane3.setViewportView(listPesananTxt);

        jLabel11.setText("Keterangan");

        keteranganTxt.setColumns(20);
        keteranganTxt.setRows(5);
        jScrollPane4.setViewportView(keteranganTxt);

        javax.swing.GroupLayout editLayout = new javax.swing.GroupLayout(edit.getContentPane());
        edit.getContentPane().setLayout(editLayout);
        editLayout.setHorizontalGroup(
            editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editLayout.createSequentialGroup()
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(editLayout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(statSdh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(editLayout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(statBlm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLayout.createSequentialGroup()
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(custTelpTxt)
                                .addComponent(fNameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lNameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel10)
                            .addComponent(nomorMejaTxt)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        editLayout.setVerticalGroup(
            editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editLayout.createSequentialGroup()
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(statSdh, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statBlm)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editLayout.createSequentialGroup()
                        .addComponent(fNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(custTelpTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomorMejaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jButton4.setText("Pilih Foto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        fpath.setText("...");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fpath, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(fpath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setText("jLabel13");

        jTextField1.setText("jTextField1");

        jLabel14.setText("jLabel14");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(344, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel8);

        javax.swing.GroupLayout tambahMenuLayout = new javax.swing.GroupLayout(tambahMenu.getContentPane());
        tambahMenu.getContentPane().setLayout(tambahMenuLayout);
        tambahMenuLayout.setHorizontalGroup(
            tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        tambahMenuLayout.setVerticalGroup(
            tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Final Report");
        setMinimumSize(new java.awt.Dimension(900, 464));

        resultTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Depan", "Nama Belakang", "No. telp Customer", "Alamat", "Nomor Meja", "Pesanan", "Keterangan", "Total Biaya", "Kode Pembayaran", "Status Pembayaran", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resultTbl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                resultTblFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(resultTbl);
        if (resultTbl.getColumnModel().getColumnCount() > 0) {
            resultTbl.getColumnModel().getColumn(0).setResizable(false);
            resultTbl.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jLabel12.setText("Tanggal Laporan");

        datesearch.setText("yyyy-mm-dd");
        datesearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datesearchActionPerformed(evt);
            }
        });
        datesearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                datesearchKeyTyped(evt);
            }
        });

        search.setText("Cari...");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(datesearch, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(datesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
                .addContainerGap())
        );

        autoChck.setText("Auto Refresh");
        autoChck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoChckActionPerformed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel2.setText("Auto Refresh in : ");
        jToolBar1.add(jLabel2);

        autoRefreshtxt.setText("Auto Refresh turned off.");
        autoRefreshtxt.setMaximumSize(new java.awt.Dimension(150, 14));
        autoRefreshtxt.setMinimumSize(new java.awt.Dimension(50, 14));
        jToolBar1.add(autoRefreshtxt);

        jMenu1.setText("File");

        refreshBtn.setText("Refresh Table");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });
        jMenu1.add(refreshBtn);

        jumpBtn.setText("Jump to ...");
        jumpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpBtnActionPerformed(evt);
            }
        });
        jMenu1.add(jumpBtn);

        logoutBtn.setText("Exit & Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        jMenu1.add(logoutBtn);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        editData.setText("Edit Data");
        editData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDataActionPerformed(evt);
            }
        });
        jMenu2.add(editData);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(autoChck)
                        .addGap(10, 10, 10))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoChck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed

    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped
        // TODO add your handling code here:
       
        char a = evt.getKeyChar();
        if(Character.isAlphabetic(a)){
            evt.consume();
        }
        else if(index == 4 && searchField.getText().length()==8){
            evt.consume();
        }
    }//GEN-LAST:event_searchFieldKeyTyped

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBtnActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboActionPerformed

    private void autoChckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoChckActionPerformed
        // TODO add your handling code here:
        if(autoChck.isSelected()){
            autoRefresh();
        }
        else{
            autoRefresh.interrupt();
            autoRefreshtxt.setText(mStr.autoRefreshoff);
        }
    }//GEN-LAST:event_autoChckActionPerformed

    private void idFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idFieldKeyTyped
        // TODO add your handling code here:
        Character x = evt.getKeyChar();
        if(Character.isAlphabetic(x)){
            evt.consume();
        }
    }//GEN-LAST:event_idFieldKeyTyped

    private void copyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mdl = (DefaultTableModel)resultTbl.getModel();
        row = resultTbl.getSelectedRow();
        Object value = mdl.getValueAt(row, 0);
        idField.setText(value.toString());
        
    }//GEN-LAST:event_copyBtnActionPerformed

    private void resultTblFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_resultTblFocusGained
        // TODO add your handling code here:
        hideBtn();
    }//GEN-LAST:event_resultTblFocusGained

    private void editDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDataActionPerformed
        // TODO add your handling code here:
        editFrame.setVisible(true);
    }//GEN-LAST:event_editDataActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // TODO add your handling code here:
        refreshData();
    }//GEN-LAST:event_refreshBtnActionPerformed


    
    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        
        String lastuser;
        try {
            fs = new FileInputStream(mStr.properties);
            gg.load(fs);
            lastuser = gg.getProperty(mStr.lstusr);
            st = conn.createStatement();
            ps = conn.prepareStatement("UPDATE `datamanager` SET `lastLogin` = '0',`session` = '0' WHERE `datamanager`.`username` = '"+lastuser+"'");
            ps.executeUpdate();
            this.dispose();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void jumpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpBtnActionPerformed
        // TODO add your handling code here:
        jump.setVisible(true);
    }//GEN-LAST:event_jumpBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(reportRadio.isSelected()){
            MainFrame report = new MainFrame();
            report.setVisible(true);
            this.dispose();
        }
        else if(custRadio.isSelected()){
            CustFrame cust = new CustFrame();
            cust.setVisible(true);
            this.dispose();
        }
        else if(makananRadio.isSelected()){
            MakananFrame makanan = new MakananFrame();
            makanan.setVisible(true);
            this.dispose();
        }
        else if(minumanRadio.isSelected()){
            MinumanFrame minuman = new MinumanFrame();
            minuman.setVisible(true);
            this.dispose();
        }
        else if(tambahRadio.isSelected()){
            tambahMenu.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        idEdit = idField.getText();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM vfreport WHERE idFR = '"+idEdit+"'");
            rs.next();
            if(rs.getRow()==1){
                idTxt.setText(rs.getString(1));
                fNameTxt.setText(rs.getString(2));
                lNameTxt.setText(rs.getString(3));
                custTelpTxt.setText(rs.getString(4));
                Integer stat = rs.getInt(11);
                if(stat==1){
                    statSdh.setSelected(true);
                }
                else{
                    statBlm.setSelected(true);
                }
                alamatTxt.setText(rs.getString(5));
                nomorMejaTxt.setText(rs.getString(6));
                listPesananTxt.setText(rs.getString(7));
                keteranganTxt.setText(rs.getString(8));
                
                edit.setVisible(true);
                editFrame.dispose();
            }
            else{
                JOptionPane.showMessageDialog(rootPane, mStr.nodata);
            }
            
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void custTelpTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custTelpTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custTelpTxtActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
            // TODO add your handling code here:
            String ed1 = fNameTxt.getText();
            String ed2 = lNameTxt.getText();
            String ed3 = custTelpTxt.getText();
            String ed4;
            String ed5 = keteranganTxt.getText();
            String ed6 = alamatTxt.getText();
            String ed7 = nomorMejaTxt.getText();
            String ed8 = listPesananTxt.getText();
            
            
            if(statSdh.isSelected()==true){
                ed4 = "1";
            }
            else{
                ed4 = "0";
            }
        try{   
            //ps = conn.prepareStatement("UPDATE `vfreport` SET `fName` = '"+ed1+"',`lName` = '"+ed2+"', `telp` = '"+ed3+"', `statusPembayaran` = '"+ed4+"', `keterangan` = '"+ed5+"', `alamat` ='"+ed6+"', `nomorMeja` ='"+ed7+"', `ListPesanan` ='"+ed8+"' WHERE `vfreport`.`idFR` = '"+idField.getText()+"'");
            ps = conn.prepareStatement("UPDATE datacustomer SET fName = '"+ed1+"', lName = '"+ed2+"', telp ='"+ed3+"', alamat ='"+ed6+"' WHERE idCustomer = '"+idField.getText()+"'");
            ps1 = conn.prepareStatement("UPDATE meja SET nomorMeja ='"+ed7+"' WHERE idMeja = '"+idField.getText()+"'");
            ps2 = conn.prepareStatement("UPDATE pesanan SET ListPesanan ='"+ed8+"', keterangan ='"+ed5+"' WHERE idPesanan ='"+idField.getText()+"'");
            ps3 = conn.prepareStatement("UPDATE pembayaran SET statusPembayaran ='"+ed4+"' WHERE idPembayaran ='"+idField.getText()+"'");
            ps.executeUpdate();
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            if(ps.executeUpdate()==1 && ps1.executeUpdate()==1 && ps2.executeUpdate()==1 && ps3.executeUpdate()==1){
                JOptionPane.showMessageDialog(rootPane, mStr.editOK);
                refreshData();
                editFrame.setVisible(true);
                edit.dispose();
            }
            else{
                JOptionPane.showMessageDialog(rootPane, mStr.editFAIL);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void custTelpTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custTelpTxtKeyTyped
        // TODO add your handling code here:
        Character a = evt.getKeyChar();
        if(Character.isAlphabetic(a)){
            evt.consume();
        }
    }//GEN-LAST:event_custTelpTxtKeyTyped

    private void datesearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datesearchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_datesearchActionPerformed

    private void datesearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datesearchKeyTyped
        // TODO add your handling code here:
        Character a = evt.getKeyChar();
        if(Character.isAlphabetic(a)){
            evt.consume();
        }
    }//GEN-LAST:event_datesearchKeyTyped

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        DefaultTableModel mdl = (DefaultTableModel)resultTbl.getModel();
        ArrayList<FinalReportUser> list = new ArrayList();
        try {
            // TODO add your handling code here:
            String search = "SELECT * FROM vfreport WHERE date LIKE "+"'%"+datesearch.getText()+"%'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(search);
            while(rs.next()){
                FinalReportUser user = new FinalReportUser(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getString(12));
                list.add(user);
            }
            
            Object[] row = new Object[12];
        
        for(int i=0;i<list.size();i++){
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getfName();
            row[2] = list.get(i).getlName();
            row[3] = list.get(i).gettelpCust();
            row[4] = list.get(i).getalamat();
            row[5] = list.get(i).getnomorMeja();
            row[6] = list.get(i).getListPesanan();
            row[7] = list.get(i).getketerangan();
            row[8] = list.get(i).gettotalBiaya();
            row[9] = list.get(i).getkodePembayaran();
            row[10] = list.get(i).getstatusPembayaran();
            row[11] = list.get(i).getDate();
            mdl.addRow(row);
        }
        if(list.isEmpty()){
            JOptionPane.showMessageDialog(this, mStr.nodata);
        }  
        
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        FTPClient client = new FTPClient();
        FileInputStream is = null;
        JFileChooser jfc = new JFileChooser();
        File img;
        int opened = jfc.showOpenDialog(MainFrame.this);
        if(opened == JFileChooser.APPROVE_OPTION){
            img = jfc.getSelectedFile();
            System.out.println(img.getPath());
            try {
                is = new FileInputStream(img);
                String host = mStr.host;
                String fname = img.getName();
                client.connect(host);
                client.login(mStr.huser,mStr.hpswd);
                if(client.isConnected()){
                    System.out.println("Connected");
                    client.storeFile(fname,is);
                    System.out.println(fname);
                    if(client.storeFile(mStr.ftpimgdir+fname, is)){
                        System.out.println("good");
                        JOptionPane.showMessageDialog(rootPane, fname);
                        
                    } else{
                        System.out.println("fail");
                    }
                   
                } else{
                    JOptionPane.showMessageDialog(rootPane, mStr.ftpconFAIL);
                }
               
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                try {
                    if(is!=null){
                        is.close();
                    }
                } catch (IOException e) {
                }
            }
        } 
    }//GEN-LAST:event_jButton4ActionPerformed
    
    public void hideBtn(){
        row = resultTbl.getSelectedRow();
        if(resultTbl.isRowSelected(row)){
            copyBtn.setEnabled(true);
        }
    }
    
    /*public void getsCode(Integer code) throws IOException{
            
            this.code.load(inStream);
            this.code.setProperty("session", "xxx");
            System.out.println("Prop"+code);
            System.out.println(this.code.getProperty("session"));
    }*/
    
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamatTxt;
    private javax.swing.JCheckBox autoChck;
    private javax.swing.JLabel autoRefreshtxt;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton copyBtn;
    private javax.swing.JRadioButton custRadio;
    private javax.swing.JTextField custTelpTxt;
    private javax.swing.JTextField datesearch;
    private javax.swing.JFrame edit;
    private javax.swing.JMenuItem editData;
    private javax.swing.JFrame editFrame;
    private javax.swing.JTextField fNameTxt;
    private javax.swing.JFrame filterFrame;
    private javax.swing.JTextField fpath;
    private javax.swing.JTextField idField;
    private javax.swing.JTextField idTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JFrame jump;
    private javax.swing.JMenuItem jumpBtn;
    private javax.swing.JTextArea keteranganTxt;
    private javax.swing.JTextField lNameTxt;
    private javax.swing.JTextArea listPesananTxt;
    private javax.swing.JMenuItem logoutBtn;
    private javax.swing.JRadioButton makananRadio;
    private javax.swing.JRadioButton minumanRadio;
    private javax.swing.JTextField nomorMejaTxt;
    private javax.swing.JMenuItem refreshBtn;
    private javax.swing.JRadioButton reportRadio;
    private javax.swing.JTable resultTbl;
    private javax.swing.JButton search;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchField;
    private javax.swing.ButtonGroup stat;
    private javax.swing.JRadioButton statBlm;
    private javax.swing.JRadioButton statSdh;
    private javax.swing.JFrame tambahMenu;
    private javax.swing.JRadioButton tambahRadio;
    // End of variables declaration//GEN-END:variables
}
