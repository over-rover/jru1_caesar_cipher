package service;

import consts.Consts;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Utils {
    //Нормализует значение получаемого ключа.
    public static int normalizeKey(int key) {
        int normalizedKey = key % Consts.RU_ALPHABET.length;
        return (normalizedKey >= 0) ? normalizedKey : normalizedKey + Consts.RU_ALPHABET.length;
    }

    //Получает String-текст, возвращает хэшсет из слов.
    public static HashSet<String> normalizeText(String text) {
        HashSet<String> set = new HashSet<>();
        Collections.addAll(set, text.split(Consts.SPACE));
        return set;
    }

    //Возвращает криптотаблицу - мапу, где ключ - это символ словаря, value - символ, смещенный на значение key
    public static HashMap<Character, Character> createCryptoTable(int key) {
        HashMap<Character, Character> symbols = new HashMap<>();
        for (int i = 0; i < Consts.RU_ALPHABET.length; i++) {
            int newIndex = Utils.normalizeKey(i + key);
            Character newChar = Consts.RU_ALPHABET[newIndex];
            symbols.put(Consts.RU_ALPHABET[i], newChar);
        }
        return symbols;
    }
}