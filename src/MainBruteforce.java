import consts.Consts;
import model.CryptoModel;
import model.HackModel;
import service.BruteForceService;
import service.CryptoService;
import service.FileService;

public class MainBruteforce {
    public static void main(String[] args) {
        //Создаем зашифрованный файл
        CryptoModel cryptoModel = new CryptoModel();
        setEncryptModel(cryptoModel);
        new CryptoService(new FileService()).crypt(cryptoModel);
        System.out.println("Ключ key = " + cryptoModel.getKey() +
                " Файл зашифрован и дотупен здесь: resources/bruteforce/source.txt");

        //Расшифровка файла брутфорсом
        HackModel hackModel = new HackModel();
        setBruteforceModel(hackModel);
        new BruteForceService(hackModel).bruteForce();
        System.out.println("Расшифрованный файл: resources/bruteforce/target.txt");
        System.out.println("Ключ = " + (hackModel.getKey() - Consts.RU_ALPHABET.length));
    }

    public static void setEncryptModel(CryptoModel model) {
        model.setSourcePath("resources/encrypt/source.txt");
        model.setTargetPath("resources/bruteforce/source.txt");
        model.setKey((int) (Math.random() * 1000 - 500));
    }

    public static void setBruteforceModel (HackModel model) {
        model.setSourcePath("resources/bruteforce/source.txt");
        model.setTargetPath("resources/bruteforce/target.txt");
        model.setDictionaryPath("resources/bruteforce/dictionary.txt");
        model.setKey(0);
    }
}
