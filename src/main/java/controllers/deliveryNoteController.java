package controllers;

        import dao.GenericDAOImpl;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import models.DeliveryNoteEntity;

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
    private ComboBox<?> comboItem;

    @FXML
    private Button delivery;

    @FXML
    private TableView tableDiliveryNote;

    @FXML
    private TableView tableItems;

    @FXML
    private TextField pID;


    GenericDAOImpl dao;

    public void initialize(){
        dao = new GenericDAOImpl();
        tableItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadData();
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
        DeliveryNoteEntity note = (DeliveryNoteEntity) tableItems.getSelectionModel().selectedItemProperty().get();
        return (T) note;
    }

    @Override
    public void clearForm() {

        deliveryNoteID.clear();
        requisitionID.clear();
        pID.clear();
        totalAmount.clear();
        status.clear();

    }


}

