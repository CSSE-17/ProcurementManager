package models;

import javax.persistence.*;

@Entity
@Table(name = "requisition_item", schema = "procurement_db", catalog = "")
public class RequisitionItemEntity {
    private String reqId;
    private Integer qty;

    @Id
    @Column(name = "reqID")
    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    @Basic
    @Column(name = "qty")
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequisitionItemEntity that = (RequisitionItemEntity) o;

        if (reqId != null ? !reqId.equals(that.reqId) : that.reqId != null) return false;
        if (qty != null ? !qty.equals(that.qty) : that.qty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reqId != null ? reqId.hashCode() : 0;
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        return result;
    }
}
