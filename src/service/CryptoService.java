package service;

import exceptions.ValidateException;
import model.CryptoModel;

import java.util.List;
import java.util.Map;


public class CryptoService {
    protected final FileService fileService;
    protected final FileValidationService fileValidationService;

    public CryptoService(FileService fileService, FileValidationService fileValidationService) {
        this.fileService = fileService;
        this.fileValidationService = fileValidationService;
    }

    public void crypt(CryptoModel model) throws ValidateException {
        fileValidationService.isSystemFile(model.getSourcePath());
        List<String> sourceText = fileService.read(model.getSourcePath());
        fileValidationService.isEmpty(sourceText);
        StringBuilder cryptoText = cryptText(String.valueOf(sourceText), model.getCryptoTable());
        fileValidationService.isSystemFile(model.getTargetPath());
        fileService.write(model.getTargetPath(), cryptoText);
    }

    protected StringBuilder cryptText(String stringText, Map<Character, Character> cryptoTable) {
        StringBuilder stringBuilderText = new StringBuilder();
        for (int i = 0; i < stringText.length(); i++) {
            char symbol = stringText.charAt(i);
            if (!Character.isUpperCase(symbol)) {
                stringBuilderText.append(cryptoTable.getOrDefault(symbol, symbol));
            } else {
                symbol = Character.toLowerCase(symbol);
                stringBuilderText.append(Character.toUpperCase(cryptoTable.getOrDefault(symbol, symbol)));
            }
        }
        return stringBuilderText;
    }
}