package service;

import consts.Consts;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Utils {
    public static int normalizeKey(int key) {
        int normalizedKey = key % Consts.RU_ALPHABET.length;
        return (normalizedKey >= 0) ? normalizedKey : normalizedKey + Consts.RU_ALPHABET.length;
    }

    public static HashSet<String> normalizeText(List<String> text) {
        String[] array;
        HashSet<String> set = new HashSet<>();
        for (String line : text) {
            array = line.split(" ");
            Collections.addAll(set, array);
        }
        return set;
    }
}