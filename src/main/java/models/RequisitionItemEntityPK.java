package models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RequisitionItemEntityPK implements Serializable {
    private String reqId;
    private String itemName;

    @Column(name = "reqID")
    @Id
    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    @Column(name = "itemName")
    @Id
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequisitionItemEntityPK that = (RequisitionItemEntityPK) o;

        if (reqId != null ? !reqId.equals(that.reqId) : that.reqId != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reqId != null ? reqId.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        return result;
    }
}
