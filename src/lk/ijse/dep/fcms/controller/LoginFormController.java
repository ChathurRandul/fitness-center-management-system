package lk.ijse.dep.fcms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.fcms.bo.BOFactory;
import lk.ijse.dep.fcms.bo.BOTypes;
import lk.ijse.dep.fcms.bo.custom.LoginBO;
import lk.ijse.dep.fcms.bo.custom.PaymentBO;
import lk.ijse.dep.fcms.bo.custom.impl.LoginBOImpl;
import lk.ijse.dep.fcms.db.DBConnection;
import lk.ijse.dep.fcms.dto.LoginDTO;
import lk.ijse.dep.fcms.entity.Gender;
import lk.ijse.dep.fcms.entity.UserType;
import lk.ijse.dep.fcms.util.MembershipTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXComboBox cmbUser;
    public AnchorPane root;
    PreparedStatement pst;
    ResultSet rst;

    private LoginBO loginBO = BOFactory.getInstance().getBO(BOTypes.LOGIN);

    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbUser.getItems().setAll(UserType.values());

    }

    public void btnLogin_OnAction(ActionEvent actionEvent) throws Exception {
        String userID = txtUsername.getText();
        String password = txtPassword.getText();
        String selectedUserType = cmbUser.getSelectionModel().getSelectedItem().toString();

        if (cmbUser.getSelectionModel().getSelectedIndex() == -1 || txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Empty Fields!").show();
        } else {
            Connection connection = DBConnection.getInstance().getConnection();
            pst = connection.prepareStatement("SELECT * FROM Login WHERE userID=? AND password=?");
            pst.setString(1,userID);
            pst.setString(2,password);
            rst = pst.executeQuery();
            if (rst.next()){
                String userType = rst.getString("userType");
                if (selectedUserType.equalsIgnoreCase("MANAGER") && userType.equalsIgnoreCase("MANAGER")){
                    navigation("/lk/ijse/dep/fcms/view/ManagerDashboardForm.fxml");
                } else if (selectedUserType.equalsIgnoreCase("TRAINER") && userType.equalsIgnoreCase("TRAINER")) {
                    navigation("/lk/ijse/dep/fcms/view/TrainerDashboardForm.fxml");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Credentials!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Credentials!").show();
            }
        }
    }

    public void navigation(String path) throws IOException {
        Parent root = null;

        FXMLLoader fxmlLoader = null;
        root = FXMLLoader.load(this.getClass().getResource(path));


        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();

            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();

            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }
}
