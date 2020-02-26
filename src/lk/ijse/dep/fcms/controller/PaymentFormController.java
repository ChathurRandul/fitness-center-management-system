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
import lk.ijse.dep.fcms.bo.custom.MemberBO;
import lk.ijse.dep.fcms.bo.custom.PaymentBO;
import lk.ijse.dep.fcms.dto.MembershipDTO;
import lk.ijse.dep.fcms.dto.MembershipDetailDTO;
import lk.ijse.dep.fcms.dto.PaymentDTO;
import lk.ijse.dep.fcms.util.MembershipTM;
import lk.ijse.dep.fcms.util.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentFormController implements Initializable {
    public Text lblPaymentId;
    public JFXTextField txtAmount;
    public Text lblMemberId;
    public TableView<PaymentTM> tblPayment;
    public TableColumn colId;
    public TableColumn colMemberId;
    public TableColumn colAmount;
    public TableColumn colDate;
    public ImageView btnBack;
    public AnchorPane root;
    public JFXTextField txtSearch;
    public JFXButton btnAddPayment;

    private PaymentBO paymentBO = BOFactory.getInstance().getBO(BOTypes.PAYMENT);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblPayment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        tblPayment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("memberID"));
        tblPayment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("payment"));
        tblPayment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        txtAmount.setDisable(true);
        btnAddPayment.setDisable(true);

        try {
            List<PaymentDTO> allPayments = paymentBO.findAllPayments();
            ObservableList<PaymentTM> payments = tblPayment.getItems();
            for (PaymentDTO payment : allPayments) {
                payments.add(new PaymentTM(payment.getPaymentID(), payment.getMemberID(), payment.getPayment(), payment.getPaymentDate()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact Developer").show();
            Logger.getLogger("lk.ijse.dep.fcms.controller").log(Level.SEVERE, null, e);
        }
    }

    public void btnAddPayment_OnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Confirm Payment?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            PaymentDTO newPayment = new PaymentDTO(
                    lblPaymentId.getText(),
                    lblMemberId.getText(),
                    Double.parseDouble(txtAmount.getText()),
                    null
            );
            try {
                paymentBO.savePayment(newPayment);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION,
                        "Payment added successfully!",
                        ButtonType.OK);
                Optional<ButtonType> buttonType2 = alert2.showAndWait();
                txtAmount.setDisable(true);
                btnAddPayment.setDisable(true);
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

    public void initializeForPaymentForm(String memberID) {
        lblMemberId.setText(memberID);
        txtAmount.setDisable(false);
        btnAddPayment.setDisable(false);

        int maxId = 0;

        try {
            String lastPaymentId = paymentBO.getLastPaymentId();

            if (lastPaymentId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastPaymentId.replace("PMT", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "PMT000" + maxId;
            } else if (maxId < 100) {
                id = "PMT00" + maxId;
            } else if (maxId < 1000) {
                id = "PMT0" + maxId;
            } else {
                id = "PMT" + maxId;
            }
            lblPaymentId.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
