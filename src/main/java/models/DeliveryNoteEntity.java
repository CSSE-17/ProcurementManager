package models;

import javax.persistence.*;

/**
 * Created by HPkavin on 11/24/2017.
 */
@Entity
@Table(name = "delivery_note", schema = "procurement_db", catalog = "")
public class DeliveryNoteEntity {
    private String noteID;
    private String purchID;
    private String requisitionID;
    private Double totalAmount;
    private String status;
    private String paidDate;

    @Id
    @Column(name = "noteID")
    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    @Basic
    @Column(name = "purchID")
    public String getPurchID() {
        return purchID;
    }

    public void setPurchID(String purchId) {
        this.purchID = purchId;
    }

    @Basic
    @Column(name = "requisitionID")
    public String getRequisitionID() {
        return requisitionID;
    }

    public void setRequisitionID(String requisitionId) {
        this.requisitionID = requisitionId;
    }

    @Basic
    @Column(name = "totalAmount")
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
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
    @Column(name = "paidDate")
    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryNoteEntity that = (DeliveryNoteEntity) o;

        if (noteID != null ? !noteID.equals(that.noteID) : that.noteID != null) return false;
        if (purchID != null ? !purchID.equals(that.purchID) : that.purchID != null) return false;
        if (requisitionID != null ? !requisitionID.equals(that.requisitionID) : that.requisitionID != null)
            return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (paidDate != null ? !paidDate.equals(that.paidDate) : that.paidDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noteID != null ? noteID.hashCode() : 0;
        result = 31 * result + (purchID != null ? purchID.hashCode() : 0);
        result = 31 * result + (requisitionID != null ? requisitionID.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (paidDate != null ? paidDate.hashCode() : 0);
        return result;
    }
}
