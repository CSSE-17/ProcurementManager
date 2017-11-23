package controllers;

import dao.RequisitionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.RequisitionEntity;

public class RequisitionController {

    @FXML
    private TextField req_id;

    @FXML
    private TextField mng_id;

    @FXML
    private Label emp_id;

    @FXML
    private TextField amount;

    @FXML
    private DatePicker req_date;

    @FXML
    private TextField item_name;

    @FXML
    private TableView<?> table_Offer;

    @FXML
    private TextField site_no;

    @FXML
    private TextField req_qty;

    @FXML
    private DatePicker grant_date;

    @FXML
    private TableView requisition_tbl;

    public void initialize(){


    }
    public void loadCashPaymentsTable() {

        TableColumn<RequisitionEntity, String> itemcode_col = new TableColumn<>("Item Code");
        itemcode_col.setCellValueFactory(new PropertyValueFactory<>("Item_code"));

        TableColumn<RequisitionEntity, String> itemname_col = new TableColumn<>("Item Name");
        itemname_col.setCellValueFactory(new PropertyValueFactory<>("Item_name"));

        TableColumn<RequisitionEntity, String> description_col = new TableColumn<>("Description");
        description_col.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<RequisitionEntity, Integer> quantity_col = new TableColumn<>("Quantity");
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<RequisitionEntity, String> receivefrm_col = new TableColumn<>("Receive From");
        receivefrm_col.setCellValueFactory(new PropertyValueFactory<>("receivefrm"));

        TableColumn<RequisitionEntity, String> receivedate_col = new TableColumn<>("Receive Date");
        receivedate_col.setCellValueFactory(new PropertyValueFactory<>("receivedate"));

        TableColumn<RequisitionEntity, String> expiredate_col = new TableColumn<>("Expire Date");
        expiredate_col.setCellValueFactory(new PropertyValueFactory<>("Expiredate"));

        TableColumn<RequisitionEntity, Integer> unit_price_col = new TableColumn<>("Unit Price");
        unit_price_col.setCellValueFactory(new PropertyValueFactory<>("unit_price"));

        TableColumn<RequisitionEntity, Integer> min_level_col = new TableColumn<>("Minimum qty Level");
        min_level_col.setCellValueFactory(new PropertyValueFactory<>("min_level"));

        RequisitionDAO dao = new RequisitionDAO();
        requisition_tbl.setItems(dao.getAll());

//        table_cash_payment.setItems(getAllCashPaymentData());
//        table_cash_payment.getColumns().clear();
//        table_cash_payment.getColumns().addAll(id_col, amount_col, date_col, payee_col);

    }



}
