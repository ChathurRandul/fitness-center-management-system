package lk.ijse.dep.fcms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
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
import lk.ijse.dep.fcms.bo.custom.EquipmentBO;
import lk.ijse.dep.fcms.bo.custom.PaymentBO;
import lk.ijse.dep.fcms.dto.EquipmentDTO;
import lk.ijse.dep.fcms.dto.PaymentDTO;
import lk.ijse.dep.fcms.util.EquipmentTM;
import lk.ijse.dep.fcms.util.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquipmentFormController implements Initializable {
    public Text lblId;
    public JFXTextField txtDescription;
    public JFXTextField txtPrice;
    public TableView<EquipmentTM> tblEquipment;
    public TableColumn colId;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colDate;
    public ImageView btnBack;
    public AnchorPane root;
    public JFXTextField txtSearch;
    public JFXButton btnNew;
    public JFXButton btnAdd;

    private EquipmentBO equipmentBO = BOFactory.getInstance().getBO(BOTypes.EQUIPMENT);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblEquipment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("equipmentID"));
        tblEquipment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblEquipment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblEquipment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dateAdded"));

        txtDescription.setDisable(true);
        txtPrice.setDisable(true);
        btnAdd.setDisable(true);

        loadAllEquipments();
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Add Equipment?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            EquipmentDTO newEquipment = new EquipmentDTO(
                    lblId.getText(),
                    txtDescription.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    null
            );
            try {
                equipmentBO.saveEquipment(newEquipment);

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION,
                        "Equipment added successfully!",
                        ButtonType.OK);
                Optional<ButtonType> buttonType2 = alert2.showAndWait();
                tblEquipment.getItems().clear();
                loadAllEquipments();
                txtDescription.setDisable(true);
                txtPrice.setDisable(true);
                btnAdd.setDisable(true);
                btnNew_OnAction(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
                Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
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
        txtDescription.clear();
        txtPrice.clear();
        txtDescription.setDisable(false);
        txtPrice.setDisable(false);
        btnAdd.setDisable(false);

        int maxId = 0;

        try {
            String lastEquipmentId = equipmentBO.getLastEquipmentId();

            if (lastEquipmentId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastEquipmentId.replace("EQT", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "EQT000" + maxId;
            } else if (maxId < 100) {
                id = "EQT00" + maxId;
            } else if (maxId < 1000) {
                id = "EQT0" + maxId;
            } else {
                id = "EQT" + maxId;
            }
            lblId.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
            Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
        }
    }

    public void loadAllEquipments(){
        try {
            List<EquipmentDTO> allEquipments = equipmentBO.findAllEquipments();
            ObservableList<EquipmentTM> equipments = tblEquipment.getItems();
            for (EquipmentDTO equipment : allEquipments) {
                equipments.add(new EquipmentTM(equipment.getEquipmentID(), equipment.getDescription(), equipment.getPrice(), equipment.getDateAdded()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
            Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
        }
    }
}
