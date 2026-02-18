package com.sodemed.utils.medicationorders;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FolderHandler {

    private static final String UPLOAD_DIR = "/uploads"; // pasarlo a una tabla para guardarlo

    private static final Map<Integer, String> quarterlyMap = new HashMap<>();

    static {
        quarterlyMap.put(1, "01-03");
        quarterlyMap.put(2, "04-06");
        quarterlyMap.put(3, "07-09");
        quarterlyMap.put(4, "10-12");
    }

    // creacion de los folrder trimestrales y mensuales

    public String preparePath() {
        LocalDate effDate = LocalDate.now();
        int year = effDate.getYear();
        int month = effDate.getMonthValue();
        String monthPath = String.valueOf(month);
        monthPath = monthPath.length() == 1 ? "0" + String.valueOf(month) : String.valueOf(month);
        String globalpath = UPLOAD_DIR + "/" + year + "/" + getQuarterly(month) + "/" + monthPath;
        return globalpath;
    }

    public String getQuarterly(int month) {
        if (month < 1 || month > 12) {
            return "Invalid month";
        }
        int key = (int) Math.ceil((double) month / 3);
        return quarterlyMap.get(key);
    }

    public boolean createFolders(String path) {
        try {
            File directory = new File(String.valueOf(path));
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
