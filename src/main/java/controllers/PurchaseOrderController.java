package controllers;

import dao.GenericDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.PurchaseOrdersEntity;
import models.RequisitionEntity;
import models.RequisitionItemEntity;
import models.SupplierEntity;

import java.util.Optional;

public class PurchaseOrderController implements Controller {
    @FXML
    private TextField txtPOID;
    @FXML
    private ComboBox txtReqID;
    @FXML
    private ComboBox txtSupID;
    @FXML
    private TextField txtTotal;
    @FXML
    private TableView tablePurchaseOrders;
    @FXML
    private TableView tableItems;

    GenericDAOImpl dao;

    public void initialize(){
        dao = new GenericDAOImpl();
        tablePurchaseOrders.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadData();
        loadReqIDs();
        loadSupIDs();
//        loadItemsTable();
    }

    @Override
    public void create() {
        PurchaseOrdersEntity porder = getFormData();

        dao.setup();
        dao.create(porder);
        dao.exit();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful!");
        alert.setHeaderText("Purchase Order created :" + porder.getPoid());
        Optional<ButtonType> result = alert.showAndWait();

        loadData();
        clearForm();
    }

    @Override
    public <T> T getFormData() {
        PurchaseOrdersEntity poder = new PurchaseOrdersEntity();

        poder.setPoid(txtPOID.getText());
        poder.setReqid((String) txtReqID.getValue());
        poder.setSupplierId((String) txtSupID.getValue());
        poder.setTotalAmount(Double.parseDouble(txtTotal.getText()));

        return (T) poder;
    }

    @Override
    public void loadData() {
        tablePurchaseOrders.getColumns().clear();

        dao.setup();
        ObservableList<PurchaseOrdersEntity> pos = FXCollections.observableArrayList(dao.read(PurchaseOrdersEntity.class));
        dao.exit();

    }

    public void loadItemsTable(String reqId) {

//        DiliveryNoteDAO DAO = new DiliveryNoteDAO();
//        DAO.setup();
        dao.setup();
        ObservableList<RequisitionItemEntity> items = FXCollections.observableArrayList(dao.read(RequisitionItemEntity.class));
        dao.exit();

        TableColumn<RequisitionItemEntity, String> poIdCol = new TableColumn<>("Purchase ID");
        poIdCol.setCellValueFactory(new PropertyValueFactory<RequisitionItemEntity, String>("poid"));

        TableColumn<RequisitionItemEntity, String> reqIdCol = new TableColumn<>("Requisiton ID");
        reqIdCol.setCellValueFactory(new PropertyValueFactory<>("reqid"));

        TableColumn<RequisitionItemEntity, Number> supidCol = new TableColumn<>("Supplier ID");
        supidCol.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));

        TableColumn<RequisitionItemEntity, Double> tamontCol = new TableColumn<>("Total Amount");
        tamontCol.setCellValueFactory(new PropertyValueFactory<>("total_amount"));

        for (RequisitionItemEntity item : items){
            if(reqId == item.getReqId())
                tableItems.getItems().add(item);
        }

        tableItems.getColumns().addAll(poIdCol,reqIdCol,supidCol,tamontCol);


    }





//    public void loadItemsCom(String reqId) {
//
////        DiliveryNoteDAO DAO = new DiliveryNoteDAO();
////        DAO.setup();
//        dao.setup();
//        ObservableList<RequisitionItemEntity> items = FXCollections.observableArrayList(dao.read(RequisitionItemEntity.class));
//        dao.exit();
//
//        for( RequisitionItemEntity item : items ) {
//            if(reqId == item.getReqId()) {
//                comboItem.getItems().add(item.getItemName());
//            }
//        }
//    }


    @Override
    public void delete() {
//        PurchaseOrdersEntity porder = getSelectedInstance();
//
//        if (porder != null) {
//            Alert confDialog = new Alert(Alert.AlertType.CONFIRMATION);
//            confDialog.setTitle("Confirm action!");
//            confDialog.setHeaderText("Are you sure you want to permanently delete supplier " + porder.getPoid() + "?");
//            Optional<ButtonType> result = confDialog.showAndWait();
//
//            if (result.isPresent() && result.get() != ButtonType.OK) {
//                return;
//            }
//
//            dao.setup();
//            dao.delete(porder);
//            dao.exit();
//            loadData();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText("Select a Order first.");
//            alert.showAndWait();
//        }
    }

    @Override
    public <T> T getSelectedInstance() {
        PurchaseOrdersEntity porder = (PurchaseOrdersEntity) tablePurchaseOrders.getSelectionModel().selectedItemProperty().get();
        return (T) porder;
    }


    public void loadReqIDs() {
        dao.setup();
        ObservableList<RequisitionEntity> requisitions = FXCollections.observableArrayList(dao.read(RequisitionEntity.class));
        dao.exit();

        for(RequisitionEntity req : requisitions) {
            txtReqID.getItems().add(req.getRequisitionId());
        }
    }


    public void loadSupIDs() {
        dao.setup();
        ObservableList<SupplierEntity> supplier = FXCollections.observableArrayList(dao.read(SupplierEntity.class));
        dao.exit();

        for(SupplierEntity sup : supplier) {
            txtSupID.getItems().add(sup.getSupplierId());
        }
    }

    @Override
    public void clearForm() {
        txtPOID.clear();
        txtReqID.setPromptText("Select");
        txtSupID.setPromptText("Select");
        txtTotal.clear();

    }
}