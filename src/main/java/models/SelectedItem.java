package models;

public class SelectedItem {
    private String itemName;
    private int qty;

    public SelectedItem(String itemName, int qty) {
        this.itemName = itemName;
        this.qty = qty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
