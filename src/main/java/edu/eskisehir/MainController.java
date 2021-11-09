package edu.eskisehir;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    public Button btnExit;
    public ToggleSwitch switchMode;
    public Label lblLang;

    Map<String, String> map;
    ModeProvider modeProvider;
    int a = 0;

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

        modeProvider = new ModeProvider(Mode.DARK);
        changeTextColor();
    }

    public void setTimer(ActionEvent actionEvent) throws IOException, InterruptedException {
        try {
            a = Integer.parseInt(txtTime.getText());
        } catch (NumberFormatException e) {
            txtTime.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Stage alertStg = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStg.getIcons().add(new Image(String.valueOf(getClass().getResource("images/error.png"))));
            alert.setHeaderText(map.get("alert_header"));
            alert.setTitle(map.get("alert_title"));
            alert.showAndWait();
            return;
        }
        a = a * 60;
        String cmd = "shutdown -s -t " + a;
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        //The computer will shut down after "a" minutes.
        //Bilgisayar "a" dakika sonra kapanacak!
        lbl.setText(map.get("console1") + a / 60 + map.get("console2"));
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
        if (a != 0)
            lbl.setText(map.get("console1") + a / 60 + map.get("console2"));
    }

    public void setTexts() {
        App.stg.setTitle(map.get("stage_title"));
        btnSet.setText(map.get("btn_set"));
        btnCancel.setText(map.get("btn_cancel"));
        lblSubTitle.setText(map.get("lbl_sub_title"));
        header.setText(map.get("header"));
        btnExit.setText(map.get("btn_exit"));

    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void changeMode(MouseEvent mouseEvent) {
        modeProvider.switchMode();
        changeTextColor();
    }

    public void changeTextColor() {
        lbl.setStyle("-fx-text-fill:" + modeProvider.getTextColor());
        lblSubTitle.setStyle("-fx-text-fill:" + modeProvider.getTextColor());
        header.setStyle("-fx-text-fill:" + modeProvider.getTextColor());
        lblLang.setStyle("-fx-text-fill:" + modeProvider.getTextColor());
        pane.setStyle("-fx-background-color:" + modeProvider.getBgColor());
    }
}
