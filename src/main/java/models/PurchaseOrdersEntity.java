package models;

import javax.persistence.*;

@Entity
@Table(name = "purchase_orders", schema = "procurement_db")
public class PurchaseOrdersEntity {
    private String poid;
    private String reqid;
    private String supplierId;
    private double totalAmount;

    @Id
    @Column(name = "poid")
    public String getPoid() {
        return poid;
    }

    public void setPoid(String poid) {
        this.poid = poid;
    }

    @Basic
    @Column(name = "reqid")
    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    @Basic
    @Column(name = "supplier_id")
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "total_amount")
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseOrdersEntity that = (PurchaseOrdersEntity) o;

        if (Double.compare(that.totalAmount, totalAmount) != 0) return false;
        if (poid != null ? !poid.equals(that.poid) : that.poid != null) return false;
        if (reqid != null ? !reqid.equals(that.reqid) : that.reqid != null) return false;
        if (supplierId != null ? !supplierId.equals(that.supplierId) : that.supplierId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = poid != null ? poid.hashCode() : 0;
        result = 31 * result + (reqid != null ? reqid.hashCode() : 0);
        result = 31 * result + (supplierId != null ? supplierId.hashCode() : 0);
        temp = Double.doubleToLongBits(totalAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}