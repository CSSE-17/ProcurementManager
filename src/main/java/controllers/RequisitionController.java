package controllers;

import dao.GenericDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.ItemsEntity;
import util.UniqueID;

public class RequisitionController implements Controller{
    @FXML
    private TextField txtReqID;
    @FXML
    private TableView tableItems;
    @FXML
    private ChoiceBox chbItemName;
    @FXML
    private TextField txtItemsFilter;

    GenericDAOImpl dao;

    public void initialize(){
        dao = new GenericDAOImpl();
        tableItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        txtReqID.setDisable(true);
        generateRequisitionId();
        loadItems();
    }

    private void generateRequisitionId() {
        String reqId = UniqueID.generateUniqueId();
        txtReqID.setText(reqId);
    }

    private void loadItems() {
        dao.setup();
        ObservableList<ItemsEntity> items = FXCollections.observableArrayList(dao.read(ItemsEntity.class));
        dao.exit();

        TableColumn<ItemsEntity, String> itemNameCol = new TableColumn<>("Name");
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<ItemsEntity, Double> itemPriceCol = new TableColumn<>("Price");
        itemPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ItemsEntity, Double> brandCol = new TableColumn<>("Brand");
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));

        FilteredList<ItemsEntity> filteredItems = new FilteredList<>(items, p -> true);

        txtItemsFilter.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredItems.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getItemName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<ItemsEntity> sortedItems = new SortedList<>(filteredItems);
        sortedItems.comparatorProperty().bind(tableItems.comparatorProperty());

        tableItems.setItems(sortedItems);
        tableItems.getColumns().addAll(itemNameCol, itemPriceCol, brandCol);
    }

    @Override
    public void create() {

    }

    @Override
    public <T> T getFormData() {
        return null;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void delete() {

    }

    @Override
    public <T> T getSelectedInstance() {
        return null;
    }

    @Override
    public void clearForm() {

    }
}
