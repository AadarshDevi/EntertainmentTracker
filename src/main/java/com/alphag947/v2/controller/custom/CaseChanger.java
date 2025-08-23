package com.alphag947.v2.controller.custom;

import com.alphag947.backend.logging.LoggerFactory;

public class CaseChanger {

    public static String toCamelCase(String string) {
        String[] array = string.split(" ");
        StringBuilder newText = new StringBuilder();
        for (String text : array) {
            String changedText = text.toLowerCase();
            LoggerFactory.getConsoleLogger().dbg(CaseChanger.class, changedText);
            String firstLetter = String.valueOf(changedText.charAt(0)).toUpperCase();
            String camelText = firstLetter + changedText.substring(1);
            LoggerFactory.getConsoleLogger().dbg(CaseChanger.class, camelText);
            newText.append(camelText).append(" ");
        }
        return newText.toString().strip();
    }
}
