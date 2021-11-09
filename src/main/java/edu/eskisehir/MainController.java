package edu.eskisehir;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController {
    public Pane pane;
    public Button btnSet;
    public TextField txtTime;
    public Button btnCancel;
    public Label lbl;

    public void setTimer(ActionEvent actionEvent) throws IOException, InterruptedException {
        int a = 0;
        try {
            a = Integer.parseInt(txtTime.getText());
        } catch (NumberFormatException e) {
            txtTime.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Geçerli bir sayı giriniz");
            alert.setTitle("Geçerli bir sayı giriniz");
            alert.showAndWait();
        }
        a = a * 60;
        String cmd = "shutdown -s -t " + a;
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();

        lbl.setText("Bilgisayar " + a / 60 + " dakika sonra kapanacak!");
    }

    public void cancelTimer(ActionEvent actionEvent) throws IOException, InterruptedException {
        String cmd = "shutdown -a";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();

        lbl.setText("");
    }
}
