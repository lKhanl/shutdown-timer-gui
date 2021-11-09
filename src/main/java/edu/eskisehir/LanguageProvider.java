package edu.eskisehir;

import java.util.*;

public class LanguageProvider {

    private final Language preferredLang;

    public LanguageProvider(Language preferredLang) {
        this.preferredLang = preferredLang;
    }

    public Map<String, String> getTexts() {
        String header = "";
        String stage_title = "";
        String lbl_sub_title = "";
        String btn_set = "";
        String btn_cancel = "";
        String btn_exit = "";
        String alert_header = "";
        String alert_title = "";
        String console1 = "";
        String console2 = "";
        String undefined_os_header = "";

        List<String> values = new LinkedList<>();
        List<String> keys = new LinkedList<>();

        switch (preferredLang) {
            case ENG:
                header = "Shutdown Timer";
                stage_title = "Shutdown Timer";
                lbl_sub_title = "How many minutes will pc shut down?";
                btn_set = "Set Timer";
                btn_cancel = "Cancel Timer";
                btn_exit = "Exit";
                alert_header = "Please input a valid integer!";
                alert_title = "Error";
                console1 = "The computer will shut down after ";
                console2 = " minutes!";
                undefined_os_header = "İşletim sisteminiz tanımlanamadı!";
                break;
            case TR:
                header = "Otomatik Bilgisayar Kapatıcı";
                stage_title = "Otomatik Bilgisayar Kapatıcı";
                lbl_sub_title = "Kaç dakika sonra bilgisayar kapansın?";
                btn_set = "Ayarla";
                btn_cancel = "İptal et";
                btn_exit = "Çıkış";
                alert_header = "Lütfen geçerli bir tamsayı giriniz!";
                alert_title = "Hata";
                console1 = "Bilgisayar ";
                console2 = " dakika sonra kapanacak!";
                undefined_os_header = "Undefined Operating System! Please contact us!";
        }

        keys.add("header");
        keys.add("stage_title");
        keys.add("lbl_sub_title");
        keys.add("btn_set");
        keys.add("btn_cancel");
        keys.add("btn_exit");
        keys.add("alert_header");
        keys.add("alert_title");
        keys.add("console1");
        keys.add("console2");
        keys.add("undefined_os_header");

        values.add(header);
        values.add(stage_title);
        values.add(lbl_sub_title);
        values.add(btn_set);
        values.add(btn_cancel);
        values.add(btn_exit);
        values.add(alert_header);
        values.add(alert_title);
        values.add(console1);
        values.add(console2);
        values.add(undefined_os_header);

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i),values.get(i));
        }
        return map;
    }
}
