package controllers;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.UserAccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Cypher;

public class LoginController {
    static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    public static final int MAX_LOGIN_ATTEMPTS = 5;
    private static String loggedInUser;
    private static String loggedInUserType;

    private int attemptsLeft;
    private boolean authenticationError;

    @FXML
    private Button btnClose;
    @FXML
    private Label msgAuthError;
    @FXML
    private Label msgAttemptsLeft;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;

    /**
     * Called automatically when the FXML is loaded.
     * Initialize login stage and reset all counters and messages.
     */
    public void initialize(){
        attemptsLeft = MAX_LOGIN_ATTEMPTS;
        authenticationError = Boolean.FALSE;
        updateMessages();
    }

    /**
     * Close the login stage.
     */
    public void closeLogin() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Update info/error messages on login stage.
     */
    private void updateMessages() {
        // Authentication error message.
        if (authenticationError == Boolean.FALSE) {
            msgAuthError.setVisible(false);
        } else {
            msgAuthError.setVisible(true);
        }

        if (attemptsLeft == MAX_LOGIN_ATTEMPTS) {
            msgAttemptsLeft.setVisible(false);
        } else {
            msgAttemptsLeft.setText("Attempts left: " + attemptsLeft);
            msgAttemptsLeft.setVisible(true);
        }
    }

    /**
     * Deduct an attempt.
     */
    private void updateAttemptsLeft() {
        if (attemptsLeft != 0) {
            attemptsLeft--;
        }
    }

    /**
     * Log in to the system if the credentials are correct.
     * If not, update relevant messages.
     */
    public void login() {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (validateCredentails(username, password)) {
            authenticationError = Boolean.FALSE;

            LOG.info("User {} logged in as {}", loggedInUser, loggedInUserType);
            displayHome();
        } else {
            authenticationError = Boolean.TRUE;
            processAuthError();
        }
    }

    /**
     * Close the login stage and display 'home' for the authenticated user.
     */
    private void displayHome() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
            Scene home = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(home);
            stage.setMaximized(true);
            stage.show();

            // close login.
            closeLogin();
        }catch(Exception e) {
            LOG.error("Error displaying home." + e);
        }
    }

    /**
     * Validate login credentials.
     * @param userName
     * @param password
     */
    private static synchronized boolean validateCredentails(String userName, String password) {
        // Generate md5 hash from entered password.
        String passwordHash = Cypher.generateMD5(password);

        UserDAO userDAO = new UserDAO();
        userDAO.setup();
        UserAccountEntity user = userDAO.read(userName);
        userDAO.exit();

        if (user != null && user.getPassword().equals(passwordHash)) {
            loggedInUser = user.getUserName();
            loggedInUserType = user.getAccType();
            return true;
        }
        return false;
    }

    /**
     * Update attempts left and if maximum allowed attempts is reached, display error and exit.
     */
    private void processAuthError() {
        updateAttemptsLeft();
        if (attemptsLeft == 0) {
            closeLogin();
        } else {
            updateMessages();
        }
    }

    /**
     * Show password recovery wizard.
     */
    public void gotoPasswordRecovery() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/PasswordRecovery.fxml"));
            Scene home = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(home);
            stage.setResizable(false);
            stage.show();

            // close current window
            closeLogin();
        } catch (Exception e) {
            LOG.error("Error setting up javaFX stage. " + e);
        }
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    public static String getLoggedInUserType() {
        return loggedInUserType;
    }
}
