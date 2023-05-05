package vn.viettuts.qlsv.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "expandList")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExpandModelXML {
    private List<ExpandModel> expand;

    public List<ExpandModel> getExpand() {
        return expand;
    }

    public void setExpand(List<ExpandModel> expand) {
        this.expand = expand;
    }
}
