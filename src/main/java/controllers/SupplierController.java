package controllers;

import dao.GenericDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.SupplierEntity;

import java.util.Optional;

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

        loadData();
        clearForm();
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
        tableSuppliers.getColumns().clear();

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

    @Override
    public void delete() {
        SupplierEntity supplier = getSelectedInstance();

        if (supplier != null) {
            Alert confDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confDialog.setTitle("Confirm action!");
            confDialog.setHeaderText("Are you sure you want to permanently delete supplier " + supplier.getName() + "?");
            Optional<ButtonType> result = confDialog.showAndWait();

            if (result.isPresent() && result.get() != ButtonType.OK) {
                return;
            }

            dao.setup();
            dao.delete(supplier);
            dao.exit();
            loadData();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Select a supplier first.");
            alert.showAndWait();
        }
    }

    @Override
    public <T> T getSelectedInstance() {
        SupplierEntity supplier = (SupplierEntity) tableSuppliers.getSelectionModel().selectedItemProperty().get();
        return (T) supplier;
    }

    @Override
    public void clearForm() {
        txtSupplierID.clear();
        txtSupplierName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
    }
}
