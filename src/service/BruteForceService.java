package service;

import consts.Consts;
import exceptions.ValidateException;
import model.HackModel;

import java.util.List;

public class BruteForceService {
    private final HackModel model;
    private final CryptoService cryptoService;
    private final TextAnalyzer textAnalyzer;
    protected final FileService fileService;
    protected final FileValidationService fileValidationService;

    public BruteForceService(HackModel model, CryptoService cryptoService, TextAnalyzer textAnalyzer,
                             FileService fileService, FileValidationService fileValidationService) {
        this.model = model;
        this.cryptoService = cryptoService;
        this.textAnalyzer = textAnalyzer;
        this.fileService = fileService;
        this.fileValidationService = fileValidationService;
    }

    private List<String> readDictionaryFrom(String dictionaryPath) {
        return fileService.read(dictionaryPath);
    }

    public void bruteForce() throws ValidateException {
        List<String> dictionary = readDictionaryFrom(model.getDictionaryPath());
        fileValidationService.isEmpty(dictionary);

        fileValidationService.isSystemFile(model.getSourcePath());
        List<String> sourceText = fileService.read(model.getSourcePath());
        fileValidationService.isEmpty(sourceText);
        String resultText = getResult(sourceText, dictionary);
        fileService.write(model.getTargetPath(), resultText);
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
