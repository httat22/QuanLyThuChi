package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.viettuts.qlsv.dao.ExpandDao;
import vn.viettuts.qlsv.dao.IncomeDao;
import vn.viettuts.qlsv.model.ExpandModel;
import vn.viettuts.qlsv.model.IncomeModel;
import vn.viettuts.qlsv.view.AppView;

public class AppController {
    private AppView appView;
    private IncomeDao incomeDao;
    private ExpandDao expandDao;

    public AppController(AppView view) {
        this.appView = view;
        incomeDao = new IncomeDao();
        expandDao = new ExpandDao();

        view.addAddIncomeListener(new AddIncomeListener());
        view.addEditIncomeListener(new EditIncomeListener());
        view.addDeleteIncomeListener(new DeleteIncomeListener());
        view.addClearIncomeListener(new ClearIncomeListener());
        view.addListIncomeSelectionListener(new ListIncomeSelectionListener());

        view.addAddExpandListener(new AddExpandListener());
        view.addEditExpandListener(new EditExpandListener());
        view.addDeleteExpandListener(new DeleteExpandListener());
        view.addClearExpandListener(new ClearExpandListener());

        view.addListExpandSelectionListener(new ListExpandSelectionListener());

        view.addSortByDateListener(new SortByDateListener());
        view.addSortByMoneyListener(new SortByMoneyListener());
        view.addSortByCategoryListener(new SortByCategoryListener());
        view.addSortByIDListener(new SortByIDListener());
        
        view.addAllStatisticsListener(new AllStatisticsListener());
        view.addDayStatisticsListener(new DayStatisticsListener());
        view.addMonthStatisticsListener(new MonthStatisticsListener());
        view.addYearStatisticsListener(new YearStatisticsListener());
    
    }

    public void showAppView() {
        List<IncomeModel> incomeList = incomeDao.getIncomeList();
        List<ExpandModel> expandList = expandDao.getExpandList();
        appView.setVisible(true);
        incomeDao.sortIncomeByID();
        expandDao.sortExpandByID();
        appView.showListIncome(incomeList);
        appView.showListExpand(expandList);
    }

    /**
     * Lớp AddStudentListener 
     * chứa cài đặt cho sự kiện click button "Add"
     * 
     * @author viettuts.vn
     */

    class AddIncomeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            IncomeModel incomeModel = appView.getIncomeInfo();
            if (incomeModel != null) {
                incomeDao.add(incomeModel);
                appView.showIncome(incomeModel);
                appView.showListIncome(incomeDao.getIncomeList());
                appView.showMessage("Thêm thành công!");
            }
        }
    }

    class EditIncomeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            IncomeModel incomeModel = appView.getIncomeInfo();
            if (incomeModel != null) {
                incomeDao.edit(incomeModel);
                appView.showIncome(incomeModel);
                appView.showListIncome(incomeDao.getIncomeList());
                appView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class DeleteIncomeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            IncomeModel incomeModel = appView.getIncomeInfo();
            if (incomeModel != null) {
                incomeDao.delete(incomeModel);
                appView.clearIncomeInfo();
                appView.showListIncome(incomeDao.getIncomeList());
                appView.showMessage("Xóa thành công!");
            }
        }
    }

    class ClearIncomeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            appView.clearIncomeInfo();
        }
    }

    class ListIncomeSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            appView.fillIncomeFromSelectedRow();
        }
    }

    class AddExpandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ExpandModel expandModel = appView.getExpandInfo();
            if (expandModel != null) {
                expandDao.add(expandModel);
                appView.showExpand(expandModel);
                appView.showListExpand(expandDao.getExpandList());
                appView.showMessage("Thêm thành công!");
            }
        }
    }

    class EditExpandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ExpandModel expandModel = appView.getExpandInfo();
            if (expandModel != null) {
                expandDao.edit(expandModel);
                appView.showExpand(expandModel);
                appView.showListExpand(expandDao.getExpandList());
                appView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class DeleteExpandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ExpandModel expandModel = appView.getExpandInfo();
            if (expandModel != null) {
                expandDao.delete(expandModel);
                appView.clearExpandInfo();
                appView.showListExpand(expandDao.getExpandList());
                appView.showMessage("Xóa thành công!");
            }
        }
    }

    class ClearExpandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            appView.clearExpandInfo();
        }
    }

    class ListExpandSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            appView.fillExpandFromSelectedRow();
        }
    }

    class SortByDateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            incomeDao.sortIncomeByDate();
            expandDao.sortExpandByDate();
            appView.showListIncome(incomeDao.getIncomeList());
            appView.showListExpand(expandDao.getExpandList());
        }
    }

    class SortByIDListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            incomeDao.sortIncomeByID();
            expandDao.sortExpandByID();
            appView.showListIncome(incomeDao.getIncomeList());
            appView.showListExpand(expandDao.getExpandList());
        }
    }

    class SortByMoneyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            incomeDao.sortIncomeByMoney();
            expandDao.sortExpandByMoney();
            appView.showListIncome(incomeDao.getIncomeList());
            appView.showListExpand(expandDao.getExpandList());
        }
    }

    class SortByCategoryListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            incomeDao.sortIncomeByCategory();
            expandDao.sortExpandByCategory();
            appView.showListIncome(incomeDao.getIncomeList());
            appView.showListExpand(expandDao.getExpandList());
        }
    }

    class AllStatisticsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            long totalIncome = incomeDao.totalIncomeAll();
            long totalExpand = expandDao.totalExpandAll();
            appView.printIncomeAndExpand(totalIncome, totalExpand);
        }
    }
    class DayStatisticsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Date date = appView.getDateForStatistics();
            long totalIncome = incomeDao.totalIncomeDay(date);
            long totalExpand = expandDao.totalExpandDay(date);
            appView.printIncomeAndExpand(totalIncome, totalExpand);
        }
    }
    class MonthStatisticsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Date date = appView.getDateForStatistics();

            long totalIncome = incomeDao.totalIncomeMonth(date);
            long totalExpand = expandDao.totalExpandMonth(date);
            appView.printIncomeAndExpand(totalIncome, totalExpand);
        }
    }
    class YearStatisticsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Date date = appView.getDateForStatistics();

            long totalIncome = incomeDao.totalIncomeYear(date);
            long totalExpand = expandDao.totalExpandYear(date);
            appView.printIncomeAndExpand(totalIncome, totalExpand);
        }
    }
}
