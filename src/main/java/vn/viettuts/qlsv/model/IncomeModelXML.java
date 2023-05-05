package vn.viettuts.qlsv.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "incomeList")
@XmlAccessorType(XmlAccessType.FIELD)
public class IncomeModelXML {
    private List<IncomeModel> income;

    public List<IncomeModel> getIncome() {
        return income;
    }

    public void setIncome(List<IncomeModel> income) {
        this.income = income;
    }
}
