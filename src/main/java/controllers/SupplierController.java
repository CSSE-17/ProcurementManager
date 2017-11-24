package controllers;

import dao.GenericDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private TableView tableSuppliers;


    GenericDAOImpl dao;

    public void initialize(){
        dao = new GenericDAOImpl();
        tableSuppliers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadData();
    }

    @Override
    public void create() {
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

    @Override
    public void loadData() {
        dao.setup();
        ObservableList<SupplierEntity> sups = FXCollections.observableArrayList(dao.read(SupplierEntity.class));
        dao.exit();

        TableColumn<SupplierEntity, String> supplierIdCol = new TableColumn<>("Supplier ID");
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        TableColumn<SupplierEntity, String> supplierNameCol = new TableColumn<>("Name");
        supplierNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<SupplierEntity, String> supplierEmailCol = new TableColumn<>("Email");
        supplierEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<SupplierEntity, String> supplierPhoneCol = new TableColumn<>("Phone");
        supplierPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<SupplierEntity, String> supplierAddressCol = new TableColumn<>("Address");
        supplierAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));


        tableSuppliers.setItems(sups);
        tableSuppliers.getColumns().addAll(supplierIdCol, supplierNameCol, supplierEmailCol,
                supplierPhoneCol, supplierAddressCol);
    }
}
