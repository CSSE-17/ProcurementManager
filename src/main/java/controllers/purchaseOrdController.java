package controllers;

/**
 * Created by HPkavin on 11/23/2017.
 */
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;

public class purchaseOrdController {

    @FXML
    private TextField pID;

    @FXML
    private TextField mngID;

    @FXML
    private TextField subID;

    @FXML
    private Button pPaid;

    @FXML
    private Button pDelete;

    @FXML
    private Button pUpdate;

    @FXML
    private TextField pQty;

    @FXML
    private Label pStatus;

    @FXML
    private DatePicker reqDate;

    @FXML
    private TableView<?> purchaseTbl;

    @FXML
    private TextField SupName;

    @FXML
    private TextField itemName;

    @FXML
    private TextField PAmount;

    @FXML
    private DatePicker paidDate;

    @FXML
    private TextField EsupName;

    @FXML
    private TextField EpID;

    @FXML
    private TextField EsupEmail;

    @FXML
    private Button Efind;

    @FXML
    private Button eSend;

    @FXML
    private TextArea Ebody;

}



