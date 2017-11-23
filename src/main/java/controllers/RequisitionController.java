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

        TableColumn<RequisitionEntity, String> itemcode_col = new TableColumn<>("Purchase ID");
        itemcode_col.setCellValueFactory(new PropertyValueFactory<>("purchID"));

        TableColumn<RequisitionEntity, String> itemname_col = new TableColumn<>("Manager ID");
        itemname_col.setCellValueFactory(new PropertyValueFactory<>("ManagerID"));

        TableColumn<RequisitionEntity, String> description_col = new TableColumn<>("Supplier ID");
        description_col.setCellValueFactory(new PropertyValueFactory<>("supID"));

        TableColumn<RequisitionEntity, Integer> SupName = new TableColumn<>("Supplier Name");
        SupName.setCellValueFactory(new PropertyValueFactory<>("SupName"));

        TableColumn<RequisitionEntity, Integer> qty = new TableColumn<>("Quantity");
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        TableColumn<RequisitionEntity, String> amount = new TableColumn<>("Amount");
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<RequisitionEntity, String> itemName = new TableColumn<>("Item Name ");
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<RequisitionEntity, String> status = new TableColumn<>("status");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<RequisitionEntity, Integer> grantDate = new TableColumn<>("Grant Date");
        grantDate.setCellValueFactory(new PropertyValueFactory<>("grantDate"));

        TableColumn<RequisitionEntity, Integer> payDate = new TableColumn<>("payment Date");
        payDate.setCellValueFactory(new PropertyValueFactory<>("payDate"));



        RequisitionDAO dao = new RequisitionDAO();
        requisition_tbl.setItems(dao.getAll());

//        table_cash_payment.setItems(getAllCashPaymentData());
//        table_cash_payment.getColumns().clear();
//        table_cash_payment.getColumns().addAll(id_col, amount_col, date_col, payee_col);

    }



}
