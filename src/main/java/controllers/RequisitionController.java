package controllers;

import dao.RequisitionDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.RequisitionEntity;
import util.FormValidate;

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
    @FXML
    private TextField gReq_id;

    @FXML
    private TextField eMng_id1;

    @FXML
    private TextField gPersonName;

    @FXML
    private Label emp_id1;

    @FXML
    private DatePicker eReq_date;

    @FXML
    private Button gGrant;

    @FXML
    private Label gStatus;

    @FXML
    private DatePicker gGrant_date;

    @FXML
    private TableView<?> gRequisition_tbl;

    @FXML
    private Button gDenied;

    public void initialize(){


    }
    public void addSupplier() {
        try {
            if (!checkInventoryEmptyFields()) {
                String reqId = req_id.getText();
                String mngId = mng_id.getText();
                String empId = emp_id.getText();
                String amountP = amount.getText();
                String ReqDate = req_date.getValue().toString();
                String itemName = item_name.getText();
                String siteNo = site_no.toString();
                String reqQty = req_qty.getText();
//                String grantDate = grant_date.getValue();


                RequisitionDAO  requisitionDio = new RequisitionDAO();
                requisitionDio.setup();
                RequisitionEntity  newRequisition = new RequisitionEntity();


                newRequisition.setReqId(reqId);
                newRequisition.setManagerId(mngId);
                newRequisition.setEmpId(empId);
                newRequisition.setAmount(Double.parseDouble(amountP));
                newRequisition.setReqDate(ReqDate);
                newRequisition.setItemName(itemName);
                newRequisition.setSiteNo(siteNo);
                newRequisition.setQty(Integer.parseInt(reqQty));
                newRequisition.setStatus("None");

                requisitionDio.create(newRequisition);
                requisitionDio.exit();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Alert");
                alert.setHeaderText("Requisition Adding Request");
                alert.setContentText("Requisition Information is added Successfully");
                alert.showAndWait();

            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Fields Cannot be Empty");
            alert.showAndWait();
//            LOG.info("Fields Cannot be Empty");

        }

    }
    boolean checkInventoryEmptyFields() {
        FormValidate fv = new FormValidate();

        if (fv.isEmptyField(req_id.getText(), "Requisition ID")) {
            return true;
        } else if (fv.isEmptyField( mng_id.getText(), "Manager Id")) {
            return true;
        } else if (fv.isEmptyField( emp_id.getText(), "Employee ID")) {
            return true;
        } else if (fv.isEmptyField(amount.getText(), "TP Number")) {
            return true;
        } else if (fv.isEmptyField(req_date.getValue().toString(), "Req Date")) {
            return true;
        } else if (fv.isEmptyField(item_name.getText(), "Item Name")) {
            return true;
        } else if (fv.isEmptyField(site_no.getText(), "Site No")) {
            return true;
        } else if (fv.isEmptyField(req_qty.getText(), "Qty")) {
            return true;
        }

        return false;
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
