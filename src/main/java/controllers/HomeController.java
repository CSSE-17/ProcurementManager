package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private StackPane stackpaneHomeCenter;
    @FXML
    private Label lblLoggedInUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadRequisitionsPanel();
        lblLoggedInUser.setText(LoginController.getLoggedInUser());
    }
    /**
     * load the Requisitions FXML into center of the home borderpane.
     */
    public void loadRequisitionsPanel() {
        loadCenterPanel("/views/requisitions.fxml");
        LOG.info("Navigated to Requisitions");
    }

    /**
     * load the Suppliers FXML into center of the home borderpane.
     */
    public void loadSuppliersPanel() {
        loadCenterPanel("/views/suppliers.fxml");
        LOG.info("Navigated to Suppliers");
    }

    public void loadDeliveryNotePanel() {
        loadCenterPanel("/views/DeliveryNote.fxml");
        LOG.info("Navigated to DeliveryNote");
    }
    public void loadItemsPanel() {
        loadCenterPanel("/views/items.fxml");
        LOG.info("Navigated to Items");
    }
    /**
     * Loads the FXML scene to the center of home borderpane.
     *
     * @param fxmlPath
     */
    public void loadCenterPanel(String fxmlPath) {
        stackpaneHomeCenter.getChildren().clear();
        try {
            stackpaneHomeCenter.getChildren().clear();
            stackpaneHomeCenter.getChildren().add(FXMLLoader.load(getClass().getResource(fxmlPath)));
        } catch (Exception e) {
            LOG.error("Error loading {}", fxmlPath, e);
        }
    }
}
