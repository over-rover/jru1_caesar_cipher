package service;

import consts.Consts;
import model.HackModel;

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
        String resultText = getResult(sourceText, dictionary);
        cryptoService.writeToFile(model.getTargetPath(), resultText);
    }


    private String getResult(List<String> sourceText, List<String> dictionary) {
        String resultText = "";
        int maxResult = 0;
        int cryptoKey = 0;
        for (int key = 0; key < Consts.RU_ALPHABET.length; key++) {
            model.setKey(key);
            String decryptedText = cryptoService.cryptText(String.valueOf(sourceText), model.getCryptoTable()).toString();
            int result = textAnalyzer.countRootMatches(decryptedText, dictionary);
            if (result > maxResult) {
                maxResult = result;
                cryptoKey = key;
                resultText = decryptedText;
            }
        }
        model.setKey(cryptoKey);

        return resultText;
    }
}
