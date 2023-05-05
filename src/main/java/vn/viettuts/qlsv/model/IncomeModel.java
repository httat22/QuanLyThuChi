package vn.viettuts.qlsv.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="income")
@XmlAccessorType(XmlAccessType.FIELD)
public class IncomeModel implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String date;
    private String note;
    private long income;
    private String category;
    
    public IncomeModel(int id, String date, String note, long income, String category) {
        super();
        this.id = id;
        this.date = date;
        this.note = note;
        this.income = income;
        this.category = category;
    }
    public IncomeModel() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public long getIncome() {
        return income;
    }
    public void setIncome(long income) {
        this.income = income;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
