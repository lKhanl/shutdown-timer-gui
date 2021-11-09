package edu.eskisehir;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    public Pane pane;
    public Button btnSet;
    public TextField txtTime;
    public Button btnCancel;
    public Label lbl;
    public ComboBox<Language> comboBox;
    public Label lblSubTitle;
    public Label header;

    Map<String, String> map;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Language> list = new LinkedList<>();
        list.add(Language.ENG);
        list.add(Language.TR);
        ObservableList<Language> observableList = FXCollections.observableList(list);
        comboBox.setItems(observableList);
        comboBox.getSelectionModel().select(Language.ENG);
        LanguageProvider languageProvider = new LanguageProvider(Language.ENG);
        map = languageProvider.getTexts();
        setTexts();
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


    public void selectLanguage(ActionEvent actionEvent) {
        Language selected = comboBox.getSelectionModel().getSelectedItem();
        LanguageProvider provider = new LanguageProvider(selected);
        map = provider.getTexts();
        setTexts();
    }

    public void setTexts() {
        App.stg.setTitle(map.get("stage_title"));
        btnSet.setText(map.get("btn_set"));
        btnCancel.setText(map.get("btn_cancel"));
        lblSubTitle.setText(map.get("lbl_sub_title"));
        header.setText(map.get("header"));
    }
}
