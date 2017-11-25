package controllers;

import dao.GenericDAOImpl;
import dao.SupplierDAO;
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
    @FXML
    private Button btnUpdate;

    GenericDAOImpl dao;

    public void initialize(){
        dao = new GenericDAOImpl();
        tableSuppliers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadData();

        tableSuppliers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            SupplierEntity entity = (SupplierEntity) obs.getValue();

            if (entity == null) {
                return;
            }

            String supId = entity.getSupplierId();
            String SupName = entity.getName();
            String address = entity.getAddress();
            String email = entity.getEmail();
            String phone = entity.getPhone();


            txtSupplierID.setText(supId);
            txtSupplierName.setText(SupName);
            txtAddress.setText(address);
            txtEmail.setText(email);
            txtPhone.setText(phone);

           // btnAdd.setVisible(false);
            btnUpdate.setVisible(true);
          //  btn_delete.setVisible(true);
        });

    }

    public void update() {
        SupplierEntity supplier = getFormData();
        SupplierDAO dao = new SupplierDAO();

        dao.setup();
        dao.update(supplier);
        dao.exit();

        loadData();
        clearForm();
    }

    @Override
    public void create() {
        SupplierEntity supplier = getFormData();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Alert");
            alert.setHeaderText("Item Add Confirmation");
            alert.setContentText("Do you want to Add this Item Details ? ");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao.setup();
                dao.create(supplier);
                dao.exit();
            } else {
            }
            loadData();
            clearForm();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Fields Cannot be Empty");
            alert.showAndWait();
        }
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
