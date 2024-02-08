package model;

/*
 * Местонахождение файла-словаря решил привязать к модели.
 **/
public class HackModel extends CryptoModel {
    private String dictionaryPath;

    public String getDictionaryPath() {
        return dictionaryPath;
    }

    public void setDictionaryPath(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }
}