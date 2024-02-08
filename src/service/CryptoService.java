package service;

import consts.Consts;
import exceptions.FileException;
import model.CryptoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CryptoService {
    FileService fileService;
    private final FileValidationService fileValid = new FileValidationService();

    public CryptoService(FileService fileService) {
        this.fileService = fileService;
    }

    public void crypt(CryptoModel model) {
        List<String> sourceText = readFile(model.getSourcePath());
        fileValid.isEmpty(sourceText);
        List<String> cryptoText = doCrypt(model, sourceText);
        writeToFile(model.getTargetPath(), cryptoText);
    }

    protected List<String> readFile(String sourcePath) {
        return fileService.read(sourcePath);
    }

    protected void writeToFile(String targetPath, List<String> cryptoText) {
        fileService.write(targetPath, cryptoText);
    }

    protected List<String> doCrypt(CryptoModel model, List<String> sourceText) {
        List<String> cryptoText = new ArrayList<>();
        HashMap<Character, Character> symbols = createCryptoTable(model);
        StringBuilder sb = new StringBuilder();
        for (String s : sourceText) {
            for (int i = 0; i < s.length(); i++) {
                char symbol = s.charAt(i);
                if (Character.isUpperCase(symbol)) {
                    symbol = Character.toLowerCase(symbol);
                    sb.append(Character.toUpperCase(symbols.getOrDefault(Character.toLowerCase(symbol), symbol)));
                } else {
                    sb.append(symbols.getOrDefault(symbol, symbol));
                }
            }
            cryptoText.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return cryptoText;
    }

    private HashMap<Character, Character> createCryptoTable(CryptoModel model) {
        HashMap<Character, Character> symbols = new HashMap<>();
        int key = model.getKey();
        for (int i = 0; i < Consts.RU_ALPHABET.length; i++) {
            int newIndex = Utils.normalizeKey(i + key);
            Character newChar = Consts.RU_ALPHABET[newIndex];
            symbols.put(Consts.RU_ALPHABET[i], newChar);
        }
        return symbols;
    }
}