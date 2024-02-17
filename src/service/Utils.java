package service;

import consts.Consts;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Utils {
    /**
     * Нормализует значение ключа таким образом, чтобы его абсолютное значение не превышало
     * размерности алфавита.
     *
     * @param key - ключ, имеющий любое значение в границах типа
     * @return нормализованное значение ключа
     */
    public static int normalizeKey(int key) {
        int normalizedKey = key % Consts.RU_ALPHABET.length;
        return (normalizedKey >= 0) ? normalizedKey : normalizedKey + Consts.RU_ALPHABET.length;
    }

    /**
     * Разбивает текст на слова и помещает их в коллекцию типа Set
     *
     * @param text - текст
     * @return коллекция уникальных слов, содержащихся в тексте
     */
    public static Set<String> normalizeText(String text) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, text.split(Consts.SPACE));
        return set;
    }

    /**
     * Создает таблицу соответствия открытых и зашифрованных символов.
     *
     * @param key - ключ шифрования
     * @return map "открытый символ" - "зашифрованный символ"
     */
    public static Map<Character, Character> createCryptoTable(int key) {
        Map<Character, Character> symbols = new HashMap<>();
        for (int i = 0; i < Consts.RU_ALPHABET.length; i++) {
            int newIndex = Utils.normalizeKey(i + key);
            Character newChar = Consts.RU_ALPHABET[newIndex];
            symbols.put(Consts.RU_ALPHABET[i], newChar);
        }
        return symbols;
    }
}