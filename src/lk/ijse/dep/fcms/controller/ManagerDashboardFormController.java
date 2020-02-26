package lk.ijse.dep.fcms.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.fcms.db.DBConnection;
import lk.ijse.dep.fcms.dto.EquipmentDTO;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerDashboardFormController implements Initializable {
    public ImageView btnMembership;
    public ImageView btnEquipment;
    public ImageView btnPayment;
    public ImageView btnTrainer;
    public Text lblMainText;
    public Text lblSubText;
    public AnchorPane root;

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
                case "btnMembership":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/MembershipForm.fxml"));
                    break;
                case "btnPayment":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/PaymentForm.fxml"));
                    break;
                case "btnTrainer":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fcms/view/TrainerForm.fxml"));
                    break;
                case "btnEquipment":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/fcms/view/EquipmentForm.fxml"));
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
                case "btnMembership":
                    lblMainText.setText("Membership");
                    lblSubText.setText("Member Management Operations");
                    break;
                case "btnPayment":
                    lblMainText.setText("Payment");
                    lblSubText.setText("Payment Management Operations");
                    break;
                case "btnTrainer":
                    lblMainText.setText("Trainer");
                    lblSubText.setText("Trainer Management Operations");
                    break;
                case "btnEquipment":
                    lblMainText.setText("Equipment");
                    lblSubText.setText("Equipment Management Operations");
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

    public void btnBackup_OnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the DB Backup");
        fileChooser.getExtensionFilters().
                add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showSaveDialog(this.root.getScene().getWindow());
        if (file != null) {

            try {
                Process process = Runtime.getRuntime().
                        exec("mysqldump -h" + DBConnection.host + " -u" + DBConnection.username +
                                " -p" + DBConnection.password + " " + DBConnection.db + " --result-file " +
                                file.getAbsolutePath() + ((file.getAbsolutePath().endsWith(".sql"))? "" : ".sql"));
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    InputStream errorStream = process.getErrorStream();
                    InputStreamReader isr = new InputStreamReader(errorStream);
                    BufferedReader br = new BufferedReader(isr);
                    String out = "";
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        out += line + "\n";
                    }
                    System.out.println(out);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Database Backed Up Successfully!").show();
                }
                System.out.println(exitCode);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void btnRestore_OnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Let's restore the backup");
        fileChooser.getExtensionFilters().
                add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showOpenDialog(this.root.getScene().getWindow());
        if (file != null){
            //We have to restore the backup then
            String[] commands = {"mysql","-h",DBConnection.host,"-u",DBConnection.username,
                    "-p" + DBConnection.password,"--port",DBConnection.port,DBConnection.db, "-e","source " + file.getAbsolutePath()};

            this.root.getScene().setCursor(Cursor.WAIT);

            Task task = new Task<Void>(){

                @Override
                protected Void call() throws Exception {
                    Process process = Runtime.getRuntime().exec(commands);
                    int exitCode = process.waitFor();
                    if (exitCode != 0){
                        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream())); //string > line by line
                        br.lines().forEach(System.out::println);
                        br.close();

                        throw new RuntimeException("Error");
                    } else {
                        return null;
                    }
                }
            };

            task.setOnSucceeded(event -> {
                this.root.getScene().setCursor(Cursor.DEFAULT);
                new Alert(Alert.AlertType.INFORMATION,"Restored successfully").show();
            });

            task.setOnFailed(event -> {
                this.root.getScene().setCursor(Cursor.DEFAULT);
                new Alert(Alert.AlertType.INFORMATION,"Restored failed").show();
            });

            new Thread(task).start();

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

