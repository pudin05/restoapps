

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MakananFrame extends javax.swing.JFrame {
    InputStream inStream;
    Thread autoRefresh;
    int index,row;
    Properties code;
    Connection conn;
    String[] db = "jdbc:mysql://putraput.eu.org:3306/depot,putra,delfira26".split(",");
    Statement st;
    ResultSet rs;
    String query2;
    static String query = "SELECT * FROM makanan";
    
    public MakananFrame() {
        Konektor k = new Konektor(db);
        conn = k.getConn();
        initComponents();
        hideBtn();
    }
    
    public ArrayList<MakananUser> userList(String qry){
        ArrayList<MakananUser> userList = new ArrayList<>();
        
        try {

              
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            MakananUser user;
            while(rs.next()){
                user = new MakananUser(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return userList;
    }
    public void showUser(String qry1){
        DefaultTableModel mdl = (DefaultTableModel)resultTbl.getModel();
        ArrayList<MakananUser> list = userList(qry1);
        
        Object[] row = new Object[5];
        
        for(int i=0;i<list.size();i++){
            row[0] = list.get(i).getidMakanan();
            row[1] = list.get(i).getnamaMakanan();
            row[2] = list.get(i).gethargaMakanan();
            row[3] = list.get(i).getpromoMakanan();
            row[4] = list.get(i).getstatusMakanan();
            mdl.addRow(row);
        }
        if(list.isEmpty()){
            JOptionPane.showMessageDialog(this, "Opps!, No Data Found!");
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
                            autoRefreshtxt.setText("Refreshed");
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
        tambahMenu = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        edit = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        ed1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ed2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ed3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ed4 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        ada = new javax.swing.JRadioButton();
        kosong = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTbl = new javax.swing.JTable();
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
        jMenuItem1 = new javax.swing.JMenuItem();

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
                        .addComponent(idField, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
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
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editFrameLayout = new javax.swing.GroupLayout(editFrame.getContentPane());
        editFrame.getContentPane().setLayout(editFrameLayout);
        editFrameLayout.setHorizontalGroup(
            editFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        editFrameLayout.setVerticalGroup(
            editFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
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

        jump.setMinimumSize(new java.awt.Dimension(200, 184));
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

        tambahMenu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(tambahMenu);
        tambahMenu.setText("Tambah Menu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(tambahMenu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(custRadio)
                            .addComponent(reportRadio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(tambahMenu)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        edit.setMinimumSize(new java.awt.Dimension(350, 300));

        jLabel3.setText("ID Makanan");

        jLabel5.setText("Nama Makanan");

        jLabel6.setText("Harga");

        jLabel7.setText("Promo");

        buttonGroup2.add(ada);
        ada.setText("Ada");

        buttonGroup2.add(kosong);
        kosong.setText("Kosong");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ada)
                    .addComponent(kosong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kosong)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jButton2.setText("Simpan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editLayout = new javax.swing.GroupLayout(edit.getContentPane());
        edit.getContentPane().setLayout(editLayout);
        editLayout.setHorizontalGroup(
            editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editLayout.createSequentialGroup()
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(ed1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(ed2)
                            .addComponent(ed3))
                        .addGap(80, 80, 80)
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(ed4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        editLayout.setVerticalGroup(
            editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ed2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ed3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Customer Manage");
        setMinimumSize(new java.awt.Dimension(900, 464));

        resultTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Makanan", "Harga", "Promo", "Status"
            }
        ));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );

        autoChck.setText("Auto Refresh");
        autoChck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoChckActionPerformed(evt);
            }
        });

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

        jMenuItem1.setText("Edit Data");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        searchBy();
        
        char a = evt.getKeyChar();
        if(Character.isAlphabetic(a)){
            evt.consume();
        }
        else if(index == 4 && searchField.getText().length()==8){
            evt.consume();
        }
    }//GEN-LAST:event_searchFieldKeyTyped

    public void searchBy(){
        index = combo.getSelectedIndex();
        if(index == 0){
            query2 = "idFR";
            searchField.setText("");
        }
        else if(index == 1){
            query2 = "namaCustomer";
            searchField.setText("");
        }
        else if(index == 2){
            query2 = "NoTelpCust";
            searchField.setText("");
        }
        else if(index == 3){
            query2 = "nomorMeja";
            searchField.setText("");
        }
        else if(index == 4){
            query2 = "kodePembayaran";
            searchField.setText("");
            
        }
        else if(index == 5){
            query2 = "status";
            searchField.setText("");
        }
    }
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        searchBy();
        DefaultTableModel mdl = (DefaultTableModel)resultTbl.getModel();
        mdl.setRowCount(0);
        showUser("SELECT * FROM makanan WHERE "+query2+" = '"+searchField.getText()+"'");
        System.out.println("SELECT * FROM makanan WHERE "+query2+" = '"+searchField.getText()+"'");
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
            autoRefreshtxt.setText("Auto Refresh turned off.");
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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        editFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // TODO add your handling code here:
        refreshData();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
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
        else if(tambahMenu.isSelected()){
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
              
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM makanan WHERE idMakanan = '"+idField.getText()+"'");
            rs.next();
            if(rs.getRow()==1){
                ed1.setText(rs.getString(1));
                ed2.setText(rs.getString(2));
                ed3.setText(rs.getString(3));
                ed4.setText(rs.getString(4));
                if(rs.getInt(5)==1){
                    ada.setSelected(true);
                }
                else{
                    kosong.setSelected(true);
                }
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan!");
            }
        } catch (Exception e) {
        
        }
        edit.setVisible(true);
        editFrame.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String idMakanan = ed1.getText();
        String namaMakanan = ed2.getText();
        String harga = ed3.getText();
        String promo = ed4.getText();
        String status;
        if(ada.isSelected()){
            status = "1";
        }
        else{
            status = "0";
        }
        
        try {
              
            PreparedStatement ps = conn.prepareStatement("UPDATE makanan SET idMakanan = '"+idMakanan+"', namaMakanan = '"+namaMakanan+"', hargaMakanan = '"+harga+"', promoMakanan = '"+promo+"', statusMakanan = '"+status+"' WHERE idMakanan = '"+idField.getText()+"'");
            ps.executeUpdate();
            if(ps.executeUpdate()==1){
                JOptionPane.showMessageDialog(rootPane, "Edit Berhasil");
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Error, Silahkan hubungi pengembang.");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    public void hideBtn(){
        row = resultTbl.getSelectedRow();
        if(resultTbl.isRowSelected(row)){
            copyBtn.setEnabled(true);
        }
    
    }
    public void getCode(Integer code) throws IOException{
          
            this.code.load(inStream);
            this.code.setProperty("session", "xxx");
            System.out.println("Prop"+code);
            System.out.println(this.code.getProperty("session"));
        
        
    }
    
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
                new MakananFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ada;
    private javax.swing.JCheckBox autoChck;
    private javax.swing.JLabel autoRefreshtxt;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton copyBtn;
    private javax.swing.JRadioButton custRadio;
    private javax.swing.JTextField ed1;
    private javax.swing.JTextField ed2;
    private javax.swing.JTextField ed3;
    private javax.swing.JTextField ed4;
    private javax.swing.JFrame edit;
    private javax.swing.JFrame editFrame;
    private javax.swing.JFrame filterFrame;
    private javax.swing.JTextField idField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JFrame jump;
    private javax.swing.JMenuItem jumpBtn;
    private javax.swing.JRadioButton kosong;
    private javax.swing.JMenuItem logoutBtn;
    private javax.swing.JRadioButton makananRadio;
    private javax.swing.JRadioButton minumanRadio;
    private javax.swing.JMenuItem refreshBtn;
    private javax.swing.JRadioButton reportRadio;
    private javax.swing.JTable resultTbl;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchField;
    private javax.swing.JRadioButton tambahMenu;
    // End of variables declaration//GEN-END:variables
}
