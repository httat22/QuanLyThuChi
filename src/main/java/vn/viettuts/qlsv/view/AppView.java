package vn.viettuts.qlsv.view;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import vn.viettuts.qlsv.model.ExpandModel;
import vn.viettuts.qlsv.model.IncomeModel;

public class AppView extends JFrame implements ActionListener, ListSelectionListener{
    
    private JButton addIncomeBtn, addExpandBtn;
    private JButton editIncomeBtn, editExpandBtn;
    private JButton deleteIncomeBtn, deleteExpandBtn;
    private JButton clearIncomeBtn, clearExpandBtn;

    private JScrollPane jScrollPaneIncomeTable, jScrollPaneExpandTable;
    private JTable incomeTable, expandTable;

    private JLabel titleLabel;
    private JLabel dateLabel;
    private JLabel noteLabel;
    private JLabel incomeLabel;
    private JLabel categoryLabel;
    private Date currentDate;
    private JCalendar calendar;

    private JTextField noteIncomeField, noteExpandField;
    private JTextField incomeField, expandField;
    private JTextField incomeId, expandId;

    private JPanel panelIncome, panelExpand, panelStatistics;

    private JComboBox<String> jComboBoxIncome, jComboBoxExpand;

    private JDateChooser jDateChooserIncome, jDateChooserExpand;
    
    private Object data = new Object[][] {};

    private String[] columnNamesIncome = new String[] {
        "ID", "Date", "Note", "Income", "Category"
    };
    private String[] columnNamesExpenses = new String[] {
        "ID", "Date", "Note", "Expenses", "Category"
    };
    private String[] jComboBoxIncomeList = new String[] {
        "Tiền lương", "Tiền phụ cấp", "Tiền thưởng", "Đầu tư", "Thu nhập phụ", "Thu nhập khác"
    };
    private String[] jComboBoxExpandList = new String[] {
        "Nhu yếu phẩm", "Ăn uống", "Quần áo", "Y tế", "Đi lại", "Giải trí", "Chi tiêu khác"
    };
    private JButton dayBtn;
    private JButton monthBtn;
    private JButton yearBtn;
    private JButton allBtn;
    private JButton sortDateBtn;
    private JLabel chooseDateLabel;
    private JLabel totalIncomeLabel;
    private JLabel totalIncome;
    private JLabel totalExpandLabel;
    private JLabel totalExpand;
    private JDateChooser chooseDateForStatistics;
    private JButton sortMoneyBtn;
    private JButton sortCategoryBtn;
    private JButton sortIDBtn;
    private JLabel statisticsTitle;
    private JButton searchBtn;
 
    public AppView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initIncomePanel();
        initExpandPanel();
        initStatisticsPanel();

        this.setLayout(new GridLayout(3,1));
        this.add(panelIncome);
        this.add(panelExpand);
        this.add(panelStatistics);


