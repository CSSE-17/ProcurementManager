package models;

import javax.persistence.*;

/**
 * Created by HPkavin on 11/22/2017.
 */
@Entity
@Table(name = "requisition", schema = "procurement", catalog = "")
public class RequisitionEntity {
    private String reqId;
    private String managerId;
    private String empId;
    private String siteNo;
    private Integer qty;
    private Double amount;
    private String status;
    private String itemName;
    private String grantDate;
    private String reqDate;

    @Id
    @Column(name = "reqID")
    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    @Basic
    @Column(name = "managerID")
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "empID")
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Basic
    @Column(name = "siteNo")
    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }

    @Basic
    @Column(name = "qty")
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Basic
    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "itemName")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "grantDate")
    public String getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(String grantDate) {
        this.grantDate = grantDate;
    }

    @Basic
    @Column(name = "reqDate")
    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequisitionEntity that = (RequisitionEntity) o;

        if (reqId != null ? !reqId.equals(that.reqId) : that.reqId != null) return false;
        if (managerId != null ? !managerId.equals(that.managerId) : that.managerId != null) return false;
        if (empId != null ? !empId.equals(that.empId) : that.empId != null) return false;
        if (siteNo != null ? !siteNo.equals(that.siteNo) : that.siteNo != null) return false;
        if (qty != null ? !qty.equals(that.qty) : that.qty != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (grantDate != null ? !grantDate.equals(that.grantDate) : that.grantDate != null) return false;
        if (reqDate != null ? !reqDate.equals(that.reqDate) : that.reqDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reqId != null ? reqId.hashCode() : 0;
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        result = 31 * result + (empId != null ? empId.hashCode() : 0);
        result = 31 * result + (siteNo != null ? siteNo.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (grantDate != null ? grantDate.hashCode() : 0);
        result = 31 * result + (reqDate != null ? reqDate.hashCode() : 0);
        return result;
    }
}
