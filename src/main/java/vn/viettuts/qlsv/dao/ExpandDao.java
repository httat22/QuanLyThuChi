package vn.viettuts.qlsv.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import vn.viettuts.qlsv.model.ExpandModel;
import vn.viettuts.qlsv.model.ExpandModelXML;
import vn.viettuts.qlsv.utils.FileUtils;

public class ExpandDao {
    private static final String EXPAND_FILE_NAME = "expand.xml";
    private List<ExpandModel> expandList;
    private static int id;

    public ExpandDao() {
        this.expandList = readExpandList();
        if (expandList == null) {
            expandList = new ArrayList<>();
            id = 0;
        } else {
            id = expandList.get(expandList.size() - 1).getId();
        }
    }

    public void writeExpandList(List<ExpandModel> list) {
        ExpandModelXML ExpandModelXML = new ExpandModelXML();
        ExpandModelXML.setExpand(list);
        FileUtils.writeXMLtoFile(EXPAND_FILE_NAME, ExpandModelXML);
    }

    public List<ExpandModel> readExpandList() {
        List<ExpandModel> list = new ArrayList<>();
        ExpandModelXML ExpandModelXML = (ExpandModelXML) FileUtils.readXMLFile(
            EXPAND_FILE_NAME, ExpandModelXML.class
        );
        if (ExpandModelXML != null) {
            list = ExpandModelXML.getExpand();
        }

        return list;
    }

    public void add(ExpandModel ExpandModel) {
        id++;
        ExpandModel.setId(id);
        expandList.add(ExpandModel);
        writeExpandList(expandList);
    }

    public void edit(ExpandModel ExpandModel) {
        int size = expandList.size();
        System.out.println(ExpandModel.getId());
        for (int i = 0; i<size; i++) {
            System.out.print(expandList.get(i).getId() + " ");
            if (expandList.get(i).getId() == ExpandModel.getId()) {
                
                expandList.get(i).setDate(ExpandModel.getDate());
                expandList.get(i).setNote(ExpandModel.getNote());
                expandList.get(i).setExpand(ExpandModel.getExpand());
                expandList.get(i).setCategory(ExpandModel.getCategory());
                writeExpandList(expandList);
                break;
            }
        }
    }

    public boolean delete(ExpandModel ExpandModel) {
        boolean isFound = false;
        int size = expandList.size();
        for (int i = 0; i < size; i++) {
            if (expandList.get(i).getId() == ExpandModel.getId()) {
                ExpandModel = expandList.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            expandList.remove(ExpandModel);
            writeExpandList(expandList);
            return true;
        }
        return false;
    }

    public void sortExpandByDate() {
        Collections.sort(expandList, new Comparator<ExpandModel>() {
            public int compare(ExpandModel expandModel1, ExpandModel expandModel2) {
                Date date1 = new Date();
                Date date2 = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateString1 = expandModel1.getDate();
                String dateString2 = expandModel2.getDate();
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

    public void sortExpandByMoney() {
        Collections.sort(expandList, new Comparator<ExpandModel>() {
            public int compare(ExpandModel expandModel1, ExpandModel expandModel2) {
                long expand1 = expandModel1.getExpand();
                long expand2 = expandModel2.getExpand();
                return Long.compare(expand1, expand2);
            }
        });
    }

    public void sortExpandByID() {
        Collections.sort(expandList, new Comparator<ExpandModel>() {
            public int compare(ExpandModel expandModel1, ExpandModel expandModel2) {
                int expand1 = expandModel1.getId();
                int expand2 = expandModel2.getId();
                return Integer.compare(expand1, expand2);
            }
        });
    }

    public void sortExpandByCategory() {
        Collections.sort(expandList, new Comparator<ExpandModel>() {
            public int compare(ExpandModel expandModel1, ExpandModel expandModel2) {
                String expand1 = expandModel1.getCategory();
                String expand2 = expandModel2.getCategory();
                return expand1.compareTo(expand2);
            }
        });
    }

    public long totalExpandAll() {
        int total = 0;
        for (ExpandModel e : expandList) {
            total += e.getExpand();
        }
        return total;
    }

    public long totalExpandDay(Date date) {
        long total = 0;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayChooser = calendar.get(Calendar.DAY_OF_MONTH);
        int monthChooser = calendar.get(Calendar.MONTH);
        int yearChooser = calendar.get(Calendar.YEAR);
        
        for (ExpandModel e : expandList) {
            String dateStr = e.getDate();
            String[] parts = dateStr.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            if (day == dayChooser && month == monthChooser+1 && year == yearChooser) {
                total += e.getExpand();
            }
        }
        return total;
    }

    public long totalExpandMonth(Date date) {
        long total = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int monthChooser = calendar.get(Calendar.MONTH);
        int yearChooser = calendar.get(Calendar.YEAR);
        
        for (ExpandModel e : expandList) {
            String dateStr = e.getDate();
            String[] parts = dateStr.split("/");
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            if (month == monthChooser+1 && year == yearChooser) {
                total += e.getExpand();
            }
        }
        return total;
    }

    public long totalExpandYear(Date date) {
        long total = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int yearChooser = calendar.get(Calendar.YEAR);
        
        for (ExpandModel e : expandList) {
            String dateStr = e.getDate();
            String[] parts = dateStr.split("/");
            int year = Integer.parseInt(parts[2]);
            if (year == yearChooser) {
                total += e.getExpand();
            }
        }
        return total;
    }

    public List<ExpandModel> getExpandList() {
        return expandList;
    }

    public void setExpandList(List<ExpandModel> expandList) {
        this.expandList = expandList;
    }
}
