package edu.eskisehir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LanguageProvider {

    private final Language preferredLang;

    public LanguageProvider(Language preferredLang) {
        this.preferredLang = preferredLang;
    }

    public Map<String, String> getTexts() {
        Path p = null;
        String path = "src/main/resources/edu/eskisehir/lang/";
        switch (preferredLang) {
            case TR:
                p = Paths.get(path + "all_texts_tr.txt");
                break;
            case ENG:
                p = Paths.get(path + "all_texts_eng.txt");
                break;

        }
        List<String> list = null;
        try {
            list = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        for (String s : list) {
            String[] arr = s.split("=");
//            System.out.println(Arrays.toString(arr));
            map.put(arr[0], arr[1]);
        }
        return map;
    }
}
