package controllers;

import dao.GenericDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.SupplierEntity;

public class SupplierController implements Controller {
    @FXML
    private TextField txtSupplierID;
    @FXML
    private TextField txtSupplierName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextArea txtAddress;

    @Override
    public void create() {
        GenericDAOImpl dao = new GenericDAOImpl();

        SupplierEntity supplier = getFormData();

        dao.setup();
        dao.create(supplier);
        dao.exit();
    }

    @Override
    public <T> T getFormData() {
        SupplierEntity supplier = new SupplierEntity();

        supplier.setSupplierId(txtSupplierID.getText());
        supplier.setName(txtSupplierName.getText());
        supplier.setEmail(txtEmail.getText());
        supplier.setPhone(txtPhone.getText());
        supplier.setAddress(txtAddress.getText());

        return (T) supplier;
    }
}
