package model;

import consts.Consts;

public class HackModel extends CryptoModel {
    private String dictionaryPath = Consts.DICTIONARY_FILE;

    public String getDictionaryPath() {
        return dictionaryPath;
    }

    public void setDictionaryPath(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }
}