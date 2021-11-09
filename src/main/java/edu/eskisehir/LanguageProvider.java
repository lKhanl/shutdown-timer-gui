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

        List<String> values = new LinkedList<>();
        List<String> keys = new LinkedList<>();

        switch (preferredLang) {
            case ENG:
                header = "Shutdown Timer";
                stage_title = "Shutdown Timer";
                lbl_sub_title = "How many minutes will pc shut down?";
                btn_set = "Set Timer";
                btn_cancel = "Cancel Timer";
                break;
            case TR:
                header = "Otomatik Bilgisayar Kapatıcı";
                stage_title = "Otomatik Bilgisayar Kapatıcı";
                lbl_sub_title = "Kaç dakika sonra bilgisayar kapansın?";
                btn_set = "Ayarla";
                btn_cancel = "İptal et";
        }

        keys.add("header");
        keys.add("stage_title");
        keys.add("lbl_sub_title");
        keys.add("btn_set");
        keys.add("btn_cancel");

        values.add(header);
        values.add(stage_title);
        values.add(lbl_sub_title);
        values.add(btn_set);
        values.add(btn_cancel);

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i),values.get(i));
        }
        return map;
    }
}
