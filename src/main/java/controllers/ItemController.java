package controllers;

import dao.GenericDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.ItemsEntity;

import java.util.Optional;

public class ItemController implements Controller {
    @FXML
    private TextField txtItemName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtBrand;
    @FXML
    private TableView tableItems;


    GenericDAOImpl dao;

    public void initialize(){
        dao = new GenericDAOImpl();
        tableItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadData();
    }

    @Override
    public void create() {
        ItemsEntity item = getFormData();

        dao.setup();
        dao.create(item);
        dao.exit();

        loadData();
        clearForm();
    }

    @Override
    public <T> T getFormData() {
        ItemsEntity item = new ItemsEntity();

        item.setItemName(txtItemName.getText());
        item.setPrice(Float.parseFloat(txtPrice.getText()));
        item.setBrand(txtBrand.getText());

        return (T) item;
    }

    @Override
    public void loadData() {
        tableItems.getColumns().clear();

        dao.setup();
        ObservableList<ItemsEntity> itm = FXCollections.observableArrayList(dao.read(ItemsEntity.class));
        dao.exit();

        TableColumn<ItemsEntity, String> itemNameCol = new TableColumn<>("Item Name");
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<ItemsEntity, Float> itemPriceCol = new TableColumn<>("Price");
        itemPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ItemsEntity, String> itemBrandCol = new TableColumn<>("Brand");
        itemBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));


        tableItems.setItems(itm);
        tableItems.getColumns().addAll(itemNameCol, itemPriceCol, itemBrandCol);
    }

    @Override
    public void delete() {
        ItemsEntity item = getSelectedInstance();

        if (item != null) {
            Alert confDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confDialog.setTitle("Confirm action!");
            confDialog.setHeaderText("Are you sure you want to permanently delete supplier " + item.getItemName() + "?");
            Optional<ButtonType> result = confDialog.showAndWait();

            if (result.isPresent() && result.get() != ButtonType.OK) {
                return;
            }

            dao.setup();
            dao.delete(item);
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
        ItemsEntity item = (ItemsEntity) tableItems.getSelectionModel().selectedItemProperty().get();
        return (T) item;
    }

    @Override
    public void clearForm() {
        txtItemName.clear();
        txtPrice.clear();
        txtBrand.clear();
    }
}