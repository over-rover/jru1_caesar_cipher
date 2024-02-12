package service;

import model.CryptoModel;

import java.util.HashMap;
import java.util.List;


public class CryptoService {
    protected final FileService fileService;
    protected final FileValidationService fileValid;

    public CryptoService(FileService fileService, FileValidationService fileValid) {
        this.fileService = fileService;
        this.fileValid = fileValid;
    }

    public void crypt(CryptoModel model) {
        List<String> sourceText = readFile(model.getSourcePath());
        fileValid.isEmpty(sourceText);
        StringBuilder cryptoText = cryptText(String.valueOf(sourceText), model.getCryptoTable());
        writeToFile(model.getTargetPath(), cryptoText);
    }

    protected List<String> readFile(String sourcePath) {
        return fileService.read(sourcePath);
    }

    protected void writeToFile(String targetPath, CharSequence cryptoText) {
        fileService.write(targetPath, cryptoText);
    }

    protected StringBuilder cryptText(String stringText, HashMap<Character, Character> cryptoTable) {
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