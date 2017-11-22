package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableView<?> table_Offer1;

    public void initialize(){

    }
}
