package vn.viettuts.qlsv.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="expand")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExpandModel implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String date;
    private String note;
    private Long expand;
    private String category;
    
    public ExpandModel(int id, String date, String note, Long expand, String category) {
        super();
        this.id = id;
        this.date = date;
        this.note = note;
        this.expand = expand;
        this.category = category;
    }
    public ExpandModel() {
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
    public Long getExpand() {
        return expand;
    }
    public void setExpand(Long expand) {
        this.expand = expand;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}

