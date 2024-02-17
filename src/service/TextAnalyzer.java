package service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextAnalyzer {

    /**
     * Проверяет схожесть слова с какой-либо словоформой из словаря и
     * считает число таких совпадений.
     *
     * @param text       - строка, содержащая текст
     * @param dictionary - словарь усникальных словоформ
     * @return число слов, похожих на словоформы из словаря
     */
    protected int countRootMatches(String text, List<String> dictionary) {
        Set<String> normalizedText = Utils.normalizeText(text);
        Set<String> matchedRoots = new HashSet<>();
        for (String word : normalizedText) {
            addRootIfPresentInWord(word, dictionary, matchedRoots);
        }
        return matchedRoots.size();
    }

    private void addRootIfPresentInWord(String word, List<String> dictionary, Set<String> foundRoots) {
        for (String root : dictionary) {
            if (word.contains(root)) {
                foundRoots.add(root);
            }
        }
    }
}