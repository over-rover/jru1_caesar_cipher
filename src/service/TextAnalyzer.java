package service;

import java.util.HashSet;
import java.util.List;

public class TextAnalyzer {
    protected int countRootMatches(List<String> text, List<String> dictionary) {
        HashSet<String> normalizedText = Utils.normalizeText(text);
        HashSet<String> foundRoots = new HashSet<>();
        for (String word : normalizedText) {
            fixRootIfMatches(word, dictionary, foundRoots);
        }
        return getNumberOfMatches(foundRoots);
    }

    private int getNumberOfMatches(HashSet<String> foundRoots) {
        return foundRoots.size();
    }

    private void fixRootIfMatches(String word, List<String> dictionary, HashSet<String> foundRoots) {
        for (String root : dictionary) {
            if (word.contains(root)) {
                foundRoots.add(root);
            }
        }
    }
}