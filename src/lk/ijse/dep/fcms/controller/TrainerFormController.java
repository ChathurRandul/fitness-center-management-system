package lk.ijse.dep.fcms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.fcms.bo.BOFactory;
import lk.ijse.dep.fcms.bo.BOTypes;
import lk.ijse.dep.fcms.bo.custom.LoginBO;
import lk.ijse.dep.fcms.bo.custom.MemberBO;
import lk.ijse.dep.fcms.bo.custom.TrainerBO;
import lk.ijse.dep.fcms.dto.LoginDTO;
import lk.ijse.dep.fcms.dto.MembershipDTO;
import lk.ijse.dep.fcms.dto.MembershipDetailDTO;
import lk.ijse.dep.fcms.dto.TrainerDTO;
import lk.ijse.dep.fcms.entity.Gender;
import lk.ijse.dep.fcms.util.MembershipTM;
import lk.ijse.dep.fcms.util.TrainerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainerFormController implements Initializable {
    public Text lblId;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextArea txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtNIC;
    public JFXTextField txtPassword;
    public TableView<TrainerTM> tblTrainer;
    public TableColumn colId;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public ImageView btnBack;
    public AnchorPane root;
    public JFXTextField txtSearch;
    public JFXButton btnNew;
    public JFXButton btnAdd;

    private TrainerBO trainerBO = BOFactory.getInstance().getBO(BOTypes.TRAINER);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblTrainer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("trainerID"));
        tblTrainer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblTrainer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblTrainer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblTrainer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        txtFirstName.setDisable(true);
        txtLastName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        txtNIC.setDisable(true);
        btnAdd.setDisable(true);
        txtPassword.setDisable(true);

        try {
            List<TrainerDTO> allTrainers = trainerBO.findAllTrainers();
            ObservableList<TrainerTM> trainers = tblTrainer.getItems();
            for (TrainerDTO trainer : allTrainers) {
                trainers.add(new TrainerTM(trainer.getTrainerID(), trainer.getFirstName(), trainer.getLastName(), trainer.getAddress(), trainer.getContactNo()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
            Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
        }

        tblTrainer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TrainerTM>() {
            @Override
            public void changed(ObservableValue<? extends TrainerTM> observable, TrainerTM oldValue, TrainerTM newValue) {
                TrainerTM selectedItem = tblTrainer.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnAdd.setText("Add");
                    return;
                }

                btnAdd.setText("Update");
                btnAdd.setDisable(false);
                txtFirstName.setDisable(false);
                txtLastName.setDisable(false);
                txtAddress.setDisable(false);
                txtContactNo.setDisable(false);
                txtNIC.setDisable(false);
                txtPassword.setDisable(false);
                try {
                    TrainerDTO trainer = trainerBO.findTrainer(selectedItem.getTrainerID());
                    lblId.setText(trainer.getTrainerID());
                    txtFirstName.setText(trainer.getFirstName());
                    txtLastName.setText(trainer.getLastName());
                    txtAddress.setText(trainer.getAddress());
                    txtContactNo.setText(trainer.getContactNo());
                    txtNIC.setText(trainer.getNic());
                    LoginDTO login = trainerBO.findLogin(selectedItem.getTrainerID());
                    txtPassword.setText(login.getPassword());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        if (btnAdd.getText().equals("Add")) {
            LoginDTO logins = new LoginDTO(
                    lblId.getText(),
                    txtPassword.getText(),
                    "TRAINER"
            );

            ObservableList<TrainerTM> trainers = tblTrainer.getItems();
            TrainerDTO newTrainer = new TrainerDTO(
                    lblId.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtAddress.getText(),
                    txtContactNo.getText(),
                    txtNIC.getText()
            );

            try {
                trainerBO.saveTrainer(newTrainer);
                trainerBO.saveLogin(logins);
                trainers.add(new TrainerTM(newTrainer.getTrainerID(), newTrainer.getFirstName(), newTrainer.getLastName(), newTrainer.getAddress(), newTrainer.getContactNo()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Trainer added successfully!",
                        ButtonType.OK);
                Optional<ButtonType> buttonType = alert.showAndWait();
                btnNew_OnAction(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
                Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
            }
        } else {
            TrainerTM selectedTrainer = tblTrainer.getSelectionModel().getSelectedItem();
            LoginDTO logins = new LoginDTO(
                    lblId.getText(),
                    txtPassword.getText(),
                    "TRAINER"
            );
            try {
                trainerBO.updateTrainer(new TrainerDTO(selectedTrainer.getTrainerID(),
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        txtAddress.getText(),
                        txtContactNo.getText(),
                        txtNIC.getText()));

                selectedTrainer.setFirstName(txtFirstName.getText());
                selectedTrainer.setLastName(txtLastName.getText());
                selectedTrainer.setAddress(txtAddress.getText());
                selectedTrainer.setContactNo(txtContactNo.getText());
                selectedTrainer.setNic(txtNIC.getText());

                trainerBO.updateLogin(logins);
                new Alert(Alert.AlertType.INFORMATION, "Trainer updated!").show();
                tblTrainer.refresh();
                btnNew_OnAction(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE, null, e);
            }
        }
    }

    public void btnBack_OnMouseClicked(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/ManagerDashboardForm.fxml"));


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

    public void btnBack_OnMouseEntered(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.BLACK);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void btnBack_OnMouseExited(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    public void btnNew_OnAction(ActionEvent actionEvent) {
        lblId.setText("");
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtNIC.clear();
        txtPassword.clear();
        tblTrainer.getSelectionModel().clearSelection();
        txtFirstName.setDisable(false);
        txtLastName.setDisable(false);
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        txtNIC.setDisable(false);
        txtPassword.setDisable(false);
        txtFirstName.requestFocus();
        btnAdd.setDisable(false);

        int maxId = 0;

        try {
            String lastTrainerId = trainerBO.getLastTrainerId();

            if (lastTrainerId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastTrainerId.replace("TRN", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "TRN000" + maxId;
            } else if (maxId < 100) {
                id = "TRN00" + maxId;
            } else if (maxId < 1000) {
                id = "TRN0" + maxId;
            } else {
                id = "TRN" + maxId;
            }
            lblId.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
            Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
        }
    }
}
