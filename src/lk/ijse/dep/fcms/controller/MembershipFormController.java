package lk.ijse.dep.fcms.controller;

import com.jfoenix.controls.*;
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
import lk.ijse.dep.fcms.bo.custom.MemberBO;
import lk.ijse.dep.fcms.dto.MembershipDTO;
import lk.ijse.dep.fcms.dto.MembershipDetailDTO;
import lk.ijse.dep.fcms.entity.Gender;
import lk.ijse.dep.fcms.util.MembershipTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MembershipFormController implements Initializable {
    public AnchorPane root;
    public Text lblId;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextArea txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtNIC;
    public JFXTextField txtWeight;
    public JFXTextField txtHeight;
    public JFXDatePicker dtpBirthday;
    public JFXButton btnAdd;
    public JFXButton btnRenew;
    public ImageView btnBack;
    public TableView<MembershipTM> tblMember;
    public TableColumn colId;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public JFXTextField txtSearch;
    public JFXComboBox cmbGender;
    public JFXButton btnNewMember;

    private MemberBO memberBO = BOFactory.getInstance().getBO(BOTypes.MEMBER);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblMember.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("memberID"));
        tblMember.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblMember.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblMember.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblMember.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        txtFirstName.setDisable(true);
        txtLastName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        dtpBirthday.setDisable(true);
        txtNIC.setDisable(true);
        cmbGender.setDisable(true);
        txtWeight.setDisable(true);
        txtHeight.setDisable(true);
        btnAdd.setDisable(true);
        btnRenew.setDisable(true);

        try {
            List<MembershipDTO> allMembers = memberBO.findAllMembers();
            ObservableList<MembershipTM> members = tblMember.getItems();
            for (MembershipDTO member : allMembers) {
                members.add(new MembershipTM(member.getMemberID(), member.getFirstName(), member.getLastName(), member.getAddress(), member.getContactNo()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
            Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
        }

        tblMember.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MembershipTM>() {
            @Override
            public void changed(ObservableValue<? extends MembershipTM> observable, MembershipTM oldValue, MembershipTM newValue) {
                MembershipTM selectedItem = tblMember.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnAdd.setText("Add");
                    btnRenew.setDisable(true);
                    return;
                }

                btnAdd.setText("Update");
                btnAdd.setDisable(false);
                txtFirstName.setDisable(false);
                txtLastName.setDisable(false);
                txtAddress.setDisable(false);
                txtContactNo.setDisable(false);
                dtpBirthday.setDisable(false);
                txtNIC.setDisable(false);
                cmbGender.setDisable(false);
                txtWeight.setDisable(false);
                txtHeight.setDisable(false);
                try {
                    MembershipDTO member = memberBO.findMember(selectedItem.getMemberID());
                    lblId.setText(member.getMemberID());
                    txtFirstName.setText(member.getFirstName());
                    txtLastName.setText(member.getLastName());
                    txtAddress.setText(member.getAddress());
                    txtContactNo.setText(String.valueOf(member.getContactNo()));
                    dtpBirthday.setValue(member.getDateOfBirth().toLocalDate());
                    txtNIC.setText(member.getNic());
                    cmbGender.getSelectionModel().select(member.getGender());
                    txtWeight.setText(String.valueOf(member.getWeight()));
                    txtHeight.setText(String.valueOf(member.getHeight()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    MembershipDetailDTO member = memberBO.getLastMembershipDetail(selectedItem.getMemberID());
                    LocalDate today = LocalDate.now();
                    LocalDate expireDate = member.getDateOfExpire().toLocalDate();
                    boolean isAfter = today.isAfter(expireDate);
                    if (isAfter) {
                        System.out.println("Expired");
                        System.out.println(today);
                        System.out.println(expireDate);
                        btnRenew.setDisable(false);
                    } else {
                        System.out.println("Not Expired");
                        System.out.println(today);
                        System.out.println(expireDate);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        if (btnAdd.getText().equals("Add")) {
            MembershipDetailDTO memberDetails = new MembershipDetailDTO(
                    lblId.getText(),
                    null,
                    null
            );

            ObservableList<MembershipTM> members = tblMember.getItems();
            MembershipDTO newMember = new MembershipDTO(
                    lblId.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtAddress.getText(),
                    txtContactNo.getText(),
                    Date.valueOf(dtpBirthday.getValue()),
                    txtNIC.getText(),
                    cmbGender.getSelectionModel().getSelectedItem().toString(),
                    Integer.parseInt(txtWeight.getText()),
                    Integer.parseInt(txtHeight.getText())
            );

            try {
                memberBO.saveMember(newMember);
                members.add(new MembershipTM(newMember.getMemberID(), newMember.getFirstName(), newMember.getLastName(), newMember.getAddress(), newMember.getContactNo()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Member added successfully! Click Ok to make payment",
                        ButtonType.OK);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.OK) {
                    URL resource = this.getClass().getResource("/lk/ijse/dep/fcms/view/PaymentForm.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader(resource);
                    Parent root = fxmlLoader.load();

                    if (root != null) {
                        Scene subScene = new Scene(root);
                        Stage primaryStage = (Stage) this.root.getScene().getWindow();

                        primaryStage.setScene(subScene);
                        primaryStage.centerOnScreen();

                        PaymentFormController paymentFormController = fxmlLoader.getController();
                        paymentFormController.initializeForPaymentForm(newMember.getMemberID());

                        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                        tt.setFromX(-subScene.getWidth());
                        tt.setToX(0);
                        tt.play();
                    }
                }
                btnNewMember_OnAction(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
                Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
            }
        } else {
            MembershipTM selectedMember = tblMember.getSelectionModel().getSelectedItem();
            try {
                memberBO.updateMember(new MembershipDTO(selectedMember.getMemberID(),
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        txtAddress.getText(),
                        txtContactNo.getText(),
                        Date.valueOf(dtpBirthday.getValue()),
                        txtNIC.getText(),
                        cmbGender.getSelectionModel().getSelectedItem().toString(),
                        Integer.parseInt(txtWeight.getText()),
                        Integer.parseInt(txtHeight.getText())));
                selectedMember.setFirstName(txtFirstName.getText());
                selectedMember.setLastName(txtLastName.getText());
                selectedMember.setAddress(txtAddress.getText());
                selectedMember.setContactNo(txtContactNo.getText());
                selectedMember.setDateOfBirth(Date.valueOf(dtpBirthday.getValue()));
                selectedMember.setNic(txtNIC.getText());
                selectedMember.setGender(cmbGender.getSelectionModel().getSelectedItem().toString());
                selectedMember.setWeight(Integer.parseInt(txtWeight.getText()));
                selectedMember.setWeight(Integer.parseInt(txtHeight.getText()));
                tblMember.refresh();
                btnNewMember_OnAction(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE, null, e);
            }
        }
    }

    public void btnExpiredMembers_OnAction(ActionEvent actionEvent) {
    }

    public void btnNewMember_OnAction(ActionEvent actionEvent) {
        lblId.setText("");
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        dtpBirthday.getEditor().clear();
        txtNIC.clear();
        cmbGender.getItems().clear();
        txtWeight.clear();
        txtHeight.clear();
        tblMember.getSelectionModel().clearSelection();
        txtFirstName.setDisable(false);
        txtLastName.setDisable(false);
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        dtpBirthday.setDisable(false);
        txtNIC.setDisable(false);
        cmbGender.setDisable(false);
        txtWeight.setDisable(false);
        txtHeight.setDisable(false);
        txtFirstName.requestFocus();
        btnAdd.setDisable(false);
        cmbGender.getItems().setAll(Gender.values());
        int maxId = 0;

        try {
            String lastMemberId = memberBO.getLastMemberId();

            if (lastMemberId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastMemberId.replace("MBR", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "MBR000" + maxId;
            } else if (maxId < 100) {
                id = "MBR00" + maxId;
            } else if (maxId < 1000) {
                id = "MBR0" + maxId;
            } else {
                id = "MBR" + maxId;
            }
            lblId.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
            Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
        }
    }

    public void btnRenew_OnAction(ActionEvent actionEvent) throws IOException {

        URL resource = this.getClass().getResource("/lk/ijse/dep/fcms/view/RenewMembershipForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent root = fxmlLoader.load();

        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();

            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();

            RenewMembershipFormController renewMembershipFormController = fxmlLoader.getController();
            MembershipTM selectedMember = tblMember.getSelectionModel().getSelectedItem();
            renewMembershipFormController.initializeForRenewForm(selectedMember.getMemberID());

            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
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
}
