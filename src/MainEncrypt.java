import model.CryptoModel;
import service.CryptoService;
import service.FileService;

public class MainEncrypt {
    public static void main(String[] args) {
        CryptoModel cryptoModel = new CryptoModel();
        cryptoModel.setSource("E:\\_Sashpav\\разное-sashpav\\Обучение-курсы\\JavaRushUniversity\\jru1_caesar_cipher\\resources\\open.txt");
        cryptoModel.setTarget("E:\\_Sashpav\\разное-sashpav\\Обучение-курсы\\JavaRushUniversity\\jru1_caesar_cipher\\resources\\encrypted.txt");

        cryptoModel.setKey(47);

        CryptoService cryptoService = new CryptoService(new FileService());
        cryptoService.encrypt(cryptoModel);


    }
}
