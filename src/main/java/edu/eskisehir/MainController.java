package edu.eskisehir;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Pane pane;
    public Button btnSet;
    public TextField txtTime;
    public Button btnCancel;
    public Label lbl;
    public ComboBox<String> comboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list = new LinkedList<>();
        list.add("ENG");
        list.add("TR");

        ObservableList<String> observableList = FXCollections.observableList(list);

        comboBox.setItems(observableList);
        comboBox.getSelectionModel().select("TR");
    }

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
            return;
        }
        a = a * 60;
        String cmd = "shutdown -s -t " + a;
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();

        lbl.setText("Bilgisayar " + a / 60 + " dakika sonra kapanacak!");
        txtTime.setText("");
    }

    public void cancelTimer(ActionEvent actionEvent) throws IOException, InterruptedException {
        String cmd = "shutdown -a";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();

        lbl.setText("");
        txtTime.setText("");
    }


}