        this.setTitle("Manage Income and Expenses");
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        this.setResizable(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Income Panel
    public void initIncomePanel() {
        // khởi tạo các phím chức năng
        addIncomeBtn = new JButton("Add");
        editIncomeBtn = new JButton("Edit");
        deleteIncomeBtn = new JButton("Delete");
        clearIncomeBtn = new JButton("Clear");

        // khởi tạo bảng thu nhập
        jScrollPaneIncomeTable = new JScrollPane();
        incomeTable = new JTable();

        // khởi tạo các label
        titleLabel = new JLabel("INCOME MANAGEMENT");
        titleLabel.setForeground(Color.RED);
        dateLabel = new JLabel("Date");
        noteLabel = new JLabel("Note");
        incomeLabel = new JLabel("Income");
        categoryLabel = new JLabel("Category");

        // khởi tạo các trường nhập dữ liệu cho income
        incomeId = new JTextField(6);
        incomeId.setEditable(false);
        jDateChooserIncome = new JDateChooser();
        currentDate = new Date();
        // jDateChooserIncome.getDateEditor().getUiComponent().setEnabled(false);
        jDateChooserIncome.setDate(currentDate);
        jDateChooserIncome.setPreferredSize(new Dimension(150, jDateChooserIncome.getPreferredSize().height));
        noteIncomeField = new JTextField();
        noteIncomeField.setPreferredSize(new Dimension(150, noteIncomeField.getPreferredSize().height));

        incomeField = new JTextField(10);
        AbstractDocument doc = (AbstractDocument) incomeField.getDocument();
        doc.setDocumentFilter(new IntegerFilter());

        jComboBoxIncome = new JComboBox<>(jComboBoxIncomeList);
        jComboBoxIncome.setPreferredSize(new Dimension(150, jComboBoxIncome.getPreferredSize().height));
        

        // cài đặt các cột và data cho bảng
        incomeTable.setModel(new DefaultTableModel((Object[][]) data, columnNamesIncome));
        jScrollPaneIncomeTable.setViewportView(incomeTable);
        jScrollPaneIncomeTable.setPreferredSize(new Dimension (480, 210));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        panelIncome = new JPanel();
        panelIncome.setSize(800, 220);
        panelIncome.setLayout(layout);

        panelIncome.add(jScrollPaneIncomeTable);

        panelIncome.add(addIncomeBtn);
        panelIncome.add(editIncomeBtn);
        panelIncome.add(deleteIncomeBtn);
        panelIncome.add(clearIncomeBtn);

        panelIncome.add(titleLabel);
        panelIncome.add(dateLabel);
        panelIncome.add(noteLabel);
        panelIncome.add(incomeLabel);
        panelIncome.add(categoryLabel);

        panelIncome.add(incomeId);
        panelIncome.add(jDateChooserIncome);
        panelIncome.add(noteIncomeField);
        panelIncome.add(incomeField);
        panelIncome.add(jComboBoxIncome);
        
        
        layout.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, panelIncome);

        layout.putConstraint(SpringLayout.WEST, dateLabel, 10, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 40, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, noteLabel, 10, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, noteLabel, 70, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, incomeLabel, 10, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, incomeLabel, 100, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, categoryLabel, 10, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, categoryLabel, 130, SpringLayout.NORTH, panelIncome);
        
        layout.putConstraint(SpringLayout.WEST, incomeId, 180, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, incomeId, 10, SpringLayout.NORTH, panelIncome);

        layout.putConstraint(SpringLayout.WEST, jDateChooserIncome, 110, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, jDateChooserIncome, 40, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, noteIncomeField, 110, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, noteIncomeField, 70, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, incomeField, 110, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, incomeField, 100, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, jComboBoxIncome, 110, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, jComboBoxIncome, 130, SpringLayout.NORTH, panelIncome);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneIncomeTable, 300, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneIncomeTable, 10, SpringLayout.NORTH, panelIncome);
        
        layout.putConstraint(SpringLayout.WEST, addIncomeBtn, 20, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, addIncomeBtn, 190, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, editIncomeBtn, 60, SpringLayout.WEST, addIncomeBtn);
        layout.putConstraint(SpringLayout.NORTH, editIncomeBtn, 190, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, deleteIncomeBtn, 60, SpringLayout.WEST, editIncomeBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteIncomeBtn, 190, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, clearIncomeBtn, 80, SpringLayout.WEST, deleteIncomeBtn);
        layout.putConstraint(SpringLayout.NORTH, clearIncomeBtn, 190, SpringLayout.NORTH, panelIncome);

