package vn.viettuts.qlsv.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import vn.viettuts.qlsv.model.ExpandModel;
import vn.viettuts.qlsv.model.IncomeModel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.List;

public class SearchView extends JFrame{

    
    private JPanel panelIncome;
    private JPanel panelExpend;
    private Object data = new Object[][] {};

    private JScrollPane jScrollPaneIncomeTable, jScrollPaneExpandTable;
    private JTable incomeTable, expandTable;

    private String[] columnNamesIncome = new String[] {
        "ID", "Date", "Note", "Income", "Category"
    };
    private String[] columnNamesExpenses = new String[] {
        "ID", "Date", "Note", "Expenses", "Category"
    };
    private JLabel expandLabel;
    private JLabel incomeLabel;

    public SearchView() {
        initComponents();
    }

    private void initComponents() {
        initIncomePanel();
        initExpensesPanel();

        this.setLayout(new GridLayout(2,1));
        this.add(panelIncome);
        this.add(panelExpend);


        this.setTitle("Manage Income and Expenses");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        this.setResizable(false);
    }

    private void initExpensesPanel() {
        expandLabel = new JLabel("Expense");

        expandTable = new JTable();
        jScrollPaneExpandTable = new JScrollPane();
        expandTable.setModel(new DefaultTableModel((Object[][]) data, columnNamesExpenses));
        jScrollPaneExpandTable.setViewportView(expandTable);
        jScrollPaneExpandTable.setPreferredSize(new Dimension (560, 200));

        SpringLayout layout = new SpringLayout();
        panelExpend = new JPanel();
        panelExpend.setSize(600, 240);
        panelExpend.setLayout(layout);
        panelExpend.add(expandLabel);
        panelExpend.add(jScrollPaneExpandTable);

        layout.putConstraint(SpringLayout.WEST, expandLabel, 10, SpringLayout.WEST, panelExpend);
        layout.putConstraint(SpringLayout.NORTH, expandLabel, 10, SpringLayout.NORTH, panelExpend);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneExpandTable, 10, SpringLayout.WEST, panelExpend);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneExpandTable, 30, SpringLayout.NORTH, panelExpend);
    }

    private void initIncomePanel() {
        incomeLabel = new JLabel("Income");

        incomeTable = new JTable();
        jScrollPaneIncomeTable = new JScrollPane();
        incomeTable.setModel(new DefaultTableModel((Object[][]) data, columnNamesIncome));
        jScrollPaneIncomeTable.setViewportView(incomeTable);
        jScrollPaneIncomeTable.setPreferredSize(new Dimension (560, 200));

        SpringLayout layout = new SpringLayout();
        panelIncome = new JPanel();
        panelIncome.setSize(600, 240);
        panelIncome.setLayout(layout);
        panelIncome.add(incomeLabel);
        panelIncome.add(jScrollPaneIncomeTable);

        layout.putConstraint(SpringLayout.WEST, incomeLabel, 10, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, incomeLabel, 10, SpringLayout.NORTH, panelIncome);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneIncomeTable, 10, SpringLayout.WEST, panelIncome);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneIncomeTable, 30, SpringLayout.NORTH, panelIncome);
    }

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
}
