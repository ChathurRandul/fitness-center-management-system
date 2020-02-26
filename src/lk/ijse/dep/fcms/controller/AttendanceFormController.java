package lk.ijse.dep.fcms.controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class AttendanceFormController {
    public AnchorPane root;
    public ImageView btnBack;
    public ImageView btnAddAttendance;
    public ImageView btnViewAttendance;
    public Text lblMainText;
    public Text lblSubText;

    public void OnMouseClicked(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "btnBack":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/TrainerDashboardForm.fxml"));
                    break;
                case "btnAddAttendance":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/AddAttendanceForm.fxml"));
                    break;
                case "btnViewAttendance":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/fcms/view/ViewAttendanceForm.fxml"));
                    root = fxmlLoader.load();
                    break;
            }

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

    public void OnMouseEntered(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "btnBack":
                    break;
                case "btnAddAttendance":
                    lblMainText.setText("Add Attendance");
                    lblSubText.setText("Daily Attendance Sheet");
                    break;
                case "btnViewAttendance":
                    lblMainText.setText("View Attendance");
                    lblSubText.setText("View Attendance Records");
                    break;
            }

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

    public void OnMouseExited(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMainText.setText("");
            lblSubText.setText("");

        }
    }

}
