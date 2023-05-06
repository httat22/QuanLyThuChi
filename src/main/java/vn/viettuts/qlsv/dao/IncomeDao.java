package vn.viettuts.qlsv.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import vn.viettuts.qlsv.model.IncomeModel;
import vn.viettuts.qlsv.model.IncomeModelXML;
import vn.viettuts.qlsv.utils.FileUtils;

public class IncomeDao {
    private static final String INCOME_FILE_NAME = "income.xml";
    private static int id = 0;

    private List<IncomeModel> incomeList;

    public IncomeDao() {
        this.incomeList = readIncomeList();
        if (incomeList == null) {
            incomeList = new ArrayList<>();
            id = 0;
        } else {
            id = incomeList.get(incomeList.size() - 1).getId();
        }
    }

    public void writeIncomeList(List<IncomeModel> list) {
        IncomeModelXML incomeModelXML = new IncomeModelXML();
        incomeModelXML.setIncome(list);
        FileUtils.writeXMLtoFile(INCOME_FILE_NAME, incomeModelXML);
    }

    public List<IncomeModel> readIncomeList() {
        List<IncomeModel> list = new ArrayList<>();
        IncomeModelXML incomeModelXML = (IncomeModelXML) FileUtils.readXMLFile(
            INCOME_FILE_NAME, IncomeModelXML.class
        );
        if (incomeModelXML != null) {
            list = incomeModelXML.getIncome();
        }

        return list;
    }

    public void add(IncomeModel incomeModel) {
        id++;
        incomeModel.setId(id);
        incomeList.add(incomeModel);
        writeIncomeList(incomeList);
    }

    public void edit(IncomeModel incomeModel) {
        int size = incomeList.size();
        System.out.println(incomeModel.getId());
        for (int i = 0; i<size; i++) {
            System.out.print(incomeList.get(i).getId() + " ");
            if (incomeList.get(i).getId() == incomeModel.getId()) {
                
                incomeList.get(i).setDate(incomeModel.getDate());
                incomeList.get(i).setNote(incomeModel.getNote());
                incomeList.get(i).setIncome(incomeModel.getIncome());
                incomeList.get(i).setCategory(incomeModel.getCategory());
                writeIncomeList(incomeList);
                break;
            }
        }
    }

    public boolean delete(IncomeModel incomeModel) {
        boolean isFound = false;
        int size = incomeList.size();
        for (int i = 0; i < size; i++) {
            if (incomeList.get(i).getId() == incomeModel.getId()) {
                incomeModel = incomeList.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            incomeList.remove(incomeModel);
            writeIncomeList(incomeList);
            return true;
        }
        return false;
    }

    public void sortIncomeByDate() {
        Collections.sort(incomeList, new Comparator<IncomeModel>() {
            public int compare(IncomeModel incomeModel1, IncomeModel incomeModel2) {
                Date date1 = new Date();
                Date date2 = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateString1 = incomeModel1.getDate();
                String dateString2 = incomeModel2.getDate();
                try {
                    date1 = dateFormat.parse(dateString1);
                    date2 = dateFormat.parse(dateString2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date1.compareTo(date2);
            }
        });
    }

    public void sortIncomeByID() {
        Collections.sort(incomeList, new Comparator<IncomeModel>() {
            public int compare(IncomeModel incomeModel1, IncomeModel incomeModel2) {
                int income1 = incomeModel1.getId();
                int income2 = incomeModel2.getId();
                return Integer.compare(income1, income2);
            }
        });
    }

    public void sortIncomeByMoney() {
        Collections.sort(incomeList, new Comparator<IncomeModel>() {
            public int compare(IncomeModel incomeModel1, IncomeModel incomeModel2) {
                long income1 = incomeModel1.getIncome();
                long income2 = incomeModel2.getIncome();
                return Long.compare(income1, income2);
            }
        });
    }

    public void sortIncomeByCategory() {
        Collections.sort(incomeList, new Comparator<IncomeModel>() {
            public int compare(IncomeModel incomeModel1, IncomeModel incomeModel2) {
                String income1 = incomeModel1.getCategory();
                String income2 = incomeModel2.getCategory();
                return income1.compareTo(income2);
            }
        });
    }

    public List<IncomeModel> searchByDate(Date date) {
        List<IncomeModel> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(date);
        for (IncomeModel i : incomeList) {
            if (i.getDate().equals(dateString)) {
                list.add(i);
            }
        }
        return list;
    }

    public long totalIncomeAll() {
        long total = 0;
        for (IncomeModel e : incomeList) {
            total += e.getIncome();
        }
        return total;
    }

    public long totalIncomeDay(Date date) {
        long total = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayChooser = calendar.get(Calendar.DAY_OF_MONTH);
        int monthChooser = calendar.get(Calendar.MONTH);
        int yearChooser = calendar.get(Calendar.YEAR);
        
        for (IncomeModel e : incomeList) {
            String dateStr = e.getDate();
            String[] parts = dateStr.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            if (day == dayChooser && month == monthChooser+1 && year == yearChooser) {
                total += e.getIncome();
            }
        }
        return total;
    }

    public long totalIncomeMonth(Date date) {
        long total = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int monthChooser = calendar.get(Calendar.MONTH);
        int yearChooser = calendar.get(Calendar.YEAR);
        
        for (IncomeModel e : incomeList) {
            String dateStr = e.getDate();
            String[] parts = dateStr.split("/");
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            ///////////////////////////////////////////////////////
            if (month == monthChooser+1 && year == yearChooser) {
                total += e.getIncome();
            }
        }
        return total;
    }

    public long totalIncomeYear(Date date) {
        long total = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int yearChooser = calendar.get(Calendar.YEAR);
        
        for (IncomeModel e : incomeList) {
            String dateStr = e.getDate();
            String[] parts = dateStr.split("/");
            int year = Integer.parseInt(parts[2]);
            if (year == yearChooser) {
                total += e.getIncome();
            }
        }
        return total;
    }

    public List<IncomeModel> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<IncomeModel> incomeList) {
        this.incomeList = incomeList;
    }
}
