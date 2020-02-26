package lk.ijse.dep.fcms.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import lk.ijse.dep.fcms.dto.MembershipDetailDTO;
import lk.ijse.dep.fcms.util.MembershipTM;
import lk.ijse.dep.fcms.bo.custom.MemberBO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;



public class RenewMembershipFormController {
    public AnchorPane root;
    public JFXComboBox cmbType;
    public Text lblId;
    public ImageView btnBack;
    public Text lblIssueDate;
    public Text lblExpireDate;

    private MemberBO memberBO = BOFactory.getInstance().getBO(BOTypes.MEMBER);

    public void btnRenew_OnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Renew this membership?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            MembershipDetailDTO newMembership = new MembershipDetailDTO(
                    lblId.getText(),
                    null,
                    null
            );
            try {
                memberBO.renewMember(newMembership);
                //members.add(new MembershipTM(newMember.getMemberID(), newMember.getFirstName(), newMember.getLastName(), newMember.getAddress(), newMember.getContactNo()));
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION,
                        "Membership renewed successfully! Click Ok to make payment",
                        ButtonType.OK);
                Optional<ButtonType> buttonType2 = alert2.showAndWait();
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
                        paymentFormController.initializeForPaymentForm(newMembership.getMemberID());

                        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                        tt.setFromX(-subScene.getWidth());
                        tt.setToX(0);
                        tt.play();
                    }
                }
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
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/MembershipForm.fxml"));


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

    public void initializeForRenewForm(String memberID) {
        lblId.setText(memberID);
        lblIssueDate.setText(LocalDate.now().toString());
        lblExpireDate.setText(LocalDate.now().plusYears(1).toString());
    }
}