        // disable Edit and Delete buttons
        editIncomeBtn.setEnabled(false);
        deleteIncomeBtn.setEnabled(false);
        // enable Add button
        addIncomeBtn.setEnabled(true);
    }

    public void initExpandPanel() {
        // khởi tạo các phím chức năng
        addExpandBtn = new JButton("Add");
        editExpandBtn = new JButton("Edit");
        deleteExpandBtn = new JButton("Delete");
        clearExpandBtn = new JButton("Clear");

        // khởi tạo bảng thu nhập
        jScrollPaneExpandTable = new JScrollPane();
        expandTable = new JTable();

        // khởi tạo các label
        titleLabel = new JLabel("EXPENSES MANAGEMENT");
        titleLabel.setForeground(Color.RED);
        dateLabel = new JLabel("Date");
        noteLabel = new JLabel("Note");
        incomeLabel = new JLabel("Expenses");
        categoryLabel = new JLabel("Category");

        // khởi tạo các trường nhập dữ liệu cho income
        expandId = new JTextField(6);
        expandId.setEditable(false);
        jDateChooserExpand = new JDateChooser();
        currentDate = new Date();
        // jDateChooserExpand.getDateEditor().getUiComponent().setEnabled(false);
        jDateChooserExpand.setDate(currentDate);
        jDateChooserExpand.setPreferredSize(new Dimension(150, jDateChooserExpand.getPreferredSize().height));
        noteExpandField = new JTextField();
        noteExpandField.setPreferredSize(new Dimension(150, noteExpandField.getPreferredSize().height));

        expandField = new JTextField(10);
        AbstractDocument doc = (AbstractDocument) expandField.getDocument();
        doc.setDocumentFilter(new IntegerFilter());

        jComboBoxExpand = new JComboBox<>(jComboBoxExpandList);
        jComboBoxExpand.setPreferredSize(new Dimension(150, jComboBoxExpand.getPreferredSize().height));

        // cài đặt các cột và data cho bảng
        expandTable.setModel(new DefaultTableModel((Object[][]) data, columnNamesExpenses));
        jScrollPaneExpandTable.setViewportView(expandTable);
        jScrollPaneExpandTable.setPreferredSize(new Dimension (480, 210));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        panelExpand = new JPanel();
        panelExpand.setSize(800, 220);
        panelExpand.setLayout(layout);

        panelExpand.add(jScrollPaneExpandTable);

        panelExpand.add(addExpandBtn);
        panelExpand.add(editExpandBtn);
        panelExpand.add(deleteExpandBtn);
        panelExpand.add(clearExpandBtn);

        panelExpand.add(titleLabel);
        panelExpand.add(dateLabel);
        panelExpand.add(noteLabel);
        panelExpand.add(incomeLabel);
        panelExpand.add(categoryLabel);

        panelExpand.add(expandId);
        panelExpand.add(jDateChooserExpand);
        panelExpand.add(noteExpandField);
        panelExpand.add(expandField);
        panelExpand.add(jComboBoxExpand);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, panelExpand);

        layout.putConstraint(SpringLayout.WEST, dateLabel, 10, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 40, SpringLayout.NORTH, panelExpand);
        layout.putConstraint(SpringLayout.WEST, noteLabel, 10, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, noteLabel, 70, SpringLayout.NORTH, panelExpand);
        layout.putConstraint(SpringLayout.WEST, incomeLabel, 10, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, incomeLabel, 100, SpringLayout.NORTH, panelExpand);
        layout.putConstraint(SpringLayout.WEST, categoryLabel, 10, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, categoryLabel, 130, SpringLayout.NORTH, panelExpand);
        
        layout.putConstraint(SpringLayout.WEST, expandId, 180, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, expandId, 10, SpringLayout.NORTH, panelExpand);

        layout.putConstraint(SpringLayout.WEST, jDateChooserExpand, 110, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, jDateChooserExpand, 40, SpringLayout.NORTH, panelExpand);
        layout.putConstraint(SpringLayout.WEST, noteExpandField, 110, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, noteExpandField, 70, SpringLayout.NORTH, panelExpand);
        layout.putConstraint(SpringLayout.WEST, expandField, 110, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, expandField, 100, SpringLayout.NORTH, panelExpand);
        layout.putConstraint(SpringLayout.WEST, jComboBoxExpand, 110, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, jComboBoxExpand, 130, SpringLayout.NORTH, panelExpand);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneExpandTable, 300, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneExpandTable, 10, SpringLayout.NORTH, panelExpand);
        
        layout.putConstraint(SpringLayout.WEST, addExpandBtn, 20, SpringLayout.WEST, panelExpand);
        layout.putConstraint(SpringLayout.NORTH, addExpandBtn, 190, SpringLayout.NORTH, panelExpand);
        layout.putConstraint(SpringLayout.WEST, editExpandBtn, 60, SpringLayout.WEST, addExpandBtn);
        layout.putConstraint(SpringLayout.NORTH, editExpandBtn, 190, SpringLayout.NORTH, panelExpand);
        layout.putConstraint(SpringLayout.WEST, deleteExpandBtn, 60, SpringLayout.WEST, editExpandBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteExpandBtn, 190, SpringLayout.NORTH, panelExpand);
        layout.putConstraint(SpringLayout.WEST, clearExpandBtn, 80, SpringLayout.WEST, deleteExpandBtn);
        layout.putConstraint(SpringLayout.NORTH, clearExpandBtn, 190, SpringLayout.NORTH, panelExpand);

        // disable Edit and Delete buttons
        editExpandBtn.setEnabled(false);
        deleteExpandBtn.setEnabled(false);
        // enable Add button
        addExpandBtn.setEnabled(true);
    }

    public void initStatisticsPanel() {
        dayBtn = new JButton("Day");
        monthBtn = new JButton("Month");
        yearBtn = new JButton("Year");
        allBtn = new JButton("All");
        sortDateBtn = new JButton("Sort by date");
        sortMoneyBtn = new JButton("Sort by money");
        sortCategoryBtn = new JButton("Sort by category");
        sortIDBtn = new JButton("Sort by ID");
        searchBtn = new JButton("Search By Date");

        statisticsTitle = new JLabel("STATISTICS");
        statisticsTitle.setForeground(Color.RED);

        chooseDateLabel = new JLabel("Select statistics date");
        totalIncomeLabel = new JLabel("Total income:");
        totalIncome = new JLabel();
        totalExpandLabel = new JLabel("Total expenses:");
        totalExpand = new JLabel();

        chooseDateForStatistics = new JDateChooser();
        currentDate = new Date();
        // chooseDateForStatistics.getDateEditor().getUiComponent().setEnabled(false);
        chooseDateForStatistics.setDate(currentDate);
        chooseDateForStatistics.setPreferredSize(new Dimension(100, chooseDateForStatistics.getPreferredSize().height));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        panelStatistics = new JPanel();
        panelExpand.setSize(800, 150);
        panelStatistics.setLayout(layout);

        panelStatistics.add(dayBtn);
        panelStatistics.add(monthBtn);
        panelStatistics.add(yearBtn);
        panelStatistics.add(allBtn);
        panelStatistics.add(sortDateBtn);
        panelStatistics.add(sortMoneyBtn);
        panelStatistics.add(sortCategoryBtn);
        panelStatistics.add(sortIDBtn);
        panelStatistics.add(searchBtn);

        panelStatistics.add(statisticsTitle);
        panelStatistics.add(chooseDateLabel);
        panelStatistics.add(totalIncomeLabel);
        panelStatistics.add(totalIncome);
        panelStatistics.add(totalExpandLabel);
        panelStatistics.add(totalExpand);
        panelStatistics.add(chooseDateForStatistics);


        layout.putConstraint(SpringLayout.WEST, statisticsTitle, 10, SpringLayout.WEST, panelStatistics);
        layout.putConstraint(SpringLayout.NORTH, statisticsTitle, 40, SpringLayout.NORTH, panelStatistics);

        layout.putConstraint(SpringLayout.WEST, chooseDateLabel, 10, SpringLayout.WEST, panelStatistics);
        layout.putConstraint(SpringLayout.NORTH, chooseDateLabel, 70, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, chooseDateForStatistics, 150, SpringLayout.WEST, chooseDateLabel);
        layout.putConstraint(SpringLayout.NORTH, chooseDateForStatistics, 70, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, dayBtn, 150, SpringLayout.WEST, chooseDateForStatistics);
        layout.putConstraint(SpringLayout.NORTH, dayBtn, 70, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, monthBtn, 100, SpringLayout.WEST, dayBtn);
        layout.putConstraint(SpringLayout.NORTH, monthBtn, 70, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, yearBtn, 100, SpringLayout.WEST, monthBtn);
        layout.putConstraint(SpringLayout.NORTH, yearBtn, 70, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, allBtn, 100, SpringLayout.WEST, yearBtn);
        layout.putConstraint(SpringLayout.NORTH, allBtn, 70, SpringLayout.NORTH, panelStatistics);
        
        layout.putConstraint(SpringLayout.WEST, totalIncomeLabel, 50, SpringLayout.WEST, panelStatistics);
        layout.putConstraint(SpringLayout.NORTH, totalIncomeLabel, 100, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, totalIncome, 100, SpringLayout.WEST, totalIncomeLabel);
        layout.putConstraint(SpringLayout.NORTH, totalIncome, 100, SpringLayout.NORTH, panelStatistics);

        layout.putConstraint(SpringLayout.WEST, totalExpandLabel, 200, SpringLayout.WEST, totalIncome);
        layout.putConstraint(SpringLayout.NORTH, totalExpandLabel, 100, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, totalExpand, 100, SpringLayout.WEST, totalExpandLabel);
        layout.putConstraint(SpringLayout.NORTH, totalExpand, 100, SpringLayout.NORTH, panelStatistics);

        layout.putConstraint(SpringLayout.WEST, sortDateBtn, 10, SpringLayout.WEST, panelStatistics);
        layout.putConstraint(SpringLayout.NORTH, sortDateBtn, 130, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, sortMoneyBtn, 150, SpringLayout.WEST, sortDateBtn);
        layout.putConstraint(SpringLayout.NORTH, sortMoneyBtn, 130, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, sortCategoryBtn, 150, SpringLayout.WEST, sortMoneyBtn);
        layout.putConstraint(SpringLayout.NORTH, sortCategoryBtn, 130, SpringLayout.NORTH, panelStatistics);
        layout.putConstraint(SpringLayout.WEST, sortIDBtn, 150, SpringLayout.WEST, sortCategoryBtn);
        layout.putConstraint(SpringLayout.NORTH, sortIDBtn, 130, SpringLayout.NORTH, panelStatistics);

        layout.putConstraint(SpringLayout.WEST, searchBtn, 10, SpringLayout.WEST, panelStatistics);
        layout.putConstraint(SpringLayout.NORTH, searchBtn, 170, SpringLayout.NORTH, panelStatistics);
    }
    
    /**
     * hiển thị list vào bảng
     * 
     * @param list
     */
    public void showListIncome(List<IncomeModel> list) {
        int size = list.size();
        Object [][] incomObjects = new Object[size][5];
        for (int i = 0; i < size; i++) {
            incomObjects[i][0] = list.get(i).getId();
            incomObjects[i][1] = list.get(i).getDate();
            incomObjects[i][2] = list.get(i).getNote();
            incomObjects[i][3] = list.get(i).getIncome();
            incomObjects[i][4] = list.get(i).getCategory();
        }
        incomeTable.setModel(new DefaultTableModel(incomObjects, columnNamesIncome));
    }  
    
    public void showListExpand(List<ExpandModel> list) {
        int size = list.size();
        Object [][] incomObjects = new Object[size][5];
        for (int i = 0; i < size; i++) {
            incomObjects[i][0] = list.get(i).getId();
            incomObjects[i][1] = list.get(i).getDate();
            incomObjects[i][2] = list.get(i).getNote();
            incomObjects[i][3] = list.get(i).getExpand();
            incomObjects[i][4] = list.get(i).getCategory();
        }
        expandTable.setModel(new DefaultTableModel(incomObjects, columnNamesExpenses));
    } 

    /**
     * điền thông tin của hàng được chọn từ bảng 
     * vào các trường tương ứng.
     */
    public void fillIncomeFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = incomeTable.getSelectedRow();
        if (row >= 0) {
            
            incomeId.setText(incomeTable.getModel().getValueAt(row, 0).toString());

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = incomeTable.getModel().getValueAt(row, 1).toString();
            try {
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            jDateChooserIncome.setDate(date);

            noteIncomeField.setText(incomeTable.getModel().getValueAt(row, 2).toString());
            incomeField.setText(incomeTable.getModel().getValueAt(row, 3).toString());
            jComboBoxIncome.setSelectedItem(incomeTable.getModel().getValueAt(row, 4).toString());
            
            // enable Edit and Delete buttons
            editIncomeBtn.setEnabled(true);
            deleteIncomeBtn.setEnabled(true);
            // disable Add button
            addIncomeBtn.setEnabled(false);
        }
    }


    public void fillExpandFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = expandTable.getSelectedRow();
        if (row >= 0) {
            
            expandId.setText(expandTable.getModel().getValueAt(row, 0).toString());

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = expandTable.getModel().getValueAt(row, 1).toString();
            try {
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            jDateChooserExpand.setDate(date);

            noteExpandField.setText(expandTable.getModel().getValueAt(row, 2).toString());
            expandField.setText(expandTable.getModel().getValueAt(row, 3).toString());
            jComboBoxExpand.setSelectedItem(expandTable.getModel().getValueAt(row, 4).toString());
            
            // enable Edit and Delete buttons
            editExpandBtn.setEnabled(true);
            deleteExpandBtn.setEnabled(true);
            // disable Add button
            addExpandBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin
     */
    public void clearIncomeInfo() {
        incomeId.setText("");
        jDateChooserIncome.setDate(new Date());
        noteIncomeField.setText("");
        incomeField.setText("");
        jComboBoxIncome.setSelectedIndex(0);
        // disable Edit and Delete buttons
        editIncomeBtn.setEnabled(false);
        deleteIncomeBtn.setEnabled(false);
        // disable Add button
        addIncomeBtn.setEnabled(true);
    }
    public void clearExpandInfo() {
        expandId.setText("");
        jDateChooserExpand.setDate(new Date());
        noteExpandField.setText("");
        expandField.setText("");
        jComboBoxExpand.setSelectedIndex(0);
        // disable Edit and Delete buttons
        editExpandBtn.setEnabled(false);
        deleteExpandBtn.setEnabled(false);
        // disable Add button
        addExpandBtn.setEnabled(true);
    }

    /**
     * hiển thị thông tin income
     * 
     * @param incomeModel
     */
    public void showIncome(IncomeModel incomeModel) {
        incomeId.setText("" + incomeModel.getId());
        Date dateFromTable = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = incomeModel.getDate();
            try {
                dateFromTable = dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        jDateChooserIncome.setDate(dateFromTable);
        noteIncomeField.setText(incomeModel.getNote());
        incomeField.setText("" + incomeModel.getIncome());
        jComboBoxIncome.setSelectedItem(incomeModel.getCategory());
        // enable Edit and Delete buttons
        editIncomeBtn.setEnabled(true);
        deleteIncomeBtn.setEnabled(true);
        // disable Add button
        addIncomeBtn.setEnabled(false);
    }

    public void showExpand(ExpandModel expandModel) {
        expandId.setText("" + expandModel.getId());
        Date dateFromTable = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = expandModel.getDate();
            try {
                dateFromTable = dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        jDateChooserExpand.setDate(dateFromTable);
        noteExpandField.setText(expandModel.getNote());
        expandField.setText("" + expandModel.getExpand());
        jComboBoxExpand.setSelectedItem(expandModel.getCategory());
        // enable Edit and Delete buttons
        editExpandBtn.setEnabled(true);
        deleteExpandBtn.setEnabled(true);
        // disable Add button
        addExpandBtn.setEnabled(false);
    }

    /**
     * lấy thông tin
     * 
     * @return
     */
    public IncomeModel getIncomeInfo() {
        // validate
        // if (!validateNoteIncome() || !validateIncome()) {
        //     return null;
        // }
        if (!validateDateIncome() || !validateIncome()) {
            return null;
        }
        try {
            IncomeModel incomeModel = new IncomeModel();

            if (incomeId.getText() != null && !"".equals(incomeId.getText())) {
                incomeModel.setId(Integer.parseInt(incomeId.getText()));
            }

            Date selectedDate;
            if (jDateChooserIncome.getDate() != null) {
                selectedDate = jDateChooserIncome.getDate();
            } else {
                selectedDate = calendar.getCalendar().getTime();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            incomeModel.setDate(dateFormat.format(selectedDate));
            incomeModel.setNote(noteIncomeField.getText().trim());
            incomeModel.setIncome(Long.parseLong(incomeField.getText().trim()));
            incomeModel.setCategory(jComboBoxIncome.getSelectedItem().toString());

            return incomeModel;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public ExpandModel getExpandInfo() {
        // validate
        // if (!validateNoteExpand() || !validateExpand()) {
        //     return null;
        // }
        if (!validateDateExpand() || !validateExpand()) {
                return null;
        }
        try {
            ExpandModel expandModel = new ExpandModel();

            if (expandId.getText() != null && !"".equals(expandId.getText())) {
                expandModel.setId(Integer.parseInt(expandId.getText()));
            }

            Date selectedDate;
            if (jDateChooserExpand.getDate() != null) {
                selectedDate = jDateChooserExpand.getDate();
            } else {
                selectedDate = calendar.getCalendar().getTime();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            expandModel.setDate(dateFormat.format(selectedDate));
            expandModel.setNote(noteExpandField.getText().trim());
            expandModel.setExpand(Long.parseLong(expandField.getText().trim()));
            expandModel.setCategory(jComboBoxExpand.getSelectedItem().toString());

            return expandModel;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }


    // validate
    // public boolean validateNoteIncome() {
    //     String note = noteIncomeField.getText();
    //     if (note == null || "".equals(note.trim())) {
    //         noteIncomeField.requestFocus();
    //         showMessage("Ghi chú không được trống.");
    //         return false;
    //     }
    //     return true;
    // }

    // public boolean validateNoteExpand() {
    //     String note = noteExpandField.getText();
    //     if (note == null || "".equals(note.trim())) {
    //         noteExpandField.requestFocus();
    //         showMessage("Ghi chú không được trống.");
    //         return false;
    //     }
    //     return true;
    // }

    public boolean validateIncome() {
        String income = incomeField.getText();
        if (income == null || "".equals(income.trim())) {
            incomeField.requestFocus();
            showMessage("Thu nhập không được trống.");
            return false;
        }
        return true;
    }

    public boolean validateExpand() {
        String expand = expandField.getText();
        if (expand == null || "".equals(expand.trim())) {
            expandField.requestFocus();
            showMessage("Khoản chi không được trống.");
            return false;
        }
        return true;
    }

    public boolean validateDateIncome() {
        Date date = jDateChooserIncome.getDate();
        if (date == null) {
            jDateChooserIncome.requestFocus();
            showMessage("Cần nhập đúng định dạng của ngày");
            return false;
        }
        return true;
    }

    public boolean validateDateExpand() {
        Date date = jDateChooserExpand.getDate();
        if (date == null) {
            jDateChooserExpand.requestFocus();
            showMessage("Cần nhập đúng định dạng của ngày");
            return false;
        }
        return true;
    }

    // In ra tổng thu nhập và chi tiêu

    public void printIncomeAndExpand(long t1, long t2) {
        totalIncome.setText(t1+" VNĐ");
        totalExpand.setText(t2+" VNĐ");
        if (t1 < t2) {
            showMessage("Chi tiêu vượt quá thu nhập.");
        }
    }

    // Lấy ngày tháng năm từ JDateChoooser

    public Date getDateForStatistics() {
        return chooseDateForStatistics.getDate();
    }


    public void actionPerformed(ActionEvent e) {
    }
    
    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void addAddIncomeListener(ActionListener listener) {
        addIncomeBtn.addActionListener(listener);
    }
    
    public void addEditIncomeListener(ActionListener listener) {
        editIncomeBtn.addActionListener(listener);
    }
    
    public void addDeleteIncomeListener(ActionListener listener) {
        deleteIncomeBtn.addActionListener(listener);
    }
    
    public void addClearIncomeListener(ActionListener listener) {
        clearIncomeBtn.addActionListener(listener);
    }
    
    public void addListIncomeSelectionListener(ListSelectionListener listener) {
        incomeTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void addAddExpandListener(ActionListener listener) {
        addExpandBtn.addActionListener(listener);
    }
    
    public void addEditExpandListener(ActionListener listener) {
        editExpandBtn.addActionListener(listener);
    }
    
    public void addDeleteExpandListener(ActionListener listener) {
        deleteExpandBtn.addActionListener(listener);
    }
    
    public void addClearExpandListener(ActionListener listener) {
        clearExpandBtn.addActionListener(listener);
    }

    public void addSortByDateListener(ActionListener listener) {
        sortDateBtn.addActionListener(listener);
    }

    public void addSortByMoneyListener(ActionListener listener) {
        sortMoneyBtn.addActionListener(listener);
    }

    public void addSortByCategoryListener(ActionListener listener) {
        sortCategoryBtn.addActionListener(listener);
    }

    public void addSortByIDListener(ActionListener listener) {
        sortIDBtn.addActionListener(listener);
    }
    
    public void addListExpandSelectionListener(ListSelectionListener listener) {
        expandTable.getSelectionModel().addListSelectionListener(listener);
    }
    public void addAllStatisticsListener(ActionListener listener) {
        allBtn.addActionListener(listener);
    }
    public void addDayStatisticsListener(ActionListener listener) {
        dayBtn.addActionListener(listener);
    }
    public void addMonthStatisticsListener(ActionListener listener) {
        monthBtn.addActionListener(listener);
    }
    public void addYearStatisticsListener(ActionListener listener) {
        yearBtn.addActionListener(listener);
    }
    public void addSearchListener(ActionListener listener) {
        searchBtn.addActionListener(listener);
    }
}
