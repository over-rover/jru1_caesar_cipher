package service;

import consts.Consts;
import exceptions.FileException;
import model.CryptoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CryptoService {

    FileService fileService;

    public CryptoService(FileService fileService) {
        this.fileService = fileService;
    }

    public void encrypt(CryptoModel cryptoModel) {
        List<String> sourceText = fileService.read(cryptoModel);
        isEmpty(sourceText);
        List<String> encryptedText = doEncrypt(cryptoModel, sourceText);
        fileService.write(cryptoModel, encryptedText);
        //fileService.deleteFile(cryptoModel);
    }

    public void decrypt(CryptoModel cryptoModel) {

    }

    public void bruteForce() {

    }

    private void isEmpty(List<String> sourceText) {
        if (sourceText.isEmpty())
            throw new FileException(Consts.NO_DATA_IN_FILE);
    }

    private List<String> doEncrypt(CryptoModel cryptoModel, List<String> sourceText) {
        List<String> modifiedText = new ArrayList<>();
        HashMap<Character, Character> symbols = createCryptoTable(cryptoModel);
        StringBuilder sb = new StringBuilder();
        for (String s : sourceText) {
            for (int i = 0; i < s.length(); i++) {
                Character symbol = s.charAt(i);
                if (Character.isLowerCase(symbol)) {
                    sb.append(symbols.getOrDefault(symbol, symbol));
                } else {
                    sb.append(Character.toUpperCase(symbols.getOrDefault(symbol, symbol)));
                }
            }
            modifiedText.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return modifiedText;
    }

    private HashMap<Character, Character> createCryptoTable(CryptoModel cryptoModel) {
        HashMap<Character, Character> symbols = new HashMap<>();
        int key = cryptoModel.getKey();
        for (int i = 0; i < Consts.RU_ALPHABET.length; i++) {
            int newIndex = Utils.normalizeKey(i + key);
            Character newChar = Consts.RU_ALPHABET[newIndex];
            symbols.put(Consts.RU_ALPHABET[i], newChar);
        }
        /*for (Map.Entry<Character, Character> element : symbols.entrySet()) {
            System.out.println(element.getKey() + " - " + element.getValue());
        }*/
        return symbols;
    }
}