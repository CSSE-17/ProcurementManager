package controllers;

        import dao.DiliveryNoteDAO;
        import dao.GenericDAOImpl;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import models.DeliveryNoteEntity;
        import models.RequisitionItemEntity;
        import org.hibernate.Session;

        import java.time.LocalDate;
        import java.util.List;
        import java.util.Optional;

/**
 * Created by HPkavin on 11/24/2017.
 */
public class deliveryNoteController implements Controller {

    @FXML
    private TextField deliveryNoteID;

    @FXML
    private TextField requisitionID;

    @FXML
    private TextField totalAmount;

    @FXML
    private TextField status;

    @FXML
    private Button btnAdd;

    @FXML
    private ComboBox comboItem;

    @FXML
    private Button delivery;

    @FXML
    private Button deliveryNoteUpdate;

    @FXML
    private TableView tableDiliveryNote;

    @FXML
    private TableView tableItems;

    @FXML
    private TextField pID;


    GenericDAOImpl dao;

    public void initialize() {
        dao = new GenericDAOImpl();
        tableItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadData();
//        loadItemsCom();
        deliveryNoteUpdate.setVisible(false);
        btnAdd.setVisible(true);

//        generateSupplierID();
        tableDiliveryNote.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            DeliveryNoteEntity entity= (DeliveryNoteEntity) obs.getValue();

            if (entity== null) {
                return;
            }

            String noteId = entity.getNoteID();
            String reqId = entity.getRequisitionID();
            String purchaseId = entity.getPurchID();
            String totAmount = String.valueOf(entity.getTotalAmount());
            String satus = entity.getStatus();

            deliveryNoteID.setText(noteId);
            requisitionID.setText(reqId);
            totalAmount.setText(totAmount);
            status.setText(satus);
            pID.setText(purchaseId);
            tableItems.getColumns().clear();
            loadItemsTable(reqId);
            loadItemsCom(reqId);
            btnAdd.setVisible(false);
            deliveryNoteUpdate.setVisible(true);
        });
    }

    public void loadItemsCom(String reqId) {

//        DiliveryNoteDAO DAO = new DiliveryNoteDAO();
//        DAO.setup();
        dao.setup();
        ObservableList<RequisitionItemEntity> items = FXCollections.observableArrayList(dao.read(RequisitionItemEntity.class));
        dao.exit();

        for( RequisitionItemEntity item : items ) {
            if(reqId.equals(item.getReqId())) {
                comboItem.getItems().add(item.getItemName());
            }
        }
    }


    public void loadItemsTable(String reqId) {

//        DiliveryNoteDAO DAO = new DiliveryNoteDAO();
//        DAO.setup();
        tableItems.getItems().clear();
        dao.setup();
        ObservableList<RequisitionItemEntity> items = FXCollections.observableArrayList(dao.read(RequisitionItemEntity.class));
        dao.exit();

        TableColumn<RequisitionItemEntity, String> reqIdCol = new TableColumn<>("Requisition ID");
        reqIdCol.setCellValueFactory(new PropertyValueFactory<>("reqId"));

        TableColumn<RequisitionItemEntity, String> purchIdCol = new TableColumn<>("Item Name");
        purchIdCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<RequisitionItemEntity, Number> qtyCol = new TableColumn<>("Qty");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));

        TableColumn<RequisitionItemEntity, Double> StatusCol = new TableColumn<>("Status");
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<RequisitionItemEntity, String> dDateCol = new TableColumn<>("Dilivery Date");
        dDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        for (RequisitionItemEntity item : items){
            if(reqId.equals(item.getReqId())){
                tableItems.getItems().add(item);
            }
        }

        tableItems.getColumns().addAll(reqIdCol, purchIdCol, qtyCol,StatusCol,dDateCol);


    }


    @Override
    public void create() {
        DeliveryNoteEntity note = getFormData();

        dao.setup();
        dao.create(note);
        dao.exit();

        loadData();
        clearForm();
    }


//    @Override
    public void update() {


        Alert confD = new Alert(Alert.AlertType.CONFIRMATION);
        confD.setTitle("Confirm action!");
        confD.setHeaderText("Are you sure you want to permanently Update Delivery Note  ?");
        Optional<ButtonType> result = confD.showAndWait();

        if (result.isPresent() && result.get() != ButtonType.OK) {
            return;
        }
        DeliveryNoteEntity note = getFormData();
        DiliveryNoteDAO DAO = new DiliveryNoteDAO();
        DAO.setup();
        DAO.update(note);
        DAO.exit();
        loadData();
        clearForm();
        btnAdd.setVisible(true);
        deliveryNoteUpdate.setVisible(false);

    }

    @Override
    public <T> T getFormData() {
        DeliveryNoteEntity note = new DeliveryNoteEntity();

        note.setNoteID(deliveryNoteID.getText());
        note.setPurchID(pID.getText());
        note.setRequisitionID(requisitionID.getText());
        note.setTotalAmount(Double.parseDouble(totalAmount.getText()));
        note.setPaidDate("None");
        note.setStatus("Pending");

        return (T) note;
    }

    @Override
    public void loadData() {
        tableDiliveryNote.getColumns().clear();

        dao.setup();
        ObservableList<DeliveryNoteEntity> itm = FXCollections.observableArrayList(dao.read(DeliveryNoteEntity.class));
        dao.exit();

        TableColumn<DeliveryNoteEntity, String> noteIdCol = new TableColumn<>("Note ID");
        noteIdCol.setCellValueFactory(new PropertyValueFactory<>("noteID"));

        TableColumn<DeliveryNoteEntity, String> purchIdCol = new TableColumn<>("Purchase ID");
        purchIdCol.setCellValueFactory(new PropertyValueFactory<>("purchID"));

        TableColumn<DeliveryNoteEntity, String> requisitionIdCol = new TableColumn<>("Requisition ID");
        requisitionIdCol.setCellValueFactory(new PropertyValueFactory<>("requisitionID"));

        TableColumn<DeliveryNoteEntity, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        TableColumn<DeliveryNoteEntity, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<DeliveryNoteEntity, String> paidDateCol = new TableColumn<>("Payment Date");
        paidDateCol.setCellValueFactory(new PropertyValueFactory<>("paidDate"));


        tableDiliveryNote.setItems(itm);
        tableDiliveryNote.getColumns().addAll(noteIdCol, purchIdCol, requisitionIdCol,amountCol,statusCol,paidDateCol);
    }

    @Override
    public void delete() {
        DeliveryNoteEntity note = getSelectedInstance();

        if (note != null) {
            Alert confD = new Alert(Alert.AlertType.CONFIRMATION);
            confD.setTitle("Confirm action!");
            confD.setHeaderText("Are you sure you want to permanently delete Delivery Note " + note.getNoteID() + "?");
            Optional<ButtonType> result = confD.showAndWait();

            if (result.isPresent() && result.get() != ButtonType.OK) {
                return;
            }

            dao.setup();
            dao.delete(note);
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
        DeliveryNoteEntity note = (DeliveryNoteEntity) tableDiliveryNote.getSelectionModel().selectedItemProperty().get();
        return (T) note;
    }

    @Override
    public void clearForm() {

        deliveryNoteID.clear();
        requisitionID.clear();
        pID.clear();
        totalAmount.clear();
        status.clear();
        tableItems.getColumns().clear();
        loadData();
        tableItems.getItems().clear();
        btnAdd.setVisible(true);
        deliveryNoteUpdate.setVisible(false);



    }


}

