package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import util.UniqueID;

public class RequisitionController {
    @FXML
    private TextField txtReqID;

    public void initialize(){
        txtReqID.setDisable(true);
        generateRequisitionId();
    }

    private void generateRequisitionId() {
        String reqId = UniqueID.generateUniqueId();
        txtReqID.setText(reqId);
    }
}
