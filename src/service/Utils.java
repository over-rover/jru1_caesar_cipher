package service;

import consts.Consts;

public class Utils {
    public static int normalizeKey(int key) {
        int normalizedKey = key % Consts.RU_ALPHABET.length;
        return (normalizedKey >= 0) ? normalizedKey : normalizedKey + Consts.RU_ALPHABET.length;
    }
}