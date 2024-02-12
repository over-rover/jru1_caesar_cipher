package service;

import java.util.HashSet;
import java.util.List;

public class TextAnalyzer {

    //Проверяет, имеются ли эталонные слова в тексте, и возвращает количество совпавших слов.
    protected int countRootMatches(String text, List<String> dictionary) {
        HashSet<String> normalizedText = Utils.normalizeText(text);
        HashSet<String> matchedRoots = new HashSet<>();
        for (String word : normalizedText) {
            addRootIfPresentInWord(word, dictionary, matchedRoots);
        }
        return matchedRoots.size();
    }

    private void addRootIfPresentInWord(String word, List<String> dictionary, HashSet<String> foundRoots) {
        for (String root : dictionary) {
            if (word.contains(root)) {
                foundRoots.add(root);
            }
        }
    }
}