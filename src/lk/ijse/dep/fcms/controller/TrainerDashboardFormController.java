package lk.ijse.dep.fcms.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TrainerDashboardFormController implements Initializable {
    public ImageView btnAttendance;
    public ImageView btnViewWorkout;
    public ImageView btnMember;
    public ImageView btnWorkout;
    public Text lblMainText;
    public Text lblSubText;
    public AnchorPane root;
    public ImageView btnExercise;

    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void OnMouseClicked(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "btnAttendance":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/AttendanceForm.fxml"));
                    break;
                case "btnMember":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/MemberForm.fxml"));
                    break;
                case "btnExercise":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/ExerciseForm.fxml"));
                    break;
                case "btnWorkout":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/WorkoutForm.fxml"));
                    break;
                case "btnViewWorkout":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/fcms/view/ViewWorkoutForm.fxml"));
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
                case "btnAttendance":
                    lblMainText.setText("Attendance");
                    lblSubText.setText("Member Attendance Management Operations");
                    break;
                case "btnMember":
                    lblMainText.setText("Member");
                    lblSubText.setText("Member Management Operations");
                    break;
                case "btnExercise":
                    lblMainText.setText("Exercise");
                    lblSubText.setText("Exercise Management Operations");
                    break;
                case "btnWorkout":
                    lblMainText.setText("Workout");
                    lblSubText.setText("Workout Management Operations");
                    break;
                case "btnViewWorkout":
                    lblMainText.setText("View Workout");
                    lblSubText.setText("View Workout Records");
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
            lblMainText.setText("Welcome");
            lblSubText.setText("Fitness Center Management Operations");
        }
    }

    public void btnSignOut_OnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                " Do you want to sign out?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            Parent root = null;

            FXMLLoader fxmlLoader = null;
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/LoginForm.fxml"));


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
}