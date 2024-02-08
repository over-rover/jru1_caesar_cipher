package service;

import consts.Consts;
import model.HackModel;

import java.util.ArrayList;
import java.util.List;

public class BruteForceService {
    private final HackModel model;
    private final CryptoService cryptoService = new CryptoService(new FileService());
    private final FileValidationService fileValid = new FileValidationService();
    private final TextAnalyzer textAnalyzer = new TextAnalyzer();

    public BruteForceService(HackModel model) {
        this.model = model;
    }

    private List<String> readDictionaryFrom(String dictionaryPath) {
        return cryptoService.readFile(dictionaryPath);
    }

    public void bruteForce() {
        List<String> dictionary = readDictionaryFrom(model.getDictionaryPath());
        fileValid.isEmpty(dictionary);
        List<String> sourceText = cryptoService.readFile(model.getSourcePath());
        fileValid.isEmpty(sourceText);
        List<String> resultText = getResults(sourceText, dictionary);
        cryptoService.writeToFile(model.getTargetPath(), resultText);
    }

    private List<String> getResults(List<String> sourceText, List<String> dictionary) {
        List<String> resultText = new ArrayList<>();
        int maxResult = 0;
        for (int key = 0; key < Consts.RU_ALPHABET.length; key++) {
            model.setKey(key);
            List<String> decryptedText = cryptoService.doCrypt(model, sourceText);
            int result = textAnalyzer.countRootMatches(decryptedText, dictionary);
            if (result > maxResult) {
                maxResult = result;
                resultText = decryptedText;
                resultText.add("Расшифрован ключем: " + (key - Consts.RU_ALPHABET.length));
            }
            model.setKey(key);
        }
        return resultText;
    }
}
