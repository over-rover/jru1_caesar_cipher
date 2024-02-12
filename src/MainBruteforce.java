import consts.Consts;
import model.CryptoModel;
import model.HackModel;
import service.*;

public class MainBruteforce {
    public static void main(String[] args) {
        //ExceptionLogger получает exception и записывает стэк-трейс в лог, используя FileService
        LoggerService loggerService = new LoggerService();
        //FileService может бросать исключения и отправлять их на обработку в ExceptionLogger
        FileService fileService = new FileService(loggerService);
        //Я не нашел вариантов, как создать их в методе main и обменять ссылками друг на друга.

        FileValidationService fileValidationService = new FileValidationService(loggerService);
        CryptoService cryptoService = new CryptoService(fileService, fileValidationService);
        TextAnalyzer textAnalyzer = new TextAnalyzer();


        //Создаем зашифрованный файл
        CryptoModel cryptoModel = new CryptoModel();
        setEncryptModel(cryptoModel);
        cryptoService.crypt(cryptoModel);
        System.out.println("Ключ key = " + cryptoModel.getKey() +
                " Зашифрованный файл: resources/bruteforce/source.txt");

        //Расшифровка файла брутфорсом
        HackModel hackModel = new HackModel();
        setBruteforceModel(hackModel);
        BruteForceService bruteForceService = new BruteForceService(hackModel, cryptoService, textAnalyzer);
        bruteForceService.bruteForce();
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
