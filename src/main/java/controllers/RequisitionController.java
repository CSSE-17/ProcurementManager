package controllers;

import dao.GenericDAOImpl;
import dao.RequisitionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;
import models.*;
import util.UniqueID;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class RequisitionController implements Controller{
    @FXML
    private TextField txtReqID;
    @FXML
    private TableView tableItems;
    @FXML
    private TextField txtItemsFilter;
    @FXML
    private TableView tableSelectedItems;
    @FXML
    private TableView table_requisitions;
    @FXML
    private Button btnApprove;

    GenericDAOImpl dao;
    private ArrayList<SelectedItem> selectedItems;

    public void initialize(){
        dao = new GenericDAOImpl();
        tableItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        txtReqID.setDisable(true);
        generateRequisitionId();
        loadItems();
        initSelectedItems();
        loadRequisitions();

        if (!LoginController.getLoggedInUserType().equals("staff_manager")) {
            btnApprove.setDisable(true);
        }
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

    private void initSelectedItems() {
        tableSelectedItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<SelectedItem, String> itemNameCol = new TableColumn<>("Item Name");
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<SelectedItem, Number> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));

        qtyCol.setCellFactory(TextFieldTableCell.<SelectedItem, Number>forTableColumn(new NumberStringConverter()));
        qtyCol.setEditable(true);

        tableSelectedItems.setEditable(true);
        tableSelectedItems.getColumns().addAll(itemNameCol, qtyCol);
    }

    private ItemsEntity getSelectedItem() {
        return (ItemsEntity) tableItems.getSelectionModel().selectedItemProperty().get();
    }

    public void pickItem() {
        ItemsEntity item = getSelectedItem();
        SelectedItem selectedItem = new SelectedItem(item.getItemName(), 1);

        tableSelectedItems.getItems().add(selectedItem);
    }

    public void removeItem() {
        ObservableList<SelectedItem> itemSelected, allItems;
        allItems = tableSelectedItems.getItems();
        itemSelected = tableSelectedItems.getSelectionModel().getSelectedItems();

        itemSelected.forEach(allItems::remove);
    }

    public void createRequisition() {
        ObservableList<SelectedItem> allItems;
        allItems = tableSelectedItems.getItems();

        RequisitionEntity requisition = new RequisitionEntity();
        requisition.setRequisitionId(txtReqID.getText());
        requisition.setDate(new Timestamp(System.currentTimeMillis()));
        requisition.setCreatedBy(LoginController.getLoggedInUser());
        requisition.setStatus("pending");

        dao.setup();

        dao.create(requisition);

        for (SelectedItem item : allItems) {
            RequisitionItemEntity req_item = new RequisitionItemEntity();
            req_item.setReqId(requisition.getRequisitionId());
            req_item.setItemName(item.getItemName());
            req_item.setQty(item.getQty());
            req_item.setStatus("pending");

            dao.create(req_item);
        }

        clearForm();
        loadRequisitions();

        dao.exit();
    }

    private void loadRequisitions() {
        table_requisitions.getColumns().clear();

        dao.setup();
        ObservableList<RequisitionEntity> reqs = FXCollections.observableArrayList(dao.read(RequisitionEntity.class));
        dao.exit();

        TableColumn<RequisitionEntity, String> requisitionIdCol = new TableColumn<>("Requisition ID");
        requisitionIdCol.setCellValueFactory(new PropertyValueFactory<>("requisitionId"));

        TableColumn<RequisitionEntity, String> createdByCol = new TableColumn<>("Created By");
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));

        TableColumn<RequisitionEntity, Date> dateCol = new TableColumn<>("Created Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<RequisitionEntity, String> reqStatus = new TableColumn<>("Status");
        reqStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        table_requisitions.setItems(reqs);
        table_requisitions.getColumns().addAll(requisitionIdCol, createdByCol, dateCol, reqStatus);
    }

    public void approveRequisition() {
        RequisitionEntity req = (RequisitionEntity) table_requisitions.getSelectionModel().getSelectedItem();
        req.setStatus("approved");

        RequisitionDAO reqDAO = new RequisitionDAO();
        reqDAO.setup();

        reqDAO.update(req);

        reqDAO.exit();

        loadRequisitions();
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
        generateRequisitionId();
        tableSelectedItems.getItems().clear();
    }
}
